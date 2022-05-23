package vttp.miniproject1.Controller;


import vttp.miniproject1.Controllers.AnimeController;
import vttp.miniproject1.Models.Anime;
import vttp.miniproject1.Models.Search;
import vttp.miniproject1.Services.SearchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

public class AnimeControllerTest {

    @Mock
    private SearchService service;

    private AnimeController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new AnimeController(service);
    }

    @Test
    public void testSearchFlightShouldReturnError() {
        List<Anime> animeList = new ArrayList<>();
        Search search = new Search();
        Model model = Mockito.mock(Model.class);
        Mockito.when(service.search(search)).thenReturn(animeList);

        String result = controller.searchFlight(search, model);

        Mockito.verify(model).addAttribute("errorMessage", "Anime not found");
        Assertions.assertEquals("home", result);
    }

    @Test
    public void testSearchFlight() {
        List<Anime> animeList = new ArrayList<>();
        animeList.add(new Anime());

        Search search = new Search();
        Model model = Mockito.mock(Model.class);
        Mockito.when(service.search(search)).thenReturn(animeList);

        String result = controller.searchFlight(search, model);

        Mockito.verify(model).addAttribute("showAnime", true);
        Assertions.assertEquals("home", result);
    }
}
