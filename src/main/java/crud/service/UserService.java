package crud.service;

import crud.model.User;

import java.util.List;

public interface UserService {
    public List<User> allU();

    public void addU(User user);

    public void deleteU(User user);

    public void editU(User user);

    public User getByIdU(Long id);
}
