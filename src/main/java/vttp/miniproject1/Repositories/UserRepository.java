package vttp.miniproject1.Repositories;

import vttp.miniproject1.Models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository <User, Long> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

}
