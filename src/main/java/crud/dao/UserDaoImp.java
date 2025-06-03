package crud.dao;

import crud.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    public EntityManagerFactory entityManagerFactory;

    public UserDaoImp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("entityManager");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new User("Bill", "Gates", "faja@google.com"));
        entityManager.persist(new User("John", "Smith", "ddd@mail.ru"));
        entityManager.persist(new User("Николай", "Луговин", "lugovin@gmail.com"));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void addUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void editUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public User getUserById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } finally {
            entityManager.close();
        }
    }

}




