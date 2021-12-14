package com.guap.psis.lr1.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Value;

@Value
public class PageRequestDto {

    @Parameter(example = "0")
    Integer pageNum;

    @Parameter(example = "10")
    Integer pageSize;
}
