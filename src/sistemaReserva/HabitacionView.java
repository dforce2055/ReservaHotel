package sistemaReserva;

public class HabitacionView {
  private String numero;
  private String piso;
  private String descripcion;
  private String caracteristicas;
  private String tipo;
  
  public HabitacionView(String numero, String piso, String descripcion, String caracteristicas, String tipo) {
    super();
    this.numero = numero;
    this.piso = piso;
    this.descripcion = descripcion;
    this.caracteristicas = caracteristicas;
    this.tipo = tipo;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getPiso() {
    return piso;
  }

  public void setPiso(String piso) {
    this.piso = piso;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getCaracteristicas() {
    return caracteristicas;
  }

  public void setCaracteristicas(String caracteristicas) {
    this.caracteristicas = caracteristicas;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }
  
}
