package sistemaReserva;
import java.time.LocalDate;
import java.util.Vector;

public class SistemaReserva
{
  private Vector<Cliente> clientes;
  private Vector<Cliente> clientesInactivos;
  private Vector<Habitacion> habitaciones;
  private Vector<Trabajador> trabajadores;
  private Vector<Trabajador> trabajadoresInactivos;
  private Vector<Reserva> reservas;
  private Vector<Reserva> reservasInactivas;
  private Vector<Estadia> estadias;
  private Vector<Estadia> estadiasInactivas;
  private Vector<ServicioAdicional> servicios;
  private Vector<ServicioAdicional> serviciosInactivos;
  private Tarifario tarifario;
  private Trabajador trabajadorValidado;//En futuras versiones un Array
  
  public SistemaReserva()
  {
    clientes = new Vector<Cliente>();
    clientesInactivos = new Vector<Cliente>();
    habitaciones = new Vector<Habitacion>();
    trabajadores = new Vector<Trabajador>();
    trabajadoresInactivos = new Vector<Trabajador>();
    reservas = new Vector<Reserva>();
    reservasInactivas = new Vector<Reserva>();
    estadias = new Vector<Estadia>();
    estadiasInactivas = new Vector<Estadia>();
    servicios = new Vector<ServicioAdicional>();
    serviciosInactivos = new Vector<ServicioAdicional>();
    tarifario = new Tarifario();
    //Datos prueba
    CargaDatosPrueba prueba = new CargaDatosPrueba(this);
    prueba.CargarDatos();
  }
  
  private Cliente buscarCliente(int numero)
  {
    for (Cliente c: clientes)
      if (c.sosCliente(numero))
        return c;
    return null;
  }
  
  public ClienteView buscarClienteViewPorNumero(int numeroCliente)
  {
    Cliente cliente = buscarCliente(numeroCliente);
    if (cliente != null)
    	return cliente.getView();
    return null;
  }
  
  private Cliente buscarClientePorDocumento(String tipoDoc, String numDoc)
  {
    for (Cliente c: clientes)
      if (c.esTuDocumento(tipoDoc, numDoc))
        return c;
    return null;
  }
  
  public ClienteView buscarClienteViewPorDocumento(String tipoDoc, String numDoc)
  {
	    Cliente cliente = buscarClientePorDocumento(tipoDoc, numDoc);
	    if (cliente != null)
	    	return cliente.getView();
	    return null;
  }
  
  
  private boolean existeClienteConEseDocumento(String tipoDoc, String numDoc)
  {
    Cliente cliente = buscarClientePorDocumento(tipoDoc, numDoc);
    
    return cliente != null;
  }
  
  public Vector<ItemTarifaView> buscarTarifasView()
  {
	  return tarifario.getView();
  }
  
  private Trabajador buscarTrabajador(int legajo)
  {
    for (Trabajador t: trabajadores)
      if (t.sosTrabajador(legajo))
        return t;
    return null;
  }
  
  public TrabajadorView buscarTrabajadorView(int legajo)
  {
	  Trabajador trabajador = buscarTrabajador(legajo);
	  if (trabajador != null)
		  return trabajador.getView();
	  return null;
  }
  
  private Trabajador buscarTrabajadorPorDocumento(String tipoDoc, String numDoc)
  {
    for (Trabajador t: trabajadores)
      if (t.esTuDocumento(tipoDoc, numDoc)) 
        return t;
    return null;
  }
  
  public TrabajadorView buscarTrabajadorViewPorDocumento(String tipoDoc, String numDoc)
  {
	  Trabajador trabajador = buscarTrabajadorPorDocumento(tipoDoc, numDoc);
	  if (trabajador != null)
		  return trabajador.getView();
	  return null;
  }
  
  private boolean existeTrabajadorConEseDocumento(String tipoDoc, String numDoc)
  {
    Trabajador trabajador = buscarTrabajadorPorDocumento(tipoDoc, numDoc);
    
    return trabajador != null;
  }
  
  //usado para el login
  public Trabajador buscarTrabajadorPorUsuario(String usuario)
  {
    for (Trabajador t: trabajadores)
      if (t.esTuNombreUsuario(usuario))
        return t;
    return null;
  }
  
