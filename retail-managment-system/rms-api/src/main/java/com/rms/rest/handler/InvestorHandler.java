package com.rms.rest.handler;

import com.rms.domain.investor.Investor;
import com.rms.domain.investor.Transaction;
import com.rms.rest.dto.InvestorDto;
import com.rms.rest.exception.ErrorCodes;
import com.rms.rest.exception.ResourceNotFoundException;
import com.rms.rest.exception.ResourceRelatedException;
import com.rms.rest.exception.Response;
import com.rms.rest.modelmapper.InvestorMapper;
import com.rms.service.InvestorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class InvestorHandler {
    private InvestorService investorService;
    private InvestorMapper mapper;


    public ResponseEntity<List<InvestorDto>> getAll() {
        List<Investor> investors = investorService.getAll();
        List<InvestorDto> dtos = mapper.toDto(investors);
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<InvestorDto> getById(Integer id) {
        Investor investor = investorService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Investor.class.getSimpleName(), id));
        InvestorDto dto = mapper.toDto(investor);
        return ResponseEntity.ok(dto);
    }


    public ResponseEntity<InvestorDto> addInvestor(InvestorDto investorDto) {
        Investor investor = mapper.toEntity(investorDto);
        investorService.save(investor);
        InvestorDto dto = mapper.toDto(investor);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<InvestorDto> updateInvestor(InvestorDto investorDto, Integer id) {
        Investor investor = investorService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Investor.class.getSimpleName(), id));
        mapper.updateEntityFromDto(investorDto, investor);
        investorService.save(investor);
        InvestorDto dto = mapper.toDto(investor);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> deleteById(Integer id) {
        investorService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Investor.class.getSimpleName(), id));
        try {
            investorService.deleteById(id);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Investor.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }
}

