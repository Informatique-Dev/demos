package com.rms.service;

import com.rms.domain.purchase.Bill;
import com.rms.repository.BillRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BillService {

    private BillRepository billRepository;

    public Page<Bill>getAll(Integer page , Integer size)
    {
      return billRepository.findAll(PageRequest.of(page,size));
    }
    public Optional<Bill>getById(int id)
    {
        return billRepository.findById(id);
    }
    public Bill save(Bill bill)
    {
        return billRepository.save(bill);
    }
    public Bill update(Bill bill)
    {
        return billRepository.save(bill);
    }

    public void delete(Bill bill)
    {
        billRepository.delete(bill);
    }

    public Optional<Bill> findBillNumber(String billNo)
    {
      return billRepository.findBillNumber(billNo);
    }

}