  private Habitacion buscarHabitacion(String numero)
  {
    for (Habitacion h: habitaciones)
      if (h.sosHabitacion(numero))
    	  if (!h.estasInactiva())
    		  return h;
    return null;
  }
  
  public HabitacionView buscarHabitacionView(String numero)
  {
	  Habitacion habitacion = buscarHabitacion(numero);
	  if (habitacion != null)
		  return habitacion.getView();
	  return null;
  }
  
  private Reserva buscarReserva(int numero)
  { 
    for (Reserva r: reservas)
      if (r.sosReserva(numero))
        return r;
    return null;
  }
  
  private Estadia buscarEstadia(int numero)
  {
    for (Estadia e: estadias)
      if (e.sosEstadia(numero))
        return e;
    return null;
  }
  
  private ServicioAdicional buscarServicioAdicional(int codigo)
  {
    for (ServicioAdicional s: servicios)
      if (s.sosServicio(codigo))
        return s;
    return null;
  }
  
  private Vector<Estadia> buscarEstadiasPorTipo(String tipoHabitacion)
  {
    Vector<Estadia> v = new Vector<Estadia>();
    if (existeTipoHabitacion(tipoHabitacion))
    {
      for (Estadia e: estadias)
        if (e.tuTipoEs(tipoHabitacion))
          v.add(e);
    }
    return v;
  }

  public Vector<Reserva> buscarReservasPorTipo(String tipoHabitacion)
  {
    Vector<Reserva> v = new Vector<Reserva>();
    if (existeTipoHabitacion(tipoHabitacion))  
    {
      for (Reserva r: reservas)
        if (r.tuTipoEs(tipoHabitacion))
          v.add(r);
    }
    return v;
  }
  
  public ReservaView buscarReservaView(String nroReserva)
  {
    for (Reserva reserva: reservas)
      if (reserva.sosReserva(Integer.parseInt(nroReserva)))
        return reserva.getView();
    return null;
  }

  public int altaCliente(String nombre, String apellido, String tipoDoc, 
      String numDoc, String direccion, String telefono, String email)
  {
    Cliente cliente = buscarClientePorDocumento(tipoDoc, numDoc);
    if (cliente == null)
    {
      cliente = new Cliente(nombre, apellido, tipoDoc, numDoc, direccion, telefono, email);
      clientes.add(cliente);
      return cliente.getNumero();
    }
    return 0;
  }
  
  public int altaTrabajador(String nombre, String apellido, String tipoDoc, 
      String numDoc, String direccion, String telefono, String email, 
      String usuario, String password)
  {
    Trabajador trabajador = buscarTrabajadorPorDocumento(tipoDoc, numDoc);
    if (trabajador == null)
    {
      trabajador = new Trabajador(nombre, apellido, tipoDoc, numDoc, direccion, telefono, email, usuario, password);
      trabajadores.add(trabajador);
      return trabajador.getLegajo();
    }
    return 0;
  }
  
  public boolean altaHabitacion(String numero, String piso, String descripcion, 
      String caracteristicas, String tipo)
  {
    boolean resultado = existeTipoHabitacion(tipo); 
    if (resultado == true)
    {
      Habitacion habitacion = buscarHabitacion(numero);
      if (habitacion == null)
      {
        habitacion = new Habitacion(numero, piso, descripcion, caracteristicas, tipo);
        habitaciones.add(habitacion);
        return true;
      }
    }
    return false;
  }
  
  public int altaReserva(int numeroCliente, int legajoTrabajador, 
      String tipoHabitacion, LocalDate fechaIngreso, LocalDate fechaSalida, 
      String observaciones)
  {
    boolean resultado = existeTipoHabitacion(tipoHabitacion); 
    
    if (resultado == true)
    {
      Cliente cliente = buscarCliente(numeroCliente);
      if (cliente != null)
      {
        Trabajador trabajador = buscarTrabajador(legajoTrabajador);
        if (trabajador != null)
        {
          resultado = hayDisponibilidadPorTipo(tipoHabitacion, fechaIngreso, fechaSalida);
          if (resultado == true)
          {
            double precio = tarifario.getPrecio(tipoHabitacion);
            Reserva reserva = new Reserva(cliente, trabajador, tipoHabitacion, fechaIngreso, fechaSalida, precio, observaciones);
            reservas.add(reserva);
            return reserva.getNumeroReserva();
          }
        }
      } 
    }
    return 0;
  }
  
