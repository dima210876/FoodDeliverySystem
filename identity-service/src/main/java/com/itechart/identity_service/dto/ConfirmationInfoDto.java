package com.itechart.identity_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationInfoDto
{
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String confirmationToken;
}
