package com.example.back_end.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.back_end.Entites.Bus_details;
import com.example.back_end.Entites.Buses;

@Repository
public interface master_repo extends JpaRepository<Buses, Long> {
    // @NonNull
    // List<Buses> findAll();
    List<Bus_details> findByDepartureDate(LocalDate departureDate);
}