  public void altaEstadiaConReserva(int numeroReserva, String numeroHabitacion)
  { 
    Reserva reserva = buscarReserva(numeroReserva);
    if (reserva != null)
    {
      Habitacion habitacion = buscarHabitacion(numeroHabitacion);
      if (habitacion != null && habitacion.estasDisponible())
      {
        Estadia estadia = new Estadia(reserva, habitacion);
        habitacion.ocupar();
        reserva.arribar();
        reservasInactivas.add(reserva);
        reservas.remove(reserva);
        estadias.add(estadia);
      }
    }
  }
  
  public void altaEstadiaSinReserva(String numeroHabitacion, 
      LocalDate fechaIngreso, LocalDate fechaSalida, int numeroCliente, 
      int legajoTrabajador, String observaciones)
  {
    Cliente cliente = buscarCliente(numeroCliente);
    if (cliente != null)
    {
      Trabajador trabajador = buscarTrabajador(legajoTrabajador);
      if (trabajador != null)
      {
        Habitacion habitacion = buscarHabitacion(numeroHabitacion);
        if (habitacion != null && habitacion.estasDisponible())
        {
          String tipoHabitacion = habitacion.getTipo();
          boolean resultado = hayDisponibilidadPorTipo(tipoHabitacion, fechaIngreso, fechaSalida);//verificar por las dudas 
          if (resultado == true) 
          {
            habitacion.ocupar();
            double precio = tarifario.getPrecio(tipoHabitacion);
            Estadia estadia = new Estadia(habitacion, fechaIngreso, fechaSalida, precio, cliente, trabajador, observaciones);
            estadias.add(estadia);
          }
        }
      }
    }
  }
  
  public void altaServicioAdicional(int codigo, String descripcion, double precio)
  {
    ServicioAdicional servicio = buscarServicioAdicional(codigo);
    if (servicio == null)
    {
      servicio = new ServicioAdicional(codigo, descripcion, precio);
      servicios.add(servicio);
    }
  }

  public boolean altaTarifa(String tipoHabitacion, double precio)
  {
    boolean resultado = existeTipoHabitacion(tipoHabitacion);
    if (resultado == false)
    {
      tarifario.agregarItem(tipoHabitacion, precio);
    }
    return !resultado;
  }
  
  public void bajaCliente(int numero)
  {
    Cliente cliente = buscarCliente(numero);
    if (cliente != null)
    {
      cliente.darBaja();
      clientesInactivos.add(cliente);
      clientes.remove(cliente);
    }
  }

  public void bajaTrabajador(int legajo)
  {
    Trabajador trabajador = buscarTrabajador(legajo);
    if (trabajador != null)
    {
      trabajador.darBaja();
      trabajadoresInactivos.add(trabajador);
      trabajadores.remove(trabajador);
    }
  }

  public void bajaHabitacion(String numero)
  {
    Habitacion habitacion = buscarHabitacion(numero);
    if (habitacion != null)
      habitacion.darBaja();
  }

  public void bajaServicio(int codigo)
  {
    ServicioAdicional servicio = buscarServicioAdicional(codigo);
    if (servicio != null)
    {
      serviciosInactivos.add(servicio);
      servicios.remove(servicio);
    }
  }

  public void bajaTarifa(String tipoHabitacion)
  {
    boolean resultado = existeTipoHabitacion(tipoHabitacion); 
    if (resultado == true)
    {
      int cantidad = cantidadHabitacionesDeTipo(tipoHabitacion);
      //No se permite dar de baja una tarifa si hay habitaciones con ese tipo
      if ( cantidad == 0)
      {
        tarifario.bajaItem(tipoHabitacion);
      }
    }
  }
  
