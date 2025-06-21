package com.example.back_end.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.back_end.Entites.Bus_details;

@Repository
public interface bus_repo extends JpaRepository<Bus_details, Long> {

    List<Bus_details> findByaIdAndIsDeleted(Long aId, Long isDeleted);
    Bus_details findById(int Id);

}
