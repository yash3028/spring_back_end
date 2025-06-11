package com.example.back_end.Controllers;

import com.example.back_end.Entites.User;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.back_end.Entites.Bus_details;
import com.example.back_end.JWTUtils.Validation;
import com.example.back_end.Repository.bus_repo;
import com.example.back_end.Services.Bus_services;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/agent")
public class Agent_controller {
    @Autowired
    private Bus_services bus_services;
    @Autowired
    private Validation validation;

    @Autowired
    private bus_repo busRepo;

    @PostMapping("/save_bus")
    public ResponseEntity<Bus_details> saveBus(@RequestBody Bus_details bus_details, HttpServletRequest  request) throws Exception {
        User user = validation.validate(request);
        bus_details.setAId(user.getId());
        return ResponseEntity.ok(bus_services.save_bus(bus_details));
    }
    
    @GetMapping("/get_request")
    public ResponseEntity<List<Bus_details>> getRequest(HttpServletRequest  request){
        try{
            long id = validation.extractId(request);
            List<Bus_details> requests = busRepo.findByaIdAndIsDeleted(id, 0L);
            return ResponseEntity.ok(requests);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/delete_request/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long id, HttpServletRequest request){
        try{
            long Id = validation.extractId(request);
            Optional<Bus_details> optional = busRepo.findById(id);
            if(optional.isPresent()){
                Bus_details bus = optional.get();
                bus.setIsDeleted(1L);
                busRepo.save(bus);
                return ResponseEntity.ok("deleted successfully");
            }
        }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting bus");
        }
        return null;
    }

    @PutMapping("/edit/request/{id}")
    public ResponseEntity<String> updateBus(@PathVariable Long id, @RequestBody Bus_details updatedBus) {
        try {
            Optional<Bus_details> optionalBus = busRepo.findById(id);
            if (optionalBus.isPresent()) {
                Bus_details existingBus = optionalBus.get();
                existingBus.setOperator_name(updatedBus.getOperator_name());
                existingBus.setBus_no(updatedBus.getBus_no());
                existingBus.setFromLocation(updatedBus.getFromLocation());
                existingBus.setToLocation(updatedBus.getToLocation());
                existingBus.setDepartureDate(updatedBus.getDepartureDate());
                existingBus.setDeparture_time(updatedBus.getDeparture_time());
                existingBus.setArrival_time(updatedBus.getArrival_time());
                existingBus.setSeats_available(updatedBus.getSeats_available());
                existingBus.setPrice(updatedBus.getPrice());
                busRepo.save(existingBus);
                return ResponseEntity.ok("Updated");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bus not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update bus");
        }
    }



}