  public void bajaEstadia(int numero)
  {
    Estadia estadia = buscarEstadia(numero);
    if (estadia != null)
    {
      Habitacion habitacion = estadia.getHabitacion();
      habitacion.rehabilitar();
      estadiasInactivas.add(estadia);
      estadias.remove(estadia);
    }
  }

  public void cancelarReserva(int numero)
  {
    Reserva reserva = buscarReserva(numero);
    if (reserva != null)
    {
      reserva.cancelar();
      reservasInactivas.add(reserva);
      reservas.remove(reserva);
    }
  }
  
  public double cerrarEstadia(int numero)
  {
    double total = 0;
    Estadia estadia = buscarEstadia(numero);
    if (estadia != null)
    {
      total = estadia.calcularTotal();
      bajaEstadia(numero);
    }
    return total;
  }

  public double cerrarEstadiaAnticipada(int numero, LocalDate fechaSalida)
  {
    double total = 0;
    Estadia estadia = buscarEstadia(numero);
    if (estadia != null)
    {
      LocalDate fechaEntrada = estadia.getFechaIngreso();
      boolean resultado = validarFecha(fechaEntrada, fechaSalida);
      if (resultado == true)
      {
        estadia.setFechaSalida(fechaSalida);
        total = estadia.calcularTotal();
        bajaEstadia(numero);
      }
    }
    return total;
  }

  public boolean modificarCliente(int numero, String nombre, String apellido, 
      String tipoDoc, String numDoc, String direccion, String telefono, 
      String email)
  {
    Cliente cliente = buscarCliente(numero);
    
    if (cliente != null)
    {
      String nombreCliente = cliente.getNombre();
      String apellidoCliente = cliente.getApellido();
      String tipoDocCliente = cliente.getTipoDocumento();
      String numDocCliente = cliente.getNumeroDocumento();
      String direccionCliente = cliente.getDireccion();
      String telefonoCliente = cliente.getTelefono();
      String emailCliente = cliente.getEmail();
      
      if (!nombre.equals(nombreCliente))
    	  cliente.setNombre(nombre);
      if (!apellido.equals(apellidoCliente))
          cliente.setApellido(apellido);
   
      if (!tipoDoc.equalsIgnoreCase(tipoDocCliente) || !numDoc.equalsIgnoreCase(numDocCliente))
      {
    	  boolean rta = existeClienteConEseDocumento(tipoDoc, numDoc);
	      if (rta == false)//Si NO existe un cliente con ese Tipo y numero de documento
	      {
	    	  if(!tipoDoc.equals(tipoDocCliente))
	    		  cliente.setTipoDocumento(tipoDoc);
	        
	    	  if(!numDoc.equals(numDocCliente))
	    		  cliente.setNumeroDocumento(numDoc);
	      }
      }
      if (!direccion.equals(direccionCliente))
    	  cliente.setDireccion(direccion);
      if (!telefono.equals(telefonoCliente))
          cliente.setTelefono(telefono);
      if (!email.equals(emailCliente))
          cliente.setEmail(email);
        
        return true;
    }
    return false;
  }
  
  public boolean modificarTrabajador(int legajo, String nombre, String apellido, String tipoDoc, String numDoc, String direccion, String telefono, String email)
  {
    Trabajador trabajador = buscarTrabajador(legajo);
    
    if (trabajador != null)
    {
      String nombreTrabajador = trabajador.getNombre(),
      apellidoTrabajador = trabajador.getApellido(),
      tipoDocTrabajador = trabajador.getTipoDocumento(),
      numDocTrabajador = trabajador.getNumeroDocumento(),
      direccionTrabajador = trabajador.getDireccion(),
      telefonoTrabajador = trabajador.getTelefono(),
      emailTrabajador = trabajador.getEmail();
      
      if (!nombre.equals(nombreTrabajador))
        trabajador.setNombre(nombre);
      if (!apellido.equals(apellidoTrabajador))
        trabajador.setApellido(apellido);
      
      if (!tipoDoc.equals(tipoDocTrabajador) || !numDoc.equals(numDocTrabajador))
      {
        boolean existeTrabajadorConEseDocumento = existeTrabajadorConEseDocumento(tipoDoc, numDoc);
        
        
        if (existeTrabajadorConEseDocumento == false)
        {
          trabajador.setTipoDocumento(tipoDoc);
          trabajador.setNumeroDocumento(numDoc);
        }
        else
          return false;
      }
      if (!direccion.equals(direccionTrabajador))
        trabajador.setDireccion(direccion);
      if (!telefono.equals(telefonoTrabajador))
        trabajador.setTelefono(telefono);
      if (!email.equals(emailTrabajador))
        trabajador.setEmail(email);
      
      return true;
    }
    return false;
  }
  
