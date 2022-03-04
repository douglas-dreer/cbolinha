package br.com.lol.bolao.controller;

import br.com.lol.bolao.dto.LeagueDTO;
import br.com.lol.bolao.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/league-of-legends/leagues")
public class LeagueController {
    @Autowired
    private LeagueService service;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<LeagueDTO>> list() {
        List<LeagueDTO> result = service.list();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{leagueId}")
    @ResponseBody
    public ResponseEntity<LeagueDTO> findById(@PathVariable("leagueId") Long leagueId) {
        return ResponseEntity.ok(service.findById(leagueId));

    }

    @GetMapping("/q")
    @ResponseBody
    public ResponseEntity<LeagueDTO> findBySlug(@RequestParam(value = "slug", defaultValue = "") String slug) {
        return ResponseEntity.ok(service.findBySlug(slug));
    }
}
