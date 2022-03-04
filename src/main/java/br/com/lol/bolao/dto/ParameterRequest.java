package br.com.lol.bolao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParameterRequest {
    private Date initialDate;
    private Date finalDate;
    private Long leagueId;
}
