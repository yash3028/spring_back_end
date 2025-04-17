package com.example.back_end.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back_end.Entites.Bus_details;
import com.example.back_end.Repository.bus_repo;

@Service
public class Bus_services {
    @Autowired
    private bus_repo bus_repo;

    public Bus_details save_bus(Bus_details bus_details) {
        return bus_repo.save(bus_details);
    }
}
