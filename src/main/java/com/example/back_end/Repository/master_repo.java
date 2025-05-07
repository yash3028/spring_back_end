package com.example.back_end.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.back_end.Entites.Buses;

import io.micrometer.common.lang.NonNull;

@Repository
public interface master_repo extends JpaRepository<Buses, Long> {
    @NonNull
    List<Buses> findAll();
}
