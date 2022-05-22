package vttp.miniproject1.Services;

import vttp.miniproject1.Models.Anime;
import vttp.miniproject1.Models.Search;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private RestTemplate restTemplate;

    @Autowired
    public SearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Anime> search(Search search) {
        String url
                = "https://kitsu.io/api/edge/anime?filter[text]=" + search.getName();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        List<Anime> animeList = new ArrayList<>();


        JsonElement root = new Gson().fromJson(response.getBody(), JsonElement.class);
        JsonArray array = root.getAsJsonObject().get("data").getAsJsonArray();

        for (int i = 0; i < array.size(); i++) {
            Anime anime = new Anime();

            JsonElement element = array.get(i);
            try {
                extracted(anime, element);
            } catch (Exception e) {
                System.out.println(e);
                continue;
            }
            animeList.add(anime);
        }

        return animeList;
    }

    private void extracted(Anime anime, JsonElement element) {
        anime.setTitle_en(element.getAsJsonObject().get("attributes").getAsJsonObject().get("titles").getAsJsonObject().get("en").getAsString());
        // anime.setTitle_jp(element.getAsJsonObject().get("attributes").getAsJsonObject().get("titles").getAsJsonObject().get("ja_jp").getAsString());
        anime.setSynopsis(element.getAsJsonObject().get("attributes").getAsJsonObject().get("synopsis").getAsString());
        anime.setAverageRating(element.getAsJsonObject().get("attributes").getAsJsonObject().get("averageRating").isJsonNull() ? "0" : element.getAsJsonObject().get("attributes").getAsJsonObject().get("averageRating").getAsString());
        anime.setStartDate(element.getAsJsonObject().get("attributes").getAsJsonObject().get("startDate").isJsonNull() ? "Not Present" : element.getAsJsonObject().get("attributes").getAsJsonObject().get("startDate").getAsString());
        anime.setEndDate(element.getAsJsonObject().get("attributes").getAsJsonObject().get("endDate").isJsonNull() ? "Ongoing" : element.getAsJsonObject().get("attributes").getAsJsonObject().get("endDate").getAsString());
        anime.setImageLink(element.getAsJsonObject().get("attributes").getAsJsonObject().get("posterImage").getAsJsonObject().get("large").isJsonNull() ? "Not Present" : element.getAsJsonObject().get("attributes").getAsJsonObject().get("posterImage").getAsJsonObject().get("large").getAsString());
    }
}
