package com.example.back_end.Services;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back_end.Entites.Bus_details;
import com.example.back_end.Entites.Buses;
import com.example.back_end.Repository.master_repo;

@Service
public class Master_services {
    @Autowired
    private master_repo masterRepo;

    public List<Buses> getAllBuses() {
        return masterRepo.findAll();
    }

    public List<Bus_details> searchBus(String fromCity, String toCity, LocalDate departureDate) {
        List<Bus_details> busDetails = masterRepo.findByDepartureDate(departureDate);
        return busDetails.stream()
        .filter(bus->{
            String [] busRoute = bus.getBus_route().split("-");
            List<String> route = Array.asList(busRoute);
            
        })
    }
}
