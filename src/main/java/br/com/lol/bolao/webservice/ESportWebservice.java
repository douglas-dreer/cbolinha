package br.com.lol.bolao.webservice;

import br.com.lol.bolao.dto.LeagueDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ESportWebservice {
    private HttpHeaders headers = null;
    private RestTemplate restTemplate;

    private static final String URL_BASE = "https://esports-api.lolesports.com/persisted/gw/";
    private static final String API_NAME = "x-api-key";
    private static final String API_KEY = "0TvQnueqKa5mxJntVWt0w4LpLfEkrV1Ta8rQBb9Z";

    public ESportWebservice(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();

        headers.set(API_NAME, API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }


    public List<LeagueDTO> listLeagues() {
        restTemplate = new RestTemplate();

        headers.set(API_NAME, API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        List<LeagueDTO> results = new ArrayList<>();
        String url = String.format("%s/getLeagues?hl=pt-BR", URL_BASE);

        HttpEntity<LeagueDTO[]> request = new HttpEntity<LeagueDTO[]>(headers);

        ResponseEntity<LeagueDTO[]> response = restTemplate.exchange(url, HttpMethod.GET, request, LeagueDTO[].class);

        if ( response.getStatusCode().is2xxSuccessful() ) {
            LeagueDTO[] leagueArray = response.getBody();
        }

        return results;
    }
}
