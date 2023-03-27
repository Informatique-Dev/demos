package com.rms.rest.modelmapper;
import com.rms.domain.purchase.BillDetails;
import com.rms.rest.dto.BillDetailsDto;
import com.rms.service.BillDetailsService;
import com.rms.service.BillService;
import com.rms.service.ProductService;
import com.rms.utils.HibernateUtils;
import lombok.AllArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class BillDetailsMapper {
    @Autowired
    private BillDetailsService billDetailsService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private BillService billService;
    @Autowired
private ProductService productService;
    @Autowired
     private  BillMapper billMapper;

    @Mappings({
            @Mapping(source = "product" , target = "product" , ignore = true) ,
            @Mapping(source = "bill" , target = "bill" , ignore = true)
    })

    public abstract BillDetailsDto toDto (BillDetails billDetails);
    public abstract List<BillDetailsDto>  toDto (List<BillDetails> list);

    @AfterMapping
    public void toDtoAfterMapping(BillDetails entity, @MappingTarget BillDetailsDto dto) {
        if (HibernateUtils.isConvertable(entity.getProduct())) {
            dto.setProduct(productMapper.toDto(entity.getProduct()));
        }
        if (HibernateUtils.isConvertable(entity.getBill())) {
            dto.setBill(billMapper.toDto(entity.getBill()));
        }
    }
    @InheritInverseConfiguration
    public abstract  BillDetails  toEntity (BillDetailsDto billDetailsDto);
    public  abstract List<BillDetails> toEntity (List<BillDetailsDto>list);

    @AfterMapping
    public void toEntityAfterMapping(BillDetailsDto dto, @MappingTarget BillDetails entity) {
         if (dto.getProduct() != null) {
            entity.setProduct(productService.getById(dto.getProduct().getId()).get());
        }
        if (dto.getBill() != null) {
            entity.setBill(billService.getById(dto.getBill().getId()).get());
        }
    }

    @InheritInverseConfiguration
    public abstract BillDetails updateEntityFromDto (BillDetailsDto billDetailsDto, @MappingTarget BillDetails billDetails);


}
