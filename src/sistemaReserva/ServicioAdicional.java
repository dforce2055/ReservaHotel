package sistemaReserva;
public class ServicioAdicional
{
  private int codigo;
  private String descripcion;
  private double precio;
  
  public ServicioAdicional(int cod, String desc, double prec)
  {
    codigo = cod;
    descripcion = desc;
    precio = prec;
  }

  public int getCodigo()
  {
    return codigo;
  }

  public void setCodigo(int codigo)
  {
    this.codigo = codigo;
  }

  public String getDescripcion()
  {
    return descripcion;
  }

  public void setDescripcion(String descripcion)
  {
    this.descripcion = descripcion;
  }

  public double getPrecio()
  {
    return precio;
  }

  public void setPrecio(double precio)
  {
    this.precio = precio;
  }
  
  public boolean sosServicio(int codigo)
  {
    return this.codigo == codigo;
  }
  
  public ServicioAdicionalView getView()
  {
	  ServicioAdicionalView sav = new ServicioAdicionalView(codigo, descripcion, precio);
	  return sav;
  }
}
