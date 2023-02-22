package com.rms.service;

import com.rms.domain.investor.Investor;
import com.rms.repository.InvestorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<Investor> getAll(Integer page , Integer size) {
        return investorRepository.findAll(PageRequest.of(page,size));
    }

    public Optional<Investor> getById(Integer id) {
        return investorRepository.findById(id);
    }

    public Investor save(Investor investor) {
        return investorRepository.save(investor);
    }

    public Optional<Investor> findNationalId(String nationalId)
    {
        return investorRepository.findByNationalId(nationalId);
    }
    public void delete(Investor investor) {
        investorRepository.delete(investor);
    }

}
