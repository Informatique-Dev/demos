package com.rms.service;

import com.rms.domain.investor.Transaction;
import com.rms.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {
    private TransactionRepository transactionRepository;

    public Page<Transaction> getAll(Integer page , Integer size , String investor) {
        return transactionRepository.findAll(investor,PageRequest.of(page,size));
    }

    public Optional<Transaction> getById(Integer id) {
        return transactionRepository.findById(id);
    }

    public Transaction getByIdLazy(Integer id) {
        return transactionRepository.getOne(id);
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void delete(Transaction transaction) {
        transactionRepository.delete(transaction);
    }

}
