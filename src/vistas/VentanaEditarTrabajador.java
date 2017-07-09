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
import sistemaReserva.TrabajadorView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
public class VentanaEditarTrabajador extends JFrame {

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
  private JComboBox<Object> boxTipoDoc;
  private JLabel lblLegajo;
  private JTextField tfLegajo;
  private JButton btnBuscar;
  private JCheckBox chckbxcambiarContrasea;
  private JLabel lblContraseaAnterior;
  private JLabel lblContraseaNueva;
  private JPasswordField passwordOLD;
  private JPasswordField passwordNEW;

  /**
   * Launch the application.
   */
  
  /*
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          AltaTrabajador frame = new AltaTrabajador();
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
  public void buscar(SistemaReserva sistema)
  {
    String legajo = tfLegajo.getText();
    
    //Buscar trabajador por numero de legajo
    if(sistema.validarNumeroLegajoTrabajador(legajo))
    {
      TrabajadorView trabajador = sistema.buscarTrabajadorView(Integer.parseInt(legajo));
      if(trabajador != null)
      {
        tfLegajo.setEnabled(true);
        tfLegajo.setText(String.valueOf(trabajador.getLegajo()));
        
        tfNombre.setEnabled(true);
        tfNombre.setText(trabajador.getNombre());
        
        tfApellido.setEnabled(true);
        tfApellido.setText(trabajador.getApellido());
        
        boxTipoDoc.setEnabled(true);
        boxTipoDoc.setModel(new DefaultComboBoxModel<Object>(new String[] {
            trabajador.getTipoDoc(), "DNI", "LE", "LC", "CEDULA", 
            "PASAPORTE"}));
        
        tfNumeroDocumento.setEnabled(true);
        tfNumeroDocumento.setText(trabajador.getNumDoc());
        
        tfDireccion.setEnabled(true);
        tfDireccion.setText(trabajador.getDireccion());
        
        tfTelefono.setEnabled(true);
        tfTelefono.setText(trabajador.getTelefono());
        
        tfEmail.setEnabled(true);
        tfEmail.setText(trabajador.getEmail());
        
        passwordOLD.setEnabled(false);
        passwordOLD.setText(trabajador.getPassword());
        
        
        btnAceptar.setEnabled(true);
      }else
      {
        JOptionPane.showInternalMessageDialog(contentPane, "NO EXISTE EL "
            + "TRABAJADOR CON LEGAJO N\u00daMERO " +legajo);
      }
    }else
    {
      JOptionPane.showInternalMessageDialog(contentPane, "INGRESE SOLO N\u00daMEROS");
    }
  }
  
  public void guardar(SistemaReserva sistema)
  {
    String legajo = tfLegajo.getText();
    String nombre = tfNombre.getText();
    String apellido = tfApellido.getText();
    String tipoDoc = (String)boxTipoDoc.getSelectedItem();
    String numDoc = tfNumeroDocumento.getText();
    String direccion = tfDireccion.getText();
    String telefono = tfTelefono.getText();
    String email = tfEmail.getText();
    String passwordAnterior = new String(passwordOLD.getPassword());
    String passwordNuevo = new String(passwordNEW.getPassword());
    
    if(sistema.validarNumeroLegajoTrabajador(legajo))
    {
      TrabajadorView nuevoTrabajador = sistema.buscarTrabajadorView(Integer.parseInt(legajo));
     
      if(nuevoTrabajador != null)
      {
        if (nombre.equals("") || apellido.equals("") || tipoDoc.equals("") || numDoc.equals("")|| direccion.equals("") || telefono.equals("") || email.equals(""))
        {
          JOptionPane.showMessageDialog(contentPane, "Faltan ingresar datos.");
        }else
        {
          if(sistema.modificarTrabajador(Integer.parseInt(legajo), nombre, apellido, tipoDoc, numDoc, direccion, telefono, email))
          {
            if(chckbxcambiarContrasea.isSelected())
            {
              if(sistema.modificarTrabajadorPassword(Integer.parseInt(legajo), passwordAnterior, passwordNuevo))
              {
                JOptionPane.showMessageDialog(contentPane, "Trabajador y "
                    + "CONTRASE\u00d1A modificados Correctamente.");
                dispose();
              }
            }else
            {
              JOptionPane.showMessageDialog(contentPane, "Trabajador modificado "
                  + "Correctamente.");
              dispose();
            }
          }
        }
      }else
      {
        JOptionPane.showInternalMessageDialog(contentPane, "NO EXISTE EL "
            + "TRABAJADOR CON LEGAJO N\u00daMERO " + legajo);
      }
    }else
    {
      JOptionPane.showInternalMessageDialog(contentPane, "INGRESE SOLO N\u00daMEROS");
    }
    
  }
  
  
  public VentanaEditarTrabajador(SistemaReserva sistema)
  {
    setResizable(false);//Que no lo puedan maximizar
    setTitle("Modificar Trabajador");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 700, 480);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblNombre = new JLabel("Nombre:");
    lblNombre.setBounds(171, 90, 160, 14);
    contentPane.add(lblNombre);
    
    tfNombre = new JTextField();
    tfNombre.setEnabled(false);
    tfNombre.setBounds(337, 90, 150, 20);
    contentPane.add(tfNombre);
    tfNombre.setColumns(10);
    
    JLabel lblApellido = new JLabel("Apellido:");
    lblApellido.setBounds(171, 115, 160, 14);
    contentPane.add(lblApellido);
    
    tfApellido = new JTextField();
    tfApellido.setEnabled(false);
    tfApellido.setBounds(337, 115, 150, 20);
    contentPane.add(tfApellido);
    tfApellido.setColumns(10);
    
    JLabel lblTipoDoc = new JLabel("Tipo de Documento:");
    lblTipoDoc.setBounds(171, 140, 160, 14);
    contentPane.add(lblTipoDoc);
    
    boxTipoDoc = new JComboBox<Object>();
    boxTipoDoc.setEnabled(false);
    boxTipoDoc.setModel(new DefaultComboBoxModel<Object>(new String[] {"DNI", 
        "LE", "LC", "CEDULA", "PASAPORTE"}));
    boxTipoDoc.setBounds(337, 140, 150, 20);
    contentPane.add(boxTipoDoc);
    
    JLabel lblNumeroDocumento = new JLabel("N\u00FAmero de Documento");
    lblNumeroDocumento.setBounds(171, 165, 160, 14);
    contentPane.add(lblNumeroDocumento);
    
    tfNumeroDocumento = new JTextField();
    tfNumeroDocumento.setEnabled(false);
    tfNumeroDocumento.setBounds(337, 165, 150, 20);
    contentPane.add(tfNumeroDocumento);
    tfNumeroDocumento.setColumns(10);
    
    JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
    lblDireccion.setBounds(171, 190, 160, 14);
    contentPane.add(lblDireccion);
    
    tfDireccion = new JTextField();
    tfDireccion.setEnabled(false);
    tfDireccion.setBounds(337, 190, 150, 20);
    contentPane.add(tfDireccion);
    tfDireccion.setColumns(10);
    
    JLabel lblTelefono = new JLabel("T\u00E9lefono:");
    lblTelefono.setBounds(171, 215, 160, 14);
    contentPane.add(lblTelefono);
    
    tfTelefono = new JTextField();
    tfTelefono.setEnabled(false);
    tfTelefono.setBounds(337, 215, 150, 20);
    contentPane.add(tfTelefono);
    tfTelefono.setColumns(10);
    
    JLabel lblEmail = new JLabel("Email:");
    lblEmail.setBounds(171, 240, 160, 14);
    contentPane.add(lblEmail);
    
    tfEmail = new JTextField();
    tfEmail.setEnabled(false);
    tfEmail.setBounds(337, 240, 150, 20);
    contentPane.add(tfEmail);
    tfEmail.setColumns(10);
    
    btnAceptar = new JButton("Aceptar");
    btnAceptar.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
          guardar(sistema);
      }
    });
    btnAceptar.setEnabled(false);
    btnAceptar.addActionListener(new ActionListener() {
      
      public void actionPerformed(ActionEvent arg0)
      {
        guardar(sistema);
      }
    });
    btnAceptar.setBounds(200, 350, 100, 23);
    contentPane.add(btnAceptar);
    
    btnCancelar = new JButton("Cancelar");
    btnCancelar.addKeyListener(new KeyAdapter() {
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
    btnCancelar.setBounds(398, 350, 100, 23);
    contentPane.add(btnCancelar);
    
    lblLegajo = new JLabel("Ingresar Legajo:");
    lblLegajo.setBounds(200, 37, 117, 14);
    contentPane.add(lblLegajo);
    
    tfLegajo = new JTextField();
    tfLegajo.setBounds(337, 34, 150, 20);
    contentPane.add(tfLegajo);
    tfLegajo.setColumns(10);
    
    btnBuscar = new JButton("Buscar");
    btnBuscar.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
          buscar(sistema);
      }
    });
    btnBuscar.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0)
      {
        buscar(sistema);
      }
    });
    btnBuscar.setBounds(497, 33, 100, 23);
    contentPane.add(btnBuscar);
    
    chckbxcambiarContrasea = new JCheckBox("¿Cambiar contrase\u00f1a?");
    chckbxcambiarContrasea.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0)
      {
        lblContraseaAnterior.setEnabled(true);
        passwordOLD.setEnabled(true);
        
        lblContraseaNueva.setEnabled(true);
        passwordNEW.setEnabled(true);
      }
      @Override
      public void mouseReleased(MouseEvent e)
      {
        lblContraseaAnterior.setEnabled(false);
        passwordOLD.setEnabled(false);
        
        lblContraseaNueva.setEnabled(false);
        passwordNEW.setEnabled(false);
      }
      
    });
    chckbxcambiarContrasea.setBounds(337, 62, 200, 23);
    contentPane.add(chckbxcambiarContrasea);
    
    lblContraseaAnterior = new JLabel("contrase\u00f1a anterior:");
    lblContraseaAnterior.setEnabled(false);
    lblContraseaAnterior.setBounds(171, 266, 160, 15);
    contentPane.add(lblContraseaAnterior);
    
    passwordOLD = new JPasswordField();
    passwordOLD.setEnabled(false);
    passwordOLD.setBounds(337, 264, 150, 19);
    contentPane.add(passwordOLD);
    
    lblContraseaNueva = new JLabel("contrase\u00f1a Nueva");
    lblContraseaNueva.setEnabled(false);
    lblContraseaNueva.setBounds(171, 293, 150, 15);
    contentPane.add(lblContraseaNueva);
    
    passwordNEW = new JPasswordField();
    passwordNEW.setEnabled(false);
    passwordNEW.setBounds(337, 295, 150, 19);
    contentPane.add(passwordNEW);
  }
}
