import java.util.Comparator;

public class ComparadorHES implements Comparator<EstadoES> {
    public int compare(EstadoES e1, EstadoES e2) {
        if(e1.getH() == e2.getH())
            return 0;
        else if(e1.getH() > e2.getH())
            return 1;
        else
            return -1;
    }
}
