package app.tunde.revature_p1_backend.repositories;

import org.springframework.stereotype.Repository;

import app.tunde.revature_p1_backend.entity.User;
import app.tunde.revature_p1_backend.exceptions.EAuthException;

@Repository
public interface UserRepository {
    Integer create(String userName, String email, String password) throws EAuthException;

    User findByEmailAndPassword(String email, String password) throws EAuthException;

    Integer getCountByEmail(String email);
    
    User findById(Integer userId);
}
