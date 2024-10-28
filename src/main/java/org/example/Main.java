package org.example;

public class Main {

    public static void main(String[] args) {
        SolverMinimum solverMinimum = new SolverMinimum(x -> Math.exp(x - 1 + 1/(x-2)));
        double a = 2.5, b = 4, deltaX = 0.0001;

        ConsoleDisplay consoleDisplay = new ConsoleDisplay();

        Answer dichotomiaResult = solverMinimum.dichotomiaMethod(a, b, deltaX);
        Answer fibonachiResult = solverMinimum.fibonacciMethod(a, b, deltaX);
        Answer goldenRatioMethod = solverMinimum.goldenRatioMethod(a, b, deltaX);

        consoleDisplay.outputLaboratoryVariant();
        consoleDisplay.outputLaboratorySolverName();
        consoleDisplay.outputLaboratoryEquation();

        consoleDisplay.outputDichotomyResult(dichotomiaResult);
        consoleDisplay.outputFibonacciResult(fibonachiResult);
        consoleDisplay.outputGoldenRatioResult(goldenRatioMethod);
    }
}