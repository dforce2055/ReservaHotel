package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import sistemaReserva.SistemaReserva;
import sistemaReserva.HabitacionView;
import java.util.Vector;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaAltaHabitacion extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField tfNumero;
  private JButton btnAceptar;
  private JButton btnCancelar;
  private JComboBox<String> boxPiso;
  private JComboBox<String> boxTipoHab;
  private JTextArea tfDescripcion;
  private JTextArea textAreaCaracteristicas;
  

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
  public void altaHabitacion(SistemaReserva sistema)
  {
    String numero = tfNumero.getText();
    String piso = (String)boxPiso.getSelectedItem();
    String tipoHab = (String)boxTipoHab.getSelectedItem();
    String descripcion = tfDescripcion.getText();
    String caracteristicas = textAreaCaracteristicas.getText();
    
    if (numero.equals("") || piso.equals("") || tipoHab.equals(""))
    {
      JOptionPane.showMessageDialog(contentPane, "Faltan ingresar datos.");
    }else
    {
      if(sistema.validarNumeroHabitacion(numero))
      {
        HabitacionView habitacion = sistema.buscarHabitacionView(numero);
        if(habitacion == null)
        {
          if(sistema.altaHabitacion(numero, piso, descripcion, caracteristicas, tipoHab))
          {
            JOptionPane.showMessageDialog(contentPane, "Habitaci\u00f3n creada "
                + "Correctamente."
                +"\nHabitaci\u00f3n: " +numero 
                +"\nPiso: " +piso
                +"\nTipo: " +tipoHab);
            dispose();
          }else
          {
            JOptionPane.showMessageDialog(contentPane, "NO SE PUEDE AGREGAR");
          }
        }else
        {
          JOptionPane.showMessageDialog(contentPane, "NO ES POSIBLE AGREGAR "
              + "HABITACI\u00d3N N\u00daMERO " +numero);
        }
      }
    }
  }
  
  public VentanaAltaHabitacion(SistemaReserva sistema)
  {
    setResizable(false);//Que no lo puedan maximizar
    setTitle("Alta Habitaci\u00f3n");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 700, 480);
    contentPane = new JPanel();
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblNumero = new JLabel("N\u00FAmero:");
    lblNumero.setBounds(200, 27, 130, 14);
    contentPane.add(lblNumero);
    
    tfNumero = new JTextField();
    tfNumero.setBounds(337, 24, 60, 20);
    contentPane.add(tfNumero);
    tfNumero.setColumns(10);
    
    JLabel lblPiso = new JLabel("Piso:");
    lblPiso.setBounds(200, 52, 130, 14);
    contentPane.add(lblPiso);
    
    boxPiso = new JComboBox<String>();
    boxPiso.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5"}));
    boxPiso.setBounds(337, 49, 60, 20);
    contentPane.add(boxPiso);
    
    JLabel lblTipoHab = new JLabel("Tipo:");
    lblTipoHab.setBounds(200, 77, 130, 14);
    contentPane.add(lblTipoHab);
    
    boxTipoHab = new JComboBox<String>();
    //Aggrego las habitaciones activas que se pueden elegir
    Vector<String>tipos = sistema.getTiposHabitacionesActivas();
    for(String tipo:tipos)
      boxTipoHab.addItem(tipo);
    
    boxTipoHab.setBounds(337, 74, 150, 20);
    contentPane.add(boxTipoHab);
    
    JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
    lblDescripcion.setBounds(200, 102, 130, 14);
    contentPane.add(lblDescripcion);
    
    tfDescripcion = new JTextArea();
    tfDescripcion.setLineWrap(true);
    tfDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 11));
    tfDescripcion.setBorder(UIManager.getBorder("TextField.border"));
    tfDescripcion.setBounds(337, 97, 150, 100);
    contentPane.add(tfDescripcion);
    
    btnAceptar = new JButton("Aceptar");
    btnAceptar.addKeyListener(new KeyAdapter()
    {
      @Override
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
          altaHabitacion(sistema);
      }
    });
    btnAceptar.addActionListener(new ActionListener() {
      
      public void actionPerformed(ActionEvent arg0) {
        altaHabitacion(sistema);
      }
    });
    btnAceptar.setBounds(200, 296, 100, 23);
    contentPane.add(btnAceptar);
    
    btnCancelar = new JButton("Cancelar");
    btnCancelar.addKeyListener(new KeyAdapter()
    {
      @Override
      public void keyPressed(KeyEvent e)
      {
        dispose();
      }
    });
    btnCancelar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        dispose();
      }
    });
    btnCancelar.setBounds(387, 296, 100, 23);
    contentPane.add(btnCancelar);
    
    JLabel lblCaracteristicas = new JLabel("Caracteristicas:");
    lblCaracteristicas.setBounds(200, 218, 130, 15);
    contentPane.add(lblCaracteristicas);
    
    textAreaCaracteristicas = new JTextArea();
    textAreaCaracteristicas.setBounds(337, 218, 150, 60);
    textAreaCaracteristicas.setBorder(UIManager.getBorder("TextField.border"));
    contentPane.add(textAreaCaracteristicas);
  }
}
