package pack.spring.dao;

import pack.spring.models.User;

import java.util.List;

public interface UserDAO {

    void addUser(User user);

    User getUser(long id);

    void deleteUser(long id);

    List<User> getAllUsers();

    User updateUser(User user);

}
