package crud.dao;

import crud.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void addUser(User user);

    void deleteUser(User user);

    void editUser(User user);

    User getUserById(Long id);

}
