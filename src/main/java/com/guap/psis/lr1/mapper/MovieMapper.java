package com.guap.psis.lr1.mapper;

import com.guap.psis.lr1.db.entity.Movie;
import com.guap.psis.lr1.dto.CreateMovieDto;
import com.guap.psis.lr1.dto.GetMovieDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    GetMovieDto mapToDto(Movie movie);

    Movie mapToMovie(CreateMovieDto movieDto);

    void updateMovie(CreateMovieDto createMovieDto, @MappingTarget Movie movie);
}
