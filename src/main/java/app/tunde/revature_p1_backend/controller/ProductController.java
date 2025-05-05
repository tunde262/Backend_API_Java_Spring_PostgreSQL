package app.tunde.revature_p1_backend.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.tunde.revature_p1_backend.entity.Product;
import app.tunde.revature_p1_backend.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;

// @CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(value = "search", required = false) String searchTerm) {
        List<Product> products; 

        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            products = productService.searchProducts(searchTerm.trim());
        } else {
            products = productService.fetchAllProducts();
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Object> getProductById(HttpServletRequest request, @PathVariable("productId") Integer productId) {
        System.out.println("INSIDE GET PRODUCT BY ID HERE");
        try {
            System.out.println("Before calling productService");
            Product product = productService.fetchProductById(productId);
            System.out.println("After calling productService");
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", e.getMessage());
            return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR); // 500 if it's truly an exception
        }
    }
    

    @PostMapping("")
    public ResponseEntity<Object> addProduct(HttpServletRequest request, @RequestBody Map<String, Object> productMap) {
        System.out.println("INSIDE ADD PRODUCT HERE");
        String title = (String) productMap.get("title");
        String description = (String) productMap.get("description");
        Integer user_id = Integer.parseInt(productMap.get("user_id").toString());
        String category = (String) productMap.get("category");
        BigDecimal price = new BigDecimal(productMap.get("price").toString());
        Integer quantity = Integer.parseInt(productMap.get("quantity").toString());
        String img = (String) productMap.get("img");

        System.out.println("Before calling productService");

        try {
            Product product = productService.addProduct(title, description, user_id, category, price, quantity, img);

            System.out.println("After calling productService");
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", e.getMessage());
            return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR); // 500 if it's truly an exception
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(HttpServletRequest request, @PathVariable("productId") Integer productId, @RequestBody Product product) {
       try {
            productService.updateProduct(productId, product);
            Map<String, Boolean> map = new HashMap<>();
            map.put("success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", e.getMessage());
            return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }
    
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(HttpServletRequest request, @PathVariable("productId") Integer productId) {
        try {
             productService.removeProduct(productId);
             Map<String, Boolean> map = new HashMap<>();
             map.put("success", true);
             return new ResponseEntity<>(map, HttpStatus.OK);
         } catch (Exception e) {
             Map<String, String> errorMap = new HashMap<>();
             errorMap.put("error", e.getMessage());
             return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR); // 500
         }
    }
    
}
