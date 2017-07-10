package sistemaReserva;
public class Persona 
{
  protected String nombre;
  protected String apellido;
  protected String tipoDocumento;
  protected String numeroDocumento;
  protected String direccion;
  protected String telefono;
  protected String email;
  protected String estado;//ACTIVA | INACTIVA
  
  public Persona(String nom, String ape, String tDoc, String nDoc, String dir, 
      String tel, String eMail)
  {
    nombre = nom;
    apellido = ape;
    tipoDocumento = tDoc;
    numeroDocumento = nDoc;
    direccion = dir;
    telefono = tel;
    email = eMail;
    estado = "ACTIVO";
  }

  public String getNombre()
  {
    return nombre;
  }

  public void setNombre(String nombre)
  {
    this.nombre = nombre;
  }

  public String getApellido()
  {
    return apellido;
  }

  public void setApellido(String apellido)
  {
    this.apellido = apellido;
  }

  public String getTipoDocumento()
  {
    return tipoDocumento;
  }

  public void setTipoDocumento(String tipoDocumento)
  {
    this.tipoDocumento = tipoDocumento;
  }

  public String getNumeroDocumento()
  {
    return numeroDocumento;
  }

  public void setNumeroDocumento(String numeroDocumento)
  {
    this.numeroDocumento = numeroDocumento;
  }

  public String getDireccion()
  {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono)
  {
    this.telefono = telefono;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
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
  public boolean esTuDocumento(String tipoDoc, String numDoc)
  {
    return (tipoDocumento.equals(tipoDoc) && numeroDocumento.equals(numDoc));
  }

  public void darBaja()
  {
    estado = "INACTIVO";
  }
}
