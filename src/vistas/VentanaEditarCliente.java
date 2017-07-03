package vistas;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import sistemaReserva.SistemaReserva;
public class VentanaEditarCliente extends JFrame {

  private JPanel contentPane;
  private JTextField tfNombre;
  private JTextField tfApellido;
  private JTextField tfNumeroDocumento;
  private JTextField tfDireccion;
  private JTextField tfTelefono;
  private JTextField tfEmail;
  private JButton btnAceptar;
  private JButton btnCancelar;
  private JComboBox boxTipoDoc;
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
  public VentanaEditarCliente(SistemaReserva sistema) {
    setTitle("Modificar Cliente");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 700, 480);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblNroCliente = new JLabel("Ingrese Nro de Cliente:");
    lblNroCliente.setBounds(171, 11, 118, 44);
    contentPane.add(lblNroCliente);
    
    tfNroCliente = new JTextField();
    tfNroCliente.setBounds(337, 23, 150, 20);
    contentPane.add(tfNroCliente);
    tfNroCliente.setColumns(10);
    
    btnBuscar = new JButton("Buscar");
    btnBuscar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //TODO: Buscar Cliente por c�digo.
        
        /*
          tfNombre.setEnabled(true);
          tfApellido.setEnabled(true);
          boxTipoDoc.setEnabled(true);
          tfNumeroDocumento.setEnabled(true);
          tfDireccion.setEnabled(true);
          tfTelefono.setEnabled(true);
          tfEmail.setEnabled(true);
          btnAceptar.setEnabled(true);
        */
      }
    });
    btnBuscar.setBounds(497, 22, 89, 23);
    contentPane.add(btnBuscar);
    
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
    
    JLabel lblTipoDoc = new JLabel("Tipo de Documento:");
    lblTipoDoc.setBounds(200, 143, 130, 14);
    contentPane.add(lblTipoDoc);
    
    boxTipoDoc = new JComboBox();
    boxTipoDoc.setEnabled(false);
    boxTipoDoc.setModel(new DefaultComboBoxModel(new String[] {"TipoDoc"}));
    boxTipoDoc.setBounds(337, 140, 150, 20);
    contentPane.add(boxTipoDoc);
    
    JLabel lblNumeroDocumento = new JLabel("Numero de Documento");
    lblNumeroDocumento.setBounds(200, 168, 130, 14);
    contentPane.add(lblNumeroDocumento);
    
    tfNumeroDocumento = new JTextField();
    tfNumeroDocumento.setEnabled(false);
    tfNumeroDocumento.setBounds(337, 165, 150, 20);
    contentPane.add(tfNumeroDocumento);
    tfNumeroDocumento.setColumns(10);
    
    JLabel lblDireccion = new JLabel("Direccion:");
    lblDireccion.setBounds(200, 193, 130, 14);
    contentPane.add(lblDireccion);
    
    tfDireccion = new JTextField();
    tfDireccion.setEnabled(false);
    tfDireccion.setBounds(337, 190, 150, 20);
    contentPane.add(tfDireccion);
    tfDireccion.setColumns(10);
    
    JLabel lblTelefono = new JLabel("Telefono:");
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
    
    btnAceptar = new JButton("Aceptar");
    btnAceptar.setEnabled(false);
    btnAceptar.addActionListener(new ActionListener() {
      
      public void actionPerformed(ActionEvent e) {
        
        String codigocliente = tfNroCliente.getText();
        String nombre = tfNombre.getText();
        String apellido = tfApellido.getText();
        String tipodoc = (String)boxTipoDoc.getSelectedItem();
        String documento = tfNumeroDocumento.getText();
        String direccion = tfDireccion.getText();
        String telefono = tfTelefono.getText();
        String email = tfEmail.getText();
        
        
        if (nombre.equals("") || apellido.equals("") || documento.equals("") || direccion.equals("") || telefono.equals("") || email.equals(""))
        {
          JOptionPane.showMessageDialog(contentPane, "Faltan ingresar datos.");
        }
        
        
        
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
