package com.arrizaqu.reactauth.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.arrizaqu.reactauth.entity.Role;
import com.arrizaqu.reactauth.entity.User;
import com.arrizaqu.reactauth.repository.RoleRepo;
import com.arrizaqu.reactauth.repository.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService{

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public User saveUser(User user) {
        // TODO Auto-generated method stub
        log.info("saving user {} into database", user.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        this.userRepo.save(user);
        return user;
    }

    @Override
    public Role saveRole(Role role) {
        // TODO Auto-generated method stub
        log.info("saving role {} into database", role.getName());
        this.roleRepo.save(role);
        return role;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        // TODO Auto-generated method stub
        log.info("add role {} to user {} into database", roleName, username);
        User user = this.userRepo.findByUsername(username);
        Role role = this.roleRepo.findByName(roleName);

        user.getRoles().add(role);
        
    }

    @Override
    public User getUser(String username) {
        // TODO Auto-generated method stub
        log.info("fetching by user {} ", username);
        return this.userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        // TODO Auto-generated method stub
        log.info("fatching all user");
        return this.userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = this.userRepo.findByUsername(username);
        if(user == null){
            log.error("user {} not found in the database", username);
            throw new UsernameNotFoundException("user not found exception");
        } else {
            log.info("user {} found in the database", username, user);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
    
}
