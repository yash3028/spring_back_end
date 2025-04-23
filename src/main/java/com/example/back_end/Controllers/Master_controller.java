package com.example.back_end.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.back_end.Entites.Buses;
import com.example.back_end.Services.Master_services;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:5175")
public class Master_controller {
    @Autowired
    private Master_services master_services;

    @GetMapping("locations")
    public ResponseEntity<List<Buses>> getAllLocations() {
        return ResponseEntity.ok(master_services.getAllBuses());
    }

}
