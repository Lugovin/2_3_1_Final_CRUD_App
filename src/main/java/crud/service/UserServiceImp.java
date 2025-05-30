package crud.service;

import crud.dao.UserDao;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;



    @Transactional
    @Override
    public List<User> allU(){
        return userDao.allU();
    };

    @Transactional
    @Override
    public void addU(User user){
        userDao.addU(user);
    };

    @Transactional
    @Override
    public void deleteU(User user){
        userDao.deleteU(user);
    };

    @Transactional
    @Override
    public void editU(User user){
        userDao.editU(user);
    };

    @Transactional
    @Override
    public User getByIdU(Long id){
        return userDao.getByIdU(id);
    };

}
