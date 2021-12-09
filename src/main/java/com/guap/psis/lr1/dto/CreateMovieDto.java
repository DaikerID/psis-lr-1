package com.guap.psis.lr1.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Value
public class CreateMovieDto {

    @NotNull
    String name;

    @NotNull
    LocalDate releaseDate;

    @NotNull
    List<GetActorDto> actorList;
}
