package com.sorting.carsort.controller;

import com.sorting.carsort.dto.SortResult;
import com.sorting.carsort.model.Car;
import com.sorting.carsort.service.SortingService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final SortingService sortingService;

    public CarController(SortingService sortingService) {
        this.sortingService = sortingService;
    }

    @GetMapping
    public List<Car> getAllCars() {
        return Arrays.asList(
                new Car(1L, "Toyota", 150, 2020),
                new Car(2L, "BMW", 200, 2019),
                new Car(3L, "Audi", 180, 2021)
        );
    }

    @PostMapping("/sort/bubble")
    public SortResult bubbleSort(@RequestBody List<Car> cars) {
        return sortingService.bubbleSortByPower(cars);
    }

    @PostMapping("/sort/selection")
    public SortResult selectionSort(@RequestBody List<Car> cars) {
        return sortingService.selectionSortByPower(cars);
    }

    @PostMapping("/sort/insertion")
    public SortResult insertionSort(@RequestBody List<Car> cars) {
        return sortingService.insertionSortByPower(cars);
    }
}