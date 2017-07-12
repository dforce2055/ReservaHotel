package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import sistemaReserva.SistemaReserva;
import sistemaReserva.HabitacionView;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class VentanaEditarHabitacion extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField tfNumero;
  private JButton btnAceptar;
  private JButton btnNewButton;
  private JComboBox<String> boxPiso;
  private JComboBox<String> boxTipoHab;
  private JLabel lblNroHabitacion;
  private JTextField tfNroHabitacion;
  private JTextArea tfDescripcion;
  private JTextArea textAreaCaracte;

  /**
   * Launch the application.
   
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          //AltaHabitacion frame = new AltaHabitacion();
          //frame.setVisible(true);
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
    String numeroHabitacion = tfNroHabitacion.getText();

    if(sistema.validarNumeroHabitacion(numeroHabitacion))
    {
      HabitacionView habitacion = sistema.buscarHabitacionView(numeroHabitacion);
      
      if(habitacion != null)
      {
        tfNumero.setEnabled(true);
        tfNumero.setText(habitacion.getNumero());
        
        boxPiso.setEnabled(true);
        boxPiso.setSelectedItem(habitacion.getPiso());
        
        boxTipoHab.setEnabled(true);
        boxTipoHab.setSelectedItem(habitacion.getTipo());
        
        tfDescripcion.setEnabled(true);
        tfDescripcion.setText(habitacion.getDescripcion());
        
        textAreaCaracte.setEnabled(true);
        textAreaCaracte.setText(habitacion.getCaracteristicas());
        
        btnAceptar.setEnabled(true);
      }else
      {
        JOptionPane.showInternalMessageDialog(contentPane, "NO EXISTE LA "
            + "HABITACI\u00d3N N\u00daMERO " + numeroHabitacion);
      }
    }else
    {
      JOptionPane.showInternalMessageDialog(contentPane, "INGRESE SOLO N\u00daMEROS");
    }
  }
  
  public void guardar(SistemaReserva sistema)
  {
    String numeroHabitacion = tfNumero.getText();
    String piso = (String)boxPiso.getSelectedItem();
    String descripcion = tfDescripcion.getText();
    String caracteristicas = textAreaCaracte.getText();
    String tipo = (String)boxTipoHab.getSelectedItem();
    
    if(sistema.validarNumeroHabitacion(numeroHabitacion))
    {
      HabitacionView habitacion = sistema.buscarHabitacionView(numeroHabitacion);
      if(habitacion != null)
      {
        if(sistema.modificarHabitacion(numeroHabitacion, piso, descripcion, caracteristicas, tipo))
        {
          JOptionPane.showMessageDialog(contentPane, "Habitaci\u00f3n modificada "
              + "Correctamente."
              +"\nN\u00famero de Habitaci\u00f3n: " +numeroHabitacion 
              +"\nPiso: " +piso
              +"\nTipo: " +tipo);
          dispose();
        }
      }else
      {
        JOptionPane.showMessageDialog(contentPane, "NO SE PUEDE MODIFICAR, "
            + "NO EXISTE HABITACI\u00d3N N\u00daMERO: " +numeroHabitacion);
        tfNumero.setForeground(Color.RED);
        tfNumero.selectAll();
        tfNumero.requestFocus();
      }
    }else
    {
      JOptionPane.showInternalMessageDialog(contentPane, "INGRESE SOLO N\u00FAMEROS");
    }
  }
  
  public VentanaEditarHabitacion(SistemaReserva sistema)
  {
    setResizable(false);//Que no lo puedan maximizar
    setTitle("Modificar Habitacion");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 700, 480);
    contentPane = new JPanel();
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    lblNroHabitacion = new JLabel("<html>Ingrese N\u00famero<br>Habitación:</html>", SwingConstants.LEFT);
    lblNroHabitacion.setBounds(205, 30, 130, 30);
    contentPane.add(lblNroHabitacion);
    
    tfNroHabitacion = new JTextField();
    tfNroHabitacion.setBounds(337, 31, 60, 20);
    contentPane.add(tfNroHabitacion);
    tfNroHabitacion.setColumns(10);
    
    JButton btnBuscar = new JButton("Buscar");
    btnBuscar.addMouseListener(new MouseAdapter()
    {
      @Override
      public void mouseClicked(MouseEvent arg0)
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
    btnBuscar.setBounds(416, 30, 89, 23);
    contentPane.add(btnBuscar);
    
    JLabel lblNumero = new JLabel("N\u00FAmero:");
    lblNumero.setBounds(205, 92, 130, 14);
    contentPane.add(lblNumero);
    
    tfNumero = new JTextField();
    tfNumero.setEnabled(false);
    tfNumero.setBounds(337, 90, 60, 20);
    contentPane.add(tfNumero);
    tfNumero.setColumns(10);
    
    JLabel lblPiso = new JLabel("Piso:");
    lblPiso.setBounds(205, 118, 130, 14);
    contentPane.add(lblPiso);
    
    boxPiso = new JComboBox<String>();
    boxPiso.setEnabled(false);
    boxPiso.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5"}));
    boxPiso.setBounds(337, 115, 60, 20);
    contentPane.add(boxPiso);
    
    JLabel lblTipoHab = new JLabel("Tipo:");
    lblTipoHab.setBounds(205, 143, 130, 14);
    contentPane.add(lblTipoHab);
    
    boxTipoHab = new JComboBox<String>();
    //Aggrego las habitaciones activas que se pueden elegir
    for(String tipo:sistema.getTiposHabitacionesActivos())
      boxTipoHab.addItem(tipo);
    
    boxTipoHab.setEnabled(false);
    boxTipoHab.setBounds(337, 140, 150, 20);
    contentPane.add(boxTipoHab);
    
    JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
    lblDescripcion.setBounds(205, 164, 130, 14);
    contentPane.add(lblDescripcion);
    
    tfDescripcion = new JTextArea();
    tfDescripcion.setEnabled(false);
    tfDescripcion.setLineWrap(true);
    tfDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 11));
    tfDescripcion.setBorder(UIManager.getBorder("TextField.border"));
    tfDescripcion.setBounds(337, 163, 150, 100);
    contentPane.add(tfDescripcion);
    
    btnAceptar = new JButton("Aceptar");
    btnAceptar.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        guardar(sistema);
      }
    });
    btnAceptar.addKeyListener(new KeyAdapter()
    {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
          guardar(sistema);
      }
    });
    btnAceptar.setEnabled(false);
    btnAceptar.addActionListener(new ActionListener() {
      
      public void actionPerformed(ActionEvent arg0) {
        
        String numero = tfNumero.getText();
        String piso = (String)boxPiso.getSelectedItem();
        String tipohab = (String)boxTipoHab.getSelectedItem();
        String descripcion = tfDescripcion.getText();
        
        if (numero.equals("") || piso.equals("") || tipohab.equals("") || descripcion.equals(""))
        {
          JOptionPane.showMessageDialog(contentPane, "Faltan ingresar datos.");
        }

        
      }
    });
    btnAceptar.setBounds(200, 389, 100, 23);
    contentPane.add(btnAceptar);
    
    btnNewButton = new JButton("Cancelar");
    btnNewButton.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e)
      {
        dispose();
      }
    });
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        dispose();
      }
    });
    btnNewButton.setBounds(398, 389, 100, 23);
    contentPane.add(btnNewButton);
    
    textAreaCaracte = new JTextArea();
    textAreaCaracte.setBounds(337, 277, 150, 60);
    textAreaCaracte.setBorder(UIManager.getBorder("TextField.border"));
    contentPane.add(textAreaCaracte);
    
    JLabel lblCaracter = new JLabel("Características:");
    lblCaracter.setBounds(205, 277, 130, 15);
    contentPane.add(lblCaracter);
  }
}
