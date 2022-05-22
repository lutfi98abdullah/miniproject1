package vttp.miniproject1.Services;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GhibliService {
    public static final String GIPHY_SEARCH = "https://ghibliapi.herokuapp.com/films";

    // GIPHY_API_KEY
    @Value("${ghibli.api.key}")
    
}


