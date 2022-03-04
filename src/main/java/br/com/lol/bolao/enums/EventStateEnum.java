package br.com.lol.bolao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EventStateEnum {
    COMPLETED(1L, "completed"),
    UNSTARTED(2L, "unstarted");

    private Long id;
    private String name;
}
