package com.example.back_end.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.back_end.Entites.Bus_details;
import com.example.back_end.Entites.Tickets;
import com.example.back_end.Entites.User;
import com.example.back_end.Models.Credentials;
import com.example.back_end.Repository.bus_repo;
import com.example.back_end.Repository.ticket_repo;
import com.example.back_end.Repository.user_repo;
import com.example.back_end.Services.user_services;
import com.example.back_end.JWTUtils.Validation;


import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5175")

public class User_controller {
    @Autowired
    private user_services user_service;
    @Autowired
    private user_repo userRepo;
    @Autowired
    private ticket_repo tickerRepo;
    @Autowired
    private bus_repo busRepo;
    @Autowired
    private Validation validation;

    @Value("${file.upload-dir}")
    private String uploadDir;
   @PostMapping("/save_user")
    public ResponseEntity<User> saveUser(
        @RequestParam("full_name") String fullName,
        @RequestParam("mobile") String mobile,
        @RequestParam("email") String email,
        @RequestParam("date_of_birth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.time.LocalDate dob,
        @RequestParam("password") String password,
        @RequestParam("company_name") String companyName,
        @RequestParam("userrole") String userrole,
        @RequestParam("country_code") String countryCode,
        @RequestParam(value = "logo", required = false) MultipartFile logo,
        HttpServletRequest request
    ) throws Exception {
        Optional<User> existingUser = userRepo.findByMobile(mobile);
        if (existingUser.isPresent()) {
            return ResponseEntity.status(409).body(null);
        }

        User user = new User();
        user.setFull_name(fullName);
        user.setMobile(mobile);
        user.setEmail(email);
        java.sql.Date sqlDob = java.sql.Date.valueOf(dob);
        user.setDate_of_birth(sqlDob);
        user.setPassword(password); 
        user.setCompany_name(companyName);
        user.setUserrole(userrole);
        user.setCountry_code(countryCode);

       if (logo != null && !logo.isEmpty()) {
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

      String fileName = System.currentTimeMillis() + "_" + logo.getOriginalFilename();
        File dest = new File(uploadFolder, fileName);
        logo.transferTo(dest);
        user.setLogo(fileName); 
    }

    User savedUser = user_service.save_user(user);

    return ResponseEntity.ok(savedUser);
}

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Credentials cred)
            throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException {
        Map<String, Object> result = user_service.login(cred);
        if ("success".equals(result.get("message"))) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(401).body(result);
        }
    }
    @GetMapping("/ticket/{userId}")
    public ResponseEntity<List<Tickets>> getTicket(@PathVariable Long userId){
        List<Tickets> ticket = tickerRepo.findByUserIdOrderByCreatedTimeDesc(userId);
        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/book")
    public ResponseEntity<?> bookTicket(@RequestBody Map<String, Object>body, HttpServletRequest request) throws Exception{
        
        long Id = validation.extractId(request);
        long busId = Long.valueOf(body.get("busId").toString());
        long seatstoBook = Long.valueOf(body.get("seats").toString());

        Optional<Bus_details> bus = busRepo.findById(busId);
        if (!bus.isPresent()) {
        return ResponseEntity.status(404).body("Bus not found");
        }
        LocalDateTime time = LocalDateTime.now();
        Bus_details Bus = bus.get(); 
        long availableSeats = Long.parseLong(Bus.getSeats_available());
        availableSeats -= seatstoBook;
        Bus.setSeats_available(String.valueOf(availableSeats));
        double price = Bus.getPrice() * seatstoBook;
        Tickets ticket = new Tickets();
        ticket.setBusId(Bus);
        ticket.setUserId(Id);
        ticket.setSeatsBooked(seatstoBook);
        ticket.setPrice(Bus.getPrice());
        ticket.setTotalPrice(price);
        ticket.setCreatedTime(Timestamp.valueOf(time));
        ticket.setIs_deleted(false);
        tickerRepo.save(ticket);
        return ResponseEntity.ok("ticket booked");
    }
}