  public boolean modificarTrabajadorPassword(int legajo, String passwordAnterior, String passwordNuevo)
  {
    Trabajador trabajador = buscarTrabajador(legajo);
    
    if (trabajador != null)
    {
      String passwordActual = trabajador.getPassword();
      if(passwordAnterior.equals(passwordActual))
      {
        trabajador.setPassword(passwordNuevo);
        return true;
      }
    }
    return false;
  }
  
  public boolean modificarHabitacion(String numero, String piso, 
      String descripcion, String caracteristicas, String tipo)
  {
    Habitacion habitacion = buscarHabitacion(numero);
    
    if (habitacion != null)
    {
      String pisoHabitacion = habitacion.getPiso(),
      descripcionHabitacion = habitacion.getDescripcion(),
      caracteristicasHabitacion = habitacion.getCaracteristicas(),
      tipoHabitacion = habitacion.getTipo();
      	
      if (!piso.equals(pisoHabitacion))
        habitacion.setPiso(piso);
      
      if (!descripcion.equals(descripcionHabitacion))
        habitacion.setDescripcion(descripcion);
      
      if (!caracteristicas.equals(caracteristicasHabitacion))
        habitacion.setCaracteristicas(caracteristicas);
      
      if (!tipo.equals(tipoHabitacion))
      {
        boolean resultado = existeTipoHabitacion(tipo);
        
        if (resultado == true)
          habitacion.setTipo(tipo);
        else
          return false;
      }
      return true;
    }
    return false;
  }
  //Testear mÃ©todo
  public boolean modificarReserva(int numero, int numeroCliente, 
      String tipoHabitacion, LocalDate fechaIngreso, LocalDate fechaSalida, 
      String observaciones)
  {
    Reserva reserva = buscarReserva(numero);
    
    if (reserva != null)
    {
      boolean resultado;
      Cliente clienteReserva = reserva.getCliente();
      String tipoHabitacionReserva = reserva.getTipoHabitacion();
      LocalDate fechaIngresoReserva = reserva.getFechaIngreso();
      LocalDate fechaSalidaReserva = reserva.getFechaSalida();
      String observacionesReserva = reserva.getObservaciones();
      
      Cliente cliente = buscarCliente(numeroCliente);
      
      if (cliente != null && !cliente.equals(clienteReserva))
        reserva.setCliente(cliente);
      
      if (!observaciones.equals(observacionesReserva))
        reserva.setObservaciones(observaciones);
      
      if (tipoHabitacion.equals(tipoHabitacionReserva))
      {
        if (fechaIngreso.equals(fechaIngresoReserva) && fechaSalida.equals(fechaSalidaReserva)) //no esta en el diagrama
          return true;
        
        if (fechaIngreso.isAfter(fechaIngresoReserva) && fechaIngreso.isBefore(fechaSalidaReserva))
          reserva.setFechaIngreso(fechaIngreso);
        
        if (fechaSalida.isBefore(fechaSalidaReserva) && fechaSalida.isAfter(fechaIngresoReserva))
          reserva.setFechaSalida(fechaSalida);
        
        if (fechaIngreso.isBefore(fechaIngresoReserva))
        {
          resultado = hayDisponibilidadPorTipo(tipoHabitacionReserva, fechaIngreso, fechaIngresoReserva);
          if (resultado == true)
            reserva.setFechaIngreso(fechaIngreso);
          else
            return false;
        }
        
        if (fechaSalida.isAfter(fechaSalidaReserva))
        {
          resultado = hayDisponibilidadPorTipo(tipoHabitacionReserva, fechaSalidaReserva, fechaSalida);
          if (resultado == true)
            reserva.setFechaSalida(fechaSalida);
          else
            return false;
        }
      }
      
      else
      {
        if (fechaIngreso.equals(fechaIngresoReserva) && fechaSalida.equals(fechaSalidaReserva))
        {
          resultado = hayDisponibilidadPorTipo(tipoHabitacion, fechaIngresoReserva, fechaSalidaReserva);
          if (resultado == true)
            reserva.setTipoHabitacion(tipoHabitacion);
          else
            return false;
        }
        
        
        else
        {
          if (fechaIngreso.isAfter(fechaIngresoReserva) && fechaIngreso.isBefore(fechaSalidaReserva))
          {
            resultado = hayDisponibilidadPorTipo(tipoHabitacion, fechaIngreso, fechaSalidaReserva);
            if (resultado == true)
            {
              reserva.setTipoHabitacion(tipoHabitacion);
              reserva.setFechaIngreso(fechaIngreso);
              fechaIngresoReserva = reserva.getFechaIngreso();
            }
            else
              return false;
          }
          
          if (fechaSalida.isBefore(fechaSalidaReserva) && fechaSalida.isAfter(fechaIngresoReserva))
          {
            resultado = hayDisponibilidadPorTipo(tipoHabitacion, fechaIngresoReserva, fechaSalida);
            if (resultado == true)
            {
              reserva.setTipoHabitacion(tipoHabitacion);
              reserva.setFechaSalida(fechaSalida);
              fechaSalidaReserva = reserva.getFechaSalida();
            }
            else
              return false;
          }
          
          
          if (fechaIngreso.isBefore(fechaIngresoReserva) && fechaSalida.isAfter(fechaSalidaReserva))
          {
            resultado = hayDisponibilidadPorTipo(tipoHabitacion, fechaIngreso, fechaSalida);
            if (resultado == true)
            {
              reserva.setTipoHabitacion(tipoHabitacion);
              reserva.setFechaIngreso(fechaIngreso);
              reserva.setFechaSalida(fechaSalida);
            }
            else
              return false;
          }
          
          
          
          else
          {
            if (fechaIngreso.isBefore(fechaIngresoReserva))
            {
              resultado = hayDisponibilidadPorTipo(tipoHabitacion, fechaIngreso, fechaSalidaReserva);
              if (resultado == true)
              {
                reserva.setTipoHabitacion(tipoHabitacion);
                reserva.setFechaIngreso(fechaIngreso);
              }
              else
                return false;
            }
            
            if (fechaSalida.isAfter(fechaSalidaReserva))
            {
              resultado = hayDisponibilidadPorTipo(tipoHabitacion, fechaIngresoReserva, fechaSalida);
              if (resultado == true)
              {
                reserva.setTipoHabitacion(tipoHabitacion);
                reserva.setFechaSalida(fechaSalida);
              }
              else
                return false;
            }
          }
        }
      }
      return true;
    }
    return false;
  }

