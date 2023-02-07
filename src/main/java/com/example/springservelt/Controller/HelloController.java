package com.example.springservelt.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hola")
    public String holaMundo() {
        return "Hola Mundo, probando dev tools12 sdf!!";
    }

    @GetMapping("/boostrap")
    public String boostrap(){
        return "boostrap213";
    }
}
