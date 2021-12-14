package com.guap.psis.lr1.db.repository;

import com.guap.psis.lr1.db.entity.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {

    Flux<Movie> findBy(Pageable pageable);
}
