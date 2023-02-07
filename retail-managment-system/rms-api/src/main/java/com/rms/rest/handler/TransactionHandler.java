package com.rms.rest.handler;

import com.rms.domain.investor.Investor;
import com.rms.domain.investor.Transaction;
import com.rms.rest.dto.TransactionDto;
import com.rms.rest.exception.ErrorCodes;
import com.rms.rest.exception.ResourceNotFoundException;
import com.rms.rest.exception.ResourceRelatedException;
import com.rms.rest.exception.Response;
import com.rms.rest.modelmapper.TransactionMapper;
import com.rms.service.InvestorService;
import com.rms.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TransactionHandler {
    private TransactionService transactionService;
    private TransactionMapper mapper;
    private InvestorService investorService;


    public ResponseEntity<List<TransactionDto>> getAll() {
        List<Transaction> transactions = transactionService.getAll();
        List<TransactionDto> dtos = mapper.toDto(transactions);
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<TransactionDto> getById(Integer id) {
        Transaction transaction = transactionService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Transaction.class.getSimpleName(),id));
        TransactionDto dto = mapper.toDto(transaction);
        return ResponseEntity.ok(dto);
    }


    public ResponseEntity<TransactionDto> addTransaction(TransactionDto transactionDto) {
        Investor investor = investorService.getById(transactionDto.getInvestor().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Investor.class.getSimpleName(),transactionDto.getInvestor().getId()));
        Transaction transaction = mapper.toEntity(transactionDto);
        transaction.setInvestor(investor);
        transactionService.save(transaction);
        TransactionDto dto = mapper.toDto(transaction);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<TransactionDto> updateTransaction(TransactionDto transactionDto,Integer id) {
        Transaction transaction = transactionService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Transaction.class.getSimpleName(),id));
        Investor investor = investorService.getById(transactionDto.getInvestor().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Investor.class.getSimpleName(),transactionDto.getInvestor().getId()));
        transaction.setInvestor(investor);
        mapper.updateEntityFromDto(transactionDto, transaction);
        transactionService.save(transaction);
        TransactionDto dto = mapper.toDto(transaction);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> deleteById(Integer id) {
        transactionService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Transaction.class.getSimpleName(),id));
        try {
            transactionService.deleteById(id);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Transaction.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }
}
