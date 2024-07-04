package com.thullyoo.bilheteria_api.repositories;

import com.thullyoo.bilheteria_api.entities.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByName(String name);
}
