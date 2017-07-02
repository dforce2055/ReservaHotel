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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import sistemaReserva.SistemaReserva;
import sistemaReserva.Trabajador;

public class VentanaMenuPrincipal extends JFrame
{

  private JPanel menuPrincipal;
  

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
          //MenuPrincipal frame = new MenuPrincipal();
          //frame.setVisible(true);
        }catch(Exception e){
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public VentanaMenuPrincipal(SistemaReserva sistema, Trabajador trabajadorValidado)
  {
    setResizable(false);//Que no lo puedan maximizar
    //Dimension sizePantalla = Toolkit.getDefaultToolkit().getScreenSize();//Calcular el centro de la pantalla
    setTitle("Sistema de Reserva");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(280, 144, 800, 480);
    menuPrincipal = new JPanel();
    menuPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(menuPrincipal);
    menuPrincipal.setLayout(null);
    
    JLabel lblBienvenido = new JLabel("Usuario: " +trabajadorValidado.getNombreUsuario());
    lblBienvenido.setForeground(Color.WHITE);
    lblBienvenido.setFont(new Font("Ubuntu", Font.PLAIN, 20));
    lblBienvenido.setBounds(300, 12, 220, 20);
    menuPrincipal.add(lblBienvenido);
    
    JButton btnAltaDeCliente = new JButton("Alta de Cliente");
    btnAltaDeCliente.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0)
      {
        VentanaAltaCliente altaCliente = new VentanaAltaCliente(sistema);
        altaCliente.setVisible(true);
      }
    });
    btnAltaDeCliente.setBounds(420, 108, 180, 25);
    menuPrincipal.add(btnAltaDeCliente);
    
    JButton btnAltaDeTrabajador = new JButton("Alta de Trabajador");
    btnAltaDeTrabajador.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0){
        VentanaAltaTrabajador altaTrabajador = new VentanaAltaTrabajador(sistema);
        altaTrabajador.setVisible(true);
      }
    });
    btnAltaDeTrabajador.setBounds(420, 145, 180, 25);
    menuPrincipal.add(btnAltaDeTrabajador);
    
    JButton btnAltaDeHabitacin = new JButton("Alta de Habitación");
    btnAltaDeHabitacin.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e){
        VentanaAltaHabitacion altaHabitacion = new VentanaAltaHabitacion(sistema);
        altaHabitacion.setVisible(true);
      }
    });
    btnAltaDeHabitacin.setBounds(420, 182, 180, 25);
    menuPrincipal.add(btnAltaDeHabitacin);
    
    JButton btnAltaDeEstadia = new JButton("Alta de Estadia");
    btnAltaDeEstadia.setEnabled(false);
    btnAltaDeEstadia.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        //VentanaAltaEstadia altaEstadia = new VentanaAltaEstadia(sistema);
        //altaEstadia.setVisible;
      }
    });
    btnAltaDeEstadia.setBounds(420, 219, 180, 25);
    menuPrincipal.add(btnAltaDeEstadia);
    
    JButton btnAltaDeReserva = new JButton("Alta de Reserva");
    btnAltaDeReserva.setEnabled(false);
    btnAltaDeReserva.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        VentanaAltaReserva altaReserva = new VentanaAltaReserva(sistema);
        altaReserva.setVisible(true);
      }
    });
    btnAltaDeReserva.setBounds(420, 256, 180, 25);
    menuPrincipal.add(btnAltaDeReserva);
    
    JButton btnEditarCliente = new JButton("Editar Cliente");
    btnEditarCliente.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        VentanaEditarCliente editarCliente = new VentanaEditarCliente(sistema);
        editarCliente.setVisible(true);
      }
    });
    btnEditarCliente.setBounds(612, 108, 180, 25);
    menuPrincipal.add(btnEditarCliente);
    
    JButton btnEditarTrabajador = new JButton("Editar Trabajador");
    btnEditarTrabajador.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        VentanaEditarTrabajador editarTrabajador = new VentanaEditarTrabajador(sistema);
        editarTrabajador.setVisible(true);
      }
    });
    btnEditarTrabajador.setBounds(612, 145, 180, 25);
    menuPrincipal.add(btnEditarTrabajador);
    
    JButton btnEditarHabitacin = new JButton("Editar Habitación");
    btnEditarHabitacin.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        VentanaEditarHabitacion editarHabitacion = new VentanaEditarHabitacion(sistema);
        editarHabitacion.setVisible(true);
      }
    });
    btnEditarHabitacin.setBounds(612, 182, 180, 25);
    menuPrincipal.add(btnEditarHabitacin);
    
    JButton btnEditarEstadia = new JButton("Editar Estadia");
    btnEditarEstadia.setEnabled(false);
    btnEditarEstadia.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        //VentanaEditarEstadia editarEstadia = new VentanaEditarEstadia(sistema);
        //editarEstadia.setVisible(true);
      }
    });
    btnEditarEstadia.setBounds(612, 219, 180, 25);
    menuPrincipal.add(btnEditarEstadia);
    
    JButton btnEditarReserva = new JButton("Editar Reserva");
    btnEditarReserva.setEnabled(false);
    btnEditarReserva.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        VentanaEditarReserva editarReserva = new VentanaEditarReserva(sistema);
        editarReserva.setVisible(true);
      }
    });
    btnEditarReserva.setBounds(612, 256, 180, 25);
    menuPrincipal.add(btnEditarReserva);
    
    JLabel lblNewLabel = new JLabel("fondo");
    lblNewLabel.setBounds(0, 0, 800, 480);
    lblNewLabel.setIcon(new ImageIcon(VentanaMenuPrincipal.class.getResource("/imagenes/background.jpg")));
    menuPrincipal.add(lblNewLabel);
  }
}
