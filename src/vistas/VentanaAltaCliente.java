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
public class VentanaAltaCliente extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField tfNombre;
  private JTextField tfApellido;
  private JTextField tfNumeroDocumento;
  private JTextField tfDireccion;
  private JTextField tfTelefono;
  private JTextField tfEmail;
  private JButton btnAceptar;
  private JButton btnNewButton;
  private JComboBox<Object> boxTipoDoc;

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
  public void altaCliente(SistemaReserva sistema)
  {
    String nombre = tfNombre.getText();
    String apellido = tfApellido.getText();
    String tipoDoc = (String)boxTipoDoc.getSelectedItem();
    String numDoc = tfNumeroDocumento.getText();
    String direccion = tfDireccion.getText();
    String telefono = tfTelefono.getText();
    String email = tfEmail.getText();
    
    if (nombre.equals("") || apellido.equals("") || numDoc.equals("") || direccion.equals("") || telefono.equals("") || email.equals(""))
    {
      JOptionPane.showMessageDialog(contentPane, "Faltan ingresar datos.");
    }else
    {
      if(sistema.validarNumeroDocumento(numDoc))//Validar el campo NumeroDocumento, solo numeros
      {
        ClienteView nuevoCliente = sistema.buscarClienteViewPorDocumento(tipoDoc, numDoc);
        if(nuevoCliente == null)
        {
          if(sistema.validarEmail(email))
          {
            int nroCliente = sistema.altaCliente(nombre, apellido, tipoDoc, numDoc, direccion, telefono, email);
            JOptionPane.showMessageDialog(contentPane, "Cliente agregado Correctamente. "
                +"Numero de Cliente: " +nroCliente);
            dispose();
          }else
          {
            JOptionPane.showMessageDialog(contentPane, "EMAIL INVALIDO");
          }
        }else
        {
          JOptionPane.showMessageDialog(contentPane, "NO SE PUEDE INGRESAR, "
              + "YA EXISTE EL CLIENTE");
          tfNumeroDocumento.setForeground(Color.RED);
          tfNumeroDocumento.selectAll();
          tfNumeroDocumento.requestFocus();
        }
      }else
      {
        JOptionPane.showMessageDialog(contentPane, "El N\u00FAmero de "
            + "Documento no puede contener letras");
        tfNumeroDocumento.setForeground(Color.RED);
        tfNumeroDocumento.selectAll();
        tfNumeroDocumento.requestFocus();
      }
    }
  }
  
  public VentanaAltaCliente(SistemaReserva sistema)
  {
    setResizable(false);//Que no lo puedan maximizar
    setTitle("Alta Cliente");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 700, 480);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblNombre = new JLabel("Nombre:");
    lblNombre.setBounds(200, 93, 130, 14);
    contentPane.add(lblNombre);
    
    tfNombre = new JTextField();
    tfNombre.setBounds(337, 90, 150, 20);
    contentPane.add(tfNombre);
    tfNombre.setColumns(10);
    
    JLabel lblApellido = new JLabel("Apellido:");
    lblApellido.setBounds(200, 118, 130, 14);
    contentPane.add(lblApellido);
    
    tfApellido = new JTextField();
    tfApellido.setBounds(337, 115, 150, 20);
    contentPane.add(tfApellido);
    tfApellido.setColumns(10);
    
    JLabel lblTipoDoc = new JLabel("Tipo de Documento:");
    lblTipoDoc.setBounds(200, 143, 130, 14);
    contentPane.add(lblTipoDoc);
    
    boxTipoDoc = new JComboBox<Object>();
    boxTipoDoc.setModel(new DefaultComboBoxModel<Object>(new String[] {"DNI", "LE", "LC", "CEDULA", "PASAPORTE"}));
    boxTipoDoc.setBounds(337, 140, 150, 20);
    contentPane.add(boxTipoDoc);
    
    JLabel lblNumeroDocumento = new JLabel("N\u00FAmero de Documento");
    lblNumeroDocumento.setBounds(200, 168, 130, 14);
    contentPane.add(lblNumeroDocumento);
    
    tfNumeroDocumento = new JTextField();
    tfNumeroDocumento.setBounds(337, 165, 150, 20);
    contentPane.add(tfNumeroDocumento);
    tfNumeroDocumento.setColumns(10);
    
    JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
    lblDireccion.setBounds(200, 193, 130, 14);
    contentPane.add(lblDireccion);
    
    tfDireccion = new JTextField();
    tfDireccion.setBounds(337, 190, 150, 20);
    contentPane.add(tfDireccion);
    tfDireccion.setColumns(10);
    
    JLabel lblTelefono = new JLabel("T\u00E9lefono:");
    lblTelefono.setBounds(200, 218, 130, 14);
    contentPane.add(lblTelefono);
    
    tfTelefono = new JTextField();
    tfTelefono.setBounds(337, 215, 150, 20);
    contentPane.add(tfTelefono);
    tfTelefono.setColumns(10);
    
    JLabel lblEmail = new JLabel("Email:");
    lblEmail.setBounds(200, 243, 130, 14);
    contentPane.add(lblEmail);
    
    tfEmail = new JTextField();
    tfEmail.setBounds(337, 240, 150, 20);
    contentPane.add(tfEmail);
    tfEmail.setColumns(10);
    
    btnAceptar = new JButton("Aceptar");
    btnAceptar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        altaCliente(sistema);
      }
    });
    btnAceptar.setBounds(200, 296, 89, 23);
    contentPane.add(btnAceptar);
    
    btnNewButton = new JButton("Cancelar");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        dispose();
      }
    });
    btnNewButton.setBounds(398, 296, 89, 23);
    contentPane.add(btnNewButton);
  }
}
