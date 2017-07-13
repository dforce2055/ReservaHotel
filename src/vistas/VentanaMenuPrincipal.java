package vistas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import sistemaReserva.SistemaReserva;
import sistemaReserva.Trabajador;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMenuPrincipal extends JFrame
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
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
    btnAltaDeCliente.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    	}
    });
    btnAltaDeCliente.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
        {
          VentanaAltaCliente altaCliente = new VentanaAltaCliente(sistema);
          altaCliente.setVisible(true);
        }
      }
    });
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
    btnAltaDeTrabajador.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
        {
          VentanaAltaTrabajador altaTrabajador = new VentanaAltaTrabajador(sistema);
          altaTrabajador.setVisible(true);
        }
      }
    });
    btnAltaDeTrabajador.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0){
        VentanaAltaTrabajador altaTrabajador = new VentanaAltaTrabajador(sistema);
        altaTrabajador.setVisible(true);
      }
    });
    btnAltaDeTrabajador.setBounds(420, 145, 180, 25);
    menuPrincipal.add(btnAltaDeTrabajador);
    
    JButton btnAltaDeHabitacion = new JButton("Alta de Habitaci\u00F3n");
    btnAltaDeHabitacion.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
        {
          VentanaAltaHabitacion altaHabitacion = new VentanaAltaHabitacion(sistema);
          altaHabitacion.setVisible(true);
        }
      }
    });
    btnAltaDeHabitacion.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e){
        VentanaAltaHabitacion altaHabitacion = new VentanaAltaHabitacion(sistema);
        altaHabitacion.setVisible(true);
      }
    });
    btnAltaDeHabitacion.setBounds(420, 182, 180, 25);
    menuPrincipal.add(btnAltaDeHabitacion);
    
    JButton btnAltaDeEstadia = new JButton("Alta de Estad\u00EDa");
    btnAltaDeEstadia.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
        {
          VentanaAltaEstadia altaEstadia = new VentanaAltaEstadia(sistema);
          altaEstadia.setVisible(true);
        }
      }
    });
    btnAltaDeEstadia.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        VentanaAltaEstadia altaEstadia = new VentanaAltaEstadia(sistema);
        altaEstadia.setVisible(true);
      }
    });
    btnAltaDeEstadia.setBounds(420, 286, 180, 25);
    menuPrincipal.add(btnAltaDeEstadia);
    
    JButton btnAltaDeReserva = new JButton("Alta de Reserva");
    btnAltaDeReserva.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
        {
          VentanaAltaReserva altaReserva = new VentanaAltaReserva(sistema);
          altaReserva.setVisible(true);
        }
      }
    });
    btnAltaDeReserva.setEnabled(true);
    btnAltaDeReserva.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        VentanaAltaReserva altaReserva = new VentanaAltaReserva(sistema);
        altaReserva.setVisible(true);
      }
    });
    btnAltaDeReserva.setBounds(420, 322, 180, 25);
    menuPrincipal.add(btnAltaDeReserva);
    
    JButton btnEditarCliente = new JButton("Editar Cliente");
    btnEditarCliente.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
        {
          VentanaEditarCliente editarCliente = new VentanaEditarCliente(sistema);
          editarCliente.setVisible(true);
        }
      }
    });
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
    btnEditarTrabajador.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
        {
          VentanaEditarTrabajador editarTrabajador = new VentanaEditarTrabajador(sistema);
          editarTrabajador.setVisible(true);
        }
      }
    });
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
    
    JButton btnEditarHabitacion = new JButton("Editar Habitaci\u00F3n");
    btnEditarHabitacion.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
        {
          VentanaEditarHabitacion editarHabitacion = new VentanaEditarHabitacion(sistema);
          editarHabitacion.setVisible(true);
        }
      }
    });
    btnEditarHabitacion.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        VentanaEditarHabitacion editarHabitacion = new VentanaEditarHabitacion(sistema);
        editarHabitacion.setVisible(true);
      }
    });
    btnEditarHabitacion.setBounds(612, 182, 180, 25);
    menuPrincipal.add(btnEditarHabitacion);
    
    JButton btnEditarEstadia = new JButton("Editar Estad\u00EDa");
    btnEditarEstadia.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    	}
    });
    btnEditarEstadia.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
        {
          VentanaEditarEstadia editarEstadia = new VentanaEditarEstadia(sistema);
          editarEstadia.setVisible(true);
        }
      }
    });
    btnEditarEstadia.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        VentanaEditarEstadia editarEstadia = new VentanaEditarEstadia(sistema);
        editarEstadia.setVisible(true);
      }
    });
    btnEditarEstadia.setBounds(610, 286, 180, 25);
    menuPrincipal.add(btnEditarEstadia);
    
    JButton btnEditarReserva = new JButton("Editar Reserva");
    btnEditarReserva.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
        {
          VentanaEditarReserva editarReserva = new VentanaEditarReserva(sistema);
          editarReserva.setVisible(true);
        }
      }
    });
    btnEditarReserva.setEnabled(true);
    btnEditarReserva.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        VentanaEditarReserva editarReserva = new VentanaEditarReserva(sistema);
        editarReserva.setVisible(true);
      }
    });
    btnEditarReserva.setBounds(610, 322, 180, 25);
    menuPrincipal.add(btnEditarReserva);
    
    JButton btnNewButton = new JButton("New button");
    btnNewButton.setBounds(612, 218, 180, 23);
    menuPrincipal.add(btnNewButton);
    
    JButton btnAltadeTarifa = new JButton("Alta de Tarifa");
    btnAltadeTarifa.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		VentanaAltaTarifa altaTarifa = new VentanaAltaTarifa(sistema);
    		altaTarifa.setVisible(true);
    	}
    });
    
    btnAltadeTarifa.setBounds(420, 218, 180, 23);
    menuPrincipal.add(btnAltadeTarifa);
    
    JButton btnAltaDeServicioAdicional = new JButton("Alta de Servicio adicional");
    btnAltaDeServicioAdicional.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		VentanaAltaServicioAdicional altaAdicional = new VentanaAltaServicioAdicional(sistema);
    		altaAdicional.setVisible(true);
    	}
    });
    btnAltaDeServicioAdicional.setBounds(420, 252, 180, 23);
    menuPrincipal.add(btnAltaDeServicioAdicional);
    
    JButton btnNewButton_2 = new JButton("New button");
    btnNewButton_2.setBounds(612, 252, 180, 23);
    menuPrincipal.add(btnNewButton_2);
    
    JLabel lblNewLabel = new JLabel("fondo");
    lblNewLabel.setBounds(0, 0, 800, 480);
    lblNewLabel.setIcon(new ImageIcon(VentanaMenuPrincipal.class.getResource("/imagenes/background.jpg")));
    menuPrincipal.add(lblNewLabel);
  }
}