  //Revizar costos adicionales de modificar Estadia y Habitacion
  public boolean modificarEstadia(int numero, int numeroCliente, 
      LocalDate fechaSalida, String observaciones)
  {
    Estadia estadia = buscarEstadia(numero);
    
    if (estadia != null)
    {
      boolean resultado;
      Cliente clienteEstadia = estadia.getCliente();
      LocalDate fechaSalidaEstadia = estadia.getFechaSalida();
      String observacionesEstadia = estadia.getObservaciones();
      
      Cliente cliente = buscarCliente(numeroCliente);
      
      if (cliente != null && !cliente.equals(clienteEstadia))
        estadia.setCliente(cliente);
      
      if (!observaciones.equals(observacionesEstadia))
        estadia.setObservaciones(observaciones);
      
      if (!fechaSalida.equals(fechaSalidaEstadia))
      {
        if (fechaSalida.isBefore(fechaSalidaEstadia))
          estadia.setFechaSalida(fechaSalida);
        else
        {
          String tipo = estadia.getTipoHabitacion();
          resultado = hayDisponibilidadPorTipo(tipo, fechaSalidaEstadia, fechaSalida);
          if (resultado == true)
            estadia.setFechaSalida(fechaSalida);
          else
            return false;
        }
      }
      return true;
    }
    return false;
  }

