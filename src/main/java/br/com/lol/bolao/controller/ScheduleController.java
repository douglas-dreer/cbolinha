package br.com.lol.bolao.controller;

import br.com.lol.bolao.enums.EventStateEnum;
import br.com.lol.bolao.dto.ParameterRequest;
import br.com.lol.bolao.dto.ScheduleDTO;
import br.com.lol.bolao.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("api/league-of-legends/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService service;

    @GetMapping("/{leagueId}/league")
    @ResponseBody
    public ResponseEntity<ScheduleDTO> list(@PathVariable("leagueId") Long leagueId) {
        ScheduleDTO result = service.list(leagueId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{leagueId}/league/status")
    @ResponseBody
    public ResponseEntity<ScheduleDTO> findByLeagueIdAndStatus(@PathVariable("leagueId") Long leagueId, @RequestBody String status) {
        EventStateEnum stateEnum = status.equals(EventStateEnum.COMPLETED.getName()) ? EventStateEnum.COMPLETED : EventStateEnum.UNSTARTED;
        ScheduleDTO result = service.findByLeagueAndStatus(leagueId, stateEnum);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/{leagueId}/league/date", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ScheduleDTO> findByLeagueIdAndDate(@PathVariable("leagueId") Long leagueId, @RequestBody ParameterRequest params ) throws ParseException {

        ScheduleDTO result = service.findByDate(leagueId, params.getInitialDate());
        return ResponseEntity.ok(result);
    }
}
