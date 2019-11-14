package zajecia;
import java.util.List;
public class Wyniki {
    private List<Double> epsilonA;
    private List<Double> epsilonT;
    private double wynik;
    public Wyniki(List<Double> epsilonA, List<Double> epsilonT, double wynik) {
        this.epsilonA = epsilonA;
        this.epsilonT = epsilonT;
        this.wynik = wynik;
    }
    public List<Double> getEpsilonA() {
        return epsilonA;
    }
    public void setEpsilonA(List<Double> epsilonA) {
        this.epsilonA = epsilonA;
    }
    public List<Double> getEpsilonT() {
        return epsilonT;
    }
    public void setEpsilonT(List<Double> epsilonT) {
        this.epsilonT = epsilonT;
    }
    public double getWyniki() {
        return wynik;
    }
    public void setWyniki(List<Double> wyniki) {
        this.wynik = wynik;
    }

}
