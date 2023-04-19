package com.rms.repository;

import com.rms.domain.security.Role;
import com.rms.domain.security.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole , Integer> {

    @Query(value = "SELECT ur.role.id FROM UserRole ur WHERE ur.user.id= :userId AND ur.role.id= :roleId")
    Optional<Integer> findRoleIdByUserIdAndRoleId(@Param("userId") Integer userId, @Param("roleId") Integer roleId);


    @Query(value = "SELECT ur.role FROM UserRole ur WHERE ur.user.userName= :username")
    List<Role> findByUserName(@Param("username") String username);

    @Query(value = "SELECT ur FROM UserRole ur JOIN FETCH ur.role WHERE ur.user.id= :userId",
            countQuery = "SELECT COUNT(ur) FROM UserRole ur WHERE ur.user.id= :userId")
    Page<UserRole> findUserRoleByUserId(@Param("userId") Integer userId  , Pageable pageable);
}
