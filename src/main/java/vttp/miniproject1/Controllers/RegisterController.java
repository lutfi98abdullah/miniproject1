package vttp.miniproject1.Controllers;

import vttp.miniproject1.Models.User;
import vttp.miniproject1.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registerPage", method = RequestMethod.GET)
    public String getRegisterPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("showErrorMessage", false);
        model.addAttribute("errorMessage", "");
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user, Model model) {
        String output = userService.register(user);
        if (output == null) {
            model.addAttribute("user", user);
            model.addAttribute("showErrorMessage", true);
            model.addAttribute("errorMessage", "Username is already present! Please try with different name");
            return "register";
        }
        model.addAttribute("user", new User());
        model.addAttribute("showErrorMessage", false);
        return "login";
    }
}
