package vistas;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import sistemaReserva.EstadiaView;
import sistemaReserva.SistemaReserva;

import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class VentanaEditarEstadia extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField tfNumero;
  private JTextField tfCliente;
  private JTextField tfHabitacion;
  private JTextField tfFechaEntrada;
  private EstadiaView estadia;
  private JTextPane tpObservaciones;
  JDateChooser dcFechaSalida;
  JButton btnCambiar;
  JButton btnCerrarEstadia;
  JButton btnCerrarAnticipada;
  LocalDate fechaSalida;
  private JButton button;

  /**
   * Launch the application.
   
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
   */
  /**
   * Create the frame.
   */
  
  private void buscar(SistemaReserva sistema)
  {
	  int numero;
	  try 
	  {
		numero = Integer.parseInt(tfNumero.getText());
	  }
	  catch (NumberFormatException e)
	  {
		  JOptionPane.showInternalMessageDialog(contentPane, "Numero invalido. ");
		  return;
	  }
	  
	  estadia = sistema.buscarEstadiaView(numero);
	  if (estadia == null)
	  {
		  JOptionPane.showInternalMessageDialog(contentPane, "No existe una estadia con ese numero. ");
	  }
	  else
	  {
		/*  tfCliente.setEnabled(true);
		  tfCliente.setText(Integer.toString(estadia.getCliente().getNumero()));
	
		  tfHabitacion.setText(estadia.getHabitacion().getNumero());
	
		  tfFechaEntrada.setText(estadia.getFechaIngreso().toString());
		  tpObservaciones.setText(estadia.getObservaciones());	  
		  
		  btnCambiar.setEnabled(true);
		  btnCerrarEstadia.setEnabled(true);
		  btnCerrarAnticipada.setEnabled(true);*/
	  }
  }
  
  public void cambiar(SistemaReserva sistema)
  {
	  int numero = estadia.getNumero();
	  int numeroCliente;
	  try
	  {
		  numeroCliente = Integer.parseInt(tfCliente.getText());
	  }
	  catch (NumberFormatException e)
	  {
		  JOptionPane.showInternalMessageDialog(contentPane, "No existe una estadia con ese numero. ");  
		  return;
	  }
	  LocalDate fechaSalida;
	  String observaciones = tpObservaciones.getText();
	 // sistema.modificarEstadia(numero, numeroCliente, fechaSalida, observaciones);
  }
  
  public VentanaEditarEstadia(SistemaReserva sistema) {
    setTitle("Modificar Estadia");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 700, 480);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblNumeroEstadia = new JLabel("N\u00FAmero de estadia");
    lblNumeroEstadia.setBounds(156, 32, 107, 14);
    contentPane.add(lblNumeroEstadia);
    
    tfNumero = new JTextField();
    tfNumero.setBounds(273, 29, 128, 20);
    contentPane.add(tfNumero);
    tfNumero.setColumns(10);
    
    JLabel lblFechaDeSalida = new JLabel("Fecha de Salida:");
    lblFechaDeSalida.setBounds(156, 137, 107, 14);
    contentPane.add(lblFechaDeSalida);
    
    dcFechaSalida = new JDateChooser();
    dcFechaSalida.setBounds(273, 131, 150, 20);
    contentPane.add(dcFechaSalida);
    
    JButton btnBuscar = new JButton("Buscar");
    btnBuscar.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		buscar(sistema);
    	}
    });
    btnBuscar.setBounds(411, 28, 91, 23);
    contentPane.add(btnBuscar);
    
    JLabel lblObservaciones = new JLabel("Observaciones:");
    lblObservaciones.setBounds(156, 168, 107, 14);
    contentPane.add(lblObservaciones);
    
    tpObservaciones = new JTextPane();
    tpObservaciones.setBounds(273, 168, 128, 119);
    contentPane.add(tpObservaciones);
    
    JLabel lblNumeroDeCliente = new JLabel("Cliente");
    lblNumeroDeCliente.setBounds(156, 57, 107, 14);
    contentPane.add(lblNumeroDeCliente);
    
    tfCliente = new JTextField();
    tfCliente.setEnabled(false);
    tfCliente.setBounds(273, 54, 128, 20);
    contentPane.add(tfCliente);
    tfCliente.setColumns(10);
    
    tfHabitacion = new JTextField();
    tfHabitacion.setEditable(false);
    tfHabitacion.setBounds(273, 77, 128, 20);
    contentPane.add(tfHabitacion);
    tfHabitacion.setColumns(10);
    
    JLabel lblHabitacion = new JLabel("Habitacion");
    lblHabitacion.setBounds(156, 82, 107, 14);
    contentPane.add(lblHabitacion);
    
    JLabel lblFechaEntrada = new JLabel("Fecha de entrada");
    lblFechaEntrada.setBounds(156, 107, 107, 14);
    contentPane.add(lblFechaEntrada);
    
    tfFechaEntrada = new JTextField();
    tfFechaEntrada.setEditable(false);
    tfFechaEntrada.setColumns(10);
    tfFechaEntrada.setBounds(273, 104, 128, 20);
    contentPane.add(tfFechaEntrada);
    
    btnCambiar = new JButton("Cambiar");
    btnCambiar.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		cambiar(sistema);
    	}
    });
    btnCambiar.setBounds(156, 303, 89, 23);
    contentPane.add(btnCambiar);
    
    btnCerrarEstadia = new JButton("Cerrar estadia");
    btnCerrarEstadia.setBounds(273, 303, 112, 23);
    contentPane.add(btnCerrarEstadia);
    
    btnCerrarAnticipada = new JButton("Cerrar anticipada");
    btnCerrarAnticipada.setBounds(411, 303, 128, 23);
    contentPane.add(btnCerrarAnticipada);
    
    button = new JButton("Buscar");
    button.setBounds(411, 53, 91, 23);
    contentPane.add(button);
  }
}