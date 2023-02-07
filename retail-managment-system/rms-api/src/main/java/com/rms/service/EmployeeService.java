package com.rms.service;

import com.rms.domain.core.Employee;
import com.rms.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public Page<Employee> getAll(Integer page , Integer size)
    {
        return employeeRepository.findAll(PageRequest.of(page,size));
    }

    public Optional<Employee> getById(int id)
    {
        return employeeRepository.findById(id);
    }

    public Employee save (Employee employee)
    {
        return employeeRepository.save(employee);
    }

    public Optional<String> findNationalId(String nationalId) {
        return employeeRepository.checkNationalId(nationalId);
    }

    public void delete(Employee employee)
    {
        employeeRepository.delete(employee);
    }

}
