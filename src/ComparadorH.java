import java.util.Comparator;

public class ComparadorH implements Comparator<EstadoMP> {
    public int compare(EstadoMP e1, EstadoMP e2) {
        if(e1.getH() == e2.getH())
            return 0;
        else if(e1.getH() > e2.getH())
            return 1;
        else
            return -1;
    }
}
