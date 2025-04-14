package com.example.back_end.Entites;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    @Column(unique = true)
    private String mobile;
    private String userrole;
    private String companyName;

    @Temporal(TemporalType.DATE)
    private Date date_of_birth;

    private String password;
    private String token;
}
