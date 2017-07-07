package vistas;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Window.Type;

import sistemaReserva.SistemaReserva;
import sistemaReserva.Trabajador;


public class VentanaLogin extends JFrame
{

  private JPanel menuLogin;
  private JTextField txtUsuario;
  private JPasswordField passwordField;
  private SistemaReserva sistema = new SistemaReserva();
  

  /**
   * Launch the application.
   */
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try{
          VentanaLogin frame = new VentanaLogin();
          frame.setVisible(true);
        }catch(Exception e){
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public VentanaLogin()
  {
    setAlwaysOnTop(true);
    setResizable(false);//Que no lo puedan maximizar
    setTitle("Sistema de Reserva");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //Posición en pantalla
    Dimension sizePantalla = Toolkit.getDefaultToolkit().getScreenSize();
    int centroALoAncho = (int)(sizePantalla.getWidth() - 800)/2;
    int centroALoAlto = (int)(sizePantalla.getHeight() - 480)/2;
    setBounds(centroALoAncho, centroALoAlto, 800, 480);
    
    menuLogin = new JPanel();
    menuLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(menuLogin);
    menuLogin.setLayout(null);
    
    
    JLabel lblBienvenido = new JLabel("Bienvenido");
    lblBienvenido.setBounds(500, 108, 120, 20);
    lblBienvenido.setForeground(Color.WHITE);
    lblBienvenido.setFont(new Font("Ubuntu", Font.PLAIN, 20));
    menuLogin.add(lblBienvenido);
    
    JLabel lblPorFavorInicie = new JLabel("Por favor inicie sesi\u00f3n");
    lblPorFavorInicie.setBounds(500, 140, 180, 15);
    lblPorFavorInicie.setForeground(Color.WHITE);
    menuLogin.add(lblPorFavorInicie);
    
    
    txtUsuario = new JTextField();
    txtUsuario.setBounds(500, 175, 140, 19);
    txtUsuario.setFont(new Font("Ubuntu", Font.PLAIN, 12));
    txtUsuario.setText("Usuario");
    menuLogin.add(txtUsuario);
    txtUsuario.setColumns(10);
    txtUsuario.selectAll();
    txtUsuario.requestFocus();
    
    passwordField = new JPasswordField();
    passwordField.setBounds(500, 206, 140, 19);
    passwordField.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent arg0)
      {
        passwordField.setText("");
      }
    });
    passwordField.setText("password");
    menuLogin.add(passwordField);
    
    JButton btnIniciarSesion = new JButton("Iniciar Sesi\u00F3n");
    btnIniciarSesion.setBounds(500, 237, 140, 25);
    btnIniciarSesion.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0)
      {
        String nombreUsuario = txtUsuario.getText();
        String password = new String(passwordField.getPassword());
       
        if(sistema.loginTrabajador(nombreUsuario, password) == true)
        {
          Trabajador trabajador = sistema.buscarTrabajadorPorUsuario(nombreUsuario);
          //JOptionPane.showMessageDialog(menuLogin, "Bienvenido " + nombreUsuario + ".");
          VentanaMenuPrincipal menuPrincipal = new VentanaMenuPrincipal(sistema, trabajador);
          menuPrincipal.setVisible(true);
          dispose();
        }
        else{
          JOptionPane.showMessageDialog(menuLogin, "Combinaci\u00f3n de usuario "
                + nombreUsuario.toUpperCase() + " y contrase\u00f1a INCORRECTOS");
        }
      }
    });
    btnIniciarSesion.setFont(new Font("Ubuntu", Font.BOLD, 12));
    menuLogin.add(btnIniciarSesion);
    
    JLabel lblNewLabel = new JLabel("fondo");
    lblNewLabel.setBounds(0, 0, 800, 451);
    lblNewLabel.setIcon(new ImageIcon(VentanaLogin.class.getResource("/imagenes/background.jpg")));
    menuLogin.add(lblNewLabel);

  }
}
