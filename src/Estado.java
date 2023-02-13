public class Estado {
    private int x;
    private int y;
    private Movimiento m;
    private int acumulado;

    public Estado(int x, int y, int acumulado, Movimiento m) {
        this.x = x + m.getX();
        this.y = y + m.getY();
        this.m = m;
        this.acumulado = acumulado;
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
}
