package com.nnk.springboot;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserDetailsServiceImpl;
import com.nnk.springboot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application{
	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	public void init(){
		User user = new User("admin",passwordEncoder.encode("admin"),"admin","ADMIN");
		userService.createUser(user);
	}
}
