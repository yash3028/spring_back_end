package com.example.back_end.Entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Bus_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bus_details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String operator_name;
    private String bus_no;
    private String bus_route;
    private String departure_date;
    private String departure_time;
    private String arrival_time;
    private String seats_available;
    private Double price;
}
