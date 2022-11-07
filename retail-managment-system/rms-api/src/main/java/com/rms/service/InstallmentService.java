package com.rms.service;

import com.rms.domain.sales.Installment;
import com.rms.repository.InstallmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class InstallmentService {
    private InstallmentRepository installmentRepository;

    public Installment getById(Integer id) {
        return installmentRepository.getOne(id);
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


}
