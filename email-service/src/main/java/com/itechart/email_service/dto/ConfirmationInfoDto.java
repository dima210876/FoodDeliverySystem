package com.itechart.email_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConfirmationInfoDto
{
    private String email;

    private String confirmationToken;
}
