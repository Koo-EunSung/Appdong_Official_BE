package com.appdong.website.init;

import com.appdong.website.entity.User;
import com.appdong.website.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final PasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        String username = "admin";
        userRepository.deleteByUsername(username);

        String password = UUID.randomUUID().toString();

        User admin = new User(null, username, bCryptPasswordEncoder.encode(password), "ROLE_ADMIN");

        userRepository.save(admin);

        System.out.println("password = " + password);
    }
}
