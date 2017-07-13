package vistas;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import sistemaReserva.SistemaReserva;
import sistemaReserva.ClienteView;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class VentanaAltaTarifa extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField tfTipoHabitacion;
  private JTextField tfPrecio;
  private JButton btnAceptar;
  private JButton btnNewButton;

  /**
   * Launch the application.
   */
  
  /*
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          AltaCliente frame = new AltaCliente();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }*/

  /**
   * Create the frame.
   */
  public void altaTarifa(SistemaReserva sistema)
  {
    String tipoHabitacion = tfTipoHabitacion.getText();
    double precio = 0;
    
    if (tipoHabitacion.equals(""))
    {
      JOptionPane.showMessageDialog(contentPane, "Ingrese un tipo de habitacion (Por ejemplo: Doble).");
    }	
    else
	{
    	try
        {
        	precio = Double.parseDouble(tfPrecio.getText());
        }
        catch (NumberFormatException e)
        {
        	JOptionPane.showMessageDialog(contentPane, "El precio debe ser un numero.");
        	return;
        }
        
    	if (precio <= 0)
    	{
    		JOptionPane.showMessageDialog(contentPane, "El precio debe ser mayor a 0. ");
    	}
    	else
    	{
		  	boolean rdo = sistema.altaTarifa(tipoHabitacion, precio);   
		  	if (rdo == true)
		  	{
		  		JOptionPane.showMessageDialog(contentPane, "Tarifa agregada exitosamente. ");
		  		dispose();
		  	}
		  	else
		  		JOptionPane.showMessageDialog(contentPane, "Error. Ya existe un tipo de habitacion con ese nombre.");
    	}
	}
  }
  
  public VentanaAltaTarifa(SistemaReserva sistema)
  {
    setResizable(false);//Que no lo puedan maximizar
    setTitle("Alta Tarifa");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 700, 480);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblTipoHabitacion = new JLabel("Tipo de habitacion:");
    lblTipoHabitacion.setBounds(197, 93, 130, 14);
    contentPane.add(lblTipoHabitacion);
    
    tfTipoHabitacion = new JTextField();
    tfTipoHabitacion.setBounds(337, 90, 150, 20);
    contentPane.add(tfTipoHabitacion);
    tfTipoHabitacion.setColumns(10);
    
    JLabel lblEmail = new JLabel("Precio:");
    lblEmail.setBounds(197, 121, 130, 14);
    contentPane.add(lblEmail);
    
    tfPrecio = new JTextField();
    tfPrecio.setBounds(337, 118, 150, 20);
    contentPane.add(tfPrecio);
    tfPrecio.setColumns(10);
    
    btnAceptar = new JButton("Aceptar");
    btnAceptar.addKeyListener(new KeyAdapter()
    {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
          altaTarifa(sistema);
      }
    });
    btnAceptar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        altaTarifa(sistema);
      }
    });
    btnAceptar.setBounds(197, 149, 89, 23);
    contentPane.add(btnAceptar);
    
    btnNewButton = new JButton("Cancelar");
    btnNewButton.addKeyListener(new KeyAdapter()
    {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
          dispose();
      }
    });
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        dispose();
      }
    });
    btnNewButton.setBounds(337, 149, 89, 23);
    contentPane.add(btnNewButton);
  }
}
