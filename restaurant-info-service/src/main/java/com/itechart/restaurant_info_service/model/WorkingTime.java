package com.itechart.restaurant_info_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "working_time")
public class WorkingTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Opening time is required")
    @Size(max = 100, message = "Time limits exceeded")
    private String openingTime;

    @NotNull(message = "Closing time is required")
    @Size(max = 100, message = "Time limits exceeded")
    private String closingTime;

    @NotNull(message = "Day of week is required")
    @Range(min = 0, max = 7, message = "Day of week limits exceeded")
    private Integer dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonBackReference
    private Restaurant restaurant;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        WorkingTime workingTime = (WorkingTime) obj;

        return openingTime.equals(workingTime.openingTime) &&
                closingTime.equals(workingTime.closingTime) &&
                dayOfWeek.equals(workingTime.dayOfWeek) &&
                restaurant.getId().equals(workingTime.restaurant.getId());
    }
}
