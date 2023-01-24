package com.rms.service;

import com.rms.domain.investor.Investor;
import com.rms.repository.InvestorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InvestorService {
    private InvestorRepository investorRepository;

    public List<Investor> getAll() {
        return investorRepository.findAll();
    }

    public Optional<Investor> getById(Integer id) {
        return investorRepository.findById(id);
    }

    public Investor save(Investor investor) {
        return investorRepository.save(investor);
    }

    public void deleteById(Integer id) {
        investorRepository.deleteById(id);
    }

}
