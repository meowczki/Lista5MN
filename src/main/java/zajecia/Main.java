package zajecia;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        double trueValue = 1.0141;
        double e = 0.0167;
        double m = 1;
        Solver solver = new Solver((x) -> m + e * Math.sin(x) - x, trueValue);
        Wyniki w1 = null;
        double fw1x = 0;
        try {
            w1 = solver.bisection(-1, 2, 0.001, 30);
            fw1x = solver.getF(w1.getWyniki());
        } catch (MaxIterationException ex) {
            ex.printStackTrace();
        }
        double a=150*Math.pow(10,6);
        TrajectoryXY xy = getTrajectory(100,e,trueValue,a);
        System.out.println(xy.getX().size());
        System.out.println(xy.getX());
        System.out.println(xy.getY());


    }

    public static TrajectoryXY getTrajectory(int liczbaPkt,double e,double trueValue, double a){
        List<Double> x = new ArrayList<>();
        List<Double> y = new ArrayList<>();
        List<Double> mList=new ArrayList<>();
        double m=0;
        Solver solver = null;
        Wyniki w1 = null;
        for(int i=0;i<liczbaPkt;i++){
            m+=2*Math.PI/liczbaPkt;
            double finalM = m;
            solver= new Solver((eks) -> finalM + e * Math.sin(eks) - eks, trueValue);
            try {
                w1 = solver.bisection(m-1, m+1, 0.001, 50);
            } catch (MaxIterationException ex) {
                ex.printStackTrace();
            }
            x.add(a*Math.cos(w1.getWyniki()-e));
            y.add(a*Math.sqrt(1-Math.pow(e,2))*Math.sin(w1.getWyniki()));
        }
        return new TrajectoryXY(x,y);
    }
}

