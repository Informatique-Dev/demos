package com.rms.repository;

import com.rms.domain.core.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee , Integer> {

@Query(value = "SELECT e FROM Employee e WHERE e.nationalId= :nationalId ")
Optional<Employee> findByNationalId( @Param("nationalId") String nationalId);
}
