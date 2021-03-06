package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenderDTO {

    private Long id;
    private String name;
    private String image;
    private boolean deleted = Boolean.FALSE;
    private List<MovieDTO> genderMovies;

}
