package app.tunde.revature_p1_backend.services;

import app.tunde.revature_p1_backend.entity.User;
import app.tunde.revature_p1_backend.exceptions.EAuthException;

public interface  UserService {
    User loginUser(String email, String password) throws EAuthException;
    User registerUser(String userName, String email, String password) throws EAuthException;
    User getUserById(int userId);
}
