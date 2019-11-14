package zajecia;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    private Function function;
    private double trueValue;
    private List<Double> epsilonA;
    private List<Double> wyniki;
    private List<Double> epsilonT;

    public Solver(Function function, double trueValue) {
        this.function = function;
        this.trueValue = trueValue;
    }

    public Wyniki bisection(double a, double b, double blad, int maxIter) throws MaxIterationException {
        double c = a;
        InitalieListAndAddFirstValues(c);
        int i = 0;
        if (function.function(a) * function.function(b) >= 0) {
            System.out.println("nieprawidłowy przedział");
            return null;
        }
        while (blad < Math.abs(epsilonA.get(i))) {
            c = (a + b) / 2;
            if (function.function(c) == 0.0)
                break;
            else if (function.function(c) * function.function(a) < 0)
                b = c;
            else {
                a = c;
            }
            wyniki.add(c);
            addBlad(i);
            i++;
            if (i > maxIter) {
                throw new MaxIterationException("przekroczono max Iteracji");
            }
        }
        return new Wyniki(epsilonA, epsilonT, c);
    }



    public Wyniki fixedPoint(double x0, double relError, int maxIter) throws MaxIterationException {
        InitalieListAndAddFirstValues(x0);
        int i = 0;
        while (relError < Math.abs(epsilonA.get(i))) {
            wyniki.add(wyniki.get(i) + function.function(wyniki.get(i)));
            addBlad(i);
            i++;
            if (i > maxIter) {
                throw new MaxIterationException("przekroczono max Iteracji");
            }
        }

        return new Wyniki(epsilonA, epsilonT, wyniki.get(wyniki.size() - 1));

    }

    public Wyniki metodaSiecznych(double x0, double x1, double relError, int maxIter) throws MaxIterationException {
        InitalieListAndAddFirstValues(x0);
        wyniki.add(x1);
        epsilonA.add((x1 - x0) / x1);
        epsilonT.add((x1 - trueValue) / trueValue);
        int i = 1;
        while (relError < Math.abs(epsilonA.get(i))) {
            double x = wyniki.get(i) - (function.function(wyniki.get(i)) * (wyniki.get(i) - wyniki.get(i - 1)) / (function.function(wyniki.get(i)) - function.function(wyniki.get(i - 1))));
            wyniki.add(x);
            addBlad(i);
            i++;
            if (i > maxIter) {
                throw new MaxIterationException("przekroczono max Iteracji");
            }
        }

        return new Wyniki(epsilonA, epsilonT, wyniki.get(wyniki.size() - 1));

    }

    public Wyniki metodaStycznych(double x0, double relError, int maxIter, Function dFunction) throws MaxIterationException {
        InitalieListAndAddFirstValues(x0);
        int i = 0;
        while (relError < Math.abs(epsilonA.get(i))) {
            wyniki.add(wyniki.get(i) - (function.function(wyniki.get(i)) / dFunction.function(wyniki.get(i))));
            addBlad(i);
            i++;
            if (i > maxIter) {
                throw new MaxIterationException("przekroczono max Iteracji");
            }
        }

        return new Wyniki(epsilonA, epsilonT, wyniki.get(wyniki.size() - 1));

    }

    public Wyniki regularFalsi(double a, double b, double relError, int maxIter) throws MaxIterationException {
        double c = a;
        InitalieListAndAddFirstValues(c);
        int i = 0;
        while (relError < Math.abs(epsilonA.get(i))) {
            if (function.function(a) * function.function(b) >= 0) {
                System.out.println("nieprawidłowy przedział");
                return null;
            }
            c = b - (function.function(b) * (a - b)) / (function.function(a) - function.function(b));
            if (function.function(c) == 0.0)
                break;
            else if (function.function(c) * function.function(a) < 0)
                b = c;
            else {
                a = c;
            }
            wyniki.add(c);
            addBlad(i);
            i++;
            if (i > maxIter) {
                throw new MaxIterationException("przekroczono max Iteracji");
            }
        }

        return new Wyniki(epsilonA, epsilonT, c);

    }
    public double getF(double x){
        return function.function(x);
    }

    private void addBlad(int i) {
        if (wyniki.get(i + 1) == 0) {
            epsilonA.add(1.);
        } else {
            epsilonA.add((wyniki.get(i + 1) - wyniki.get(i)) / wyniki.get(i + 1));
        }
        epsilonT.add((wyniki.get(i + 1) - trueValue) / trueValue);
    }
    private void InitalieListAndAddFirstValues(double c) {
        epsilonA = new ArrayList<>();
        wyniki = new ArrayList<>();
        epsilonT = new ArrayList<>();
        wyniki.add(c);
        epsilonA.add(1.);
        epsilonT.add((c - trueValue) / trueValue);

    }
}
