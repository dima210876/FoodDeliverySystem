package com.itechart.restaurant_info_service.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityRegistrationDTO {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
}
