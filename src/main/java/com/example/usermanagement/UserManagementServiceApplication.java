package com.example.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the User Management Service application.
 * This class is responsible for bootstrapping and launching the Spring Boot application.
 *
 * It uses the {@link SpringBootApplication} annotation, which is a convenience annotation
 * that adds all of the following:
 * <ul>
 *     <li>@Configuration - Marks this class as a source of bean definitions for the application context.</li>
 *     <li>@EnableAutoConfiguration - Enables Spring Boot's auto-configuration mechanism.</li>
 *     <li>@ComponentScan - Tells Spring to scan the current package and sub-packages for annotated components like controllers, services, etc.</li>
 * </ul>
 *
 * The {@link SpringApplication#run} method is invoked to start the application.
 */
@SpringBootApplication
public class UserManagementServiceApplication {

	/**
	 * The main method that serves as the entry point for the Spring Boot application.
	 * It invokes the {@link SpringApplication#run} method to launch the application.
	 *
	 * @param args Command-line arguments passed to the application on startup.
	 */
	public static void main(String[] args) {
		// Starts the Spring Boot application
		SpringApplication.run(UserManagementServiceApplication.class, args);
	}
}
