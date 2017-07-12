package sistemaReserva;
//import java.time.LocalDate;

import java.time.LocalDate;

public class test
{


  public static void main(String[] args)
  {
    SistemaReserva sis = new SistemaReserva();
    String numeroHabitacion = "100";
    /*
    for(String habitacion:sis.getTiposHabitacionesActivas())
      System.out.println(habitacion);
    
    HabitacionView habitacion = sis.buscarHabitacionView(numeroHabitacion);
    System.out.println(sis.validarNumeroHabitacion(numeroHabitacion));
    System.out.println(habitacion != null);
    
    System.out.println(habitacion.getNumero() +" " +habitacion.getTipo());
    */
    int nroReserva = sis.altaReserva(5, 1, "Doble", LocalDate.of(2017, 8, 01), LocalDate.of(2017, 8, 10), "lalala");
    System.out.println(nroReserva);
    nroReserva = sis.altaReserva(1, 1, "individual", LocalDate.of(2017, 8, 01), LocalDate.of(2017, 8, 10), "lalala");
    System.out.println(nroReserva);
    
    /*
    sis.altaTarifa("Doble", 1000f);
    sis.altaTarifa("Triple", 1250f);
    sis.altaHabitacion("100", "1", "Habitacion 100", "lol", "Doble");
    sis.altaHabitacion("101", "1", "Habitacion 101", "lol", "Doble");
    sis.altaHabitacion("102", "1", "Habitacion 102", "lol", "Triple");
    
    sis.altaTrabajador("Jose", "Argento", "DNI", "12345", "Calle oak 666", "124563", "adasd@a.com", "pepe", "racing");
    sis.altaCliente("Moni", "Argento", "DNI", "13345", "Calle oak 666", "124563", "adasd@a.com");
    
    System.out.println("Login (false): " + sis.loginTrabajador("pepe", "racings"));
    System.out.println("Login (true): " + sis.loginTrabajador("pepe", "racing"));
    
    sis.consultarDisponiblesPorPeriodo(LocalDate.parse("2017-01-01"), LocalDate.parse("2017-01-10"));
    
    sis.altaReserva(1, 1, "Doble",LocalDate.parse("2017-01-01"), LocalDate.parse("2017-01-06"), "primera reserva");
    sis.altaEstadiaConReserva(1, "100");
    sis.consultarDisponiblesPorPeriodo(LocalDate.parse("2017-01-01"), LocalDate.parse("2017-01-10"));
    
    double total = sis.cerrarEstadiaAnticipada(1, LocalDate.parse("2017-01-03"));
    System.out.println(total);
    sis.consultarDisponiblesPorPeriodo(LocalDate.parse("2017-01-01"), LocalDate.parse("2017-01-10"));
    
    sis.altaEstadiaSinReserva("101", LocalDate.parse("2017-01-01"), LocalDate.parse("2017-01-06"), 1, 1, "asd");
    sis.consultarDisponiblesPorPeriodo(LocalDate.parse("2017-01-01"), LocalDate.parse("2017-01-10"));
    
    System.out.println("...");
    sis.altaReserva(1, 1, "Doble", LocalDate.parse("2017-01-06"), LocalDate.parse("2017-01-10"), "segunda reserva");
    sis.consultarDisponiblesPorPeriodo(LocalDate.parse("2017-01-01"), LocalDate.parse("2017-01-10"));
    
    sis.altaReserva(1, 1, "Doble", LocalDate.parse("2017-01-01"), LocalDate.parse("2017-01-09"), "segunda reserva");
    sis.consultarDisponiblesPorPeriodo(LocalDate.parse("2017-01-09"), LocalDate.parse("2017-01-10"));
    
    sis.altaReserva(1, 1, "Doble", LocalDate.parse("2017-01-09"), LocalDate.parse("2017-01-10"), "segunda reserva");
    sis.consultarDisponiblesPorPeriodo(LocalDate.parse("2017-01-09"), LocalDate.parse("2017-01-10"));
   
    CargaDatosPrueba prueba = new CargaDatosPrueba(sis);
    prueba.CargarDatos();
    System.out.println(sis.calcularDisponibilidadPorTipo("Triple", LocalDate.of(2017, 06, 29), LocalDate.of(2017, 06, 29)));
     */
  }

}
