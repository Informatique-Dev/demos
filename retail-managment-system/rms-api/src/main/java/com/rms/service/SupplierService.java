package com.rms.service;

import com.rms.domain.purchase.Supplier;
import com.rms.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SupplierService {

    private SupplierRepository supplierRepository;

    public Page<Supplier> getall(Integer page, Integer size)
    {
       return supplierRepository.findAll(PageRequest.of(page,size));
    }

    public Optional<Supplier>  getById(int id)
    {
        return supplierRepository.findById(id);
    }
    public Supplier save(Supplier supplier)
    {
       return supplierRepository.save(supplier);
    }
    public Supplier update(Supplier supplier)
    {
        return supplierRepository.save(supplier);
    }
    public void delete(Supplier supplier)
    {
        supplierRepository.delete(supplier);
    }
}
