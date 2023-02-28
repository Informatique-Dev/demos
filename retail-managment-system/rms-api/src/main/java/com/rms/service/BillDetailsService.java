package com.rms.service;
import com.rms.domain.purchase.BillDetails;
import com.rms.repository.BillDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BillDetailsService {

    private BillDetailsRepository BillDetailsRepository;

    public BillDetails save(BillDetails billDetails) {
        return BillDetailsRepository.save(billDetails);
    }
    public BillDetails update(BillDetails billDetails) {
        return BillDetailsRepository.save(billDetails);
    }

    public Optional<BillDetails> getById(Integer id) {
        return BillDetailsRepository.findById(id);
    }

    public Page<BillDetails> getAll(Integer page , Integer size) {
        return BillDetailsRepository.findAll(PageRequest.of(page,size));
    }



    public void delete(BillDetails BillDetails) {
        BillDetailsRepository.delete(BillDetails);
    }

}
