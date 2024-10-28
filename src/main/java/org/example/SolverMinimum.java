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
        double t1 = 0.3819660113;
        double t2 = 1.0 - t1;
        double x0 = a;
        double x1 = a + t1 * (b - a);
        double x2 = a + t2 * (b - a);
        double x3 = b;
        int iteration = 0;
        double fr = 0;

        double f0 = function.apply(x0);
        double f1 = function.apply(x1);
        double f2 = function.apply(x2);
        double f3 = function.apply(x3);
        while (Math.abs(x3 - x0) > deltaX) {
            iteration++;
            if (f1 <= f2) {
                x3 = x2;
                f3 = f2;
                x2 = x1;
                f2 = f1;
                x1 = x0 + t1 * (x3 - x0);
                f1 = function.apply(x1);
                fr = f1;
            } else {
                x0 = x1;
                f0 = f1;
                x1 = x2;
                f1 = f2;
                x2 = x0 + t2 * (x3 - x0);
                f2 = function.apply(x2);
                fr = f2;
            }
        }
        a = x0;
        b = x3;
        return new Answer(fr, b - a, iteration);
    }

    public Answer fibonacciMethod(double a, double b, double deltaX) {
        int n = 1;
        while (fibonacci(n) < (b - a) / deltaX) {
            n++;
        }

        double x1 = a + ((double) fibonacci(n - 2) / fibonacci(n)) * (b - a);
        double x2 = a + ((double) fibonacci(n - 1) / fibonacci(n)) * (b - a);
        double f1 = function.apply(x1);
        double f2 = function.apply(x2);

        for (int i = 0; i < n; i++) {
            if (f1 > f2) {
                a = x1;
                x1 = x2;
                f1 = f2;
                x2 = a + ((double) fibonacci(n - i - 1) / fibonacci(n - i)) * (b - a);
                f2 = function.apply(x2);
            } else {
                b = x2;
                x2 = x1;
                f2 = f1;
                x1 = a + ((double) fibonacci(n - i - 2) / fibonacci(n - i)) * (b - a);
                f1 = function.apply(x1);
            }
        }
        double idkWhatIsThis = b - a;
        return new Answer(function.apply((a + b) / 2), idkWhatIsThis, n);
    }


    private static int fibonacci(int n) {
        if (n <= 0){
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

}
