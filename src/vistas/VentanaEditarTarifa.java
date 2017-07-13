package vistas;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import sistemaReserva.SistemaReserva;
import sistemaReserva.ItemTarifaView;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;;
public class VentanaEditarTarifa extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField tfPrecio;
  private JButton btnAceptar;
  private JButton btnCancelar;
  private JTextField tfTipoHabitacion;
  private JButton btnBuscar;
  private JButton btnEliminar;
  private ItemTarifaView itemTarifa;
  
  /**
   * Launch the application.
   */
  
  /*
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          modificarCliente frame = new modificarCliente();
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
  public void buscar(SistemaReserva sistema)
  {
    String tipoHabitacion = tfTipoHabitacion.getText();
   	itemTarifa = sistema.buscarItemTarifaView(tipoHabitacion);
      
      if(itemTarifa != null)
      {  
        tfPrecio.setEnabled(true);
        tfPrecio.setText(Double.toString(itemTarifa.getPrecio()));     
        
        btnEliminar.setEnabled(true);
        btnAceptar.setEnabled(true);
      }else
      {
        JOptionPane.showInternalMessageDialog(contentPane, "No existe ese tipo de habitacion.");
      }
   }
      
  
  
  public void guardar(SistemaReserva sistema)
  {
    String tipoHabitacion = itemTarifa.getTipoHabitacion();
    Double precio;
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
	  	sistema.modificarValorTarifa(tipoHabitacion, precio);
		JOptionPane.showMessageDialog(contentPane, "Cambio exitoso. ");
		dispose();
	}
  }
  
  public void baja(SistemaReserva sistema)
  {
      if (itemTarifa != null)
      {
		  int respuesta = JOptionPane.showConfirmDialog(contentPane, "Esta seguro que desea ELIMINAR esta tarifa?");
	      if(respuesta == JOptionPane.YES_OPTION)
	      {
	    	boolean rdo = sistema.bajaTarifa(itemTarifa.getTipoHabitacion());
	        if (rdo == true) 
	        	JOptionPane.showMessageDialog(contentPane, "Tarifa eliminada");
	        else
	        	JOptionPane.showMessageDialog(contentPane, "No se pudo eliminar la tarifa. No debe haber habitaciones de ese tipo.");
	        dispose();
	      }
      }
  }
  
  public VentanaEditarTarifa(SistemaReserva sistema)
  {
    setResizable(false);//Que no lo puedan maximizar
    setTitle("Modificar tarifa");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 700, 480);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblTipoHabitacion = new JLabel("Tipo de habitacion: ");
    lblTipoHabitacion.setBounds(226, 11, 101, 44);
    contentPane.add(lblTipoHabitacion);
    
    tfTipoHabitacion = new JTextField();
    tfTipoHabitacion.setBounds(337, 23, 150, 20);
    contentPane.add(tfTipoHabitacion);
    tfTipoHabitacion.setColumns(10);
    
    
    
    JLabel lblPrecio = new JLabel("Precio: ");
    lblPrecio.setBounds(226, 57, 80, 14);
    contentPane.add(lblPrecio);
    
    tfPrecio = new JTextField();
    tfPrecio.setEnabled(false);
    tfPrecio.setBounds(337, 54, 150, 20);
    contentPane.add(tfPrecio);
    tfPrecio.setColumns(10);
    
    
    
    btnBuscar = new JButton("Buscar");
    btnBuscar.addKeyListener(new KeyAdapter()
    {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
          buscar(sistema);
      }
    });
    btnBuscar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        buscar(sistema);
      }
    });
    btnBuscar.setBounds(497, 22, 89, 23);
    contentPane.add(btnBuscar);
    
    btnAceptar = new JButton("Aceptar");
    btnAceptar.addKeyListener(new KeyAdapter()
    {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
          guardar(sistema);
      }
    });
    btnAceptar.setEnabled(false);
    btnAceptar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        guardar(sistema);
      }
    });
    btnAceptar.setBounds(210, 85, 89, 23);
    contentPane.add(btnAceptar);
    
    btnCancelar = new JButton("Cancelar");
    btnCancelar.addKeyListener(new KeyAdapter()
    {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
          dispose();
      }
    });
    btnCancelar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        dispose();
      }
    });
    btnCancelar.setBounds(337, 85, 89, 23);
    contentPane.add(btnCancelar);
    
    btnEliminar = new JButton("Eliminar");
    btnEliminar.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		baja(sistema);
    	}
    });
    btnEliminar.setEnabled(false);
    btnEliminar.setBounds(497, 85, 89, 23);
    contentPane.add(btnEliminar);
  }
}
