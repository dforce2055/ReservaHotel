/* inicio de comentario inutil
 * fin de comentario inutil
 */

package sistemaReserva;
public class Cliente extends Persona
{
  private static int proximoNumero;
  private int numero;

  private static int getProximoNumero()
  {
    return ++proximoNumero;
  }
  
  public Cliente(String nom, String ape, String tDoc, String nDoc, String dir, 
      String tel, String eMail)
  {
    super(nom, ape, tDoc, nDoc, dir, tel, eMail);
    numero = getProximoNumero();
  }

  public int getNumero()
  {
    return numero;
  }

  public void setNumero(int numero)
  {
    this.numero = numero;
  }
  
  public boolean sosCliente(int codigo)
  {
    return codigo == numero;
  }
  
  public ClienteView getView()
  {
    ClienteView cv = new ClienteView(numero, nombre, apellido, tipoDocumento,
        numeroDocumento, direccion, telefono, email);
    return cv;
  }
}
