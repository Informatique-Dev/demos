package com.rms.rest.handler;

import com.rms.domain.investor.Profit;
import com.rms.domain.investor.ProfitDetails;
import com.rms.domain.purchase.BillDetails;
import com.rms.rest.dto.ProfitDetailsDto;
import com.rms.rest.dto.common.PaginatedResultDto;
import com.rms.rest.exception.*;
import com.rms.rest.modelmapper.ProfitDetailsMapper;
import com.rms.rest.modelmapper.common.PaginationMapper;
import com.rms.service.ProfitDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProfitDetailsHandler {
    private ProfitDetailsService profitDetailsService;
    private ProfitDetailsMapper mapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> getAll(Integer page, Integer size) {
        Page<ProfitDetails> profitDetails = profitDetailsService.getAll(page, size);
        List<ProfitDetailsDto> dtos = mapper.toDto(profitDetails.getContent());
        PaginatedResultDto<ProfitDetailsDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(dtos);
        paginatedResult.setPagination(paginationMapper.toDto(profitDetails));
        return ResponseEntity.ok(paginatedResult);
    }

    public ResponseEntity<?> getById(Integer id) {
        ProfitDetails profitDetails = profitDetailsService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ProfitDetails.class.getSimpleName(), id));
        ProfitDetailsDto dto = mapper.toDto(profitDetails);
        return ResponseEntity.ok(dto);
    }

    public void save(List<BillDetails> billDetails) {
        ProfitDetails profitDetails;

        for (BillDetails billdetail : billDetails) {
            profitDetails = new ProfitDetails();
            Optional<ProfitDetails> profitDetail = profitDetailsService.findByProductId(billdetail.getProduct().getId());
            if (!profitDetail.isPresent()) {

                profitDetails.setBillPrice(billdetail.getPrice());
                profitDetails.setProductPrice(billdetail.getProduct().getCashPrice());
                profitDetails.setProduct(billdetail.getProduct());
                profitDetailsService.save(profitDetails);
            } else {
                profitDetail.get().setBillPrice(billdetail.getPrice());
                profitDetail.get().setProductPrice(billdetail.getProduct().getCashPrice());
                profitDetailsService.save(profitDetail.get());
            }
        }


    }

    public void createCount(Integer productId, Integer quantity) {
        Optional<ProfitDetails> profitDetail = profitDetailsService.findByProductId(productId);
        profitDetail.get().setCount(profitDetail.get().getCount() + quantity);
    }

    public ResponseEntity<?> update(ProfitDetailsDto profitDetailsDto, Integer id) {
        ProfitDetails profitDetails = profitDetailsService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ProfitDetails.class.getSimpleName(), id));
        mapper.updateEntityFromDto(profitDetailsDto, profitDetails);
        profitDetailsService.update(profitDetails);
        ProfitDetailsDto dto = mapper.toDto(profitDetails);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete(Integer id) {
        ProfitDetails profitDetails = profitDetailsService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Profit.class.getSimpleName(), id));
        try {
            profitDetailsService.delete(profitDetails);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Profit.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }

}
