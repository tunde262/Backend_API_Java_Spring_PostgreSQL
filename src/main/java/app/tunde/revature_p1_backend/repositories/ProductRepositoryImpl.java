package app.tunde.revature_p1_backend.repositories;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import app.tunde.revature_p1_backend.entity.Product;
import app.tunde.revature_p1_backend.exceptions.EtBadRequestException;
import app.tunde.revature_p1_backend.exceptions.EtResourceNotFoundException;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static final String SQL_CREATE = "INSERT INTO products(title, description, user_id, category, price, quantity, img) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM products WHERE product_id = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM products";
    private static final String SQL_UPDATE = "UPDATE products SET title = ?, description = ?, category = ?, price = ?, quantity = ? WHERE product_id = ?";
    private static final String SQL_DELETE = "DELETE FROM products WHERE product_id = ?";
    private static final String SQL_SEARCH = "SELECT * FROM products WHERE LOWER(title) LIKE ? OR LOWER(description) LIKE ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> findAll() throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL, productRowMapper);
        } catch (Exception e) {
            throw new EtBadRequestException("Could not fetch data");
        }
    }

    @Override
    public List<Product> searchByTitleOrDescription(String searchTerm) throws EtBadRequestException {
        try {
            String likeTerm = "%" + searchTerm.toLowerCase() + "%";
            return jdbcTemplate.query(SQL_SEARCH, productRowMapper, likeTerm, likeTerm);
        } catch (Exception e) {
            throw new EtBadRequestException("Search failed: " + e.getMessage());
        }
    }

    @Override
    public Product findById(Integer productId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, productRowMapper, productId);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Product Not Found");
        }
    }

    @Override
    public Integer create(String title, String description, int user_id, String category, BigDecimal price, int quantity, String img) throws EtBadRequestException {
        System.out.println("In create product repo");
        try {
            System.out.println("About to connect");
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                System.out.println("Got connection");
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, title);
                ps.setString(2, description);
                ps.setInt(3, user_id);
                ps.setString(4, category);
                ps.setBigDecimal(5, price);
                ps.setInt(6, quantity);
                ps.setString(7, img);
                return ps;
            }, keyHolder);

            System.out.println("DATA ADDED TO PRODUCTS");

            // Log the keys returned in KeyHolder for debugging
            System.out.println("Keys in KeyHolder: " + keyHolder.getKeys().get("product_id"));
            
            // Retrieve the generated product_id
            Integer productId = (Integer) keyHolder.getKeys().get("product_id");

            // Check if productId is null, in case something went wrong
            if (productId == null) {
                throw new EtBadRequestException("Product creation failed: Unable to retrieve product_id");
            }
            
            return productId;
        } catch (Exception e) {
            // Handle exception
            throw new EtBadRequestException("Invalid request: " + e.getMessage());
        }
    }

    @Override
    public void update(Integer productId, Product product) throws EtBadRequestException {
        System.out.println("In update product repo");
        try {
            int rowsAffected = jdbcTemplate.update(
                SQL_UPDATE,
                product.getTitle(),
                product.getDescription(),
                product.getCategory(),
                product.getPrice(),
                product.getQuantity(),
                productId
            );
            if (rowsAffected == 0) {
                throw new EtResourceNotFoundException("Product not found to update");
            }
        } catch (Exception e) {
            // Handle exception
            throw new EtBadRequestException("Invalid request: " + e.getMessage());
        }
        
    }

    @Override
    public void removeById(Integer productId) throws EtResourceNotFoundException {
        System.out.println("In delete product repo");
        try {
            int rowsAffected = jdbcTemplate.update(SQL_DELETE, productId);
            if (rowsAffected == 0) {
                throw new EtResourceNotFoundException("Product not found to delete");
            }
        } catch (Exception e) {
            // Handle exception
            throw new EtBadRequestException("Invalid request: " + e.getMessage());
        }
    }

    private final RowMapper<Product> productRowMapper = ((rs, _) -> {
        return new Product(
            rs.getInt("product_id"),
            rs.getString("title"),
            rs.getString("description"),
            rs.getString("category"),
            rs.getInt("user_id"),
            rs.getBigDecimal("price"),
            rs.getInt("quantity"),
            rs.getString("img")
        );
    });
    
}
