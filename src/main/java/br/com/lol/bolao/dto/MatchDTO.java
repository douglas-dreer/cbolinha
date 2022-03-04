package br.com.lol.bolao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MatchDTO {
    public String id;
    public ArrayList<String> flags;
    public ArrayList<TeamDTO> teams;
    public StrategyDTO strategy;
}
