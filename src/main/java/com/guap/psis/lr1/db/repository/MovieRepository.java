package com.guap.psis.lr1.db.repository;

import com.guap.psis.lr1.db.entity.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {
}
