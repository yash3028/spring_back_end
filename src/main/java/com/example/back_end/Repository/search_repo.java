package com.example.back_end.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.back_end.Entites.Bus_details;

@Repository
public interface search_repo extends JpaRepository<Bus_details, Long> {
    List<Bus_details> findByFromLocationAndToLocationAndDepartureDate(String fromLocation, String toLocation,
            String departureDate);
}
