package sistemaReserva;
import java.time.LocalDate;

public class Reserva 
{
  private int numeroReserva;
  private Cliente cliente;
  private Trabajador trabajador;
  private String tipoHabitacion;
  private LocalDate fechaIngreso;
  private LocalDate fechaSalida;
  private LocalDate fechaReserva;
  private double precio;
  private String observaciones;
  private String estado;//ACTIVA | CANCELADA | ARRIBADA
  private static int proximoNumero;
  
  private static int getProximoNumero()
  {
    return ++proximoNumero;
  }
  
  public Reserva(Cliente cliente, Trabajador trabajador, String tipoHabitacion, 
      LocalDate fechaIngreso, LocalDate fechaSalida, double precio, 
      String observaciones)
  {
    super();
    this.numeroReserva = getProximoNumero();
    this.cliente = cliente;
    this.trabajador = trabajador;
    this.tipoHabitacion = tipoHabitacion;
    this.fechaIngreso = fechaIngreso;
    this.fechaSalida = fechaSalida;
    this.precio = precio;
    this.observaciones = observaciones;
    this.estado = "ACTIVA";
    fechaReserva = LocalDate.now();
  }

  public int getNumeroReserva()
  {
    return numeroReserva;
  }

  public void setNumeroReserva(int numeroReserva)
  {
    this.numeroReserva = numeroReserva;
  }

  public Cliente getCliente()
  {
    return cliente;
  }

  public void setCliente(Cliente cliente)
  {
    this.cliente = cliente;
  }

  public Trabajador getTrabajador()
  {
    return trabajador;
  }

  public void setTrabajador(Trabajador trabajador)
  {
    this.trabajador = trabajador;
  }

  public String getTipoHabitacion()
  {
    return tipoHabitacion;
  }

  public void setTipoHabitacion(String tipoHabitacion)
  {
    this.tipoHabitacion = tipoHabitacion;
  }

  public LocalDate getFechaSalida()
  {
    return fechaSalida;
  }

  public void setFechaSalida(LocalDate fechaSalida)
  {
    this.fechaSalida = fechaSalida;
  }

  public LocalDate getFechaIngreso()
  {
    return fechaIngreso;
  }

  public void setFechaIngreso(LocalDate fechaIngreso)
  {
    this.fechaIngreso = fechaIngreso;
  }

  public LocalDate getFechaReserva()
  {
    return fechaReserva;
  }

  public void setFechaReserva(LocalDate fechaReserva)
  {
    this.fechaReserva = fechaReserva;
  }

  public double getPrecio()
  {
    return precio;
  }

  public void setPrecio(double precio)
  {
    this.precio = precio;
  }

  public String getObservaciones()
  {
    return observaciones;
  }

  public void setObservaciones(String observaciones)
  {
    this.observaciones = observaciones;
  }

  public String getEstado()
  {
    return estado;
  }

  public void setEstado(String estado)
  {
    this.estado = estado;
  }
  
  //negocio
  public boolean sosReserva(int numero)
  {
    return numeroReserva == numero;
  }
  
  public void cancelar()
  {
    estado = "CANCELADA";
  }
  
  public boolean tenesElDia(LocalDate dia)
  {
    if (fechaIngreso.isEqual(dia) || (dia.isAfter(fechaIngreso)) && dia.isBefore(fechaSalida))
      return true;
    else
      return false;
  }
  
  public boolean tuTipoEs(String tipoHabitacion)
  {
    return (this.tipoHabitacion.equals(tipoHabitacion));
  }
  
  public void arribar()
  {
    estado = "ARRIBADA";
  }
  
  public ReservaView getView()
  {
	  ClienteView cv = cliente.getView();
	  TrabajadorView tv = trabajador.getView();
	  ReservaView rv = new ReservaView(numeroReserva, cv, tv, tipoHabitacion,
				fechaIngreso, fechaSalida, fechaReserva, precio,observaciones, estado);
	  return rv;
  }
}
