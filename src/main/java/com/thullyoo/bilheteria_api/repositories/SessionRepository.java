package com.thullyoo.bilheteria_api.repositories;

import com.thullyoo.bilheteria_api.entities.session.Session;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query("SELECT s FROM Session s WHERE s.day = :day AND s.time = :time AND s.room = :room")
    List<Session> findByDateAndTimeAndRoom(@Param("day") LocalDate day,
                                                     @Param("time") Time time,
                                                     @Param("room") String room);

    @Query("SELECT s FROM Session s WHERE s.movie.id = :movieId")
    List<Session> findAllByMovie(@Param("movieId") Long movieId);
}
