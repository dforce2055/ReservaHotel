package sistemaReserva;

public class ItemAdicionalEstadia {
	private ServicioAdicional servicio;
	private int cantidad;
	private double precio;
	
	public ItemAdicionalEstadia(ServicioAdicional servicio, int cantidad) {
		super();
		this.servicio = servicio;
		this.cantidad = cantidad;
		precio = servicio.getPrecio(); //se respeta el precio del momento en que se agrega
	}

	public ServicioAdicional getServicio() {
		return servicio;
	}

	public void setServicio(ServicioAdicional servicio) {
		this.servicio = servicio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	//negocio
	public ItemAdicionalEstadiaView getView()
	{
		int codigo = servicio.getCodigo();
		String descripcion = servicio.getDescripcion();
		
		ItemAdicionalEstadiaView item = new ItemAdicionalEstadiaView(cantidad, codigo, 
				precio, descripcion);
		return item;
	}
}
