package app.tunde.revature_p1_backend.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.tunde.revature_p1_backend.entity.Product;
import app.tunde.revature_p1_backend.exceptions.EtBadRequestException;
import app.tunde.revature_p1_backend.exceptions.EtResourceNotFoundException;
import app.tunde.revature_p1_backend.repositories.ProductRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> fetchAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> searchProducts(String searchTerm) {
        return productRepository.searchByTitleOrDescription(searchTerm);
    }

    @Override
    public Product fetchProductById(Integer productId) throws EtResourceNotFoundException {
        return productRepository.findById(productId);
    }

    @Override
    public Product addProduct(String title, String description, int user_id, String category, BigDecimal price, int quantity, String img) throws EtBadRequestException {
        int productId = productRepository.create(title, description, user_id, category, price, quantity, img);
        System.out.println("Returned prod ID service: " + productId);
        return productRepository.findById(productId);
    }

    @Override
    public void updateProduct(Integer productId, Product product) throws EtBadRequestException {
        productRepository.update(productId, product);
    }

    @Override
    public void removeProduct(Integer productId) throws EtResourceNotFoundException {
        productRepository.removeById(productId);
    }
    
}
