package br.com.lol.bolao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultDTO {
    private String outcome;
    private int gameWins;
}
