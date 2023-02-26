import java.util.Comparator;

public class ComparadorMoneda implements Comparator<Moneda> {
    public int compare(Moneda m1, Moneda m2) {
        if(m1.getH() == m2.getH())
            return 0;
        else if(m1.getH() > m2.getH())
            return 1;
        else
            return -1;
    }
}