package app.tunde.revature_p1_backend.services;

import java.math.BigDecimal;
import java.util.List;

import app.tunde.revature_p1_backend.entity.Product;
import app.tunde.revature_p1_backend.exceptions.EtBadRequestException;
import app.tunde.revature_p1_backend.exceptions.EtResourceNotFoundException;

public interface ProductService {
    List<Product> fetchAllProducts();
    List<Product> searchProducts(String searchTerm);
    Product fetchProductById(Integer productId) throws EtResourceNotFoundException;
    Product addProduct(String title, String description, int user_id, String category, BigDecimal price, int quantity, String img) throws EtBadRequestException;
    void updateProduct(Integer productId, Product product) throws EtBadRequestException;
    void removeProduct(Integer productId) throws EtResourceNotFoundException;
}   
