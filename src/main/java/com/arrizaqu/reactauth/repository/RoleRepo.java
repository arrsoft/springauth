package com.arrizaqu.reactauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arrizaqu.reactauth.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
