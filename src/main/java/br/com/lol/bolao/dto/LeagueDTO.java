package br.com.lol.bolao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LeagueDTO {
    private Long id;
    private String slug;
    private String name;
    private String region;
    private String image;
    private int priority;
    private DisplayPriorityDTO displayPriority;
}
