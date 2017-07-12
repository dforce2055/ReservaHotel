package sistemaReserva;

import java.time.LocalDate;

public class ReservaView
{
  private int numero;
  private ClienteView cliente;
  private TrabajadorView trabajador;
  private String tipoHabitacion;
  private LocalDate fechaIngreso;
  private LocalDate fechaSalida;
  private LocalDate fechaReserva;
  private double precio;
  private String observaciones;
  private String estado;//ACTIVA | CANCELADA | ARRIBADA
  
  public ReservaView(int numero, ClienteView cliente, TrabajadorView trabajador, String tipoHabitacion,
    LocalDate fechaIngreso, LocalDate fechaSalida, LocalDate fechaReserva, double precio,
    String observaciones, String estado)
  {
    super();
    this.numero = numero;
    this.cliente = cliente;
    this.trabajador = trabajador;
    this.tipoHabitacion = tipoHabitacion;
    this.fechaIngreso = fechaIngreso;
    this.fechaSalida = fechaSalida;
    this.fechaReserva = fechaReserva;
    this.precio = precio;
    this.observaciones = observaciones;
    this.estado = estado;
  }
  public int getNumero()
  {
    return numero;
  }
  public void setNumero(int numero)
  {
    this.numero = numero;
  }
  public ClienteView getCliente()
  {
    return cliente;
  }
  public void setCliente(ClienteView cliente)
  {
    this.cliente = cliente;
  }
  public TrabajadorView getTrabajador()
  {
    return trabajador;
  }
  public void setTrabajador(TrabajadorView trabajador)
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
}
