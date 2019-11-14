package zajecia;

import java.util.List;

public class TrajectoryXY {
    private List<Double> x;
    private  List<Double> y;

    public List<Double> getX() {
        return x;
    }

    public void setX(List<Double> x) {
        this.x = x;
    }

    public List<Double> getY() {
        return y;
    }

    public void setY(List<Double> y) {
        this.y = y;
    }

    public TrajectoryXY(List<Double> x, List<Double> y) {
        this.x = x;
        this.y = y;
    }
}
