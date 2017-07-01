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
    Vector<String> v = new Vector<String>();
    for (ItemTarifa item: items)
      if (item.estasActivo())
        v.add(item.getTipoHabitacion());
    return v;
  }
  
  public boolean existeTipo(String tipo)
  {
    ItemTarifa item = buscarItem(tipo);
    return item != null;
  }
  
  public void modificarValorTarifa(String tipo, double precio)
  {
    ItemTarifa item = buscarItem(tipo);
    if (item != null)
      item.setPrecio(precio);
  }
}
