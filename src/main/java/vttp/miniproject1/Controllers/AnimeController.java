package vttp.miniproject1.Controllers;

import vttp.miniproject1.Models.Anime;
import vttp.miniproject1.Models.Search;
import vttp.miniproject1.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AnimeController {

    private SearchService service;

    @Autowired
    public AnimeController(SearchService service) {
        this.service = service;
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String searchFlight(@ModelAttribute("search") Search search, Model model) {
        List<Anime> animeList = service.search(search);
        if (animeList.isEmpty()) {
            model.addAttribute("animes", animeList);
            model.addAttribute("showAnime", false);
            model.addAttribute("errorMessage", "Anime not found");
        } else {
            model.addAttribute("showAnime", true);
            model.addAttribute("animes", animeList);
            model.addAttribute("search", search);
        }
        return "home";
    }
}
