package com.rms.service;

import com.rms.domain.investor.Profit;
import com.rms.repository.ProfitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfitService {
    private ProfitRepository profitRepository;

    public List<Profit> getAll() {
        return profitRepository.findAll();
    }

    public Profit getById(Integer id) {
        return profitRepository.getOne(id);
    }

    public List<Profit> getAll(Boolean calculated) {
        return profitRepository.getByCalculated(calculated);
    }

    public Profit save(Profit profit) {
        return profitRepository.save(profit);
    }

}
