import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Ventana extends JFrame
{
     public Ventana()
    {   
        setSize(600, 600);              //Establece el tamaño de la ventana.
        setLocationRelativeTo(null);               //Situa la pantalla en el centro de la pantalla.
        setTitle("LABECOIN");                  //Establece el título de la ventana.
        setDefaultCloseOperation(EXIT_ON_CLOSE);     //Deja de ejecutarse al cerrar la ventana.
    }

    public void iniciarComponentes(int MAT[][])
    {
        JPanel panel = new JPanel();

        panel.setLayout(null);                              //Desactiva que se situe en el centro el texto por defecto.
        panel.setBackground(Color.WHITE);                       //Ponemos el color de fondo (Blanco).
        getContentPane().add(panel);                            //Ponemos el panel en la ventana.
        for (int i = 0; i < MAT.length; i++)
        {
            for (int j = 0; j < MAT[i].length; j++)
            {
                JLabel label = new JLabel(Integer.toString(MAT[i][j]));
                label.setHorizontalAlignment(SwingConstants.CENTER);  //Sitúa el texto a la izquierda.
                label.setBounds(j, i, 100, 100);  //Ponemos la posición que preferimos para la etiqueta
                panel.add(label);
            }
        }
    }

}