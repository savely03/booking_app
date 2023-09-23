package com.github.savely03.bookingapp.repository;

import com.github.savely03.bookingapp.entity.Users;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

    @Query("""
            SELECT id, username, password, email, role
            FROM users WHERE username = :username
            """)
    Optional<Users> findByUsername(@Param("username") String username);
}
