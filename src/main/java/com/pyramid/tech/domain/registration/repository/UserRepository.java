package com.pyramid.tech.domain.registration.repository;

import com.pyramid.tech.domain.registration.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    @Query("SELECT DISTINCT user FROM AppUser user " +
            "INNER JOIN FETCH user.authorities AS authorities " +
            "WHERE user.username = :username")
    Optional<AppUser> findByUsername(@Param("username") String username);
}
