package br.com.lol.bolao.service;

import br.com.lol.bolao.enums.EventStateEnum;
import br.com.lol.bolao.dto.EventDTO;
import br.com.lol.bolao.dto.ScheduleDTO;
import br.com.lol.bolao.dto.SchedulesDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ScheduleService {
    private static final String URL_BASE = "https://esports-api.lolesports.com/persisted/gw/";
    private static final String API_NAME = "x-api-key";
    private static final String API_KEY = "0TvQnueqKa5mxJntVWt0w4LpLfEkrV1Ta8rQBb9Z";

    private RestTemplate restTemplate;
    private HttpHeaders headers = null;

    public ScheduleDTO list(Long leagueId) {
        headers = new HttpHeaders();
        headers.set(API_NAME, API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        restTemplate = new RestTemplate();

        headers.set(API_NAME, API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        ScheduleDTO results = new ScheduleDTO();
        //https://esports-api.lolesports.com/persisted/gw/getSchedule?hl=pt-BR&leagueId=98767991332355509
        String url = String.format("%s/getSchedule?hl=pt-BR&leagueId=%s", URL_BASE, leagueId);

        HttpEntity<SchedulesDTO> request = new HttpEntity<SchedulesDTO>(headers);

        ResponseEntity<SchedulesDTO> response = restTemplate.exchange(url, HttpMethod.GET, request, SchedulesDTO.class);

        boolean status = response.getStatusCode().is2xxSuccessful();
        if ( status ) {
            results = response.getBody().getData().getSchedule();
        }

        return results;
    }

    public ScheduleDTO findByLeagueAndStatus(Long leagueId, EventStateEnum status) {
        ScheduleDTO result = new ScheduleDTO();
        ScheduleDTO scheduleResult = this.list(leagueId);
        List<EventDTO> eventList = new ArrayList<>();

        result.setPages(scheduleResult.getPages());

        for (EventDTO event : scheduleResult.getEvents()) {
            if (event.getState().equals(status.getName()) ) {
                eventList.add(event);
            }
       }
        result.setEvents(eventList);
        return result;
    }

    public ScheduleDTO findByDate(Long leagueId, Date startDate) throws ParseException {
        ScheduleDTO result = new ScheduleDTO();
        ScheduleDTO scheduleResult = this.list(leagueId);
        List<EventDTO> eventList = new ArrayList<>();

        result.setPages(scheduleResult.getPages());

        for (EventDTO event : scheduleResult.getEvents()) {
            String dateSystem = toString(event.getStartTime());
            String dateUser = toString(addDayToDate(1, startDate));
            if (dateSystem.equals(dateUser)) {
                eventList.add(event);
            }
        }
        result.setEvents(eventList);
        return result;

    }

    private Date toDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(date);

    }

    private Date addDayToDate(int day, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_WEEK, day);
        return calendar.getTime();
    }

    private String toString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat dateFormat = sdf;
        return dateFormat.format(date);

    }
}
