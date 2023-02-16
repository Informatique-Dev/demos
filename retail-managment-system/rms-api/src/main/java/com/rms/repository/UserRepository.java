package com.rms.repository;

import com.rms.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Integer> {
    @Query("select u from User u where u.userName = :userName")
    Optional<User> findByUserName(@Param("userName") String userName);
}
