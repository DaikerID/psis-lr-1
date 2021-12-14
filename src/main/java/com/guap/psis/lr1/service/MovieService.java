package com.guap.psis.lr1.service;

import com.guap.psis.lr1.controoler.exception.ServiceException;
import com.guap.psis.lr1.db.entity.Movie;
import com.guap.psis.lr1.db.repository.MovieRepository;
import com.guap.psis.lr1.dto.CreateMovieDto;
import com.guap.psis.lr1.dto.GetMovieDto;
import com.guap.psis.lr1.mapper.MovieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public Mono<GetMovieDto> getMovieById(String id) {
        return getMovie(id).map(movieMapper::mapToDto);
    }

    public Flux<GetMovieDto> getMovies() {
        return movieRepository.findAll()
                .map(movieMapper::mapToDto);
    }

    public Mono<GetMovieDto> createMovie(CreateMovieDto movieDto) {
        return movieRepository.insert(movieMapper.mapToMovie(movieDto))
                .map(movieMapper::mapToDto);
    }

    public Mono<Void> deleteMovie(String id) {
        return getMovie(id)
                .flatMap(movieRepository::delete);
    }

    public Mono<GetMovieDto> updateMovie(String id, CreateMovieDto movieDto) {
        return getMovie(id)
                .map(movie -> {
                    movieMapper.updateMovie(movieDto, movie);
                    return movie;
                })
                .flatMap(movieRepository::insert)
                .map(movieMapper::mapToDto);
    }

    private Mono<Movie> getMovie(String id) {
        return movieRepository.findById(id)
                .switchIfEmpty(Mono.error(new ServiceException("Movie not found!", HttpStatus.NOT_FOUND)));
    }
}
