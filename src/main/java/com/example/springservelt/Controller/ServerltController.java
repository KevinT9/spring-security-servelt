package com.example.springservelt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServerltController {
    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/test")
    String test() {
        return "test";
    }

}
