package com.account.account.repository;

import com.account.account.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("FROM Users WHERE email= :email")
    Optional<Users> findByEmail(@Param("email") String email);
}
