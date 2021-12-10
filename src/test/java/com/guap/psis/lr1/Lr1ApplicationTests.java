package com.guap.psis.lr1;

import com.guap.psis.lr1.controoler.exception.ErrorCode;
import com.guap.psis.lr1.dto.CreateMovieDto;
import com.guap.psis.lr1.dto.GetMovieDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureDataMongo
@AutoConfigureWebTestClient
class Lr1ApplicationTests {

  @Autowired
  WebTestClient webClient;

  @Test
  @DisplayName("Testing create movie")
  void testCreate() {
    var movie = new CreateMovieDto("name", LocalDate.now(), List.of());
    webClient.post()
        .uri("/api/v1/movie")
        .body(Mono.just(movie), CreateMovieDto.class)
        .exchange()
        .expectStatus().isOk()
        .expectBody(GetMovieDto.class)
        .consumeWith(response -> {
          var getMovieDto = response.getResponseBody();

          assertThat(getMovieDto).isNotNull();
          assertThat(getMovieDto.getId()).isNotNull();
          assertThat(getMovieDto.getName()).isEqualTo(movie.getName());
          assertThat(getMovieDto.getReleaseDate()).isEqualTo(movie.getReleaseDate());
          assertThat(getMovieDto.getActorList()).isEqualTo(movie.getActorList());
        });
  }

  @Test
  @DisplayName("Testing get movie by id")
  void testGet() {
    var movie = new CreateMovieDto("name", LocalDate.now(), List.of());
    var createdMovie = webClient.post()
        .uri("/api/v1/movie")
        .body(Mono.just(movie), CreateMovieDto.class)
        .exchange()
        .expectStatus().isOk()
        .expectBody(GetMovieDto.class)
        .returnResult()
        .getResponseBody();

    assertThat(createdMovie).isNotNull();
    assertThat(createdMovie.getId()).isNotNull();

    webClient.get()
        .uri(String.format("/api/v1/movie/%s", createdMovie.getId()))
        .exchange()
        .expectStatus().isOk()
        .expectBody(GetMovieDto.class)
        .consumeWith(response -> assertThat(createdMovie).isEqualTo(response.getResponseBody()));
  }

  @Test
  @DisplayName("Testing get movie by id with expected 404")
  void testGetWithNotFound() {

    var errorMessage = "Movie not found!";
    webClient.get()
        .uri("/api/v1/movie/not_valid_id")
        .exchange()
        .expectStatus()
        .isNotFound()
        .expectBody(ErrorCode.class)
        .consumeWith(result -> {
          var errorCode = result.getResponseBody();
          assertThat(errorCode).isNotNull();
          assertThat(errorMessage).isEqualTo(errorCode.getMessage());
        });
  }

  @Test
  @DisplayName("Testing get all movie")
  void testGetAll() {

    var moviesBeforeAdd = webClient.get()
        .uri("/api/v1/movie")
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(GetMovieDto.class)
        .returnResult()
        .getResponseBody();

    assertThat(moviesBeforeAdd).isNotNull();

    var movie = new CreateMovieDto("name", LocalDate.now(), List.of());
    var createdMovie = webClient.post()
        .uri("/api/v1/movie")
        .body(Mono.just(movie), CreateMovieDto.class)
        .exchange()
        .expectStatus().isOk()
        .expectBody(GetMovieDto.class)
        .returnResult()
        .getResponseBody();

    assertThat(createdMovie).isNotNull();
    assertThat(createdMovie.getId()).isNotNull();

    webClient.get()
        .uri("/api/v1/movie")
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(GetMovieDto.class)
        .consumeWith(result -> {
          var list = result.getResponseBody();
          assertThat(list).isNotNull();
          assertThat(list.size()).isEqualTo(moviesBeforeAdd.size() + 1);
        });

  }

  @Test
  @DisplayName("Testing delete movie")
  void testDelete() {
    var movie = new CreateMovieDto("name", LocalDate.now(), List.of());
    var createdMovie = webClient.post()
        .uri("/api/v1/movie")
        .body(Mono.just(movie), CreateMovieDto.class)
        .exchange()
        .expectStatus().isOk()
        .expectBody(GetMovieDto.class)
        .returnResult()
        .getResponseBody();

    assertThat(createdMovie).isNotNull();
    assertThat(createdMovie.getId()).isNotNull();

    webClient.delete().uri(String.format("/api/v1/movie/%s", createdMovie.getId()))
        .exchange()
        .expectStatus()
        .isOk();

    webClient.get()
        .uri(String.format("/api/v1/movie/%s", createdMovie.getId()))
        .exchange()
        .expectStatus()
        .isNotFound();
  }

}
