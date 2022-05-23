package vttp.miniproject1.Controller;

import vttp.miniproject1.Controllers.LoginController;
import vttp.miniproject1.Entity.User;
import vttp.miniproject1.Services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class LoginControllerTest {
    @Mock
    private UserService service;

    private LoginController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new LoginController(service);
    }

    @Test
    public void testGetLoginPage() {
        Model model = Mockito.mock(Model.class);
        String result = controller.getLoginPage(model);
        Assertions.assertEquals("login", result);
    }

    @Test
    public void testLoginWhenUsernameAndPasswordAreCorrect() {
        Model model = Mockito.mock(Model.class);
        User user = new User();

        Mockito.when(service.login(user.getUsername(), user.getPassword())).thenReturn(new User());

        String result = controller.login(user, model);
        Assertions.assertEquals("home", result);
    }

    @Test
    public void testLoginWhenIncorrectCredentials() {
        Model model = Mockito.mock(Model.class);
        User user = new User();

        Mockito.when(service.login(user.getUsername(), user.getPassword())).thenReturn(null);

        String result = controller.login(user, model);
        Assertions.assertEquals("login", result);
    }
}
