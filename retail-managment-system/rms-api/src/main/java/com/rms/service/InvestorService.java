package com.rms.service;

import com.rms.domain.investor.Investor;
import com.rms.repository.InvestorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InvestorService {
    private InvestorRepository investorRepository;

    public List<Investor> getAll() {
        return investorRepository.findAll();
    }

    public Investor getById(Integer id) {
        return investorRepository.getOne(id);
    }

    public Investor save(Investor investor) {
        return investorRepository.save(investor);
    }

}
