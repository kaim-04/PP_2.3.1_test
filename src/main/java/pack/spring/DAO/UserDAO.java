package pack.spring.DAO;

import org.springframework.stereotype.Component;
import pack.spring.models.User;

import java.util.ArrayList;
import java.util.List;
@Component
public class UserDAO {

    List<User> people;
    private static int COUNT;

    {
        people = new ArrayList<>();
        people.add(new User(++COUNT,"Alex"));
        people.add(new User(++COUNT,"Leo"));
        people.add(new User(++COUNT,"Max"));
        people.add(new User(++COUNT,"Cas"));
    }

    public List<User> index(){
        return people;
    }

    public User show(int id){
        return people.stream().filter(people -> people.getId() == id).findFirst().orElse(null);
    }


    public void save(User user) {
        user.setId(++COUNT);
        people.add(user);
    }

    public void update(int id, User updateUser) {
        User userUp = show(id);
        userUp.setName(updateUser.getName());
    }

    public void delete(int id) {
        people.removeIf(user -> user.getId()==id);
    }
}
