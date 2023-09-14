package com.github.savely03.bookingapp.repository;

import com.github.savely03.bookingapp.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {
}
