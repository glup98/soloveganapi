package com.augusto.soloveganbusiness.repositories;

import java.util.Optional;

import com.augusto.soloveganbusiness.models.User;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByEmail(String email);
}
