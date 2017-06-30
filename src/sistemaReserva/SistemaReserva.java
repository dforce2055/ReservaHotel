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
  }
  
  private Cliente buscarCliente(int codigo)
  {
    for (Cliente c: clientes)
      if (c.sosCliente(codigo))
        return c;
    return null;
  }
  
  private Cliente buscarClientePorDocumento(String tipoDoc, String numDoc)
  {
    for (Cliente c: clientes)
      if (c.esTuDocumento(tipoDoc, numDoc))
        return c;
    return null;
  }
  
  private Trabajador buscarTrabajador(int legajo)
  {
    for (Trabajador t: trabajadores)
      if (t.sosTrabajador(legajo))
        return t;
    return null;
  }
  
  private Trabajador buscarTrabajadorPorDocumento(String tipoDoc, String numDoc)
  {
    for (Trabajador t: trabajadores)
      if (t.esTuDocumento(tipoDoc, numDoc)) 
        return t;
    return null;
  }
  
  //usado para el login
  private Trabajador buscarTrabajadorPorUsuario(String usuario)
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
        return h;
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
  
  public void altaCliente(String nombre, String apellido, String tipoDoc, 
      String numDoc, String direccion, String telefono, String email)
  {
    Cliente cliente = buscarClientePorDocumento(tipoDoc, numDoc);
    if (cliente == null)
    {
      cliente = new Cliente(nombre, apellido, tipoDoc, numDoc, direccion, telefono, email);
      clientes.add(cliente);
    }
  }
  
  public void altaTrabajador(String nombre, String apellido, String tipoDoc, 
      String numDoc, String direccion, String telefono, String email, 
      String usuario, String password)
  {
    Trabajador trabajador = buscarTrabajadorPorDocumento(tipoDoc, numDoc);
    if (trabajador == null)
    {
      trabajador = new Trabajador(nombre, apellido, tipoDoc, numDoc, direccion, telefono, email, usuario, password);
      trabajadores.add(trabajador);
    }
  }
  
  public void altaHabitacion(String numero, String piso, String descripcion, 
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
      }
    }
  }
  
  public void altaReserva(int codigoCliente, int legajoTrabajador, 
      String tipoHabitacion, LocalDate fechaIngreso, LocalDate fechaSalida, 
      String observaciones)
  {
    boolean resultado = existeTipoHabitacion(tipoHabitacion); 
    if (resultado == true)
    {
      Cliente cliente = buscarCliente(codigoCliente);
      if (cliente != null)
      {
        Trabajador trabajador = buscarTrabajador(legajoTrabajador);
        if (trabajador != null)
        {
          resultado = hayDisponibilidadPorTipo(tipoHabitacion, fechaIngreso, fechaSalida);
          if (resultado == true)
          {
            double costoReserva = tarifario.getPrecio(tipoHabitacion);
            Reserva reserva = new Reserva(cliente, trabajador, tipoHabitacion, fechaIngreso, fechaSalida, costoReserva, observaciones);
            reservas.add(reserva);
          }
        }
      } 
    }
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
          String tipoHabitacion = habitacion.getTipoHabitacion();
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

  public void altaTarifa(String tipoHabitacion, double precio)
  {
    boolean resultado = existeTipoHabitacion(tipoHabitacion);
    if (resultado == false)
    {
      tarifario.agregarItem(tipoHabitacion, precio);
    }
  }
  
  public void bajaCliente(int codigo)
  {
    Cliente cliente = buscarCliente(codigo);
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
  
  public void bajaReserva(int numero)
  {
    Reserva reserva = buscarReserva(numero);
    if (reserva != null)
    {
      reservasInactivas.add(reserva);
      reservas.remove(reserva);
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
  
  public boolean loginTrabajador(String usuario, String pw)
  {
    boolean rta = false;
    Trabajador t = buscarTrabajadorPorUsuario(usuario);
    if (t != null)
      rta = t.esTuContrasenia(pw);
    return rta;
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
  
  /** 
   * -El dia de salida en si no se computa como un dia de la estadia; 
   * una estadia del 1 al 7 de enero tiene 6 dias, saliendo el 7
   * -Se considera:
   *   1. Que las fechas sean coherentes
   *   2. Dia por dia, que haya por lo menos una habitacion que cumpla los 
   *      requisitos
   */
  public int calcularDisponibilidadPorTipo(String tipoHabitacion, LocalDate fechaIngreso, LocalDate fechaSalida)
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
  public boolean hayDisponibilidadPorTipo(String tipoHabitacion, LocalDate fechaIngreso, LocalDate fechaSalida)
  {
    int disp = calcularDisponibilidadPorTipo(tipoHabitacion, fechaIngreso, fechaSalida); 
    return disp > 0;
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
  
  public boolean validarFecha(LocalDate fIng, LocalDate fSal)
  {
    return (fIng.compareTo(fSal) < 0); //la fecha de salida no puede ser mayor o igual a la fecha de entrada
  }
  
  /**
   * imprime la cantidad de habitaciones disponibles por cada tipo;
   * cuando haya interfaz va a tener que devolver un vector de nodos con tipoHab (string) y cantidad
   * (o sea un diccionario) 
   */ 
  public void consultarDisponiblesPorPeriodo(LocalDate fIng, LocalDate fSal)
  {  //version 2.0, deberia andar mejor... si anda
    if (!validarFecha(fIng, fSal))
    {
      System.out.println("Fecha invalida!");
      return; //fecha invalida
    }
    Vector<String> tipos = tarifario.getTipos(); //creo que seria mejor que tengamos un vector con los tipos y su abm, hay que ver como entraria en el diagrama  
    if (tipos == null)
    {
      System.out.println("No existe ningun tipo de habitacion!");
      return; //no existen tipos de habitaciones
    }
    int[] minCantidadesDisponibles = new int[tipos.size()];
    int[] cantidadesHabitaciones = new int[tipos.size()];
    for (int i = 0; i < tipos.size(); i++)
    {
      cantidadesHabitaciones[i] = cantidadHabitacionesDeTipo(tipos.elementAt(i));
      minCantidadesDisponibles[i] = cantidadesHabitaciones[i];
    }
    //se podria verificar aca si las cantidades son todas 0, o sea que no hay habitaciones
    LocalDate fAux = fIng; //fecha auxiliar: empieza en la fecha de ingreso, y se aumenta dia a dia hasta el dia de salida
    while (fAux.isBefore(fSal)) 
    {
      int[] disponibles = new int[tipos.size()];
      for (int i = 0; i < disponibles.length; i++)
        disponibles[i] = cantidadesHabitaciones[i]; //se parte desde que todas las habitaciones de este tipo estan disponibles, y se van descontando
      for (Reserva r: reservas)
        if (r.tenesElDia(fAux))
          disponibles[tipos.indexOf(r.getTipoHabitacion())]--;
      for (Estadia e: estadias)
        if (e.tenesElDia(fAux)) 
          disponibles[tipos.indexOf(e.getTipoHabitacion())]--;
      int totalDisponibles = 0; //usado mas adelante para cortar antes si en algun dia ya no hay absolutamente nada disponible
      for (int i = 0; i < disponibles.length; i++)
      {
        if (disponibles[i] < minCantidadesDisponibles[i])
          minCantidadesDisponibles[i] = disponibles[i];
        totalDisponibles += minCantidadesDisponibles[i];
      }
      if (totalDisponibles == 0)
        break; //si en algun dia hay 0 disponibles, entonces no hay nada disponible y se sale del loop de comparacion fecha a fecha
      fAux = fAux.plusDays(1); //se aumenta el auxiliar en 1 dia
    }
    System.out.println("--- DISPONIBILIDAD DESDE " + fIng.toString() + " HASTA " + fSal.toString() + " ---");
    for (int i = 0; i < minCantidadesDisponibles.length; i++)
      System.out.println(tipos.elementAt(i) + ": " + minCantidadesDisponibles[i]);    
  } 
  
  public DisponibilidadesDTO consultarDisponiblesPorPeriodoDTO(LocalDate fIng, LocalDate fSal)
  {
    DisponibilidadesDTO d = new DisponibilidadesDTO();
    boolean rta = validarFecha(fIng, fSal);
    if (rta == true)
    {
      Vector<String> tipos = tarifario.getTipos();
      int disp;
      for (String t: tipos)
      {
        disp = calcularDisponibilidadPorTipo(t, fIng, fSal);
        d.agregarItem(t, disp);
      }
    }   
    return d;
  }

  public Vector<String> getListadoHabitacionesDisponiblesHoyPorTipo(String tipoHabitacion)
  {
    boolean rta = existeTipoHabitacion(tipoHabitacion);
    Vector <String> v = new Vector<String>();
    if(rta)
    {
      for(Habitacion h:habitaciones)
      {
        boolean disponible = h.estasDisponible();
        if(disponible)
        {
          boolean esDeTipo = h.tuTipoEs(tipoHabitacion);
          if(esDeTipo)
          {
            String num = h.getNumero();
            v.add(num);
          }
        }
      }
    }
    return v;
  }

  public boolean existeTipoHabitacion(String tipo)
  {
    return tarifario.existeTipo(tipo);
  }

  public void bajaEstadia(int numero)
  {
    Estadia estadia = buscarEstadia(numero);
    if (estadia != null)
    {
      estadiasInactivas.add(estadia);
      estadias.remove(estadia);
    }
  }
  
  public double cerrarEstadia(int numero)
  {
    double total = 0;
    Estadia estadia = buscarEstadia(numero);
    if (estadia != null)
    {
      total = estadia.cerrarEstadia();
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
      if (validarFecha(fechaEntrada, fechaSalida))
      {
        estadia.setFechaSalida(fechaSalida);
        total = estadia.cerrarEstadia();
        bajaEstadia(numero);
      }
    }
    return total;
  }
  
  public void modificarValorTarifa(String tipoHabitacion, double precio)
  {
    boolean rta = existeTipoHabitacion(tipoHabitacion);
    if (rta == true)
      tarifario.modificarValorTarifa(tipoHabitacion, precio);
  }
  
  public boolean modificarReserva(int numero, int numeroCliente, String tipoHabitacion, LocalDate fechaIngreso, LocalDate fechaSalida, String observaciones)
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
          
          if (fechaIngreso.isBefore(fechaSalidaReserva) && fechaSalida.isAfter(fechaIngresoReserva))
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
  
  public boolean modificarEstadia(int numero, int numeroCliente, LocalDate fechaSalida, String observaciones)
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
} 