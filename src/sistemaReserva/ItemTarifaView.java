package sistemaReserva;

public class ItemTarifaView {
	  private String tipoHabitacion;
	  private double precio;
	
	  public ItemTarifaView(String tipoHabitacion, double precio) {
		super();
		this.tipoHabitacion = tipoHabitacion;
		this.precio = precio;
	}

	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
