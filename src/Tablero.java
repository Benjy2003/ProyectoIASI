import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Tablero 
{
    private final static int N = 10;
    private int Mat[][];
    
    private int precio;                 //Precio necesario para poder terminar.
    private int acumulado;              //Acumulado actual.

    private int xInicial;               //Posición inicial del robot.
    private int yInicial;               //Posición inicial del robot.
    private int xFinal;                 //Posición objetivo del robot.
    private int yFinal;                 //Posición objetivo del robot.
    private double h;                   //Distacia heursítica
    


    public Tablero() 
    {
        Mat = new int[N][N];
        acumulado = 0;
        cargarDatos();
    }

    private void cargarDatos() 
    {
        try 
        {
            Scanner scanner = new Scanner(new FileReader(new File("LABECOIN1.txt")));
            String dato;
            String[] numeros;
            
            dato = scanner.nextLine();
            this.precio = Integer.parseInt(dato);
            for (int i = 0; i < N; i++) 
            {
                dato = scanner.nextLine();
                numeros = dato.split(",");
                for (int j = 0; j < N; j++) 
                {
                    if(Integer.parseInt(numeros[j]) == 8)
                    {
                        xInicial = j;
                        yInicial = i;
                    }
                    if(Integer.parseInt(numeros[j]) == 7)
                    {
                        xFinal = j;
                        yFinal = i;
                    }
                    this.Mat[i][j] = Integer.parseInt(numeros[j]);
                }
            }
            scanner.close();

        } catch (Exception e)
        {
            System.err.println("Error al leer el fichero");
        }
    }

    public void mostrar(Ventana v)
    {
        v.iniciarComponentes(Mat, precio);
    }

    public void distanciaHeuristica(int x, int y)
    {
        //h^2 = a^2 + b^2
        double res = Math.pow(xFinal-x,2) + Math.pow(yFinal-y,2);
        h = Math.sqrt(res);
        System.out.println("Distancia Heuristica: " + h);
    }
    
}
