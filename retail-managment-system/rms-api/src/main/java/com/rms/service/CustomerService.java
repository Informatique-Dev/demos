package com.rms.service;

import com.rms.domain.sales.Customer;
import com.rms.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getById(Integer id) {
        return customerRepository.getOne(id);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

}
