package com.itechart.identity_service.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class ConfirmationInfoDto
{
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String confirmationToken;
}
