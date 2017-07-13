package sistemaReserva;

public class ItemAdicionalEstadiaView {
	private int cantidad;
	private int codigo;
	private double precio;
	private String descripcion;
	
	public ItemAdicionalEstadiaView(int cantidad, int codigo, double precio,
			String descripcion) {
		super();
		this.cantidad = cantidad;
		this.codigo = codigo;
		this.precio = precio;
		this.descripcion = descripcion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
