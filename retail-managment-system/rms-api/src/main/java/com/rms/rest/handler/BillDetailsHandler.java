package com.rms.rest.handler;
import com.rms.domain.core.Product;
import com.rms.domain.purchase.Bill;
import com.rms.domain.purchase.BillDetails;
import com.rms.rest.dto.BillDetailsDto;
import com.rms.rest.dto.common.PaginatedResultDto;
import com.rms.rest.exception.ErrorCodes;
import com.rms.rest.exception.ResourceNotFoundException;
import com.rms.rest.exception.ResourceRelatedException;
import com.rms.rest.exception.Response;
import com.rms.rest.modelmapper.BillDetailsMapper;
import com.rms.rest.modelmapper.common.PaginationMapper;
import com.rms.service.BillDetailsService;
import com.rms.service.BillService;
import com.rms.service.ProductService;
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
    private BillService billService;
    private ProductService productService;
    private BillDetailsMapper billDetailsMapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> getAll(Integer page, Integer size) {
        Page<BillDetails> billDetaills = billDetailsService.getAll(page, size);
        List<BillDetailsDto> dtos = billDetailsMapper.toDto(billDetaills.getContent());
        PaginatedResultDto<BillDetailsDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toDto(billDetaills));
        return ResponseEntity.ok(paginatedResultDto);
    }

 
    public ResponseEntity <?>save(int billId,List<BillDetailsDto> billDetailsDtoList)
    {
        for (BillDetailsDto billDetailsDto :billDetailsDtoList ){
            Product product = productService.getById(billDetailsDto.getProduct().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(Product.class.getSimpleName(),billDetailsDto.getProduct().getId()));
            product.setQuantity(billDetailsDto.getQuantity()+product.getQuantity());
        }
        Bill bill = billService.getById(billId)
                .orElseThrow(()->new ResourceNotFoundException(Bill.class.getSimpleName(),billId));
        List<BillDetails> billDetails=billDetailsMapper.toEntity(billDetailsDtoList);
        billDetailsService.save(bill,billDetails);
        List<BillDetailsDto>  dtos =billDetailsMapper.toDto(billDetails);
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<?> getById(Integer id) {
        BillDetails billDetails = billDetailsService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(BillDetails.class.getSimpleName(), id));

        BillDetailsDto dto = billDetailsMapper.toDto(billDetails);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> update(BillDetailsDto billDetailsDto, Integer id) {
        BillDetails billDetails = billDetailsService.getById(id).orElseThrow(() -> new ResourceNotFoundException(BillDetails.class.getSimpleName(), id));
        if (billDetailsDto.getBill() != null) {
            Bill bill = billService.getById(billDetailsDto.getBill().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(Bill.class.getSimpleName(), billDetailsDto.getBill().getId()));
            billDetails.setBill(bill);
        }
        if (billDetailsDto.getPrice() != null) {
            Product product = productService.getById(billDetailsDto.getProduct().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(Product.class.getSimpleName(), billDetailsDto.getProduct().getId()));
            billDetails.setProduct(product);
        }

 
        billDetailsMapper.updateEntityFromDto(billDetailsDto, billDetails);
        billDetailsService.update(billDetails);
        BillDetailsDto dto = billDetailsMapper.toDto(billDetails);
        return ResponseEntity.ok(dto);
 
 

    }
 

 
    public ResponseEntity<?> findAllBillDetailsByBillId(Integer id) {
        billService.getById(id).orElseThrow(() -> new ResourceNotFoundException(Bill.class.getSimpleName(), id));
        List<BillDetails> billDetails = billDetailsService.getAllBillDetailsByBillId(id);
        List<BillDetailsDto> dtos = billDetailsMapper.toDto(billDetails);
        return ResponseEntity.ok(dtos);
    }

 
    public ResponseEntity<?> delete(Integer id) {
        BillDetails billDetails = billDetailsService.getById(id).orElseThrow(() -> new ResourceNotFoundException(BillDetails.class.getSimpleName(), id));
 
        try {
            billDetailsService.delete(billDetails);
        } catch (Exception e) {
            throw new ResourceRelatedException(BillDetails.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }
}
