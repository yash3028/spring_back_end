package com.example.back_end.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.back_end.Entites.Bus_details;
import com.example.back_end.Entites.Buses;
import com.example.back_end.Models.Location;
import com.example.back_end.Services.Master_services;

@RestController
@RequestMapping("/api/master")
@CrossOrigin(origins = "http://localhost:5173")
public class Master_controller {
    @Autowired
    private Master_services master_services;

    @GetMapping("/locations")
    public ResponseEntity<List<Buses>> getAllLocations() {
        return ResponseEntity.ok(master_services.getAllBuses());
    }

    @Autowired
    private Master_services master_services1;

    @PostMapping("/search-buses")
    public ResponseEntity<List<Bus_details>> searchBuses(@RequestBody Location location) {
        System.out.println("FROM: " + location.getFrom());
        System.out.println("TO: " + location.getTo());
        System.out.println("DATE: " + location.getDepartureDate());
        String from = location.getFrom();
        String to = location.getTo();
        String departureDate = location.getDepartureDate();
        return ResponseEntity.ok(master_services1.searchBus(from, to, departureDate));
    }
}
