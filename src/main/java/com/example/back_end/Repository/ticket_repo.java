package com.example.back_end.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back_end.Entites.Tickets;

public interface ticket_repo extends JpaRepository<Tickets,Long> {

    List<Tickets> findByUserIdOrderByCreatedTimeDesc(Long UserId);
    
} 