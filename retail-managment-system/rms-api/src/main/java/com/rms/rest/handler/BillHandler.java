package com.rms.rest.handler;
import com.rms.domain.purchase.Bill;
import com.rms.domain.purchase.BillDetails;
import com.rms.domain.purchase.Supplier;
import com.rms.rest.dto.BillDetailsDto;
import com.rms.rest.dto.BillDto;
import com.rms.rest.dto.common.PaginatedResultDto;
import com.rms.rest.exception.*;
import com.rms.rest.modelmapper.BillMapper;
import com.rms.rest.modelmapper.common.PaginationMapper;
import com.rms.service.BillDetailsService;
import com.rms.service.BillService;
import com.rms.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class BillHandler {
    private BillMapper billMapper;
    private BillService billService;
    private PaginationMapper paginationMapper;
    private SupplierService supplierService;

    private BillDetailsHandler billDetailsHandler;

    private BillDetailsService billDetailsService;

    public ResponseEntity<?>getById(Integer id)
    {
      Bill bill = billService.getById(id)
              .orElseThrow(()-> new ResourceNotFoundException(Bill.class.getSimpleName(),id));
        BillDto billDto=billMapper.toDto(bill);
        return ResponseEntity.ok(billDto);
    }
    public ResponseEntity<?>getAll(Integer page , Integer size)
    {
        Page<Bill> bills =billService.getAll(page,size);
        List<BillDto>billDtos = billMapper.toDto(bills.getContent());
        PaginatedResultDto<BillDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(billDtos);
        paginatedResultDto.setPagination(paginationMapper.toDto(bills));
        return ResponseEntity.ok(paginatedResultDto);
    }
    public  ResponseEntity<?>save(BillDto billDto)
    {
        Supplier supplier =supplierService.getById(billDto.getSupplierDto().getId())
            .orElseThrow(() -> new ResourceNotFoundException(Supplier.class.getSimpleName(),billDto.getId()));
        if(billDto.getBillDetailsDtoList()==null || billDto.getBillDetailsDtoList().size()==0)
        {
            throw new BusinessValidationException(Bill.class.getName()," requested id",billDto.getId().toString(),ErrorCodes.BUSINESS_VALIDATION.getCode());
        }
        Bill bill = billMapper.toEntity(billDto);
        bill.setSupplier(supplier);
        bill.setDate(LocalDate.now());
        if(billService.findBillNumber(bill.getBillNo()).isPresent())
        {
            throw new ResourceAlreadyExistsException(Bill.class.getSimpleName(),"bill Number",bill.getBillNo(),ErrorCodes.RELATED_RESOURCE.getCode());

        }

            billService.save(bill);
            BillDto dto = billMapper.toDto(bill);
            if(billDto.getBillDetailsDtoList()!=null)
            {
                billDetailsHandler.save(bill.getId(),billDto.getBillDetailsDtoList());
            }
            return ResponseEntity.ok(dto);

    }

    public ResponseEntity<?> update(BillDto billDto, Integer id) {


        Bill bill = billService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Bill.class.getSimpleName(), id));
        if(billDto.getSupplierDto()!=null) {
            Supplier supplier = supplierService.getById(billDto.getSupplierDto().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(Bill.class.getSimpleName(), billDto.getSupplierDto().getId()));
            bill.setSupplier(supplier);
        }

        Optional<Bill>billNumExist=billService.findBillNumber(billDto.getBillNo());
        if(billNumExist.isPresent() && !billNumExist.get().getId().equals(id))
        {
            throw new ResourceAlreadyExistsException(Bill.class.getSimpleName(),"bill Number",bill.getBillNo(),ErrorCodes.DUPLICATE_RESOURCE.getCode());

        }

        billMapper.updateEntityFromDto(billDto, bill);
        bill.setDate(LocalDate.now());
        billService.update(bill);


        BillDto dto = billMapper.toDto(bill);
        return ResponseEntity.ok(dto);
    }
 
    public ResponseEntity<?> delete(Integer id) {
        Bill bill=  billService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Bill.class.getSimpleName(),id));
        try {
            billService.delete(bill);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Bill.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }





}
