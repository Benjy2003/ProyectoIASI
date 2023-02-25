public class Estado {
    private int x;
    private int y;
    private int acumulado;
    private Movimiento m;
    private double h;

    public Estado(int x, int y, int acumulado, Movimiento m, double h) {
        this.x = x + m.getX();
        this.y = y + m.getY();
        this.m = m;
        this.acumulado = acumulado;
        this.h = h;
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

    public Movimiento getM() {
        return m;
    }

    public double getH() {
        return h;
    }
}