package com.rms.rest.handler;

import com.rms.domain.investor.Transaction;
import com.rms.rest.dto.TransactionDto;
import com.rms.rest.modelmapper.TransactionMapper;
import com.rms.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TransactionHandler {
    private TransactionService transactionService;
    private TransactionMapper mapper;


    public ResponseEntity<List<TransactionDto>> getAll() {
        List<Transaction> transactions = transactionService.getAll();
        List<TransactionDto> dtos = mapper.toTransactionDto(transactions);
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<TransactionDto> getById(Integer id) {
        Transaction transaction = transactionService.getById(id).orElseThrow();
        TransactionDto dto = mapper.toTransactionDto(transaction);
        return ResponseEntity.ok(dto);
    }


    public ResponseEntity<TransactionDto> addTransaction(TransactionDto transactionDto) {
        Transaction transaction = mapper.toTransaction(transactionDto);
        transactionService.save(transaction);
        TransactionDto dto = mapper.toTransactionDto(transaction);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<TransactionDto> updateTransaction(TransactionDto transactionDto) {
        Transaction transaction = transactionService.getById(transactionDto.getId()).orElseThrow();
        mapper.updateTransactionFromDto(transactionDto, transaction);
        transactionService.save(transaction);
        TransactionDto dto = mapper.toTransactionDto(transaction);
        return ResponseEntity.ok(dto);
    }
}
