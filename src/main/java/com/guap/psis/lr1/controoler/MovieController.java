package com.guap.psis.lr1.controoler;

import com.guap.psis.lr1.dto.CreateMovieDto;
import com.guap.psis.lr1.dto.GetMovieDto;
import com.guap.psis.lr1.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/{id}")
    public Mono<GetMovieDto> getMovieById(@PathVariable String id) {
        return movieService.getMovieById(id);
    }

    @GetMapping
    public Flux<GetMovieDto> getAllMovies() {
        return movieService.getMovies();
    }

    @PostMapping
    public Mono<GetMovieDto> createMovie(@RequestBody CreateMovieDto movieDto) {
        return movieService.createMovie(movieDto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteMovie(@PathVariable String id) {
        return movieService.deleteMovie(id);
    }

}
