package sistemaReserva;
import java.util.Vector;

public class Tarifario 
{
  private Vector<ItemTarifa> items;
  
  public Tarifario()
  {
    items = new Vector<ItemTarifa>();
  }

  public Vector<ItemTarifa> getItems()
  {
    return items;
  }

  public void setItems(Vector<ItemTarifa> items)
  {
    this.items = items;
  }
  
  //negocio
  public void agregarItem(String tipoHabitacion, double precio)
  {
    ItemTarifa item = buscarItem(tipoHabitacion);
    if (item == null)
    {
      item = new ItemTarifa(tipoHabitacion, precio);
      items.add(item);
    }
  }
  
  private ItemTarifa buscarItem(String tipoHabitacion)
  {
    for (ItemTarifa item: items)
      if (item.estasActivo() && item.sosItem(tipoHabitacion))
        return item;
    return null;
  }
  
  public void bajaItem(String tipoHabitacion)
  {
    ItemTarifa item = buscarItem(tipoHabitacion);
    if (item != null)
      item.darBaja();
  }
  
  public double getPrecio(String tipoHabitacion)
  {
    ItemTarifa item = buscarItem(tipoHabitacion);
    if (item == null)
      return 0;
    else
      return item.getPrecio();
  }
  
  public Vector<String> getTiposActivos()
  {
    Vector<String> tipos = new Vector<String>();
    for (ItemTarifa item: items)
    {
    	boolean rta = item.estasActivo();
    	if (rta == true)
    	{
    		String tipo = item.getTipoHabitacion(); 
        	tipos.add(tipo);
    	}
    }
    return tipos;
  }
  
  public boolean existeTipoHabitacion(String tipo)
  {
    ItemTarifa item = buscarItem(tipo);
    return item != null;
  }
  
  public void modificarValorTarifa(String tipoHabitacion, double precio)
  {
    ItemTarifa item = buscarItem(tipoHabitacion);
    if (item != null)
      item.setPrecio(precio);
  }
  
  public Vector<ItemTarifaView> getView()
  {
	  Vector<ItemTarifaView> tarifarioView = new Vector<ItemTarifaView>();
	  for (ItemTarifa item: items)
	  {
		  ItemTarifaView itemView = item.getView();
		  tarifarioView.add(itemView);
	  }
	  return tarifarioView;
  }
}
