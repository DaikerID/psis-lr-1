package com.guap.psis.lr1.service;

import com.guap.psis.lr1.controoler.exception.ServiceException;
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
        return movieRepository.findById(id)
                .switchIfEmpty(Mono.error(new ServiceException("Movie not found!", HttpStatus.NOT_FOUND)))
                .map(movieMapper::mapToDto);

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
        return movieRepository.deleteById(id);
    }
}
