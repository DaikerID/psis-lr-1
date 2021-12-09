package com.guap.psis.lr1.dto;

import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class GetMovieDto {

    String id;

    String name;

    LocalDate releaseDate;

    List<GetActorDto> actorList;
}
