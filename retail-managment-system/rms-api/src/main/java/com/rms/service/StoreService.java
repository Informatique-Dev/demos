package com.rms.service;

import com.rms.domain.core.Store;
import com.rms.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StoreService {
    private StoreRepository storeRepository ;

    public Page<Store> getAll(Integer page , Integer size) {
        return storeRepository.findAll(PageRequest.of(page,size));
    }
    public Page<Store> getStoreByResponsibleName(Integer page , Integer size, String responsible )
    {
        return  storeRepository.findStoreByResponsible(responsible , PageRequest.of(page,size));
    }


    public Optional<Store> getById(Integer id) {
        return storeRepository.findById(id);
    }



    public Store save(Store store) {
        return storeRepository.save(store);
    }

    public void delete(Store store) {
        storeRepository.delete(store);
    }
}
