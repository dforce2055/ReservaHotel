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
public class VentanaAltaServicioAdicional extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField tfCodigo;
  private JTextField tfPrecio;
  private JButton btnAceptar;
  private JButton btnNewButton;
  private JTextField tfDescripcion;

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
  public void altaServicio(SistemaReserva sistema)
  {
    int codigo;
    String descripcion = tfDescripcion.getText();
    double precio = 0;
    
    try //en versiones futuras, cambiar el parse por un verificarCodigoAdicional
    {
    	codigo = Integer.parseInt(tfCodigo.getText());
    }
    catch (NumberFormatException e)
    {
    	JOptionPane.showMessageDialog(contentPane, "El codigo debe ser un numero.");
    	return;    	
    }
    
    if (descripcion.equals(""))
    {
      JOptionPane.showMessageDialog(contentPane, "Ingrese una descripcion (Por ejemplo: Cafe).");
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
    	else if (codigo <= 0)
    	{
    		JOptionPane.showMessageDialog(contentPane, "El codigo debe ser un numero mayor a 0. ");
    	}
    	else
    	{
		  	boolean rdo = sistema.altaServicioAdicional(codigo, descripcion, precio);
		  	if (rdo == true)
		  	{
		  		JOptionPane.showMessageDialog(contentPane, "Servicio adicional agregado exitosamente. ");
		  		dispose();
		  	}
		  	else
		  		JOptionPane.showMessageDialog(contentPane, "Error. Ya existe un servicio adicional con ese nombre.");
    	}
	}
  }
  
  public VentanaAltaServicioAdicional(SistemaReserva sistema)
  {
    setResizable(false);//Que no lo puedan maximizar
    setTitle("Alta Cliente");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 700, 480);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblCodigo = new JLabel("Codigo");
    lblCodigo.setBounds(197, 93, 130, 14);
    contentPane.add(lblCodigo);
    
    tfCodigo = new JTextField();
    tfCodigo.setBounds(337, 90, 150, 20);
    contentPane.add(tfCodigo);
    tfCodigo.setColumns(10);
    
    JLabel lblDescripcion = new JLabel("Descripcion:");
    lblDescripcion.setBounds(197, 121, 130, 14);
    contentPane.add(lblDescripcion);
    
    tfPrecio = new JTextField();
    tfPrecio.setBounds(337, 152, 150, 20);
    contentPane.add(tfPrecio);
    tfPrecio.setColumns(10);
    
    btnAceptar = new JButton("Aceptar");
    btnAceptar.addKeyListener(new KeyAdapter()
    {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
          altaServicio(sistema);
      }
    });
    btnAceptar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        altaServicio(sistema);
      }
    });
    btnAceptar.setBounds(197, 184, 89, 23);
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
    btnNewButton.setBounds(337, 184, 89, 23);
    contentPane.add(btnNewButton);
    
    tfDescripcion = new JTextField();
    tfDescripcion.setBounds(337, 121, 150, 20);
    contentPane.add(tfDescripcion);
    tfDescripcion.setColumns(10);
    
    JLabel lblPrecio = new JLabel("Precio: ");
    lblPrecio.setBounds(197, 152, 46, 14);
    contentPane.add(lblPrecio);
  }
}
