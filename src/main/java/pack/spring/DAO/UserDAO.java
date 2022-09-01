package pack.spring.DAO;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pack.spring.models.User;
import org.hibernate.Session;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDAO {


    @PersistenceContext
    private EntityManager entityManager;


    public void addUser(User user) {
        entityManager.persist(user);
    }

    public User getUser(long id) {

        return entityManager.find(User.class, id);
    }

    public void deleteUser(long id) {
        entityManager.remove(getUser(id));
    }

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        return query.getResultList();
    }

    public User updateUser(User user) {

        return entityManager.merge(user);
    }
}
