package com.sorting.carsort.service;

import com.sorting.carsort.dto.SortResult;
import com.sorting.carsort.dto.SortStep;
import com.sorting.carsort.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SortingService {

    // ========== BUBBLE SORT С ПОШАГОВОЙ ВИЗУАЛИЗАЦИЕЙ ==========
    public SortResult bubbleSortByPower(List<Car> cars) {
        List<SortStep> steps = new ArrayList<>();
        List<Car> sorted = new ArrayList<>(cars);
        int n = sorted.size();

        steps.add(new SortStep(6, "Инициализация: создаём копию массива", new ArrayList<>(sorted)));

        for (int i = 0; i < n - 1; i++) {
            steps.add(new SortStep(8, "Начинаем проход #" + (i + 1), new ArrayList<>(sorted)));

            for (int j = 0; j < n - i - 1; j++) {
                Car car1 = sorted.get(j);
                Car car2 = sorted.get(j + 1);

                steps.add(new SortStep(10,
                        "Сравниваем " + car1.getModel() + " (" + car1.getPower() + " л.с.) и " +
                                car2.getModel() + " (" + car2.getPower() + " л.с.)",
                        new ArrayList<>(sorted)));

                if (car1.getPower() > car2.getPower()) {
                    steps.add(new SortStep(12,
                            " " + car1.getModel() + " мощнее! Меняем местами",
                            new ArrayList<>(sorted)));

                    Car temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);

                    steps.add(new SortStep(15,
                            "✅ Обмен завершён: " + sorted.get(j).getModel() + " ↔ " + sorted.get(j + 1).getModel(),
                            new ArrayList<>(sorted)));
                } else {
                    steps.add(new SortStep(19,
                            "✓ Порядок правильный, пропускаем",
                            new ArrayList<>(sorted)));
                }
            }
        }

        steps.add(new SortStep(23, "🎉 Сортировка завершена!", new ArrayList<>(sorted)));

        return new SortResult(steps, "Bubble Sort (Сортировка пузырьком)");
    }

    // ========== SELECTION SORT ==========
    public SortResult selectionSortByPower(List<Car> cars) {
        List<SortStep> steps = new ArrayList<>();
        List<Car> sorted = new ArrayList<>(cars);
        int n = sorted.size();

        steps.add(new SortStep(6, "Инициализация массива", new ArrayList<>(sorted)));

        for (int i = 0; i < n - 1; i++) {
            steps.add(new SortStep(8, "Ищем минимальный элемент начиная с позиции " + i, new ArrayList<>(sorted)));
            int minIdx = i;

            for (int j = i + 1; j < n; j++) {
                steps.add(new SortStep(11,
                        "Проверяем " + sorted.get(j).getModel() + " (" + sorted.get(j).getPower() + " л.с.)",
                        new ArrayList<>(sorted)));

                if (sorted.get(j).getPower() < sorted.get(minIdx).getPower()) {
                    minIdx = j;
                    steps.add(new SortStep(13,
                            " Найден новый минимум: " + sorted.get(minIdx).getModel(),
                            new ArrayList<>(sorted)));
                }
            }

            if (minIdx != i) {
                steps.add(new SortStep(17,
                        "⚡ Меняем " + sorted.get(i).getModel() + " и " + sorted.get(minIdx).getModel(),
                        new ArrayList<>(sorted)));

                Car temp = sorted.get(minIdx);
                sorted.set(minIdx, sorted.get(i));
                sorted.set(i, temp);

                steps.add(new SortStep(20, "✅ Обмен завершён", new ArrayList<>(sorted)));
            } else {
                steps.add(new SortStep(22, "✓ Элемент уже на своём месте", new ArrayList<>(sorted)));
            }
        }

        steps.add(new SortStep(25, "🎉 Сортировка завершена!", new ArrayList<>(sorted)));

        return new SortResult(steps, "Selection Sort (Сортировка выбором)");
    }

    // ========== INSERTION SORT ==========
    public SortResult insertionSortByPower(List<Car> cars) {
        List<SortStep> steps = new ArrayList<>();
        List<Car> sorted = new ArrayList<>(cars);
        int n = sorted.size();

        steps.add(new SortStep(6, "Начинаем со второго элемента", new ArrayList<>(sorted)));

        for (int i = 1; i < n; i++) {
            Car key = sorted.get(i);
            int j = i - 1;

            steps.add(new SortStep(8,
                    " Берём " + key.getModel() + " (" + key.getPower() + " л.с.) для вставки",
                    new ArrayList<>(sorted)));

            while (j >= 0 && sorted.get(j).getPower() > key.getPower()) {
                steps.add(new SortStep(11,
                        " " + sorted.get(j).getModel() + " мощнее, сдвигаем вправо",
                        new ArrayList<>(sorted)));

                sorted.set(j + 1, sorted.get(j));
                j--;
            }

            sorted.set(j + 1, key);
            steps.add(new SortStep(14,
                    "✅ Вставляем " + key.getModel() + " на позицию " + (j + 1),
                    new ArrayList<>(sorted)));
        }

        steps.add(new SortStep(17, " Сортировка завершена!", new ArrayList<>(sorted)));

        return new SortResult(steps, "Insertion Sort (Сортировка вставками)");
    }
}