package com.itechart.restaurant_info_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "working_time")
public class WorkingTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "opening_time")
    @NotNull(message = "Opening time is required")
    private Time openingTime;

    @Column(name = "closing_time")
    @NotNull(message = "Closing time is required")
    private Time closingTime;

    @Column(name = "day_of_week")
    @NotNull(message = "Day of week is required")
    @Size(min = 1, max = 7, message = "Day of week limits exceeded")
    private Integer dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonBackReference
    private Restaurant restaurant;
}
