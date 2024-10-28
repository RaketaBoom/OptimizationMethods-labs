package org.example;

public record Answer(
        double result,
        double accuracy,
        int numberOfIterations
                     ) {
}
