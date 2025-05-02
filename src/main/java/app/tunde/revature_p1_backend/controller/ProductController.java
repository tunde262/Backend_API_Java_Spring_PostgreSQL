package app.tunde.revature_p1_backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping("")
    public String getAllProducts(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");
        return "Authenticated UserId: " + userId;
    }
}
