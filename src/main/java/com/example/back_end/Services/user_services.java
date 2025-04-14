package com.example.back_end.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back_end.Entites.User;
import com.example.back_end.Models.Credentials;
import com.example.back_end.Repository.user_repo;

@Service
public class user_services {
    @Autowired
    private  user_repo userRepo;
    
    public  User save_user(User user){
        if(user==null){
            throw new IllegalArgumentException("user should not be null");
        }
        return userRepo.save(user);
    }

    public String login(Credentials cred){
        User user = userRepo.findUserBymobile(cred.getMobile());
        if(user!=null && user.getPassword().equals(cred.getPassword())){
        return "success";
    }
    return "unauthorized";
    }
}



