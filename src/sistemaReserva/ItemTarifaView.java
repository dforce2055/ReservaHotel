package sistemaReserva;

public class ItemTarifaView {
	  private String tipoHabitacion;
	  private double precio;
	  private boolean activo;
	
	  public ItemTarifaView(String tipoHabitacion, double precio, boolean activo) {
		super();
		this.tipoHabitacion = tipoHabitacion;
		this.precio = precio;
		this.activo = activo;
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

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
