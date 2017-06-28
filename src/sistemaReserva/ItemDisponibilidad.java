package sistemaReserva;

public class ItemDisponibilidad
{
  private String tipoHabitacion;
  private int cantidad;
  
  public ItemDisponibilidad(String tipoHabitacion, int cantidad)
  {
    this.tipoHabitacion = tipoHabitacion;
    this.cantidad = cantidad;
  }

  public String getTipoHabitacion() {
    return tipoHabitacion;
  }

  public void setTipoHabitacion(String tipoHabitacion) {
    this.tipoHabitacion = tipoHabitacion;
  }

  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }
}
