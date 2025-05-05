package app.tunde.revature_p1_backend.entity;

import java.math.BigDecimal;

public class Product {
    private Integer productId;
    private String title;
    private String description;
    private String category;
    private String img;
    private Integer userId;
    private BigDecimal price;
    private Integer quantity;

    // Constructor 
    public Product(Integer productId, String title, String description, String category, Integer userId, BigDecimal price, Integer quantity, String img) {
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.img = img;
        this.userId = userId;
        this.price = price;
        this.quantity = quantity;
    }

    // Constructor 
    public Product() {

    }

    // Getters
    public Integer getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getImg() {
        return img;
    }

    public Integer getUserId() {
        return userId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    // Setters
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
