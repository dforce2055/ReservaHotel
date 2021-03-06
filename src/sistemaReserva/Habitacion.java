package sistemaReserva;
public class Habitacion
{
  private String numero;
  private String piso;
  private String descripcion;
  private String caracteristicas;
  private String estado;//DISPONIBLE | OCUPADA | EN_MANTENIMIENTO | INACTIVA
  private String tipo;// INDIVIDUAL | DOBLE | TRIPLE
  
  public Habitacion(String numero, String piso, String descripcion, 
      String caracteristicas,String tipo)
  {
    super();
    this.numero = numero;
    this.piso = piso;
    this.descripcion = descripcion;
    this.caracteristicas = caracteristicas;
    this.tipo = tipo;
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
  public String getTipo()
  {
    return tipo;
  }
  public void setTipo(String tipo)
  {
    this.tipo = tipo;
  }
  
  //negocio
  public boolean sosHabitacion(String numero)
  {
    return this.numero.equals(numero);
  }
  
  public void darBaja()
  {
    estado = "INACTIVA";
  }
  
  public void rehabilitar()
  {
    estado = "DISPONIBLE";
  }
  
  public boolean tuTipoEs(String tipo)
  {
    return this.tipo.equalsIgnoreCase(tipo);
  }
  
  public boolean estasInactiva()
  {
    return estado.equalsIgnoreCase("INACTIVA");
  }
  
  public void ocupar()
  {
    estado = "OCUPADA";
  }
  
  public boolean estasDisponible()
  {
    return estado.equalsIgnoreCase("DISPONIBLE");
  }
  
  public HabitacionView getView()
  {
    HabitacionView hv = new HabitacionView(numero, piso, descripcion, caracteristicas,
        tipo);
    return hv;
  }
}
