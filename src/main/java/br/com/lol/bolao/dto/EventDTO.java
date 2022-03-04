package br.com.lol.bolao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventDTO {
    private Date startTime;
    private String state;
    private String type;
    private String blockName;
    private LeagueDTO league;
    private MatchDTO match;
}
