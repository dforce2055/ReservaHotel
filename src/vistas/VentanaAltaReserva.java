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
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import sistemaReserva.ClienteView;
import sistemaReserva.SistemaReserva;
import sistemaReserva.TrabajadorView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaAltaReserva extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField tfCodigoCliente;
  private JTextField textFieldCliente;
  private JTextField tfTrabajador;
  private JButton btnAceptar;
  private JButton btnNewButton;
  private JDateChooser calendario_1;
  private JDateChooser calendario_2;
  private TrabajadorView trabajadorValidado;
  private ClienteView cliente;
  private JComboBox<String> boxTipoHab;
  private LocalDate fechaIngreso;
  private LocalDate fechaSalida;
  private JTextArea tfObservaciones;
  private Date fechaActual = new Date(System.currentTimeMillis());


  /**
   * Launch the application.
   */
  
  /*
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          AltaReserva frame = new AltaReserva();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  } */

  /**
   * Create the frame.
   */
  public void buscarCliente(SistemaReserva sistema)
  {
    String codigoCliente = tfCodigoCliente.getText();
    
    if(sistema.validarNumeroCliente(codigoCliente))
    {
      cliente = sistema.buscarClienteViewPorCodigo(Integer.parseInt(codigoCliente));
      if(cliente != null)
      {
        textFieldCliente.setEnabled(true);
        textFieldCliente.setEditable(false);
        textFieldCliente.setText(cliente.getApellido() +", " +cliente.getNombre());
      }
    }
    
  }
  
  public void altaEstadia(SistemaReserva sistema)
  {
    String tipoHabitacion = (String)boxTipoHab.getSelectedItem();
    String codigocliente = tfCodigoCliente.getText();
    int legajo = trabajadorValidado.getLegajo();
    String observaciones = tfObservaciones.getText();
    
    if(cliente != null)
    {
      if(tfCodigoCliente.equals("") || tfTrabajador.equals("") || boxTipoHab.equals("") || observaciones.equals(""))
      {
        JOptionPane.showMessageDialog(contentPane, "Faltan ingresar datos.");
      }
      else
      {
        int nroReserva = sistema.altaReserva(Integer.parseInt(codigocliente), legajo, tipoHabitacion, 
            fechaIngreso, fechaSalida, observaciones);
        if(nroReserva > 0)
        {
          JOptionPane.showMessageDialog(contentPane, "RESERVA REALIZADA CORRECTAMENTE.\n" 
              +"N\u00famero de Reserva: " +nroReserva
              +"\nCliente: " +cliente.getApellido() +", " +cliente.getNombre()
              +"\nFecha de ingreso: "+fechaIngreso
              +"\nFecha de salida: "+fechaSalida);
        }
      }
    }else
    {
      JOptionPane.showMessageDialog(contentPane, "Faltan ingresar el Cliente.");
    }
    
      
      

  }
  
  public VentanaAltaReserva(SistemaReserva sistema)
  {
    setResizable(false);//Que no lo puedan maximizar
    setTitle("Alta Reserva");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 700, 480);
    contentPane = new JPanel();
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblCodigoCliente = new JLabel("C\u00F3digo de Cliente:");
    lblCodigoCliente.setBounds(200, 39, 130, 14);
    contentPane.add(lblCodigoCliente);
    
    tfCodigoCliente = new JTextField();
    tfCodigoCliente.setBounds(337, 36, 150, 20);
    contentPane.add(tfCodigoCliente);
    tfCodigoCliente.setColumns(10);
    
    JLabel lbTrabajador = new JLabel("Trabajador:");
    lbTrabajador.setBounds(200, 100, 130, 14);
    contentPane.add(lbTrabajador);
    
    tfTrabajador = new JTextField();
    tfTrabajador.setBounds(337, 97, 150, 20);
    trabajadorValidado = sistema.getTrabajadorValidado();
    tfTrabajador.setText(String.valueOf(trabajadorValidado.getNombre() + ", " +trabajadorValidado.getApellido()));
    contentPane.add(tfTrabajador);
    tfTrabajador.setEditable(false);
    tfTrabajador.setColumns(10);
    
    JLabel lblTipoHab = new JLabel("Tipo de Habitaci\u00F3n:");
    lblTipoHab.setBounds(200, 125, 130, 14);
    contentPane.add(lblTipoHab);
    
    boxTipoHab = new JComboBox<String>();
    //Agrego habitaciones activas que se pueden elegir
    Vector<String>tipos = sistema.getTiposHabitacionesActivas();
    for(String tipo:tipos)
      boxTipoHab.addItem(tipo);
    
    boxTipoHab.setBounds(337, 122, 150, 20);
    contentPane.add(boxTipoHab);
    
    JLabel lblObservaciones = new JLabel("Observaciones:");
    lblObservaciones.setBounds(200, 256, 130, 14);
    contentPane.add(lblObservaciones);
    
    tfObservaciones = new JTextArea();
    tfObservaciones.setLineWrap(true);
    tfObservaciones.setFont(new Font("Tahoma", Font.PLAIN, 11));
    tfObservaciones.setBorder(UIManager.getBorder("TextField.border"));
    tfObservaciones.setBounds(337, 251, 150, 100);
    contentPane.add(tfObservaciones);
    
    JLabel lblFechaDeIngreso = new JLabel("Fecha de Ingreso:");
    lblFechaDeIngreso.setBounds(200, 159, 130, 14);
    contentPane.add(lblFechaDeIngreso);
    
    calendario_1 = new JDateChooser();
    calendario_1.setBounds(337, 153, 150, 20);
    calendario_1.setMinSelectableDate(fechaActual);
    contentPane.add(calendario_1);
  
    JLabel lblFechaDeSalida = new JLabel("Fecha de Salida");
    lblFechaDeSalida.setBounds(200, 190, 130, 14);
    contentPane.add(lblFechaDeSalida);
  
  
    calendario_2 = new JDateChooser();
    calendario_2.setBounds(337, 184, 150, 20);
    calendario_2.setMinSelectableDate(fechaActual);
    contentPane.add(calendario_2);
    
    btnAceptar = new JButton("Aceptar");
    btnAceptar.addKeyListener(new KeyAdapter()
    {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
          altaEstadia(sistema);
      }
    });
    btnAceptar.addMouseListener(new MouseAdapter()
    {
      @Override
      public void mouseClicked(MouseEvent arg0)
      {
        try
        {
          if(calendario_1 != null && calendario_2 != null)
          {
            int dia = calendario_1.getCalendar().get(Calendar.DAY_OF_MONTH);
            int mes = calendario_1.getCalendar().get(Calendar.MONTH)+1;
            int anio = calendario_1.getCalendar().get(Calendar.YEAR);
            
            fechaIngreso = LocalDate.of(anio, mes, dia);
            
            dia = calendario_2.getCalendar().get(Calendar.DAY_OF_MONTH);
            mes = calendario_2.getCalendar().get(Calendar.MONTH)+1;
            anio = calendario_2.getCalendar().get(Calendar.YEAR);
            
            fechaSalida = LocalDate.of(anio, mes, dia);
            
            altaEstadia(sistema);
          }
        }catch(Exception e)
        {
          e.printStackTrace();
          JOptionPane.showMessageDialog(contentPane, "ERROR COMPLETE FECHA INGRESO Y SALIDA");
        }
        
      }
    });
    btnAceptar.setBounds(200, 384, 89, 23);
    contentPane.add(btnAceptar);
    
    btnNewButton = new JButton("Cancelar");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        dispose();
      }
    });
    btnNewButton.setBounds(398, 384, 89, 23);
    contentPane.add(btnNewButton);
    
  
      JButton btnBuscar = new JButton("Buscar");
      btnBuscar.addKeyListener(new KeyAdapter()
      {
        @Override
        public void keyPressed(KeyEvent e)
        {
          if (e.getKeyCode()==KeyEvent.VK_ENTER)
            buscarCliente(sistema);
        }
      });
      btnBuscar.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent arg0)
        {
          buscarCliente(sistema);
        }
      });
      btnBuscar.setBounds(497, 35, 91, 23);
      contentPane.add(btnBuscar);
      
      JLabel lblCliente = new JLabel("Cliente:");
      lblCliente.setBounds(200, 70, 130, 14);
      contentPane.add(lblCliente);
      
      textFieldCliente = new JTextField();
      textFieldCliente.setBounds(337, 67, 150, 20);
      textFieldCliente.setEnabled(false);
      contentPane.add(textFieldCliente);
      textFieldCliente.setColumns(10);
      
      
  }
}
