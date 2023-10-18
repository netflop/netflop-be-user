package netflop.be.user.repository;
import netflop.be.user.entity.User;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public class UserRepository {
    public UserRepository() {
    }

    public void save(User user) {
    }

    public User findAllUser() {
        return new User("1","yoralong@gmail.com",
                "Yora", "Long", "01234567889", "Active", "User",
                null, LocalDateTime.now().toString(),null, LocalDateTime.now().toString(),false);
    }

    public User findByEmail(String email) {
        return null;
    }

    public User findByEmailAndPassword(String email, String password) {
        return null;
    }

    public User findByEmailAndPassword(String email, String password, String role) {
        return null;
    }

    public User findByEmailAndPassword(String email, String password, String role, String name) {
        return null;
    }

    public User findByEmailAndPassword(String email, String password, String role, String name, String surname) {
        return null;
    }

    public User findByEmailAndPassword(String email, String password, String role, String name, String surname, String address) {
        return null;
    }

    public User findByEmailAndPassword(String email, String password, String role, String name, String surname, String address, String phone) {
        return null;

    }
}
