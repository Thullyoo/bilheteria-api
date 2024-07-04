package com.thullyoo.bilheteria_api.repositories;

import com.thullyoo.bilheteria_api.entities.session.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}
