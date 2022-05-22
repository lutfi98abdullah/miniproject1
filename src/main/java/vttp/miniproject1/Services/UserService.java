package vttp.miniproject1.Services;

import vttp.miniproject1.Models.User;
import vttp.miniproject1.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) {
        Optional<User> byUsernameAndPassword = userRepository.findByUsernameAndPassword(username, password);
        return byUsernameAndPassword.orElse(null);
    }

    @Transactional
    public String register(User user) {
        Optional<User> userDetailsOptional = userRepository.findByUsername(user.getUsername());
        if (userDetailsOptional.isPresent()) {
            return null;
        }
        userRepository.save(User);
        return "success";
    }
}
