package com.rms.rest.handler;

import com.rms.domain.purchase.BillDetails;
import com.rms.domain.sales.OrderItem;
import com.rms.rest.dto.BillDetailsDto;
import com.rms.rest.dto.OrderItemDto;
import com.rms.rest.dto.common.PaginatedResultDto;
import com.rms.rest.exception.ErrorCodes;
import com.rms.rest.exception.ResourceNotFoundException;
import com.rms.rest.exception.ResourceRelatedException;
import com.rms.rest.exception.Response;
import com.rms.rest.modelmapper.BillDetailsMapper;
import com.rms.rest.modelmapper.common.PaginationMapper;
import com.rms.service.BillDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class BillDetailsHandler {
    private BillDetailsService billDetailsService;
    private BillDetailsMapper billDetailsMapper;
    private PaginationMapper paginationMapper ;
    public ResponseEntity<?> getAll(Integer page , Integer size){
        Page<BillDetails> billDetaills=billDetailsService.getAll(page,size);
        List<BillDetailsDto> dtos= billDetailsMapper.toDto(billDetaills.getContent());
        PaginatedResultDto<BillDetailsDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toDto(billDetaills));
        return ResponseEntity.ok(paginatedResultDto);
    }

    public ResponseEntity <?>save(BillDetailsDto billDetailsDto)
    {
        BillDetails billDetails=billDetailsMapper.toEntity(billDetailsDto);
        billDetailsService.save(billDetails);
        BillDetailsDto dto =billDetailsMapper.toDto(billDetails);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> getById(Integer id){
        BillDetails billDetails= billDetailsService.getById(id).orElseThrow(()->new ResourceNotFoundException(BillDetails.class.getSimpleName(),id));

        BillDetailsDto dto=billDetailsMapper.toDto(billDetails);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> update(BillDetailsDto billDetailsDto,Integer id){
        BillDetails billDetails= billDetailsService.getById(id).orElseThrow(()->new ResourceNotFoundException(BillDetails.class.getSimpleName(),id));
billDetailsMapper.updateEntityFromDto(billDetailsDto,billDetails);
billDetailsService.update(billDetails);
BillDetailsDto dto=  billDetailsMapper.toDto(billDetails);
return ResponseEntity.ok(dto);

    }

    public ResponseEntity<?> delete(Integer id){
        BillDetails billDetails= billDetailsService.getById(id).orElseThrow(()->new ResourceNotFoundException(BillDetails.class.getSimpleName(),id));

        try {
            billDetailsService.delete(billDetails);
        }catch (Exception e){
            throw new ResourceRelatedException(BillDetails.class.getSimpleName(),"Id",id.toString(),ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }
}
