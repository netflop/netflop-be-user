package netflop.be.user.service;
import netflop.be.user.entity.User;
import netflop.be.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findAllUser() {
        return userRepository.findAllUser();
    }
}
