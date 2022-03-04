package br.com.lol.bolao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LeagueDataDTO {
    private List<LeagueDTO> leagues;
}
