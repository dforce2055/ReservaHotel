package sistemaReserva;
import java.util.Vector;

public class DisponibilidadesView
{
  private Vector<ItemDisponibilidad> items;
  
  public DisponibilidadesView()
  {
    items = new Vector<ItemDisponibilidad>(); 
  }
  
  public void agregarItem(String tipoHabitacion, int cantidad)
  {
    ItemDisponibilidad item = new ItemDisponibilidad(tipoHabitacion, cantidad);
    items.add(item);
  }
  
  public Vector<ItemDisponibilidad> getItems()
  {
    return items;
  }
}
