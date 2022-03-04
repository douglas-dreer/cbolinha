package br.com.lol.bolao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamDTO {
    private String name;
    private String code;
    private String image;
    private ResultDTO result;
    @JsonProperty("record")
    private RecordDTO myrecord;
}
