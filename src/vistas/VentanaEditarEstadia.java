package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class VentanaEditarEstadia extends JFrame {

  private JPanel contentPane;
  private JTextField tfNroCliente;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          VentanaEditarEstadia frame = new VentanaEditarEstadia();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public VentanaEditarEstadia() {
    setTitle("Modificar Estadia");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 700, 480);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblNroCliente = new JLabel("N\u00FAmero de Cliente");
    lblNroCliente.setBounds(156, 79, 107, 14);
    contentPane.add(lblNroCliente);
    
    tfNroCliente = new JTextField();
    tfNroCliente.setBounds(273, 76, 128, 20);
    contentPane.add(tfNroCliente);
    tfNroCliente.setColumns(10);
    
    JLabel lblFechaDeSalida = new JLabel("Fecha de Salida:");
    lblFechaDeSalida.setBounds(156, 120, 107, 14);
    contentPane.add(lblFechaDeSalida);
    
    JDateChooser dateChooser = new JDateChooser();
    dateChooser.setBounds(273, 120, 150, 20);
    contentPane.add(dateChooser);
    
    JButton btnBuscar = new JButton("Buscar");
    btnBuscar.setBounds(411, 75, 91, 23);
    contentPane.add(btnBuscar);
    
    JLabel lblObservaciones = new JLabel("Observaciones:");
    lblObservaciones.setBounds(156, 168, 107, 14);
    contentPane.add(lblObservaciones);
    
    JTextPane textPane = new JTextPane();
    textPane.setBounds(273, 168, 128, 119);
    contentPane.add(textPane);
  }
}
