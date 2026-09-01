package com.sorting.carsort.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortResult {
    private List<SortStep> steps;
    private String algorithmName;
}