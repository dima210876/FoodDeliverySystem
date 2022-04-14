package com.itechart.courier_manager.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangeStatusDTO {
    private Long id;
    private String orderStatus;
}
