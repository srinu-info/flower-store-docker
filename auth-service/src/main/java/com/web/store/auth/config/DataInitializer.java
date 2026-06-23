package com.web.store.auth.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.web.store.auth.entity.Role;
import com.web.store.auth.entity.User;
import com.web.store.auth.repository.AuthRepository;



@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner createAdmin(
            AuthRepository authRepository,
            BCryptPasswordEncoder passwordEncoder) {

        return args -> {

            if (authRepository.findByEmail("admin@gmail.com").isEmpty()) {

                User admin = new User();

                admin.setTitle("Mr");
                admin.setFirstName("Admin");
                admin.setLastName("User");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setCity("Hyderabad");
                admin.setCountry("India");
                admin.setPhone(9999999999L); // or String if you change phone type
                admin.setRole(Role.ADMIN);

                authRepository.save(admin);

                System.out.println("Admin user created successfully");
            }
        };
    }
}