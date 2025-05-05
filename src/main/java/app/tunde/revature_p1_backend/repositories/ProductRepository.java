package app.tunde.revature_p1_backend.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import app.tunde.revature_p1_backend.entity.Product;
import app.tunde.revature_p1_backend.exceptions.EtBadRequestException;
import app.tunde.revature_p1_backend.exceptions.EtResourceNotFoundException;

@Repository
public interface ProductRepository {
    List<Product> findAll() throws EtResourceNotFoundException;

    List<Product> searchByTitleOrDescription(String searchTerm) throws EtBadRequestException;;

    Product findById(Integer productId) throws EtResourceNotFoundException;

    Integer create(String title, String description, int user_id, String category, BigDecimal price, int quantity, String img) throws EtBadRequestException;
    
    void update(Integer productId, Product product) throws EtBadRequestException;

    void removeById(Integer productId);
}
