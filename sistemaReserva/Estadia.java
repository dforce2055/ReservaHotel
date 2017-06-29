package sistemaReserva;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Vector;

/**
 * -se supone que la estadia se da de alta al momento de ingresar, asi que la 
 * fecha de ingreso es automatica. habria que cambiarlo? 
 * -tal vez habria que agregar una fecha de salida estimada por si se quiere 
 * liquidar por anticipado o para saber hasta cuando se va a quedar el cliente 
 * ademas de para consultar disponibilidades, y otra fecha salida que es la 
 * fecha en la que realmente se va el cliente
 * -las observaciones a lo mejor deberian ser un vector de strings en vez de un 
 * solo string? 
 * -hice 2 constructores: uno para cuando la estadia viene de una reserva, y 
 * otro cuando es de mostrador 
 **/
public class Estadia
{
  private Reserva reserva;
  private Habitacion habitacion;
  private LocalDate fechaIngreso;
  private LocalDate fechaSalida;
  private double precio;
  private Cliente cliente;
  private String observaciones;
  private Vector<ServicioAdicional> adicionales;
  private int numero;
  private Trabajador trabajador;
  private static int proxNumero;
  
  private static int getProxNumero()
  {
    return ++proxNumero;
  }
  
  public Estadia(Reserva reserva, Habitacion habitacion)
  {
    super();
    this.reserva = reserva;
    this.habitacion = habitacion;
    fechaIngreso = reserva.getFechaIngreso(); 
    fechaSalida = reserva.getFechaSalida();
    precio = reserva.getCostoReserva();
    cliente = reserva.getCliente();
    observaciones = reserva.getObservaciones();
    adicionales = new Vector<ServicioAdicional>();
    trabajador = reserva.getTrabajador();
    numero = getProxNumero();
  }

  public Estadia(Habitacion habitacion, LocalDate fechaIngreso, 
      LocalDate fechaSalida, double precio, Cliente cliente, 
      Trabajador trabajador, String observaciones)
  {
    super();
    reserva = null;
    this.habitacion = habitacion;
    this.fechaIngreso = fechaIngreso;
    this.fechaSalida = fechaSalida;
    this.precio = precio;
    this.cliente = cliente;
    this.observaciones = observaciones;
    adicionales = new Vector<ServicioAdicional>();
  }

  public Reserva getReserva()
  {
    return reserva;
  }

  public void setReserva(Reserva reserva)
  {
    this.reserva = reserva;
  }

  public Habitacion getHabitacion()
  {
    return habitacion;
  }

  public void setHabitacion(Habitacion habitacion)
  {
    this.habitacion = habitacion;
  }

  public LocalDate getFechaIngreso()
  {
    return fechaIngreso;
  }

  public void setFechaIngreso(LocalDate fechaIngreso)
  {
    this.fechaIngreso = fechaIngreso;
  }

  public LocalDate getFechaSalida()
  {
    return fechaSalida;
  }

  public void setFechaSalida(LocalDate fechaSalida)
  {
    this.fechaSalida = fechaSalida;
  }

  public double getPrecio()
  {
    return precio;
  }

  public void setPrecio(double precio)
  {
    this.precio = precio;
  }

  public Cliente getCliente()
  {
    return cliente;
  }

  public void setCliente(Cliente cliente)
  {
    this.cliente = cliente;
  }

  public String getObservaciones()
  {
    return observaciones;
  }

  public void setObservaciones(String observaciones)
  {
    this.observaciones = observaciones;
  }

  public Vector<ServicioAdicional> getAdicionales()
  {
    return adicionales;
  }

  public void setAdicionales(Vector<ServicioAdicional> adicionales)
  {
    this.adicionales = adicionales;
  }

  public int getNumero()
  {
    return numero;
  }

  public void setNumero(int numero)
  {
    this.numero = numero;
  }

  public static void setProxNumero(int proxNumero)
  {
    Estadia.proxNumero = proxNumero;
  }
  
  public Trabajador getTrabajador()
  {
    return trabajador;
  }

  public void setTrabajador(Trabajador trabajador)
  {
    this.trabajador = trabajador;
  }

  //experimental
  public boolean tenesElDia(LocalDate dia)
  {
    if (fechaIngreso.isEqual(dia) || (dia.isAfter(fechaIngreso)) && dia.isBefore(fechaSalida))
      return true;
    else
      return false;
  }
  
  public boolean tuTipoEs(String tipoHabitacion)
  {
    return (habitacion.tuTipoEs(tipoHabitacion));
  }
  
  public String getTipoHabitacion()
  {
    return habitacion.getTipoHabitacion();
  }
  
  public boolean sosEstadia(int num)
  {
    return numero == num;
  }
  
  //devuelve el valor total de la estadia teniendo en cuenta las fechas de entrada y salida
  public double cerrarEstadia()
  {
    long dias = fechaIngreso.until(fechaSalida, ChronoUnit.DAYS);
    return dias * precio;
  }
  
}
