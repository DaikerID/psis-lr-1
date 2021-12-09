package com.guap.psis.lr1.db.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
@Data
public class Movie {

    @Id
    String id;

    String name;

    LocalDate releaseDate;

    List<Actor> actorList;
}
