package br.com.lol.bolao.service;

import br.com.lol.bolao.dto.LeagueDTO;
import br.com.lol.bolao.dto.LeaguesDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LeagueService {
    private static final String URL_BASE = "https://esports-api.lolesports.com/persisted/gw/";
    private static final String API_NAME = "x-api-key";
    private static final String API_KEY = "0TvQnueqKa5mxJntVWt0w4LpLfEkrV1Ta8rQBb9Z";

    private RestTemplate restTemplate;
    private HttpHeaders headers = null;

    public List<LeagueDTO> list() {
        headers = new HttpHeaders();
        headers.set(API_NAME, API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        restTemplate = new RestTemplate();

        headers.set(API_NAME, API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        List<LeagueDTO> results = new ArrayList<>();
        String url = String.format("%s/getLeagues?hl=pt-BR", URL_BASE);

        HttpEntity<LeaguesDTO> request = new HttpEntity<LeaguesDTO>(headers);

        ResponseEntity<LeaguesDTO> response = restTemplate.exchange(url, HttpMethod.GET, request, LeaguesDTO.class);

        boolean status = response.getStatusCode().is2xxSuccessful();
        if ( status ) {
            results = response.getBody().getData().getLeagues();
        }

        return results;
    }

    public LeagueDTO findById(Long leagueId) {
        LeagueDTO league = new LeagueDTO();

        List<LeagueDTO> leagueList = this.list();

        for (LeagueDTO item : leagueList) {
            if (item.getId().equals(leagueId)) {
                league = item;
                break;
            }
        }
        return league;
    }

    public LeagueDTO findBySlug(String slug) {
        LeagueDTO league = new LeagueDTO();

        List<LeagueDTO> leagueList = this.list();

        for (LeagueDTO item : leagueList) {
            if (item.getSlug().equals(slug)) {
                league = item;
                break;
            }
        }
        return league;
    }
}
