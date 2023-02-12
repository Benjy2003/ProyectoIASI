import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Tablero 
{
    private final static int N = 10;
    private int Mat[][];
    private int precio;

    public Tablero() 
    {
        Mat = new int[N][N];
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
                    this.Mat[i][j] = Integer.parseInt(numeros[j]);
                }
            }
            scanner.close();
        } catch (Exception e)
        {
            System.err.println("Error al leer el fichero");
        }
    }

    public void mostrar()
    {
        for (int i = 0; i < N; i++) 
        {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + Mat[i][j]);
            }
            System.out.println();
        }
        System.out.println(" " + this.precio);
    }
    
}
