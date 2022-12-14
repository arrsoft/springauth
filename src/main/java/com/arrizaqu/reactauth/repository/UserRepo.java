package com.arrizaqu.reactauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arrizaqu.reactauth.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
    
    public User findByUsername(String username);

}
