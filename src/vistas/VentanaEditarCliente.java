package vistas;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import sistemaReserva.SistemaReserva;
import sistemaReserva.ClienteView;;
public class VentanaEditarCliente extends JFrame {

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
  private JButton btnCancelar;
  private JComboBox<Object> boxtipoDoc;
  private JTextField tfNroCliente;
  private JButton btnBuscar;

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
  //TODO: Buscar Cliente por c\u00F3digo.
    String codigoCliente = tfNroCliente.getText();
    if(sistema.validarNumeroCliente(codigoCliente))
    {
      ClienteView cliente = sistema.buscarClienteViewPorCodigo(codigoCliente);
      
      if(cliente != null)
      {
        tfNombre.setEnabled(true);
        tfNombre.setText(String.valueOf(cliente.getNombre()));
        
        tfApellido.setEnabled(true);
        tfApellido.setText(cliente.getApellido());
        
        boxtipoDoc.setEnabled(true);
        boxtipoDoc.setModel(new DefaultComboBoxModel<Object>(new String[] {
            cliente.getTipoDoc(), "DNI", "LE", "LC", "CEDULA", "PASAPORTE"}));
        
        
        tfNumeroDocumento.setEnabled(true);
        tfNumeroDocumento.setText(cliente.getNumDoc());
        
        tfDireccion.setEnabled(true);
        tfDireccion.setText(cliente.getDireccion());
        
        tfTelefono.setEnabled(true);
        tfTelefono.setText(cliente.getTelefono());
        
        tfEmail.setEnabled(true);
        tfEmail.setText(cliente.getTelefono());
        
        btnAceptar.setEnabled(true);
      }else
      {
        JOptionPane.showInternalMessageDialog(contentPane, "NO EXISTE EL "
            + "CLIENTE N\u00daMERO " + codigoCliente);
      }
    }else
    {
      JOptionPane.showInternalMessageDialog(contentPane, "INGRESE SOLO N\u00daMEROS");
    }
      
  }
  
  
  public void guardar(SistemaReserva sistema)
  {
    String codigoCliente = tfNroCliente.getText();
    String nombre = tfNombre.getText();
    String apellido = tfApellido.getText();
    String tipoDoc = (String)boxtipoDoc.getSelectedItem();
    String numeroDocumento = tfNumeroDocumento.getText();
    String direccion = tfDireccion.getText();
    String telefono = tfTelefono.getText();
    String email = tfEmail.getText();
    
    if(sistema.validarNumeroCliente(codigoCliente))
    {
      ClienteView cliente = sistema.buscarClienteViewPorCodigo(codigoCliente);
      
      if(cliente != null)
      {
        if (nombre.equals("") || apellido.equals("") || numeroDocumento.equals("") || 
            direccion.equals("") || telefono.equals("") || email.equals(""))
        {
          JOptionPane.showMessageDialog(contentPane, "Faltan ingresar datos.");
        }else
        {
          if(sistema.modificarCliente(Integer.parseInt(codigoCliente), nombre, 
              apellido, tipoDoc, numeroDocumento, direccion, telefono, email))
          {
            JOptionPane.showMessageDialog(contentPane, "Cliente modificado Correctamente.");
            dispose();
          }else
            JOptionPane.showMessageDialog(contentPane, "EXISTE OTRO CLIENTE CON ESE DOCUMENTO");
        }
      }
      else
      {
        JOptionPane.showInternalMessageDialog(contentPane, "NO EXISTE EL "
            + "Cliente N\u00FAmero " + codigoCliente);
      }
    }else
    {
      JOptionPane.showInternalMessageDialog(contentPane, "INGRESE SOLO N\u00FAMEROS");
    }
  }
  public VentanaEditarCliente(SistemaReserva sistema)
  {
    setResizable(false);//Que no lo puedan maximizar
    setTitle("Modificar Cliente");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 700, 480);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblNroCliente = new JLabel("Ingrese N\u00FAmero de Cliente:");
    lblNroCliente.setBounds(171, 11, 156, 44);
    contentPane.add(lblNroCliente);
    
    tfNroCliente = new JTextField();
    tfNroCliente.setBounds(337, 23, 150, 20);
    contentPane.add(tfNroCliente);
    tfNroCliente.setColumns(10);
    
    
    
    JLabel lblNombre = new JLabel("Nombre:");
    lblNombre.setBounds(200, 93, 130, 14);
    contentPane.add(lblNombre);
    
    tfNombre = new JTextField();
    tfNombre.setEnabled(false);
    tfNombre.setBounds(337, 90, 150, 20);
    contentPane.add(tfNombre);
    tfNombre.setColumns(10);
    
    JLabel lblApellido = new JLabel("Apellido:");
    lblApellido.setBounds(200, 118, 130, 14);
    contentPane.add(lblApellido);
    
    tfApellido = new JTextField();
    tfApellido.setEnabled(false);
    tfApellido.setBounds(337, 115, 150, 20);
    contentPane.add(tfApellido);
    tfApellido.setColumns(10);
    
    JLabel lbltipoDoc = new JLabel("Tipo de Documento:");
    lbltipoDoc.setBounds(200, 143, 130, 14);
    contentPane.add(lbltipoDoc);
    
    boxtipoDoc = new JComboBox<Object>();
    boxtipoDoc.setEnabled(false);
    boxtipoDoc.setModel(new DefaultComboBoxModel<Object>(new String[] {"tipoDoc", "DNI", "LE", "LC", "CEDULA", "PASAPORTE"}));
    boxtipoDoc.setBounds(337, 140, 150, 20);
    contentPane.add(boxtipoDoc);
    
    JLabel lblNumeroDocumento = new JLabel("N\u00FAmero de Documento");
    lblNumeroDocumento.setBounds(200, 168, 130, 14);
    contentPane.add(lblNumeroDocumento);
    
    tfNumeroDocumento = new JTextField();
    tfNumeroDocumento.setEnabled(false);
    tfNumeroDocumento.setBounds(337, 165, 150, 20);
    contentPane.add(tfNumeroDocumento);
    tfNumeroDocumento.setColumns(10);
    
    JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
    lblDireccion.setBounds(200, 193, 130, 14);
    contentPane.add(lblDireccion);
    
    tfDireccion = new JTextField();
    tfDireccion.setEnabled(false);
    tfDireccion.setBounds(337, 190, 150, 20);
    contentPane.add(tfDireccion);
    tfDireccion.setColumns(10);
    
    JLabel lblTelefono = new JLabel("T\u00E9lefono:");
    lblTelefono.setBounds(200, 218, 130, 14);
    contentPane.add(lblTelefono);
    
    tfTelefono = new JTextField();
    tfTelefono.setEnabled(false);
    tfTelefono.setBounds(337, 215, 150, 20);
    contentPane.add(tfTelefono);
    tfTelefono.setColumns(10);
    
    JLabel lblEmail = new JLabel("Email:");
    lblEmail.setBounds(200, 243, 130, 14);
    contentPane.add(lblEmail);
    
    tfEmail = new JTextField();
    tfEmail.setEnabled(false);
    tfEmail.setBounds(337, 240, 150, 20);
    contentPane.add(tfEmail);
    tfEmail.setColumns(10);
    
    
    
    btnBuscar = new JButton("Buscar");
    btnBuscar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e)
      {
        buscar(sistema);
      }
    });
    btnBuscar.setBounds(497, 22, 89, 23);
    contentPane.add(btnBuscar);
    
    btnAceptar = new JButton("Aceptar");
    btnAceptar.setEnabled(false);
    btnAceptar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        guardar(sistema);
      }
    });
    btnAceptar.setBounds(200, 296, 89, 23);
    contentPane.add(btnAceptar);
    
    btnCancelar = new JButton("Cancelar");
    btnCancelar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        dispose();
      }
    });
    btnCancelar.setBounds(398, 296, 89, 23);
    contentPane.add(btnCancelar);
  }
}
