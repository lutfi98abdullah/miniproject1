package vttp.miniproject1.Service;


import vttp.miniproject1.Models.Anime;
import vttp.miniproject1.Models.Search;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import vttp.miniproject1.Services.SearchService;

import java.util.List;

public class SearchServiceTest {

    @Mock
    private RestTemplate restTemplate;

    private SearchService searchService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        searchService = new SearchService(restTemplate);
    }

    @Test
    public void testSearch() {
        Search search = new Search();
        search.setName("test");
        String response = "{" +
                "    \"data\": [" +
                "        {" +
                "            \"attributes\": {" +
                "                \"synopsis\": \"test\"," +
                "                \"titles\": {" +
                "                    \"en\": \"Dragon Ball\"" +
                "                }," +
                "                \"averageRating\": \"80.75\"," +
                "                \"startDate\": \"1986-02-26\"," +
                "                \"endDate\": \"1989-04-12\"," +
                "                \"posterImage\": {" +
                "                    \"large\": \"https://media.kitsu.io/anime/poster_images/199/large.jpg\"" +
                "                }" +
                "            }" +
                "        }" +
                "]" +
                "}";
        Mockito.when(restTemplate.getForEntity("https://kitsu.io/api/edge/anime?filter[text]=test", String.class)).thenReturn(ResponseEntity.accepted().body(response));

        List<Anime> animeList = searchService.search(search);
        Assertions.assertEquals(1, animeList.size());
    }
}
