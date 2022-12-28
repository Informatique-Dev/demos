package com.rms.rest.handler;

import com.rms.domain.investor.Investor;
import com.rms.rest.dto.InvestorDto;
import com.rms.rest.modelmapper.InvestorMapper;
import com.rms.service.InvestorService;
import lombok.AllArgsConstructor;
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
        List<InvestorDto> dtos = mapper.toInvestorDto(investors);
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<InvestorDto> getById(Integer id) {
        Investor investor = investorService.getById(id);
        InvestorDto dto = mapper.toInvestorDto(investor);
        return ResponseEntity.ok(dto);
    }


    public ResponseEntity<InvestorDto> addInvestor(InvestorDto investorDto) {
        Investor investor = mapper.toInvestor(investorDto);
        investorService.save(investor);
        InvestorDto dto = mapper.toInvestorDto(investor);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<InvestorDto> updateInvestor(InvestorDto investorDto) {
        Investor investor = investorService.getById(investorDto.getId());
        mapper.updateInvestorFromDto(investorDto, investor);
        investorService.save(investor);
        InvestorDto dto = mapper.toInvestorDto(investor);
        return ResponseEntity.ok(dto);
    }
}
