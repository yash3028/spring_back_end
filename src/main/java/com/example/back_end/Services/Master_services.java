package com.example.back_end.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back_end.Entites.Bus_details;
import com.example.back_end.Entites.Cities;
import com.example.back_end.Repository.master_repo;
import com.example.back_end.Repository.search_repo;

@Service
public class Master_services {
    @Autowired
    private master_repo masterRepo;

    public List<Cities> getAllBuses() {
        return masterRepo.findAll();
    }

    @Autowired
    private search_repo searchRepo;

    public List<Bus_details> searchBus(String from, String to, String departureDate) {
        return searchRepo.findByFromLocationAndToLocationAndDepartureDate(from, to, departureDate);
    }
}
