package com.rms.service;

import com.rms.domain.investor.Profit;
import com.rms.repository.ProfitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfitService {
    private ProfitRepository profitRepository;

    public List<Profit> getAll() {
        return profitRepository.findAll();
    }

    public Optional<Profit> getById(Integer id) {
        return profitRepository.findById(id);
    }

    public List<Profit> getAll(Boolean calculated) {
        return profitRepository.getByCalculated(calculated);
    }

    public Profit save(Profit profit) {
        return profitRepository.save(profit);
    }

    public void deleteById(Integer id) {
        profitRepository.deleteById(id);
    }

}
