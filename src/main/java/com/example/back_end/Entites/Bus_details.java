package com.example.back_end.Entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bus_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bus_details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String operator_name;
    private String bus_no;
    @Column(name = "`From`")
    private String fromLocation;
    @Column(name = "`To`")
    private String toLocation;
    @Column(name = "departure_date")
    private String departureDate;
    private String departure_time;
    private String arrival_time;
    private String seats_available;
    private Double price;
}
