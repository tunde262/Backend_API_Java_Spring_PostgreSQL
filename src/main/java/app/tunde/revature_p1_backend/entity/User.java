package app.tunde.revature_p1_backend.entity;

public class User {
    private Integer userId;
    private String userName;
    private String email;
    private String password;
    private String role;

    public User(Integer userId, String userName, String email, String password, String role) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getter and Setter for userId
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // Getter and Setter for userName
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for role
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
