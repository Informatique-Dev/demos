package com.rms.rest.handler;

import com.rms.domain.investor.*;
import com.rms.rest.dto.ProfitDto;
import com.rms.rest.exception.ErrorCodes;
import com.rms.rest.exception.ResourceNotFoundException;
import com.rms.rest.exception.ResourceRelatedException;
import com.rms.rest.exception.Response;
import com.rms.rest.modelmapper.ProfitMapper;
import com.rms.service.InvestorService;
import com.rms.service.ProfitService;
import com.rms.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
public class ProfitHandler {
    private ProfitService profitService;
    private InvestorService investorService;
    private TransactionService transactionService;
    private ProfitMapper mapper;


    public ResponseEntity<List<ProfitDto>> getAll() {
        List<Profit> profits = profitService.getAll();
        List<ProfitDto> dtos = mapper.toDto(profits);
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<ProfitDto> getById(Integer id) {
        Profit profit = profitService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Profit.class.getSimpleName(), id));
        ProfitDto dto = mapper.toDto(profit);
        return ResponseEntity.ok(dto);
    }


    public ResponseEntity<ProfitDto> addProfit(ProfitDto profitDto) {
        Profit profit = mapper.toEntity(profitDto);
        profitService.save(profit);
        ProfitDto dto = mapper.toDto(profit);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<ProfitDto> distributeProfit() {
        List<Profit> profits = profitService.getAll(Boolean.FALSE);
        if (!profits.isEmpty()) {
            List<Investor> investors = investorService.getAll();

            Double totalProfit = 0.0;
            for (Profit profit : profits) {
                totalProfit += profit.getProfitAmount();
                profit.setCalculated(Boolean.TRUE);
                profitService.save(profit);
            }
            Double investorsProfit = totalProfit / 2;
            Double managerProfit = totalProfit / 2;//only one manager (omar )
            Double totalBalance = investors.stream().mapToDouble(i -> i.getBalance()).sum();
            for (Investor investor : investors) {
                Double investorProfit = investor.getBalance() / totalBalance * investorsProfit;
                if (investor.getInvestorType().equals(InvestorType.MANAGER)) {
                    investorProfit += managerProfit;
                }
                Double newBalance = investor.getBalance() + investorProfit;
                investor.setBalance(newBalance);
                investorService.save(investor);
                addTransaction(investor, investorProfit);
            }
        }
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<ProfitDto> updateProfit(ProfitDto profitDto, Integer id) {
        Profit profit = profitService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Profit.class.getSimpleName(), id));
        mapper.updateEntityFromDto(profitDto, profit);
        profitService.save(profit);
        ProfitDto dto = mapper.toDto(profit);
        return ResponseEntity.ok(dto);
    }

    private void addTransaction(Investor investor, Double profitAmount) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.PROFIT);
        transaction.setAmount(profitAmount);
        transaction.setInvestor(investor);
        transaction.setDate(LocalDate.now());
        transactionService.save(transaction);

    }

    public ResponseEntity<?> deleteById(Integer id) {
        profitService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Profit.class.getSimpleName(), id));
        try {
            profitService.deleteById(id);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Profit.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }
}