  public void modificarServicioAdicional(int numero, String descripcion, double precio)
  {
    ServicioAdicional servicio = buscarServicioAdicional(numero);
    
    if (servicio != null)
    {
      String descripcionServicio = servicio.getDescripcion();
      double precioServicio = servicio.getPrecio();
      
      if (!descripcion.equals(descripcionServicio))
        servicio.setDescripcion(descripcion);
      
      if (precio != precioServicio)
        servicio.setPrecio(precio);
    }
  }

  public void modificarValorTarifa(String tipoHabitacion, double precio)
  {
    boolean rta = existeTipoHabitacion(tipoHabitacion);
    if (rta == true)
      tarifario.modificarValorTarifa(tipoHabitacion, precio);
  }

  public int cantidadHabitacionesDeTipo(String tipoHabitacion)
  {
    boolean rta = existeTipoHabitacion(tipoHabitacion);
    int c = 0;
    if(rta == true)
    {
      for(Habitacion h:habitaciones)
      {
        boolean inactiva = h.estasInactiva();
        if(inactiva == false)
        {
          if(h.tuTipoEs(tipoHabitacion))
              c++;
        }
      }
    }
    return c;
  }

  public boolean hayDisponibilidadPorTipo(String tipoHabitacion, 
      LocalDate fechaIngreso, LocalDate fechaSalida)
  {
    int disp = calcularDisponibilidadPorTipo(tipoHabitacion, fechaIngreso, fechaSalida); 
    return disp > 0;
  }

  /** 
   * -El dia de salida en si no se computa como un dia de la estadia; 
   * una estadia del 1 al 7 de enero tiene 6 dias, saliendo el 7
   * -Se considera:
   *   1. Que las fechas sean coherentes
   *   2. Dia por dia, que haya por lo menos una habitacion que cumpla los 
   *      requisitos
   */
  public int calcularDisponibilidadPorTipo(String tipoHabitacion, 
      LocalDate fechaIngreso, LocalDate fechaSalida)
  {  
    boolean rta = existeTipoHabitacion(tipoHabitacion);
    if (rta == true)
    {
      rta = validarFecha(fechaIngreso, fechaSalida);
      if (rta == true)
      {
        int cant = cantidadHabitacionesDeTipo(tipoHabitacion);
        if (cant > 0) //deben existir habitaciones de ese tipo
        {
          //Se crean 2 vectores auxiliares con las reservas y estadias que son solo del tipo de habitacion buscado, para mejor rendimiento
          Vector<Reserva> rTipo = buscarReservasPorTipo(tipoHabitacion); //reservas del tipo buscado
          Vector<Estadia> eTipo = buscarEstadiasPorTipo(tipoHabitacion); //estadias del tipo buscado    
          int disponibles = cant;
          int menorCantDisponible = cant;
          LocalDate fechaAux = fechaIngreso; //fecha auxiliar: empieza en la fecha de ingreso, y se aumenta dia a dia hasta el dia de salida
          while (disponibles > 0 && fechaAux.isBefore(fechaSalida)) //si hay 0 disponibles hay que devolver falso
          {
            disponibles = cant; //se parte desde que todas las habitaciones de este tipo estan disponibles, y se van descontando
            for (Reserva r: rTipo)
              if (r.tenesElDia(fechaAux))
                disponibles--;
            
            for (Estadia e: eTipo)
              if (e.tenesElDia(fechaAux)) 
                disponibles--;
            
            if (disponibles < menorCantDisponible)
              menorCantDisponible = disponibles;
            fechaAux = fechaAux.plusDays(1); //se aumenta el auxiliar en 1 dia
          } 
          return menorCantDisponible; //si en ninguno de los casos anteriores de devolvio falso, es porque hay disponibilidad
        }
      }
    }
    return 0;
  }

