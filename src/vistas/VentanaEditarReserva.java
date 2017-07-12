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
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;



import com.toedter.calendar.JDateChooser;

import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JComboBox;


import sistemaReserva.ClienteView;
import sistemaReserva.ReservaView;
import sistemaReserva.SistemaReserva;
import sistemaReserva.TrabajadorView;

import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class VentanaEditarReserva extends JFrame {

  private JPanel contentPane;
  private JTextField tfCodigoCliente;
  private JTextField tfCreador;
  private JButton btnAceptar;
  private JButton btnNewButton;
  private JComboBox<String> boxTipoHab;

  private JTextField tfNroReserva;
  private JTextArea tfObservaciones;
  
  private TrabajadorView trabajadorValidado;
  private TrabajadorView trabajador;
  private ClienteView cliente;
  private ReservaView reserva;
  
  private JDateChooser calendario_1;
  private JDateChooser calendario_2;
  private LocalDate fechaIngreso;
  private LocalDate fechaSalida;
  private Date fechaActual = new Date(System.currentTimeMillis());
  private JTextField textEditor;
  private JTextField fechaIngresoAnterior;
  private JTextField fechaSalidaAnterior;

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
  public void buscar(SistemaReserva sistema)
  {
    String nroReserva = tfNroReserva.getText();
    
    if(sistema.validarNumeroReserva(nroReserva))
    {
      reserva = sistema.buscarReservaView(nroReserva);
      if(reserva != null)
      {
        cliente = reserva.getCliente();
        trabajador = reserva.getTrabajador();
        trabajadorValidado = sistema.getTrabajadorValidado();
        
        tfCodigoCliente.setEnabled(true);
        tfCodigoCliente.setEditable(false);
        tfCodigoCliente.setText(cliente.getApellido() +", " +cliente.getNombre());
        
        tfCreador.setEnabled(true);
        tfCreador.setEditable(false);
        tfCreador.setText(trabajador.getApellido() +", " +trabajador.getNombre());
        
        
        textEditor.setEnabled(true);
        textEditor.setEditable(false);
        textEditor.setText(trabajadorValidado.getApellido() +", " +trabajadorValidado.getNombre());
        
        fechaIngresoAnterior.setEditable(false);
        fechaIngresoAnterior.setEnabled(true);
        fechaIngresoAnterior.setText(reserva.getFechaIngreso().toString());
        
        fechaSalidaAnterior.setEditable(false);
        fechaSalidaAnterior.setEnabled(true);
        fechaSalidaAnterior.setText(reserva.getFechaSalida().toString());
        
        calendario_1.setEnabled(true);
        calendario_1.setDate(fechaActual);
        
        calendario_2.setEnabled(true);
        
        
      }else
      {
        JOptionPane.showMessageDialog(contentPane, "NO SE ENCONTRO UNA RESERVA "
            +"con ese n\u00famero(" +nroReserva +")");
      }
    }
  }
  
  public void editarReserva(SistemaReserva sistema)
  {
    
  }
  
  public VentanaEditarReserva(SistemaReserva sistema)
  {
    setResizable(false);//Que no lo puedan maximizar
    setTitle("Modificar Reserva");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 700, 480);
    contentPane = new JPanel();
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblNroReserva = new JLabel("N\u00FAmero de Reserva:");
    lblNroReserva.setBounds(180, 44, 150, 20);
    contentPane.add(lblNroReserva);
    
    tfNroReserva = new JTextField();
    tfNroReserva.setBounds(337, 44, 150, 20);
    contentPane.add(tfNroReserva);
    tfNroReserva.setColumns(10);
    
    JLabel lblCodigoCliente = new JLabel("Cliente:");
    lblCodigoCliente.setBounds(180, 70, 150, 20);
    contentPane.add(lblCodigoCliente);
    
    tfCodigoCliente = new JTextField();
    tfCodigoCliente.setEditable(false);
    tfCodigoCliente.setBounds(337, 70, 150, 20);
    contentPane.add(tfCodigoCliente);
    tfCodigoCliente.setColumns(10);
    
    JLabel lblCreador = new JLabel("Creada por:");
    lblCreador.setBounds(180, 98, 150, 20);
    contentPane.add(lblCreador);
    
    tfCreador = new JTextField();
    tfCreador.setEditable(false);
    tfCreador.setBounds(337, 98, 150, 20);
    contentPane.add(tfCreador);
    tfCreador.setColumns(10);
    
    JLabel lblTipoHab = new JLabel("Tipo de Habitaci\u00F3n:");
    lblTipoHab.setBounds(180, 165, 150, 14);
    contentPane.add(lblTipoHab);
    
    boxTipoHab = new JComboBox<String>();
    //Agrego habitaciones activas que se pueden elegir
    Vector<String>tipos = sistema.getTiposHabitacionesActivos();
    for(String tipo:tipos)
      boxTipoHab.addItem(tipo);
    
    
    boxTipoHab.setBounds(337, 165, 150, 20);
    contentPane.add(boxTipoHab);
    
    JLabel lblObservaciones = new JLabel("Observaciones:");
    lblObservaciones.setBounds(180, 280, 150, 20);
    contentPane.add(lblObservaciones);
    
    JTextArea tfObservaciones = new JTextArea();
    tfObservaciones.setEnabled(false);
    tfObservaciones.setEditable(false);
    tfObservaciones.setLineWrap(true);
    tfObservaciones.setFont(new Font("Tahoma", Font.PLAIN, 11));
    tfObservaciones.setBorder(UIManager.getBorder("TextField.border"));
    tfObservaciones.setBounds(337, 275, 150, 100);
    contentPane.add(tfObservaciones);
    
    btnAceptar = new JButton("Aceptar");
    btnAceptar.addMouseListener(new MouseAdapter()
    {
      @Override
      public void mouseClicked(MouseEvent e)
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
            
            editarReserva(sistema);
          }
        }catch(Exception E)
        {
          E.printStackTrace();
          JOptionPane.showMessageDialog(contentPane, "ERROR COMPLETE FECHA INGRESO Y SALIDA");
        }
        
      }
    });
    btnAceptar.addKeyListener(new KeyAdapter()
    {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
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
              
              editarReserva(sistema);
            }
          }catch(Exception E)
          {
            E.printStackTrace();
            JOptionPane.showMessageDialog(contentPane, "ERROR COMPLETE FECHA INGRESO Y SALIDA");
          }
          
        }
      }
    });
    btnAceptar.setEnabled(false);
    btnAceptar.setBounds(200, 384, 100, 23);
    contentPane.add(btnAceptar);
    
    btnNewButton = new JButton("Cancelar");
    btnNewButton.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
          dispose();
      }
    });
    btnNewButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0)
      {
        dispose();
      }
    });
    btnNewButton.setBounds(398, 384, 100, 23);
    contentPane.add(btnNewButton);
    
    JLabel lblFechaDeIngreso = new JLabel("Fecha de Ingreso:");
    lblFechaDeIngreso.setBounds(180, 220, 150, 14);
    contentPane.add(lblFechaDeIngreso);
    

    calendario_1 = new JDateChooser();
    calendario_1.setBounds(337, 220, 150, 20);
    calendario_1.setMinSelectableDate(fechaActual);
    calendario_1.setEnabled(false);
    contentPane.add(calendario_1);
    
    JLabel lblFechaDeSalida = new JLabel("Fecha de Salida");
    lblFechaDeSalida.setBounds(180, 250, 150, 14);
    contentPane.add(lblFechaDeSalida);
    
    
    calendario_2 = new JDateChooser();
    calendario_2.setBounds(337, 250, 150, 20);
    calendario_2.setMinSelectableDate(fechaActual);
    calendario_2.setEnabled(false);
    contentPane.add(calendario_2);
    
    JButton btnBuscar = new JButton("Buscar");
    btnBuscar.addMouseListener(new MouseAdapter()
    {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        buscar(sistema);
      }
    });
    btnBuscar.addKeyListener(new KeyAdapter()
    {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
          buscar(sistema);
      }
    });
    btnBuscar.setBounds(497, 43, 100, 23);
    contentPane.add(btnBuscar);
    
    JCheckBox chckbxEliminar = new JCheckBox("Eliminar");
    chckbxEliminar.setBounds(597, 413, 97, 23);
    contentPane.add(chckbxEliminar);
    
    JLabel lblEditor = new JLabel("Editada Por:");
    lblEditor.setBounds(180, 130, 150, 15);
    contentPane.add(lblEditor);
    
    textEditor = new JTextField();
    textEditor.setEditable(false);
    textEditor.setBounds(337, 130, 150, 20);
    contentPane.add(textEditor);
    textEditor.setColumns(10);
    
    fechaIngresoAnterior = new JTextField();
    fechaIngresoAnterior.setEditable(false);
    fechaIngresoAnterior.setBounds(500, 220, 114, 19);
    contentPane.add(fechaIngresoAnterior);
    fechaIngresoAnterior.setColumns(10);
    
    fechaSalidaAnterior = new JTextField();
    fechaSalidaAnterior.setEditable(false);
    fechaSalidaAnterior.setBounds(500, 250, 114, 19);
    contentPane.add(fechaSalidaAnterior);
    fechaSalidaAnterior.setColumns(10);
    
    JLabel lblFechaNueva = new JLabel("Fecha Nueva");
    lblFechaNueva.setBounds(337, 200, 130, 15);
    contentPane.add(lblFechaNueva);
    
    JLabel lblFechaAnterior = new JLabel("Fecha Anterior");
    lblFechaAnterior.setBounds(500, 200, 130, 15);
    contentPane.add(lblFechaAnterior);
  }
}
