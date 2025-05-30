package crud.dao;

import crud.model.User;

import java.util.List;

public interface UserDao {

    List<User> allU();

    void addU(User user);

    void deleteU(User user);

    void editU(User user);

    User getByIdU(Long id);

}
