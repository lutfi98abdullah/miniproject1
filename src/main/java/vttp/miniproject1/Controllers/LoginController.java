package vttp.miniproject1.Controllers;


import vttp.miniproject1.Models.Anime;
import vttp.miniproject1.Models.Search;
import vttp.miniproject1.Models.User;
import vttp.miniproject1.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;


@Controller
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getLoginPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("showErrorMessage", false);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("user") User user, Model model) {
        User login = userService.login(user.getUsername(), user.getPassword());
        if (login == null) {
            model.addAttribute("user", user);
            model.addAttribute("showErrorMessage", true);
            return "login";
        }
        List<Anime> animeList = new ArrayList<>();
        model.addAttribute("animes", animeList);
        model.addAttribute("search", new Search());
        model.addAttribute("showAnime", false);
        model.addAttribute("errorMessage", "Search For Anime!!");
        return "home";
    }
}
