package com.arrizaqu.reactauth;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.arrizaqu.reactauth.entity.Role;
import com.arrizaqu.reactauth.entity.User;
import com.arrizaqu.reactauth.service.UserService;

@SpringBootApplication
public class ReactauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactauthApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			//add data role
			userService.saveRole(new Role(null, "ROLE_USER"));
		    userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
		
		    //add data user
			userService.saveUser(new User(null, "masyda arrizaqu", "arrizaqu", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "sofia tanisha", "tanisha", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "dadang bujang", "bujang", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "udin", "udin", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "yuni", "yuni", "1234", new ArrayList<>()));
		
		    //add roletouser
			userService.addRoleToUser("arrizaqu", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("tanisha", "ROLE_MANAGER");
			userService.addRoleToUser("udin", "ROLE_ADMIN");
			userService.addRoleToUser("bujang", "ROLE_USER");
			userService.addRoleToUser("yuni", "ROLE_MANAGER");
			userService.addRoleToUser("yuni", "ROLE_SUPER_ADMIN");
			
		};
	}

}
