package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import sistemaReserva.SistemaReserva;
import sistemaReserva.TrabajadorView;
import sistemaReserva.ClienteView;
import sistemaReserva.EstadiaView;
import sistemaReserva.ReservaView;

import javax.swing.JTextPane;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Vector;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaAltaEstadia extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField tfNroReserva;
  private JTextField tfNroCliente;
  private JTextField tfNombreCliente;
  private JTextField tfTipoHabitacion;
  private JTextField tfNombreTrabajador;
  private JTextField tfFechaIngresoConReserva;
  private JTextField tfFechaSalidaConReserva;
  private LocalDate fechaIngreso;
  private LocalDate fechaSalida;
  
  private JButton btnBuscarcliente;
  private JButton btnBuscarReserva;
  private JButton btnAsignar;
  private JComboBox<String> boxTipoHab;
  private JDateChooser dcFechaIngresoSinReserva;
  private JDateChooser dcFechaSalidaSinReserva;
  private JTextPane tpObservaciones;
  
  private EstadiaView estadia;
  private ReservaView reserva;
  private ClienteView cliente;
  private TrabajadorView trabajadorValidado;

  /**
   * Launch the application.
   
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          VentanaAltaEstadia frame = new VentanaAltaEstadia();
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
  
  public void buscarReserva(SistemaReserva sistema)
  {
    String nroReserva = tfNroReserva.getText();
    
    if(sistema.validarNumeroReserva(nroReserva))
    {
      reserva = sistema.buscarReservaView(nroReserva);
      if(reserva != null)
      {
        cliente = reserva.getCliente();
        
        tfTipoHabitacion.setText(reserva.getTipoHabitacion());
        
        tfNroCliente.setText(String.valueOf(cliente.getNumero()));
        tfNombreCliente.setText(cliente.getApellido() +", " +cliente.getNombre());
        
        
        tfTipoHabitacion.setText(reserva.getTipoHabitacion());
        boxTipoHab.setSelectedItem(reserva.getTipoHabitacion());
        
        tfFechaIngresoConReserva.setText(reserva.getFechaIngreso().toString());
        tfFechaSalidaConReserva.setText(reserva.getFechaSalida().toString());
        
        tpObservaciones.setEnabled(true);
        tpObservaciones.setEditable(true);
        
        btnAsignar.setEnabled(true);
        
      }else
      {
        JOptionPane.showMessageDialog(contentPane, "NO SE ENCONTRO UNA RESERVA "
            +"con ese n\u00famero(" +nroReserva +")");
      }
    }else
    {
      JOptionPane.showInternalMessageDialog(contentPane, "INGRESE SOLO N\u00daMEROS");
    }
  }
  
  public void altaEstadiaConReserva(SistemaReserva sistema)
  {
    if(reserva != null)
    {
      Period periodo = Period.between(reserva.getFechaIngreso(), reserva.getFechaSalida());
      Vector<String>habitacionesDisponibles = sistema.getListadoHabitacionesDisponiblesHoyPorTipo(reserva.getTipoHabitacion());
      
      if(habitacionesDisponibles != null)
      {
        String numeroHabitacion = habitacionesDisponibles.elementAt(0);
        int nroEstadia = sistema.altaEstadiaConReserva(reserva.getNumero(), String.valueOf(numeroHabitacion));
        
        if(nroEstadia > 0)
        {
          estadia = sistema.buscarEstadiaView(nroEstadia);
          JOptionPane.showMessageDialog(contentPane, "ESTADIA REALIZADA CORRECTAMENTE.\n" 
              +"N\u00famero de Estadia: " +nroEstadia
              +"\nCliente: " +cliente.getApellido() +", " +cliente.getNombre()
              +"\nFecha de ingreso: "+estadia.getFechaIngreso().toString()
              +"\nFecha de salida: "+estadia.getFechaSalida().toString()
              +"\nPeriodo de " +periodo.getDays()
              +" D\u00eda\\s"
              );
          dispose();
        }else
        {
          JOptionPane.showMessageDialog(contentPane,"NO PUDO REALIZARSE LA RESERVA\n"
              +"N\u00famero de Reserva: " +nroEstadia);
        }
      }
    }
  }
  
  public void altaEstadiaSinReserva(SistemaReserva sistema)
  {
    
  }
  
  public VentanaAltaEstadia(SistemaReserva sistema) {
    setTitle("Alta Estadia");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 700, 550);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblNroReserva = new JLabel("N\u00FAmero de Reseva:");
    lblNroReserva.setBounds(180, 20, 150, 23);
    contentPane.add(lblNroReserva);
    
    tfNroReserva = new JTextField();
    tfNroReserva.setEditable(false);
    tfNroReserva.setBounds(340, 20, 130, 23);
    contentPane.add(tfNroReserva);
    tfNroReserva.setColumns(10);
    
    btnBuscarReserva = new JButton("Buscar");
    btnBuscarReserva.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        //Buscar Reserva
        buscarReserva(sistema);
      }
    });
    btnBuscarReserva.setEnabled(false);
    btnBuscarReserva.setBounds(480, 20, 100, 23);
    contentPane.add(btnBuscarReserva);
    
    JLabel lblTipoHabitacionConReserva = new JLabel("Tipo de habitación:");
    lblTipoHabitacionConReserva.setBounds(180, 60, 150, 23);
    contentPane.add(lblTipoHabitacionConReserva);
    
    JLabel lblNroCliente = new JLabel("N\u00FAmero de Cliente:");
    lblNroCliente.setBounds(180, 125, 150, 23);
    contentPane.add(lblNroCliente);
    
    tfNroCliente = new JTextField();
    tfNroCliente.setEditable(false);
    tfNroCliente.setBounds(340, 125, 130, 23);
    contentPane.add(tfNroCliente);
    tfNroCliente.setColumns(10);
    
    JRadioButton rdbtnConReserva = new JRadioButton("Con Reserva");
    rdbtnConReserva.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0)
      {
        //Habilitar búsqueda de Reserva
        if(rdbtnConReserva.isSelected())
        {
          //Habilitar
          tfNroReserva.setEnabled(true);
          tfNroReserva.setEditable(true);
          
          btnBuscarReserva.setEnabled(true);

          tfTipoHabitacion.setEnabled(true);
          
          tfFechaIngresoConReserva.setEnabled(true);
          tfFechaIngresoConReserva.setEditable(false);
          
          tfFechaSalidaConReserva.setEnabled(true);
          tfFechaSalidaConReserva.setEditable(false);
          
          //Deshabilitar
          tfNroCliente.setEditable(false);
          tfNombreCliente.setEditable(false);
          tfNombreTrabajador.setEditable(false);
          
          boxTipoHab.setEditable(false);
          boxTipoHab.setEnabled(false);
          dcFechaIngresoSinReserva.setEnabled(false);
          dcFechaSalidaSinReserva.setEnabled(false);
          tpObservaciones.setEditable(true);
        }else
        {
        //DESHabilitar
          tfNroReserva.setEnabled(false);
          tfNroReserva.setEditable(false);
          
          btnBuscarReserva.setEnabled(false);

          tfTipoHabitacion.setEnabled(false);
          
          tfFechaIngresoConReserva.setEnabled(false);
          tfFechaIngresoConReserva.setEditable(false);
          
          tfFechaSalidaConReserva.setEnabled(false);
          tfFechaSalidaConReserva.setEditable(false);
          
          //Habilitar
          tfNroCliente.setEditable(true);
          tfNombreCliente.setEditable(true);
          
          btnBuscarcliente.setEnabled(true);
          
          boxTipoHab.setEditable(true);
          dcFechaIngresoSinReserva.setEnabled(true);
          dcFechaSalidaSinReserva.setEnabled(true);
          tpObservaciones.setEditable(true);
        }
      }
    });
    rdbtnConReserva.setBounds(30, 20, 130, 23);
    contentPane.add(rdbtnConReserva);
    
    JRadioButton rdbtnSinReserva = new JRadioButton("Sin Reserva");
    rdbtnSinReserva.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        //Habilitar busqueda de cliente
        if(rdbtnSinReserva.isSelected())
        {
          //DESHABILITAR
          tfNroReserva.setEnabled(false);
          tfNroReserva.setEditable(false);
          
          btnBuscarReserva.setEnabled(false);

          tfTipoHabitacion.setEnabled(false);
          
          tfFechaIngresoConReserva.setEnabled(false);
          tfFechaIngresoConReserva.setEditable(false);
          
          tfFechaSalidaConReserva.setEnabled(false);
          tfFechaSalidaConReserva.setEditable(false);
          
          //HABILITAR
          tfNroCliente.setEditable(true);
          
          btnBuscarcliente.setEnabled(true);
          
          tfNombreCliente.setEditable(false);
          tfNombreTrabajador.setEditable(false);
          
          boxTipoHab.setEditable(true);
          boxTipoHab.setEnabled(true);
          
          dcFechaIngresoSinReserva.setEnabled(true);
          dcFechaSalidaSinReserva.setEnabled(true);
          tpObservaciones.setEditable(true);
        }else
        {
        //DESHabilitar
          tfNroReserva.setEnabled(false);
          tfNroReserva.setEditable(false);
          
          btnBuscarReserva.setEnabled(false);

          tfTipoHabitacion.setEnabled(false);
          
          tfFechaIngresoConReserva.setEnabled(false);
          tfFechaIngresoConReserva.setEditable(false);
          
          tfFechaSalidaConReserva.setEnabled(false);
          tfFechaSalidaConReserva.setEditable(false);
          
          //Habilitar
          tfNroCliente.setEditable(true);
          tfNombreCliente.setEditable(true);
          
          btnBuscarcliente.setEnabled(true);
          
          boxTipoHab.setEditable(true);
          dcFechaIngresoSinReserva.setEnabled(true);
          dcFechaSalidaSinReserva.setEnabled(true);
          tpObservaciones.setEditable(true);
        }
      }
    });
    rdbtnSinReserva.setBounds(30, 125, 130, 22);
    contentPane.add(rdbtnSinReserva);
    
    JLabel lblCliente = new JLabel("Cliente:");
    lblCliente.setBounds(180, 155, 150, 23);
    contentPane.add(lblCliente);
    
    tfNombreCliente = new JTextField();
    tfNombreCliente.setEditable(false);
    tfNombreCliente.setBounds(340, 155, 130, 23);
    contentPane.add(tfNombreCliente);
    tfNombreCliente.setColumns(10);
    
    JLabel lblTipoHabitacion = new JLabel("Tipo de Habitaci\u00F3n:");
    lblTipoHabitacion.setBounds(180, 230, 150, 23);
    contentPane.add(lblTipoHabitacion);
    
    JLabel lblFechaIngreso = new JLabel("Fecha de Ingreso:");
    lblFechaIngreso.setBounds(180, 270, 150, 23);
    contentPane.add(lblFechaIngreso);
    
    JLabel lblFechaSalida = new JLabel("Fecha de Salida:");
    lblFechaSalida.setBounds(180, 310, 150, 14);
    contentPane.add(lblFechaSalida);
    
    JLabel lblObservaciones = new JLabel("Observaciones:");
    lblObservaciones.setBounds(180, 353, 150, 23);
    contentPane.add(lblObservaciones);
    
    dcFechaIngresoSinReserva = new JDateChooser();
    dcFechaIngresoSinReserva.setEnabled(false);
    dcFechaIngresoSinReserva.setBounds(340, 270, 130, 23);
    contentPane.add(dcFechaIngresoSinReserva);
    
    dcFechaSalidaSinReserva = new JDateChooser();
    dcFechaSalidaSinReserva.setEnabled(false);
    dcFechaSalidaSinReserva.setBounds(340, 310, 130, 23);
    contentPane.add(dcFechaSalidaSinReserva);
    
    tpObservaciones = new JTextPane();
    tpObservaciones.setEnabled(false);
    tpObservaciones.setBounds(340, 346, 294, 66);
    contentPane.add(tpObservaciones);
    
    btnAsignar = new JButton("Asignar");
    btnAsignar.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0)
      {
        try
        {
          if(rdbtnConReserva.isSelected())
          {
            altaEstadiaConReserva(sistema);
          }else
          {
            if(dcFechaIngresoSinReserva != null)
            {
              int dia = dcFechaIngresoSinReserva.getCalendar().get(Calendar.DAY_OF_MONTH);
              int mes = dcFechaIngresoSinReserva.getCalendar().get(Calendar.MONTH)+1;
              int anio = dcFechaIngresoSinReserva.getCalendar().get(Calendar.YEAR);
              
              fechaIngreso = LocalDate.of(anio, mes, dia);
            }else
            {
              
            }
            
            if(dcFechaSalidaSinReserva != null)
            {
              int dia = dcFechaSalidaSinReserva.getCalendar().get(Calendar.DAY_OF_MONTH);
              int mes = dcFechaSalidaSinReserva.getCalendar().get(Calendar.MONTH)+1;
              int anio = dcFechaSalidaSinReserva.getCalendar().get(Calendar.YEAR);
              
              fechaSalida = LocalDate.of(anio, mes, dia);
            }else
            {
            }
            
            altaEstadiaSinReserva(sistema);
          }
        }catch(Exception e)
        {
          e.printStackTrace();
          JOptionPane.showMessageDialog(contentPane, "ERROR COMPLETE FECHA INGRESO Y SALIDA");
        }
        
        
      }
    });
    btnAsignar.setEnabled(false);
    btnAsignar.setBounds(180, 450, 100, 23);
    contentPane.add(btnAsignar);
    
    JButton btnCancelar = new JButton("Cancelar");
    btnCancelar.addKeyListener(new KeyAdapter()
    {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
          dispose();
      }
    });
    btnCancelar.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0)
      {
        dispose();
      }
    });
    btnCancelar.setBounds(480, 450, 100, 23);
    contentPane.add(btnCancelar);
    
    boxTipoHab = new JComboBox<String>();
    //Agrego habitaciones activas que se pueden elegir
    Vector<String>tipos = sistema.getTiposHabitacionesActivos();
    for(String tipo:tipos)
      boxTipoHab.addItem(tipo);
    
    boxTipoHab.setEnabled(true);
    boxTipoHab.setBounds(340, 230, 130, 23);
    contentPane.add(boxTipoHab);
    
    JSeparator separator = new JSeparator();
    separator.setBounds(0, 0, 1, 2);
    contentPane.add(separator);
    
    tfTipoHabitacion = new JTextField();
    tfTipoHabitacion.setEditable(false);
    tfTipoHabitacion.setBounds(340, 60, 130, 23);
    contentPane.add(tfTipoHabitacion);
    tfTipoHabitacion.setColumns(10);
    
    btnBuscarcliente = new JButton("Buscar");
    btnBuscarcliente.addMouseListener(new MouseAdapter()
    {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        //BUscar Cliente
      }
    });
    btnBuscarcliente.setEnabled(false);
    btnBuscarcliente.setBounds(480, 125, 100, 23);
    contentPane.add(btnBuscarcliente);
    
    JLabel lblTrabajador = new JLabel("Trabajador:");
    lblTrabajador.setBounds(180, 190, 150, 23);
    contentPane.add(lblTrabajador);
    
    trabajadorValidado = sistema.getTrabajadorValidado();
    tfNombreTrabajador = new JTextField();
    tfNombreTrabajador.setText(String.valueOf(trabajadorValidado.getNombre() + ", " +trabajadorValidado.getApellido()));
    tfNombreTrabajador.setEditable(false);
    tfNombreTrabajador.setColumns(10);
    tfNombreTrabajador.setBounds(340, 190, 130, 23);
    contentPane.add(tfNombreTrabajador);
    
    JLabel lblFechasreserva = new JLabel("Fechas de la Reserva");
    lblFechasreserva.setBounds(480, 230, 150, 23);
    contentPane.add(lblFechasreserva);
    
    tfFechaIngresoConReserva = new JTextField();
    tfFechaIngresoConReserva.setEnabled(false);
    tfFechaIngresoConReserva.setEditable(false);
    tfFechaIngresoConReserva.setBounds(480, 270, 130, 23);
    contentPane.add(tfFechaIngresoConReserva);
    tfFechaIngresoConReserva.setColumns(10);
    
    tfFechaSalidaConReserva = new JTextField();
    tfFechaSalidaConReserva.setColumns(10);
    tfFechaSalidaConReserva.setEnabled(false);
    tfFechaSalidaConReserva.setEditable(false);
    tfFechaSalidaConReserva.setBounds(480, 311, 130, 23);
    contentPane.add(tfFechaSalidaConReserva);
  }
}
