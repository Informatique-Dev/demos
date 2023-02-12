package com.rms.rest.dto.common;

import lombok.Data;

import java.util.List;

@Data
public class PaginatedResultDto <T>{
    private List<T> data;
    private PaginationDto pagination;

}
