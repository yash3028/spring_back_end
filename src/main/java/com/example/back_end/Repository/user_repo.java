package com.example.back_end.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.back_end.Entites.User;

@Repository
public interface user_repo extends JpaRepository<User, Long> {
    User findUserBymobile(String mobile);
}
