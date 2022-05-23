package vttp.miniproject1.Controller;

import vttp.miniproject1.Controllers.RegisterController;
import vttp.miniproject1.Entity.User;
import vttp.miniproject1.Services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class RegisterControllerTest {

    @Mock
    private UserService userService;

    private RegisterController registerController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        registerController = new RegisterController(userService);
    }

    @Test
    public void testGetRegisterPage() {
        Model model = Mockito.mock(Model.class);
        String result = registerController.getRegisterPage(model);
        Assertions.assertEquals("register", result);
    }

    @Test
    public void testRegisterWhenDetailsAreCorrect() {
        Model model = Mockito.mock(Model.class);
        User user = new User();
        Mockito.when(userService.register(user)).thenReturn("success");
        String result = registerController.register(user, model);

        Assertions.assertEquals("login", result);
    }

    @Test
    public void testRegisterWhenDetailsAreWrong() {
        Model model = Mockito.mock(Model.class);
        User user = new User();
        Mockito.when(userService.register(user)).thenReturn(null);
        String result = registerController.register(user, model);

        Assertions.assertEquals("register", result);
    }


}
