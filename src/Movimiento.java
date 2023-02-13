
public enum Movimiento {
    ARRIBA("A", 0, -1),
    ABAJO("B", 0, 1),
    IZQUIERDA("I", -1, 0),
    DERECHA("D", 1, 0),
    ARRIBA_IZQUIERDA("AI", -1, -1),
    ARRIBA_DERECHA("AD", 1, -1),
    ABAJO_IZQUIERDA("BI", -1, 1),
    ABAJO_DERECHA("BD", 1, 1);  
    
    private String m;
    private int x;
    private int y;

    private Movimiento(String m, int x, int y)
    {
        this.m = m;
        this.x = x;
        this.y = y;
    }

    public String getM()
    {
        return m;
    }
    
    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}