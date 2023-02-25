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
                            monedas.add(new Moneda(j, i, distanciaHeuristica(j, i), Integer.parseInt(numeros[j])));
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
            estados.add(new Estado(xInicial, yInicial, acumulado, null, distanciaHeuristica(xInicial, yInicial)));
            
        } catch (Exception e) {
            System.err.println("Error al leer el fichero");
        }
    }

    public void mostrar(Ventana v) {
        v.iniciarComponentes(Mat, precio);
    }

    public double distanciaHeuristica(int x, int y) {
        // h^2 = a^2 + b^2
        double res = Math.pow(xFinal - x, 2) + Math.pow(yFinal - y, 2);
        res = Math.sqrt(res);
        System.out.println("Distancia Heuristica: " + res);
        return res;
    }

}
