package com.draw;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Award {

    private Integer id;
    private String name;
    private Integer weight;
}
