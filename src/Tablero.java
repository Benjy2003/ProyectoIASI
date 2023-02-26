import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Tablero {
    private final static int N = 10;
    private int Mat[][];
    private List<Moneda> monedas;
    private List<Estado> estados;

    private int precio; // Precio necesario para poder terminar.
    private int acumulado; // Acumulado actual.

    private int xInicial; // Posición inicial del robot.
    private int yInicial; // Posición inicial del robot.
    private int xAct;
    private int yAct;
    private int xFinal; // Posición objetivo del robot.
    private int yFinal; // Posición objetivo del robot.

    public Tablero() {
        Mat = new int[N][N];
        acumulado = 0;
        monedas = new ArrayList<>();
        estados = new ArrayList<>();
        cargarDatos();
        for (int i = 0; i < monedas.size(); i++) {
            monedas.get(i).setHRobot(distanciaHeuristica(xInicial, yInicial, monedas.get(i).getX(), monedas.get(i).getY()));
            monedas.get(i).setHFin(distanciaHeuristica(monedas.get(i).getX(), monedas.get(i).getY(), xFinal, yFinal));
        }
        Collections.sort(monedas, new ComparadorMoneda());
        estados.add(new Estado(xInicial, yInicial, acumulado, Movimiento.NINGUNO,
                distanciaHeuristica(xInicial, yInicial, xFinal, yFinal)));
        xAct = xInicial;
        yAct = yInicial;
    }

    private void cargarDatos() {
        try {
            Scanner scanner = new Scanner(new FileReader(new File("LABECOIN1.txt")));
            String dato;
            String[] numeros;

            dato = scanner.nextLine();
            this.precio = Integer.parseInt(dato);
            for (int i = 0; i < N; i++) {
                dato = scanner.nextLine();
                numeros = dato.split(",");
                for (int j = 0; j < N; j++) {
                    switch (Integer.parseInt(numeros[j])) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                            monedas.add(new Moneda(j, i, Integer.parseInt(numeros[j])));
                            break;

                        case 7:
                            xFinal = j;
                            yFinal = i;
                            break;

                        case 8:
                            xInicial = j;
                            yInicial = i;
                            break;

                        default:
                            break;
                    }
                    this.Mat[i][j] = Integer.parseInt(numeros[j]);
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrar(Ventana v) {
        v.iniciarComponentes(Mat, precio);
    }

    public double distanciaHeuristica(int xOri, int yOri, int xDest, int yDest) {

        // h^2 = a^2 + b^2
        double res = Math.pow(xDest - xOri, 2) + Math.pow(yDest - yOri, 2);
        res = Math.sqrt(res);
        return res;
    }

    public List<Estado> getPosiblesMonedas(Moneda m) {
        List<Estado> mov = new ArrayList<>();
        if (Mat[yAct - 1][xAct] != 9) {
            mov.add(new Estado(xAct, yAct, acumulado, Movimiento.ARRIBA,
                    distanciaHeuristica(xAct, yAct - 1, m.getX(), m.getY())));
        }
        if (Mat[yAct - 1][xAct + 1] != 9) {
            mov.add(new Estado(xAct, yAct, acumulado, Movimiento.ARRIBA_DERECHA,
                    distanciaHeuristica(xAct + 1, yAct - 1, m.getX(), m.getY())));
        }
        if (Mat[yAct][xAct + 1] != 9) {
            mov.add(new Estado(xAct, yAct, acumulado, Movimiento.DERECHA,
                    distanciaHeuristica(xAct + 1, yAct, m.getX(), m.getY())));
        }
        if (Mat[yAct + 1][xAct + 1] != 9) {
            mov.add(new Estado(xAct, yAct, acumulado, Movimiento.ABAJO_DERECHA,
                    distanciaHeuristica(xAct + 1, yAct + 1, m.getX(), m.getY())));
        }
        if (Mat[yAct + 1][xAct] != 9) {
            mov.add(new Estado(xAct, yAct, acumulado, Movimiento.ABAJO,
                    distanciaHeuristica(xAct, yAct + 1, m.getX(), m.getY())));
        }
        if (Mat[yAct + 1][xAct - 1] != 9) {
            mov.add(new Estado(xAct, yAct, acumulado, Movimiento.ABAJO_IZQUIERDA,
                    distanciaHeuristica(xAct - 1, yAct + 1, m.getX(), m.getY())));
        }
        if (Mat[yAct][xAct - 1] != 9) {
            mov.add(new Estado(xAct, yAct, acumulado, Movimiento.IZQUIERDA,
                    distanciaHeuristica(xAct - 1, yAct, m.getX(), m.getY())));
        }
        if (Mat[yAct - 1][xAct - 1] != 9) {
            mov.add(new Estado(xAct, yAct, acumulado, Movimiento.ARRIBA_IZQUIERDA,
                    distanciaHeuristica(xAct - 1, yAct - 1, m.getX(), m.getY())));
        }
        return mov;
    }

    public List<Estado> getPosiblesSalida() {
        List<Estado> mov = new ArrayList<>();
        if (Mat[yAct - 1][xAct] != 9) {
            mov.add(new Estado(xAct, yAct-1, acumulado, Movimiento.ARRIBA,
                    distanciaHeuristica(xAct, yAct - 1, xFinal, yFinal)));
        }
        if (Mat[yAct - 1][xAct + 1] != 9) {
            mov.add(new Estado(xAct+1, yAct-1, acumulado, Movimiento.ARRIBA_DERECHA,
                    distanciaHeuristica(xAct + 1, yAct - 1, xFinal, yFinal)));
        }
        if (Mat[yAct][xAct + 1] != 9) {
            mov.add(new Estado(xAct+1, yAct, acumulado, Movimiento.DERECHA,
                    distanciaHeuristica(xAct + 1, yAct, xFinal, yFinal)));
        }
        if (Mat[yAct + 1][xAct + 1] != 9) {
            mov.add(new Estado(xAct+1, yAct+1, acumulado, Movimiento.ABAJO_DERECHA,
                    distanciaHeuristica(xAct + 1, yAct + 1, xFinal, yFinal)));
        }
        if (Mat[yAct + 1][xAct] != 9) {
            mov.add(new Estado(xAct, yAct+1, acumulado, Movimiento.ABAJO,
                    distanciaHeuristica(xAct, yAct + 1, xFinal, yFinal)));
        }
        if (Mat[yAct + 1][xAct - 1] != 9) {
            mov.add(new Estado(xAct-1, yAct+1, acumulado, Movimiento.ABAJO_IZQUIERDA,
                    distanciaHeuristica(xAct - 1, yAct + 1, xFinal, yFinal)));
        }
        if (Mat[yAct][xAct - 1] != 9) {
            mov.add(new Estado(xAct-1, yAct, acumulado, Movimiento.IZQUIERDA,
                    distanciaHeuristica(xAct - 1, yAct, xFinal, yFinal)));
        }
        if (Mat[yAct - 1][xAct - 1] != 9) {
            mov.add(new Estado(xAct-1, yAct-1, acumulado, Movimiento.ARRIBA_IZQUIERDA,
                    distanciaHeuristica(xAct - 1, yAct - 1, xFinal, yFinal)));
        }
        return mov;
    }

    public void actualizarH(){
        Moneda m;
        for(int i = 0; i < monedas.size(); i++){
            m = monedas.get(i);
            m.setH(distanciaHeuristica(xAct, yAct, m.getX(), m.getY()));
        }
    }

    public boolean sol(){
        if(xAct != xFinal)
            return false;
        if(yAct != yFinal)
            return false;
        return true;
    }

    public void solve() {

        Moneda m = monedas.get(0);
        while (acumulado < precio) {
            Estado e;
            List<Estado> mov = getPosiblesMonedas(m);
            Collections.sort(mov, new ComparadorH());
            e = mov.get(0); 
            xAct += e.getM().getX();
            yAct += e.getM().getY();
            System.out.println(e.getM().getM());
            estados.add(e);
            if (xAct == m.getX() && yAct == m.getY()) {
                acumulado += m.getValor();
                monedas.remove(0);
                actualizarH();
                Collections.sort(monedas, new ComparadorMoneda());
                m = monedas.get(0);
            }
        }

        while (!sol()) {
            Estado e;
            List<Estado> mov = getPosiblesSalida();
            Collections.sort(mov, new ComparadorH());
            e = mov.get(0); 
            xAct += e.getM().getX();
            yAct += e.getM().getY();
            System.out.println(e.getM().getM());
            estados.add(e);
        }

        System.out.println(xAct + " " + yAct);

        for (int i = 1; i < estados.size(); i++) {
            System.out.print(estados.get(i).getM().getM());
            if (i != estados.size() - 1)
                System.out.print(",");
        }

        System.out.println(yFinal + " " + xFinal);
    }

}
