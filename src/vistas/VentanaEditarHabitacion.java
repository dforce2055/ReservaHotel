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
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import sistemaReserva.SistemaReserva;
public class VentanaEditarHabitacion extends JFrame {

	private JPanel contentPane;
	private JTextField tfNumero;
	private JButton btnAceptar;
	private JButton btnNewButton;
	private JComboBox boxPiso;
	private JComboBox boxTipoHab;
	private JLabel lblNroHabitacion;
	private JTextField tfNroHabitacion;

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
	public VentanaEditarHabitacion(SistemaReserva sistema) {
		
		setTitle("Modificar Habitacion");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 480);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNroHabitacion = new JLabel("Ingrese Nro Habitacion:");
		lblNroHabitacion.setBounds(200, 34, 130, 14);
		contentPane.add(lblNroHabitacion);
		
		tfNroHabitacion = new JTextField();
		tfNroHabitacion.setBounds(337, 31, 60, 20);
		contentPane.add(tfNroHabitacion);
		tfNroHabitacion.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(416, 30, 89, 23);
		contentPane.add(btnBuscar);
		
		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(200, 93, 130, 14);
		contentPane.add(lblNumero);
		
		tfNumero = new JTextField();
		tfNumero.setEnabled(false);
		tfNumero.setBounds(337, 90, 60, 20);
		contentPane.add(tfNumero);
		tfNumero.setColumns(10);
		
		JLabel lblPiso = new JLabel("Piso:");
		lblPiso.setBounds(200, 118, 130, 14);
		contentPane.add(lblPiso);
		
		boxPiso = new JComboBox();
		boxPiso.setEnabled(false);
		boxPiso.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		boxPiso.setBounds(337, 115, 60, 20);
		contentPane.add(boxPiso);
		
		JLabel lblTipoHab = new JLabel("Tipo de Habitacion:");
		lblTipoHab.setBounds(200, 143, 130, 14);
		contentPane.add(lblTipoHab);
		
		boxTipoHab = new JComboBox();
		boxTipoHab.setEnabled(false);
		boxTipoHab.setBounds(337, 140, 150, 20);
		contentPane.add(boxTipoHab);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(200, 168, 130, 14);
		contentPane.add(lblDescripcion);
		
		JTextArea tfDescripcion = new JTextArea();
		tfDescripcion.setEnabled(false);
		tfDescripcion.setLineWrap(true);
		tfDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfDescripcion.setBorder(UIManager.getBorder("TextField.border"));
		tfDescripcion.setBounds(337, 163, 150, 100);
		contentPane.add(tfDescripcion);
		
		btnAceptar = new JButton("Aceptar");
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
		btnAceptar.setBounds(200, 296, 89, 23);
		contentPane.add(btnAceptar);
		
		btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setBounds(398, 296, 89, 23);
		contentPane.add(btnNewButton);
	}
}