package com.rms.service;

import com.rms.domain.core.Employee;
import com.rms.domain.sales.Customer;
import com.rms.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getById(Integer id) {
        return customerRepository.findById(id);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
    public Optional<Customer> findTrustReceiptNo(Integer trustReceiptNo) {
        return customerRepository.findTrustReceiptNo(trustReceiptNo);
    }


    public void deleteById(Integer id) {
        customerRepository.deleteById(id);
    }

}
