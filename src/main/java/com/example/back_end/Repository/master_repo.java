package com.example.back_end.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.back_end.Entites.Cities;


@Repository
public interface master_repo extends JpaRepository<Cities, Long> {
    
}
