package com.netflop.be.user.repository;
import com.netflop.be.user.entity.User;
import com.netflop.be.user.model.UserResponse;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public class UserRepository {
    public UserRepository() {
    }

    public void save(User user) {
    }

    //test
    public UserResponse findAllUser() {
        return new UserResponse("1","yoralong@gmail.com",
                "Yora", "Long", "01234567889", "Active", "User",
                null, LocalDateTime.now().toString(),null, LocalDateTime.now().toString(),false);
    }


    //test
    public UserResponse getUser() {
        return new UserResponse("1","yoralong@gmail.com",
                "Yora", "Long", "01234567889", "Active", "User",
                null, LocalDateTime.now().toString(),null, LocalDateTime.now().toString(),false);
    }

    //test
    public UserResponse getAdmin() {
        return new UserResponse("1","yoralong@gmail.com",
                "Yora", "Long", "01234567889", "Active", "Admin",
                null, LocalDateTime.now().toString(),null, LocalDateTime.now().toString(),false);
    }
}
