import java.util.Comparator;

public class ComparadorH implements Comparator<Estado> {
    public int compare(Estado e1, Estado e2) {
        if(e1.getH() == e2.getH())
            return 0;
        else if(e1.getH() > e2.getH())
            return 1;
        else
            return -1;
    }
}
