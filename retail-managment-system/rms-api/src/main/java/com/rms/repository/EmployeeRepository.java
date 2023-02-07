package com.rms.repository;

import com.rms.domain.core.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee , Integer> {

    @Query(value = "Select u.nationalId FROM Employee u WHERE u.nationalId= :nationalId ")
    Optional<String> checkNationalId(@Param("nationalId") String nationalId);
}
