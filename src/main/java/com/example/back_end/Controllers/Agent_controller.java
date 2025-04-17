package com.example.back_end.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.back_end.Entites.Bus_details;
import com.example.back_end.Services.Bus_services;

@RestController
@RequestMapping("/")
public class Agent_controller {
    @Autowired
    private Bus_services bus_services;

    @PostMapping("save_bus")
    public ResponseEntity<Bus_details> saveBus(@RequestBody Bus_details bus_details) {
        return ResponseEntity.ok(bus_services.save_bus(bus_details));
    }
}
