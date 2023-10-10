package com.rms.service;

import com.rms.domain.investor.ProfitDetails;
import com.rms.repository.ProfitDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfitDetailsService {

    private ProfitDetailsRepository profitDetailsRepository;

    public Page<ProfitDetails> getAll(Integer page, Integer size) {
        return profitDetailsRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<ProfitDetails> getById(Integer id) {
        return profitDetailsRepository.findById(id);
    }

    public ProfitDetails save(ProfitDetails profitDetails) {
        return profitDetailsRepository.save(profitDetails);
    }

    public ProfitDetails update(ProfitDetails profitDetails) {
        return profitDetailsRepository.save(profitDetails);
    }

    public Optional<ProfitDetails> findByProductId(Integer productId) {
        return profitDetailsRepository.findProfitDetailsByProductId(productId);
    }

    public void delete(ProfitDetails profitDetails) {
        profitDetailsRepository.delete(profitDetails);
    }

}
