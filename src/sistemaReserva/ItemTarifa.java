package sistemaReserva;
public class ItemTarifa
{
  private String tipoHabitacion;
  private double precio;
  private boolean activo;
  
  public ItemTarifa(String tipo, double precio)
  {
    tipoHabitacion = tipo;
    this.precio = precio;
    activo = true;
  }

  public String getTipoHabitacion()
  {
    return tipoHabitacion;
  }

  public void setTipoHabitacion(String tipoHabitacion)
  {
    this.tipoHabitacion = tipoHabitacion;
  }

  public double getPrecio()
  {
    return precio;
  }

  public void setPrecio(double precio)
  {
    this.precio = precio;
  }
  
  //negocio
  public boolean sosItem(String tipo)
  {
    return tipoHabitacion.equalsIgnoreCase(tipo);
  }
  
  public void darBaja()
  {
    activo = false;
  }
  
  public boolean estasActivo()
  {
    return activo;
  }
}
