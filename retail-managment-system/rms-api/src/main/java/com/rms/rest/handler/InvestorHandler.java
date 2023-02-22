package com.rms.rest.handler;

import com.rms.domain.investor.Investor;
import com.rms.rest.dto.InvestorDto;
import com.rms.rest.dto.common.PaginatedResultDto;
import com.rms.rest.exception.*;
import com.rms.rest.modelmapper.InvestorMapper;
import com.rms.rest.modelmapper.common.PaginationMapper;
import com.rms.service.InvestorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class InvestorHandler {
    private InvestorService investorService;
    private InvestorMapper mapper;
    private PaginationMapper paginationMapper ;


    public ResponseEntity<?> getAll() {
        List<Investor> investors = investorService.getAll();
        List<InvestorDto> dtos = mapper.toDto(investors);
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<?> getAll(Integer page ,Integer size) {
        Page<Investor> investors = investorService.getAll(page,size);
        List<InvestorDto> dtos = mapper.toDto(investors.getContent());
        PaginatedResultDto<InvestorDto> paginatedResult= new PaginatedResultDto<>();
        paginatedResult.setData(dtos);
        paginatedResult.setPagination(paginationMapper.toDto(investors));
        return ResponseEntity.ok(paginatedResult);
    }


    public ResponseEntity<?> getById(Integer id) {
        Investor investor = investorService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Investor.class.getSimpleName(), id));
        InvestorDto dto = mapper.toDto(investor);
        return ResponseEntity.ok(dto);
    }


    public ResponseEntity<?> save(InvestorDto investorDto) {
        Optional<Investor> existedNationalId = investorService.findNationalId(investorDto.getNationalId());
        if (existedNationalId.isPresent())
        {
            throw  new ResourceAlreadyExistsException(Investor.class.getSimpleName() ,
                    "National Id " , investorDto.getNationalId() , ErrorCodes.DUPLICATE_RESOURCE.getCode());
        }
        Investor investor = mapper.toEntity(investorDto);
        investorService.save(investor);
        InvestorDto dto = mapper.toDto(investor);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> update(InvestorDto investorDto, Integer id) {
            Investor investor = investorService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Investor.class.getSimpleName(), id));
        Optional<Investor> existedNationalId = investorService.findNationalId(investorDto.getNationalId());
        if (existedNationalId.isPresent() && !existedNationalId.get().getId().equals(id))
        {
            throw  new ResourceAlreadyExistsException(Investor.class.getSimpleName() ,
                    "National Id " , investorDto.getNationalId() , ErrorCodes.DUPLICATE_RESOURCE.getCode());
        }
        mapper.updateEntityFromDto(investorDto, investor);
        investor.setId(id);
        investorService.save(investor);
        InvestorDto dto = mapper.toDto(investor);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete(Integer id) {
       Investor investor= investorService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Investor.class.getSimpleName(), id));
        try {
            investorService.delete(investor);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Investor.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }
}

