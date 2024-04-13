package com.load.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.load.user.service.entities.User;

public interface UserRepository extends JpaRepository<User,String> {
    


    
}
