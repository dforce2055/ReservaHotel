package sistemaReserva;
public class Habitacion
{
  private String numero;
  private String piso;
  private String descripcion;
  private String caracteristicas;
  private String estado;
  private String tipoHabitacion;
  
  public Habitacion(String numero, String piso, String descripcion, 
      String caracteristicas,String tipoHabitacion)
  {
    super();
    this.numero = numero;
    this.piso = piso;
    this.descripcion = descripcion;
    this.caracteristicas = caracteristicas;
    this.tipoHabitacion = tipoHabitacion;
    estado = "DISPONIBLE";
  }
  public String getNumero()
  {
    return numero;
  }
  public void setNumero(String numero)
  {
    this.numero = numero;
  }
  public String getPiso()
  {
    return piso;
  }
  public void setPiso(String piso)
  {
    this.piso = piso;
  }
  public String getDescripcion()
  {
    return descripcion;
  }
  public void setDescripcion(String descripcion)
  {
    this.descripcion = descripcion;
  }
  public String getCaracteristicas()
  {
    return caracteristicas;
  }
  public void setCaracteristicas(String caracteristicas)
  {
    this.caracteristicas = caracteristicas;
  }

  public String getEstado()
  {
    return estado;
  }
  public void setEstado(String estado)
  {
    this.estado = estado;
  }
  public String getTipoHabitacion()
  {
    return tipoHabitacion;
  }
  public void setTipoHabitacion(String tipoHabitacion)
  {
    this.tipoHabitacion = tipoHabitacion;
  }
  
  //negocio
  public boolean sosHabitacion(String numero)
  {
    return this.numero == numero;
  }
  
  public void darBaja()
  {
    estado = "INACTIVA";
  }
  
  //experimental
  public boolean tuTipoEs(String tipo)
  {
    return tipo.equals(tipoHabitacion);
  }
  
  public boolean estasInactiva()
  {
    return estado.equals("INACTIVA");
  }
  
  public void ocupar()
  {
    estado = "OCUPADA";
  }
  
  public boolean estasDisponible()
  {
    return estado.equals("DISPONIBLE");
  }
}