  public Vector<String> getListadoHabitacionesDisponiblesHoyPorTipo(String tipoHabitacion)
  {
    boolean rta = existeTipoHabitacion(tipoHabitacion);
    Vector <String> v = new Vector<String>();
    if(rta == true)
    {
      for(Habitacion h:habitaciones)
      {
        boolean disponible = h.estasDisponible();
        if(disponible == true)
        {
          boolean esDeTipo = h.tuTipoEs(tipoHabitacion);
          if(esDeTipo == true)
          {
            String num = h.getNumero();
            v.add(num);
          }
        }
      }
    }
    return v;
  }

  public boolean validarFecha(LocalDate fechaIngreso, LocalDate fechaSalida)
  {
    return (fechaIngreso.compareTo(fechaSalida) < 0); //la fecha de salida no puede ser mayor o igual a la fecha de entrada
  }

  public boolean loginTrabajador(String usuario, String password)
  {
    boolean rta = false;
    Trabajador trabajador = buscarTrabajadorPorUsuario(usuario);
    if (trabajador != null)
    {
      rta = trabajador.esTuPassword(password);
      if(rta == true)
        setTrabajadorValidado(trabajador);
    }
    return rta;
  }
  
  public boolean existeTipoHabitacion(String tipo)
  {
    return tarifario.existeTipoHabitacion(tipo);
  }

  public Vector<ItemDisponibilidad> consultarDisponiblesPorPeriodo(
      LocalDate fechaIngreso, LocalDate fechaSalida)
  {
    Vector<ItemDisponibilidad> disponibilidades = new Vector<ItemDisponibilidad>();
    boolean rta = validarFecha(fechaIngreso, fechaSalida);
    if (rta == true)
    {
      Vector<String> tipos = tarifario.getTiposActivos();
      int cantidadDisponible;
      for (String tipo: tipos)
      {
        cantidadDisponible = calcularDisponibilidadPorTipo(tipo, fechaIngreso, fechaSalida);
        ItemDisponibilidad item = new ItemDisponibilidad(tipo, cantidadDisponible);
        disponibilidades.add(item);
      }
    }   
    return disponibilidades;
  }
  
  public Vector<String> getTiposHabitacionesActivos()
  {
    return tarifario.getTiposActivos();
  }
  
  public TrabajadorView getTrabajadorValidado()
  {
    return trabajadorValidado.getView();
  }
  
  public boolean validarNumeroDocumento(String numeroDocumento)
  {
    String patron = "[-+]?\\d*\\.?\\d+";
    return numeroDocumento != null && numeroDocumento.matches(patron);
  }
  
  public boolean validarNumeroCliente(String numeroCliente)
  {
    String patron = "[-+]?\\d*\\.?\\d+";
    return numeroCliente != null && numeroCliente.matches(patron);
  }
  
  public boolean validarNumeroLegajoTrabajador(String legajo)
  {
    String patron = "[-+]?\\d*\\.?\\d+";
    return legajo != null && legajo.matches(patron);
  }
  
  public boolean validarNumeroHabitacion(String numero)
  {
    String patron = "[-+]?\\d*\\.?\\d+";
    return numero != null && numero.matches(patron);
  }
  
  public boolean validarNumeroReserva(String numero)
  {
    String patron = "[-+]?\\d*\\.?\\d+";
    return numero != null && numero.matches(patron);
  }
  
  public boolean validarEmail(String email)
  {
    String patron = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    return email != null && email.matches(patron);
  }
  
  public String getUsuarioSinDominio(String email)
  {
    String regEx = "@[A-Za-z0-9]";
    String usuario[] = email.split(regEx);
    return usuario[0];
  }

  private void setTrabajadorValidado(Trabajador trabajador)
  {
	  trabajadorValidado = trabajador;
  }
  
  public void agregarAdicionalAEstadia(int numeroEstadia, int codigoServicio, int cantidad)
  {
	  Estadia estadia = buscarEstadia(numeroEstadia);
	  if (estadia != null)
	  {
		  ServicioAdicional adicional = buscarServicioAdicional(codigoServicio);
		  if (adicional != null)
		  {
			  if (cantidad > 0)
			  {
				  estadia.agregarAdicional(adicional, cantidad);
			  }
		  }
	  }
  }
} 


		