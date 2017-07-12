package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextPane;
import javax.swing.JSeparator;

public class VentanaAltaEstadia extends JFrame {

  private JPanel contentPane;
  private JTextField tfNroReserva;
  private JTextField tfNroCliente;
  private JTextField tfLegajo;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          VentanaAltaEstadia frame = new VentanaAltaEstadia();
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
  public VentanaAltaEstadia() {
    setTitle("Alta Estadia");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 700, 480);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblNroReserva = new JLabel("N\u00FAmero de Reseva:");
    lblNroReserva.setBounds(179, 43, 136, 14);
    contentPane.add(lblNroReserva);
    
    tfNroReserva = new JTextField();
    tfNroReserva.setEditable(false);
    tfNroReserva.setBounds(295, 39, 136, 22);
    contentPane.add(tfNroReserva);
    tfNroReserva.setColumns(10);
    
    JButton btnBuscar = new JButton("Buscar");
    btnBuscar.setEnabled(false);
    btnBuscar.setBounds(441, 40, 96, 22);
    contentPane.add(btnBuscar);
    
    JLabel lblNmeroDeHabitacin = new JLabel("N\u00FAmero de habitaci\u00F3n:");
    lblNmeroDeHabitacin.setBounds(179, 68, 136, 23);
    contentPane.add(lblNmeroDeHabitacin);
    
    JComboBox comboBox = new JComboBox();
    comboBox.setEnabled(false);
    comboBox.setBounds(295, 68, 136, 22);
    contentPane.add(comboBox);
    
    JLabel lblNroCliente = new JLabel("N\u00FAmero de Cliente:");
    lblNroCliente.setBounds(179, 128, 136, 14);
    contentPane.add(lblNroCliente);
    
    tfNroCliente = new JTextField();
    tfNroCliente.setEditable(false);
    tfNroCliente.setBounds(295, 119, 136, 23);
    contentPane.add(tfNroCliente);
    tfNroCliente.setColumns(10);
    
    JRadioButton rdbtnConReserva = new JRadioButton("Con Reserva");
    rdbtnConReserva.setBounds(64, 39, 109, 23);
    contentPane.add(rdbtnConReserva);
    
    JRadioButton rdbtnSinReserva = new JRadioButton("Sin Reserva");
    rdbtnSinReserva.setBounds(64, 124, 96, 22);
    contentPane.add(rdbtnSinReserva);
    
    JLabel lblLegajo = new JLabel("Legajo:");
    lblLegajo.setBounds(179, 153, 96, 14);
    contentPane.add(lblLegajo);
    
    tfLegajo = new JTextField();
    tfLegajo.setEnabled(false);
    tfLegajo.setBounds(295, 149, 136, 22);
    contentPane.add(tfLegajo);
    tfLegajo.setColumns(10);
    
    JLabel lblTipoHabitacion = new JLabel("Tipo de Habitaci\u00F3n:");
    lblTipoHabitacion.setBounds(179, 185, 109, 14);
    contentPane.add(lblTipoHabitacion);
    
    JLabel lblFechaIngreso = new JLabel("Fecha de Ingreso:");
    lblFechaIngreso.setBounds(179, 216, 96, 14);
    contentPane.add(lblFechaIngreso);
    
    JLabel lblFechaSalida = new JLabel("Fecha de Salida:");
    lblFechaSalida.setBounds(179, 241, 96, 14);
    contentPane.add(lblFechaSalida);
    
    JLabel lblObservaciones = new JLabel("Observaciones:");
    lblObservaciones.setBounds(179, 294, 96, 14);
    contentPane.add(lblObservaciones);
    
    JDateChooser dateChooser = new JDateChooser();
    dateChooser.setBounds(295, 210, 150, 20);
    contentPane.add(dateChooser);
    
    JDateChooser dateChooser_1 = new JDateChooser();
    dateChooser_1.setBounds(295, 235, 150, 20);
    contentPane.add(dateChooser_1);
    
    JTextPane tpObservaciones = new JTextPane();
    tpObservaciones.setEnabled(false);
    tpObservaciones.setBounds(295, 288, 150, 95);
    contentPane.add(tpObservaciones);
    
    JButton btnAceptar = new JButton("Aceptar");
    btnAceptar.setEnabled(false);
    btnAceptar.setBounds(179, 419, 91, 23);
    contentPane.add(btnAceptar);
    
    JButton btnCancelar = new JButton("Cancelar");
    btnCancelar.setBounds(354, 419, 91, 23);
    contentPane.add(btnCancelar);
    
    JComboBox cbTipoHabitacion = new JComboBox();
    cbTipoHabitacion.setEnabled(false);
    cbTipoHabitacion.setBounds(295, 181, 136, 22);
    contentPane.add(cbTipoHabitacion);
    
    JButton btnBuscarCliente = new JButton("Buscar");
    btnBuscarCliente.setEnabled(false);
    btnBuscarCliente.setBounds(441, 120, 96, 21);
    contentPane.add(btnBuscarCliente);
    
    JSeparator separator = new JSeparator();
    separator.setBounds(0, 0, 1, 2);
    contentPane.add(separator);
  }
}
