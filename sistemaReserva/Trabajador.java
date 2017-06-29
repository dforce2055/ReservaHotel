package sistemaReserva;
public class Trabajador extends Persona 
{
  private String nombreUsuario;
  private String password;
  private boolean estado;
  private int legajo;
  private static int proxLegajo;
  
  private static int getProxLegajo()
  {
    return ++proxLegajo;
  }
  
/** 
 * public Trabajador(String nom, String ape, String tDoc, String nDoc, 
 * String dir, String tel, String eMail, String nomU, String pass, int leg)
 * {
 *   super(nom, ape, tDoc, nDoc, dir, tel, eMail);
 *   nombreUsuario = nomU;
 *   password = pass;
 *   estado = true;
 *   legajo = leg;
 * }
 **/
  
  public Trabajador(String nom, String ape, String tDoc, String nDoc, 
      String dir, String tel, String eMail, String nomU, String pass)
  {
    super(nom, ape, tDoc, nDoc, dir, tel, eMail);
    nombreUsuario = nomU;
    password = pass;
    estado = true;
    legajo = getProxLegajo();
  }

  public String getNombreUsuario()
  {
    return nombreUsuario;
  }

  public void setNombreUsuario(String nombreUsuario)
  {
    this.nombreUsuario = nombreUsuario;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public boolean isEstado()
  {
    return estado;
  }

  public void setEstado(boolean estado)
  {
    this.estado = estado;
  }

  public int getLegajo() {
    return legajo;
  }

  public void setLegajo(int legajo)
  {
    this.legajo = legajo;
  }

  public boolean esTuContrasenia(String pw)
  {
    return password == pw;
  }
  
  public boolean esTuNombreUsuario(String user)
  {
    return nombreUsuario == user;
  }
    
  public boolean sosTrabajador(int legajo)
  {
    return this.legajo == legajo;
  }
}
