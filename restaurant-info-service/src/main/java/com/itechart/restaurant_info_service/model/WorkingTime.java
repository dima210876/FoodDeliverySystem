package com.itechart.restaurant_info_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @Range(min = 0, max = 1440, message = "Time limits exceeded")
    private Integer openingTimeInMinutes;

    @NotNull(message = "Closing time is required")
    @Range(min = 0, max = 1440, message = "Time limits exceeded")
    private Integer closingTimeInMinutes;

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

        return openingTimeInMinutes.equals(workingTime.openingTimeInMinutes) &&
                closingTimeInMinutes.equals(workingTime.closingTimeInMinutes) &&
                dayOfWeek.equals(workingTime.dayOfWeek) &&
                restaurant.getId().equals(workingTime.restaurant.getId());
    }
}
