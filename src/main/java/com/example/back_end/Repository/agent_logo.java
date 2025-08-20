package com.example.back_end.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back_end.Entites.Agent_logo;

public interface agent_logo extends JpaRepository<Agent_logo, Long> {
    Optional<Agent_logo> findByAgentId(Long agentId);
}
