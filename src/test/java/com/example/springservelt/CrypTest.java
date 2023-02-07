package com.example.springservelt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CrypTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("encoder:  " + encoder.encode("123"));
        System.out.println("encoder:  " + encoder.encode("admin"));
    }
}
