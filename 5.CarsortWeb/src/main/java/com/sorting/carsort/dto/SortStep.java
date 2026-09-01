package com.sorting.carsort.dto;

import com.sorting.carsort.model.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortStep {
    private int lineNumber;
    private String description;
    private List<Car> state;
}