import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Tablero {
    private final static int N = 10;
    private int Mat[][];
    private List<Moneda> monedas;
    private List<Estado> estados;

    private int precio; // Precio necesario para poder terminar.
    private int acumulado; // Acumulado actual.

    private int xInicial; // Posición inicial del robot.
    private int yInicial; // Posición inicial del robot.
    private int xFinal; // Posición objetivo del robot.
    private int yFinal; // Posición objetivo del robot.

    public Tablero() {
        Mat = new int[N][N];
        acumulado = 0;
        monedas = new ArrayList<>();
        estados = new ArrayList<>();
        cargarDatos();
        for (int i = 0; i < monedas.size(); i++) {
            monedas.get(i).setH(distanciaHeuristica(xInicial, yInicial, monedas.get(i).getX(), monedas.get(i).getY()));
        }
        estados.add(new Estado(xInicial, yInicial, acumulado, Movimiento.NINGUNO, distanciaHeuristica(xInicial, yInicial, xFinal, yFinal)));
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
        System.out.println("Distancia Heuristica: " + res);
        return res;
    }

}
