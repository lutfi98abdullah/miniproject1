package vttp.miniproject1.Service;


import vttp.miniproject1.Entity.User;
import vttp.miniproject1.Repositories.UserRepository;
import vttp.miniproject1.Services.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void testLogin() {
        User user = new User();
        Mockito.when(userRepository.findByUsernameAndPassword(any(), any())).thenReturn(Optional.of(user));
        User result = userService.login("testUsername", "testPassword");

        Assertions.assertEquals(user, result);
    }

    @Test
    public void testRegisterWhenUserNameIsAlreadyPresent() {
        User user = new User();
        Mockito.when(userRepository.findByUsername(any())).thenReturn(Optional.of(user));
        String result = userService.register(user);

        Assertions.assertNull(result);
    }

    @Test
    public void testRegister() {
        User user = new User();
        Mockito.when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
        String result = userService.register(user);

        Assertions.assertEquals("success", result);
    }
}
