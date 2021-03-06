package com.alkemy.disney.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharcterFiltersDTO {

    private String name;
    private Byte age;
    private Set<Long> charcterMovies;

}
