package com.augusto.soloveganbusiness.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.augusto.soloveganbusiness.models.User;

@Repository
public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByEmail(String email);
}
