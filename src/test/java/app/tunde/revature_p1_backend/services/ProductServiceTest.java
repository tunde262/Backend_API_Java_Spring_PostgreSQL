package app.tunde.revature_p1_backend.services;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import app.tunde.revature_p1_backend.entity.Product;
import app.tunde.revature_p1_backend.repositories.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testGetAllProducts() {
        // Setup mock data to return when findAll is called
        Product product = new Product();
        product.setTitle("Test Product");
        product.setPrice(new BigDecimal("10.99"));
        when(productRepository.findAll()).thenReturn(List.of(product));

        // Call the method to test
        List<Product> result = productService.fetchAllProducts();

        // Verify the result
        assertNotNull(result); // Ensure the result is not null
        assertEquals(1, result.size()); // Check size
        assertEquals("Test Product", result.get(0).getTitle()); // Check title of the first product
        assertEquals(new BigDecimal("10.99"), result.get(0).getPrice()); // Check price of the first product
    }

    // Add more tests here to boost coverage...

    @Test
    void testGetAllProductsEmpty() {
        // Return an empty list
        when(productRepository.findAll()).thenReturn(List.of());

        // Call the method to test
        List<Product> result = productService.fetchAllProducts();

        // Verify the result
        assertNotNull(result); // Ensure the result is not null
        assertEquals(0, result.size()); // Check size is zero
    }

    // Example of a test for error handling
    @Test
    void testGetAllProductsThrowsException() {
        // Mock an exception when repository is called
        when(productRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        // Verify the service correctly handles the exception
        try {
            productService.fetchAllProducts();
        } catch (RuntimeException e) {
            assertEquals("Database error", e.getMessage());
        }
    }
}
