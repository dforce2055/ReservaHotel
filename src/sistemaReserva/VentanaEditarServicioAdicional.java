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
import sistemaReserva.ServicioAdicionalView;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;;
public class VentanaEditarServicioAdicional extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField tfPrecio;
  private JButton btnAceptar;
  private JButton btnCancelar;
  private JTextField tfCodigo;
  private JButton btnBuscar;
  private JButton btnEliminar;
  private ServicioAdicionalView servicioAdicional;
  private JTextField tfDescripcion;
  
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
    int codigo;
    try
    {
    	codigo = Integer.parseInt(tfCodigo.getText());
    }
    catch (NumberFormatException e)
    {
        JOptionPane.showInternalMessageDialog(contentPane, "El codigo debe ser un numero. ");
        return;
    }
    
   	servicioAdicional = sistema.buscarServicioAdicionalView(codigo);
    if(servicioAdicional != null)
      {  
        tfPrecio.setEnabled(true);
        tfPrecio.setText(Double.toString(servicioAdicional.getPrecio()));     
        
        tfDescripcion.setEnabled(true);
        tfDescripcion.setText(servicioAdicional.getDescripcion());
        
        btnEliminar.setEnabled(true);
        btnAceptar.setEnabled(true);
      }else
      {
        JOptionPane.showInternalMessageDialog(contentPane, "No existe un servicio adicional con ese codigo.");
      }
   }
      
  
  
  public void guardar(SistemaReserva sistema)
  {
	if (servicioAdicional != null)
	{
		String descripcion = tfDescripcion.getText();
	    if (descripcion.equals(""))
	    {
	      	JOptionPane.showMessageDialog(contentPane, "La descripcion no puede estar en blanco.");
	    	return;
	    }
	    
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
		  	sistema.modificarServicioAdicional(servicioAdicional.getCodigo(), descripcion, precio);
			JOptionPane.showMessageDialog(contentPane, "Cambio exitoso. ");
			dispose();
		}
	}
  }
  
  public void baja(SistemaReserva sistema)
  {
      if (servicioAdicional != null)
      {
		  int respuesta = JOptionPane.showConfirmDialog(contentPane, "Esta seguro que desea ELIMINAR este servicio adicional?");
	      if(respuesta == JOptionPane.YES_OPTION)
	      {
	    	sistema.bajaServicio(servicioAdicional.getCodigo());
	        JOptionPane.showMessageDialog(contentPane, "Servicio adicional eliminado. ");
	        dispose();
	      }
      }
  }
  
  public VentanaEditarServicioAdicional(SistemaReserva sistema)
  {
    setResizable(false);//Que no lo puedan maximizar
    setTitle("Modificar tarifa");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 700, 480);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblCodigo = new JLabel("Codigo: ");
    lblCodigo.setBounds(226, 11, 101, 44);
    contentPane.add(lblCodigo);
    
    tfCodigo = new JTextField();
    tfCodigo.setBounds(337, 23, 150, 20);
    contentPane.add(tfCodigo);
    tfCodigo.setColumns(10);
    
    
    
    JLabel lblPrecio = new JLabel("Precio: ");
    lblPrecio.setBounds(226, 84, 80, 14);
    contentPane.add(lblPrecio);
    
    tfPrecio = new JTextField();
    tfPrecio.setEnabled(false);
    tfPrecio.setBounds(337, 81, 150, 20);
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
    btnAceptar.setBounds(208, 125, 89, 23);
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
    btnCancelar.setBounds(337, 125, 89, 23);
    contentPane.add(btnCancelar);
    
    btnEliminar = new JButton("Eliminar");
    btnEliminar.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		baja(sistema);
    	}
    });
    btnEliminar.setEnabled(false);
    btnEliminar.setBounds(497, 125, 89, 23);
    contentPane.add(btnEliminar);
    
    JLabel lblDescripcion = new JLabel("Descripcion: ");
    lblDescripcion.setBounds(226, 53, 71, 14);
    contentPane.add(lblDescripcion);
    
    tfDescripcion = new JTextField();
    tfDescripcion.setEnabled(false);
    tfDescripcion.setBounds(337, 50, 150, 20);
    contentPane.add(tfDescripcion);
    tfDescripcion.setColumns(10);
  }
}
