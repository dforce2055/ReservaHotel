package sistemaReserva;

import java.time.LocalDate;
import java.util.Vector;

public class EstadiaView {
	  private ReservaView reserva;
	  private HabitacionView habitacion;
	  private LocalDate fechaIngreso;
	  private LocalDate fechaSalida;
	  private double precio;
	  private ClienteView cliente;
	  private String observaciones;
	  private Vector<ServicioAdicionalView> adicionales;
	  private int numero;
	  private TrabajadorView trabajador;
	public EstadiaView(ReservaView reserva, HabitacionView habitacion, LocalDate fechaIngreso, LocalDate fechaSalida,
			double precio, ClienteView cliente, String observaciones, Vector<ServicioAdicionalView> adicionales,
			int numero, TrabajadorView trabajador) {
		super();
		this.reserva = reserva;
		this.habitacion = habitacion;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.precio = precio;
		this.cliente = cliente;
		this.observaciones = observaciones;
		this.adicionales = adicionales;
		this.numero = numero;
		this.trabajador = trabajador;
	}
	public ReservaView getReserva() {
		return reserva;
	}
	public void setReserva(ReservaView reserva) {
		this.reserva = reserva;
	}
	public HabitacionView getHabitacion() {
		return habitacion;
	}
	public void setHabitacion(HabitacionView habitacion) {
		this.habitacion = habitacion;
	}
	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public LocalDate getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public ClienteView getCliente() {
		return cliente;
	}
	public void setCliente(ClienteView cliente) {
		this.cliente = cliente;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Vector<ServicioAdicionalView> getAdicionales() {
		return adicionales;
	}
	public void setAdicionales(Vector<ServicioAdicionalView> adicionales) {
		this.adicionales = adicionales;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public TrabajadorView getTrabajador() {
		return trabajador;
	}
	public void setTrabajador(TrabajadorView trabajador) {
		this.trabajador = trabajador;
	}
}
