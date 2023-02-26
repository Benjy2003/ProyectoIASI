public class Moneda {
    private int x;
    private int y;
    private double hRobot;
    private double hFin;
    private int valor;

    public Moneda(int x, int y, int valor) {
        this.x = x;
        this.y = y;
        this.valor = valor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getHRobot() {
        return hRobot;
    }

    public double getHFin() {
        return hFin;
    }

    public double getH(){
        return hRobot + hFin;
    }

    public int getValor() {
        return valor;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHRobot(double h) {
        this.hRobot = h;
    }

    public void setHFin(double h) {
        this.hFin = h;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
