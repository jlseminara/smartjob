package com.smartjob.usersbackend;

import org.apache.catalina.util.ServerInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class UsersbackendApplication {

	public static void main(String[] args) {
        System.out.println("Tomcat version: " + ServerInfo.getServerInfo());
		SpringApplication.run(UsersbackendApplication.class, args);
	}

}
