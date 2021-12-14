package com.guap.psis.lr1.controoler;

import com.guap.psis.lr1.dto.CreateMovieDto;
import com.guap.psis.lr1.dto.GetMovieDto;
import com.guap.psis.lr1.dto.PageRequestDto;
import com.guap.psis.lr1.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "Movie controller")
@RestController
@RequestMapping("api/v1/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/{id}")
    @Operation(summary = "Get movie by id")
    public Mono<GetMovieDto> getMovieById(@PathVariable String id) {
        return movieService.getMovieById(id);
    }

    @GetMapping
    @Operation(summary = "Get all movies")
    public Flux<GetMovieDto> getAllMovies(@ParameterObject PageRequestDto pageable) {
        return movieService.getMovies(pageable);
    }

    @PostMapping
    @Operation(summary = "Create new movie")
    public Mono<GetMovieDto> createMovie(@RequestBody CreateMovieDto movieDto) {
        return movieService.createMovie(movieDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update movie")
    public Mono<GetMovieDto> updateMovie(@PathVariable String id, @RequestBody CreateMovieDto movieDto) {
        return movieService.updateMovie(id, movieDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete movie")
    public Mono<Void> deleteMovie(@PathVariable String id) {
        return movieService.deleteMovie(id);
    }

}
