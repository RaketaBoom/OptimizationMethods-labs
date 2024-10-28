package org.example;

import java.util.function.*;

public class SolverMinimum {
    private final DoubleFunction<Double> function;


    public SolverMinimum(DoubleFunction<Double> function) {
        this.function = function;
    }

    public Answer dichotomiaMethod(double a, double b, double deltaX) {
        if (a >= b) {
            throw new IllegalArgumentException("Left bound must be less than right bound.");
        }

        double epsilon = 0.1 * deltaX;
        int n = 0;

        while ((b - a) / 2 > deltaX) {
            n++;
            double x1 = (a + b - epsilon) / 2;
            double x2 = (a + b + epsilon) / 2;

            double f1 = function.apply(x1);
            double f2 = function.apply(x2);

            if (f1 < f2) {
                b = x2;
            } else {
                a = x1;
            }
        }

        return new Answer(
                (a + b) / 2,
                (b - a) / 2,
                n
        );
    }

    public Answer goldenRatioMethod(double a, double b, double deltaX) {
        double phi = (1 + Math.sqrt(5)) / 2; // Золотое сечение
        double resphi = 2 - phi;

        // Инициализация
        double x1 = a + resphi * (b - a);
        double x2 = b - resphi * (b - a);
        double f1 = function.apply(x1);
        double f2 = function.apply(x2);

        int iteration = 0;

        while (Math.abs(b - a) > deltaX) {
            iteration++;
            if (f1 < f2) {
                b = x2; // Сужаем интервал
                x2 = x1;
                f2 = f1;
                x1 = a + resphi * (b - a);
                f1 = function.apply(x1);
            } else {
                a = x1; // Сужаем интервал
                x1 = x2;
                f1 = f2;
                x2 = b - resphi * (b - a);
                f2 = function.apply(x2);
            }
        }

        // Минимум будет в середине интервала
        double minimumPoint = (f1 < f2) ? x1 : x2;

        return new Answer(minimumPoint, (b - a) / 2, iteration);
    }

    public Answer fibonacciMethod(double a, double b, double deltaX) {
        if (a >= b) {
            throw new IllegalArgumentException("Left bound must be less than right bound.");
        }

        // Определение числа итераций через длину интервала и deltaX
        int n = 1;
        double fibonacciN2 = 1, fibonacciN1 = 1, fibonacciN = 2;
        while (fibonacciN * deltaX < b - a) {
            n++;
            fibonacciN2 = fibonacciN1;
            fibonacciN1 = fibonacciN;
            fibonacciN = fibonacciN1 + fibonacciN2;
        }

        double x1 = a + fibonacciN2 / fibonacciN * (b - a);
        double x2 = a + fibonacciN1 / fibonacciN * (b - a);
        double f1 = function.apply(x1);
        double f2 = function.apply(x2);

        int iterationCount = 0;

        for (int i = 1; i < n; i++) {
            iterationCount++;
            if (f1 < f2) {
                b = x2;
                x2 = x1;
                f2 = f1;
                x1 = a + fibonacciN2 / fibonacciN * (b - a);
                f1 = function.apply(x1);
            } else {
                a = x1;
                x1 = x2;
                f1 = f2;
                x2 = a + fibonacciN1 / fibonacciN * (b - a);
                f2 = function.apply(x2);
            }
            fibonacciN = fibonacciN1;
            fibonacciN1 = fibonacciN2;
            fibonacciN2 = fibonacciN - fibonacciN1;
        }

        double minimum = (a + b) / 2;
        return new Answer(minimum, (b - a) / 2, iterationCount);
    }

}
