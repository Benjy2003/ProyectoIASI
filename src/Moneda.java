public class Moneda {
    private int x;
    private int y;
    private double h;
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

    public double getH() {
        return h;
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

    public void setH(double h) {
        this.h = h;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
