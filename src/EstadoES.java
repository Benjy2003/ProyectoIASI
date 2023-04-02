public class EstadoES {
    private int x;
    private int y;
    private int acumulado;
    private String m;
    private double h;

    public EstadoES(int x, int y, int acumulado, String m, double h) {
        this.x = x;
        this.y = y;
        this.m = m;
        this.acumulado = acumulado;
        this.h= h;
    }

    public int getX() 
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getAcumulado() 
    {
        return acumulado;
    }

    public String getM() {
        return m;
    }

    public double getH() {
        return h;
    }

    public void setAcumulado(int acumulado) {
        this.acumulado = acumulado;
    }

    public void setH(double h) {
        this.h = h;
    }
}