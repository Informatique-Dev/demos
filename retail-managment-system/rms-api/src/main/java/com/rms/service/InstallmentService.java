package com.rms.service;

import com.rms.domain.sales.Installment;
import com.rms.domain.sales.OrderItem;
import com.rms.repository.InstallmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<Installment> getAll(Integer page , Integer size ) {
        return installmentRepository.findAll(PageRequest.of(page,size));
    }

    public List<Installment> getDueInstallments(Date startDate, Date endDate) {
        return installmentRepository.getDueInstallments(startDate, endDate);
    }

    public List<Installment> getCustomerInstallments(Integer customerId) {
        return installmentRepository.getByCustomerId(customerId);
    }

    public Page<Installment> getByCustomerNationalId(String nationalId , Integer page , Integer size)
    {
        return installmentRepository.getByCustomerNationalId(nationalId, PageRequest.of(page,size));
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
    public void deleteALL(List<Installment> installments) {
        installmentRepository.deleteAll(installments);
    }


    public List<Installment> getInstallmentsByOrderId(Integer orderId){
        return installmentRepository.findInstallmentsByOrderId(orderId);
    }


}
