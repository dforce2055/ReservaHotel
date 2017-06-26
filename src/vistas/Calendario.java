package vistas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JCalendar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Calendario extends JFrame
{

  private JPanel contentPane;

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
          Calendario frame = new Calendario();
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
  public Calendario()
  {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JCalendar calendario = new JCalendar();
    calendario.setBounds(12, 12, 237, 159);
    contentPane.add(calendario);
    
    JButton boton = new JButton("fecha");
    boton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0)
      {
        int dia = calendario.getCalendar().get(Calendar.DAY_OF_MONTH);
        int mes = calendario.getCalendar().get(Calendar.MONTH);
        int anio = calendario.getCalendar().get(Calendar.YEAR);
        
        System.out.println("Fecha: " +dia +"/" +mes +"/" +anio);
      }
    });
    boton.setBounds(261, 12, 117, 25);
    contentPane.add(boton);
  }
}
