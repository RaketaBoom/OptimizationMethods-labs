package org.example;

public class ConsoleDisplay {
    private static final String BORDER = "===========================================================================================";

    public void outputGeneralLaboratoryInformation() {
        this.outputLaboratorySolverName();
        this.outputLaboratoryVariant();
        this.outputLaboratoryEquation();
    }

    public void outputLaboratoryVariant() {
        System.out.println("Вариант лабораторной работы - 8");
    }

    public void outputLaboratoryEquation() {
        System.out.println("Задание:");
        System.out.println("f(x) = exp(x - 1 + 1/(x-2)), x принадлежит [2.5, 4]");
        System.out.println(BORDER);
    }

    public void outputLaboratorySolverName() {
        System.out.println("Лабораторную работу выполнил студент группы 6301-010302D Гумаров Расул");
    }

    public void outputDichotomyResult(Answer answer) {
        System.out.println("Результат дихотомии: " + answer.result() + ", погрешность: " + answer.accuracy() + ", количество итераций: " + answer.numberOfIterations());
        System.out.println(BORDER);
    }

    public void outputGoldenRatioResult(Answer answer) {
        System.out.println("Результат золотого сечения: " + answer.result() + ", погрешность: " + answer.accuracy() + ", количество итераций: " + answer.numberOfIterations());
        System.out.println(BORDER);
    }

    public void outputFibonacciResult(Answer answer) {
        System.out.println("Результат фибоначчи: " + answer.result() + ", погрешность: " + answer.accuracy() + ", количество итераций: " + answer.numberOfIterations());
        System.out.println(BORDER);
    }
}
