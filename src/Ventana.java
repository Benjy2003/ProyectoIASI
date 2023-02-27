import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Ventana extends JFrame
{
    public Ventana()
    {   
        setSize(600, 600);              //Establece el tamaño de la ventana.
        setLocationRelativeTo(null);               //Situa la pantalla en el centro de la pantalla.
        setTitle("LABECOIN");                  //Establece el título de la ventana.
        setDefaultCloseOperation(EXIT_ON_CLOSE);     //Deja de ejecutarse al cerrar la ventana.
    }

    public void iniciarComponentes(int MAT[][], int precio)
    {
        JPanel panel = new JPanel();
        JLabel label;
        
        panel.setLayout(null);                              //Desactiva que se situe en el centro el texto por defecto.
        panel.setBackground(Color.WHITE);                       //Ponemos el color de fondo (Blanco).                           //Ponemos el panel en la ventana.
        for (int i = 0; i < MAT.length; i++)
        {
            for (int j = 0; j < MAT[i].length; j++)
            {
                label = new JLabel(Integer.toString(MAT[i][j]));
                label.setHorizontalAlignment(SwingConstants.LEFT);
                label.setFont(new Font("Arial", Font.PLAIN, 22));                  //Sitúa el texto a la izquierda.
                label.setBounds(10+(j*22), 10+(i*22), 22, 22);                        //Ponemos la posición que preferimos para la etiqueta
                panel.add(label);

            }
        }
        label = new JLabel("Precio: " + precio);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setFont(new Font("Arial", Font.PLAIN, 20));                  //Sitúa el texto a la izquierda.
        label.setBounds(10, 230, 100, 22);                        //Ponemos la posición que preferimos para la etiqueta
        panel.add(label);
        add(panel);
        setVisible(true);

    }

}