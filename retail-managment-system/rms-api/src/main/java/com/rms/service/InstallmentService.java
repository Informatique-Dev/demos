package com.rms.service;

import com.rms.domain.sales.Installment;
import com.rms.repository.InstallmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InstallmentService {
    private InstallmentRepository installmentRepository;

    public Optional<Installment> getById(Integer id) {
        return installmentRepository.findById(id);
    }

    public List<Installment> getAll() {
        return installmentRepository.findAll();
    }

    public List<Installment> getDueInstallments(Date startDate, Date endDate) {
        return installmentRepository.getDueInstallments(startDate, endDate);
    }

    public List<Installment> getCustomerInstallments(Integer customerId) {
        return installmentRepository.getByCustomerId(customerId);
    }

    public Installment save(Installment installment) {
        return installmentRepository.save(installment);
    }

    public List<Installment> save(List<Installment> installments) {
        return installmentRepository.saveAll(installments);
    }

    public void delete(Installment installment) {
        installmentRepository.delete(installment);
    }


}
