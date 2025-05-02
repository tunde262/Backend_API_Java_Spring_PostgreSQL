package app.tunde.revature_p1_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    
    @GetMapping("/")
    public String greet() {
        return "Welcome to application";
    }
}
