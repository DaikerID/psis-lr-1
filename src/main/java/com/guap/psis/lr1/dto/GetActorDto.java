package com.guap.psis.lr1.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
public class GetActorDto {

    @NotNull
    String name;

    @NotNull
    LocalDate birthDate;
}
