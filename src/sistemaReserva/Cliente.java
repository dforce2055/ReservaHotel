/* inicio de comentario inutil
 * fin de comentario inutil
 */

package sistemaReserva;
public class Cliente extends Persona
{
  private static int proxCodigoCliente;
  private int codigoCliente;

  private static int getProxCodigo()
  {
    return ++proxCodigoCliente;
  }
  
  /**
   * public Cliente(String nom, String ape, String tDoc, String nDoc, String dir,
   * String tel, String eMail, int cod)
   * {
   *   super(nom, ape, tDoc, nDoc, dir, tel, eMail);
   *   codigoCliente = cod;
   * }si dejamos de usar el static, usar este constructor
   **/ 
  
  public Cliente(String nom, String ape, String tDoc, String nDoc, String dir, 
      String tel, String eMail)
  {
    super(nom, ape, tDoc, nDoc, dir, tel, eMail);
    codigoCliente = getProxCodigo();
  }

  public int getCodigoCliente()
  {
    return codigoCliente;
  }

  public void setCodigoCliente(int codigoCliente)
  {
    this.codigoCliente = codigoCliente;
  }
  
  public boolean sosCliente(int codigo)
  {
    return codigo == codigoCliente;
  }
}
