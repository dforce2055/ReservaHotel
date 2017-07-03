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

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import sistemaReserva.SistemaReserva;


public class VentanaEditarReserva extends JFrame {

  private JPanel contentPane;
  private JTextField tfCodigoCliente;
  private JTextField tfLegajo;
  private JButton btnAceptar;
  private JButton btnNewButton;
  private JDateChooser calendario_1;

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
  public VentanaEditarReserva(SistemaReserva sistema) {
    setTitle("Modificar Reserva");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 700, 480);
    contentPane = new JPanel();
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblCodigoCliente = new JLabel("C\u00F3digo de Cliente:");
    lblCodigoCliente.setBounds(200, 75, 130, 14);
    contentPane.add(lblCodigoCliente);
    
    tfCodigoCliente = new JTextField();
    tfCodigoCliente.setBounds(337, 72, 150, 20);
    contentPane.add(tfCodigoCliente);
    tfCodigoCliente.setColumns(10);
    
    JLabel lblLegajo = new JLabel("Legajo:");
    lblLegajo.setBounds(200, 100, 130, 14);
    contentPane.add(lblLegajo);
    
    tfLegajo = new JTextField();
    tfLegajo.setBounds(337, 97, 150, 20);
    contentPane.add(tfLegajo);
    tfLegajo.setColumns(10);
    
    JLabel lblTipoHab = new JLabel("Tipo de Habitaci\u00F3n:");
    lblTipoHab.setBounds(200, 125, 130, 14);
    contentPane.add(lblTipoHab);
    
    JComboBox boxTipoHab = new JComboBox();
    boxTipoHab.setModel(new DefaultComboBoxModel(new String[] {"Tipo de Habitacion"}));
    boxTipoHab.setBounds(337, 122, 150, 20);
    contentPane.add(boxTipoHab);
    
    JLabel lblObservaciones = new JLabel("Observaciones:");
    lblObservaciones.setBounds(200, 256, 130, 14);
    contentPane.add(lblObservaciones);
    
    JTextArea tfObservaciones = new JTextArea();
    tfObservaciones.setLineWrap(true);
    tfObservaciones.setFont(new Font("Tahoma", Font.PLAIN, 11));
    tfObservaciones.setBorder(UIManager.getBorder("TextField.border"));
    tfObservaciones.setBounds(337, 251, 150, 100);
    contentPane.add(tfObservaciones);
    
    btnAceptar = new JButton("Aceptar");
    btnAceptar.addActionListener(new ActionListener() {
      
      String codigocliente = tfCodigoCliente.getText();
      String legajo = tfLegajo.getText();
      String tipohab = (String)boxTipoHab.getSelectedItem();
      String observaciones = tfObservaciones.getText();
      
      
      
      public void actionPerformed(ActionEvent arg0) {
        
        if (tfCodigoCliente.equals("") || tfLegajo.equals("") || boxTipoHab.equals("") || observaciones.equals(""))
        {
          JOptionPane.showMessageDialog(contentPane, "Faltan ingresar datos.");
        }
        else
        {
          //TODO: Agregar reserva.
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
    
    JLabel lblFechaDeIngreso = new JLabel("Fecha de Ingreso:");
    lblFechaDeIngreso.setBounds(200, 159, 130, 14);
    contentPane.add(lblFechaDeIngreso);
    
    JDateChooser calendario_1;
    calendario_1 = new JDateChooser();
      calendario_1.setBounds(337, 153, 150, 20);
      contentPane.add(calendario_1);
    
    JLabel lblFechaDeSalida = new JLabel("Fecha de Salida");
    lblFechaDeSalida.setBounds(200, 190, 130, 14);
    contentPane.add(lblFechaDeSalida);
    
    JDateChooser calendario_2;
    calendario_2 = new JDateChooser();
      calendario_2.setBounds(337, 184, 150, 20);
      contentPane.add(calendario_2);
  }
}
