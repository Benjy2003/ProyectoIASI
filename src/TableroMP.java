import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class TableroMP {
    private final static int N = 10;
    private int Mat[][];
    private List<Moneda> monedas;
    private List<EstadoMP> estados;

    private int precio; // Precio necesario para poder terminar.
    private int acumulado; // Acumulado actual.

    private int xInicial; // Posición inicial del robot.
    private int yInicial; // Posición inicial del robot.
    private int xAct;
    private int yAct;
    private int xFinal; // Posición objetivo del robot.
    private int yFinal; // Posición objetivo del robot.
    private long tiempo;
    private int nodos;
    private boolean seguir;

    public TableroMP() {
        Mat = new int[N][N];
        acumulado = 0;
        nodos = 0;
        seguir = true;
        monedas = new ArrayList<>();
        estados = new ArrayList<>();
        cargarDatos();
        for (int i = 0; i < monedas.size(); i++) {
            monedas.get(i).setH(distanciaHeuristica(xInicial, yInicial, monedas.get(i).getX(), monedas.get(i).getY()));

        }
        Collections.sort(monedas, new ComparadorMoneda());
        estados.add(new EstadoMP(xInicial, yInicial, acumulado, "Estado inicial",
                distanciaHeuristica(xInicial, yInicial, xFinal, yFinal)));
        xAct = xInicial;
        yAct = yInicial;
    }

    private void cargarDatos() {
        try {
            Scanner scanner = new Scanner(new FileReader(new File("LABECOIN10.txt")));
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

    public void mostrar() {
        System.out.println("Algoritmo Heuristico Máxima Pendiente");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(Mat[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Precio: " + precio);
        if (seguir) {
            System.out.print("Una posible solución:");

            for (int i = 1; i < estados.size(); i++) {
                System.out.print(estados.get(i).getM());
                if (i != estados.size() - 1)
                    System.out.print(",");
            }
            System.out.println();
        } else
            System.out.println("Solución no encontrada");
        System.out.println("Tiempo invertido (ms): " + tiempo);
        System.out.println("Nodos investigados: " + nodos);
    }

    public double distanciaHeuristica(int xOri, int yOri, int xDest, int yDest) {

        // h^2 = a^2 + b^2
        double res = Math.pow(xDest - xOri, 2) + Math.pow(yDest - yOri, 2);
        res = Math.sqrt(res);
        return res;
    }

    public boolean existe(EstadoMP e) {
        for (EstadoMP est : estados) {
            if (est.getH() == e.getH())
                if (est.getX() == e.getX())
                    if (est.getY() == e.getY())
                        if (est.getAcumulado() == e.getAcumulado())
                            return true;
        }
        return false;
    }

    public boolean esMejor(EstadoMP e) {
        if (e.getH() < estados.get(estados.size() - 1).getH()) {
            return true;
        } else {
            if (e.getAcumulado() > estados.get(estados.size() - 1).getAcumulado()) {
                return true;
            }
        }
        return false;
    }

    public List<EstadoMP> getPosiblesMonedasMP(Moneda m) {
        List<EstadoMP> mov = new ArrayList<>();
        EstadoMP e;
        if (Mat[yAct - 1][xAct] != 9) {
            e = new EstadoMP(xAct, yAct - 1, acumulado, "A", distanciaHeuristica(xAct, yAct - 1, m.getX(), m.getY()));
            if (!existe(e))
                mov.add(e);
        }
        if (Mat[yAct - 1][xAct + 1] != 9) {
            e = new EstadoMP(xAct + 1, yAct - 1, acumulado, "AD",
                    distanciaHeuristica(xAct + 1, yAct - 1, m.getX(), m.getY()));
            if (!existe(e))
                mov.add(e);
        }
        if (Mat[yAct][xAct + 1] != 9) {
            e = new EstadoMP(xAct + 1, yAct, acumulado, "D", distanciaHeuristica(xAct + 1, yAct, m.getX(), m.getY()));
            if (!existe(e))
                mov.add(e);
        }
        if (Mat[yAct + 1][xAct + 1] != 9) {
            e = new EstadoMP(xAct + 1, yAct + 1, acumulado, "BD",
                    distanciaHeuristica(xAct + 1, yAct + 1, m.getX(), m.getY()));
            if (!existe(e))
                mov.add(e);
        }
        if (Mat[yAct + 1][xAct] != 9) {
            e = new EstadoMP(xAct, yAct + 1, acumulado, "B", distanciaHeuristica(xAct, yAct + 1, m.getX(), m.getY()));
            if (!existe(e))
                mov.add(e);
        }
        if (Mat[yAct + 1][xAct - 1] != 9) {
            e = new EstadoMP(xAct - 1, yAct + 1, acumulado, "BI",
                    distanciaHeuristica(xAct - 1, yAct + 1, m.getX(), m.getY()));
            if (!existe(e))
                mov.add(e);
        }
        if (Mat[yAct][xAct - 1] != 9) {
            e = new EstadoMP(xAct - 1, yAct, acumulado, "I", distanciaHeuristica(xAct - 1, yAct, m.getX(), m.getY()));
            if (!existe(e))
                mov.add(e);
        }
        if (Mat[yAct - 1][xAct - 1] != 9) {
            e = new EstadoMP(xAct - 1, yAct - 1, acumulado, "AI",
                    distanciaHeuristica(xAct - 1, yAct - 1, m.getX(), m.getY()));
            if (!existe(e))
                mov.add(e);
        }
        return mov;
    }

    public List<EstadoMP> getPosiblesSalidaMP() {
        List<EstadoMP> mov = new ArrayList<>();
        EstadoMP e;
        if (Mat[yAct - 1][xAct] != 9) {
            e = new EstadoMP(xAct, yAct - 1, acumulado, "A", distanciaHeuristica(xAct, yAct - 1, xFinal, yFinal));
            if (!existe(e))
                mov.add(e);
        }
        if (Mat[yAct - 1][xAct + 1] != 9) {
            e = new EstadoMP(xAct + 1, yAct - 1, acumulado, "AD",
                    distanciaHeuristica(xAct + 1, yAct - 1, xFinal, yFinal));
            if (!existe(e))
                mov.add(e);
        }
        if (Mat[yAct][xAct + 1] != 9) {
            e = new EstadoMP(xAct + 1, yAct, acumulado, "D", distanciaHeuristica(xAct + 1, yAct, xFinal, yFinal));
            if (!existe(e))
                mov.add(e);
        }
        if (Mat[yAct + 1][xAct + 1] != 9) {
            e = new EstadoMP(xAct + 1, yAct + 1, acumulado, "BD",
                    distanciaHeuristica(xAct + 1, yAct + 1, xFinal, yFinal));
            if (!existe(e))
                mov.add(e);
        }
        if (Mat[yAct + 1][xAct] != 9) {
            e = new EstadoMP(xAct, yAct + 1, acumulado, "B", distanciaHeuristica(xAct, yAct + 1, xFinal, yFinal));
            if (!existe(e))
                mov.add(e);
        }
        if (Mat[yAct + 1][xAct - 1] != 9) {
            e = new EstadoMP(xAct - 1, yAct + 1, acumulado, "BI",
                    distanciaHeuristica(xAct - 1, yAct + 1, xFinal, yFinal));
            if (!existe(e))
                mov.add(e);
        }
        if (Mat[yAct][xAct - 1] != 9) {
            e = new EstadoMP(xAct - 1, yAct, acumulado, "I", distanciaHeuristica(xAct - 1, yAct, xFinal, yFinal));
            if (!existe(e))
                mov.add(e);
        }
        if (Mat[yAct - 1][xAct - 1] != 9) {
            e = new EstadoMP(xAct - 1, yAct - 1, acumulado, "AI",
                    distanciaHeuristica(xAct - 1, yAct - 1, xFinal, yFinal));
            if (!existe(e))
                mov.add(e);
        }
        return mov;
    }

    public void actualizarH() {
        Moneda m;
        for (int i = 0; i < monedas.size(); i++) {
            m = monedas.get(i);
            m.setH(distanciaHeuristica(xAct, yAct, m.getX(), m.getY()));
        }
    }

    public boolean sol() {
        if (xAct != xFinal)
            return false;
        if (yAct != yFinal)
            return false;
        return true;
    }

    public void maximaPendiente() {
        tiempo = System.currentTimeMillis();
        Moneda m = monedas.get(0);
        while (acumulado < precio) {
            EstadoMP e;
            int tam = 0;
            List<EstadoMP> mov = getPosiblesMonedasMP(m);
            Collections.sort(mov, new ComparadorH());
            nodos += mov.size();
            if (mov.size() != 0) {
                tam = mov.size();
                for (int i = 0; i < tam; i++) {
                    e = mov.get(0);
                    if (esMejor(e)) {
                        xAct = e.getX();
                        yAct = e.getY();
                        if (xAct == m.getX() && yAct == m.getY()) {
                            acumulado += m.getValor();
                            e.setAcumulado(acumulado);
                            monedas.remove(0);
                            actualizarH();
                            if (monedas.size() != 0) {
                                Collections.sort(monedas, new ComparadorMoneda());
                                m = monedas.get(0);
                            }
                            if (acumulado < precio) {
                                e.setH(distanciaHeuristica(xAct, yAct, m.getX(), m.getY()));
                            } else {
                                e.setH(distanciaHeuristica(xAct, yAct, xFinal, yFinal));
                            }
                        }
                        estados.add(e);
                        break;
                    } else {
                        mov.remove(0);
                    }
                }
                if (mov.size() == 0) {
                    seguir = false;
                    break;
                }
            } else {
                seguir = false;
                break;
            }
        }
        if (seguir) {
            while (!sol()) {
                EstadoMP e;
                int tam = 0;
                List<EstadoMP> mov = getPosiblesSalidaMP();
                Collections.sort(mov, new ComparadorH());
                nodos += mov.size();
                if (mov.size() != 0) {
                    tam = mov.size();
                    for (int i = 0; i < tam; i++) {
                        e = mov.get(0);
                        if (esMejor(e)) {
                            xAct = e.getX();
                            yAct = e.getY();
                            estados.add(e);
                        }else{
                            mov.remove(0);
                        }
                    }
                    if(mov.size() == 0){
                        seguir = false;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        tiempo = System.currentTimeMillis() - tiempo;
    }

}
