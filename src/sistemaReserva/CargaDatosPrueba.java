package sistemaReserva;

import java.time.LocalDate;

public class CargaDatosPrueba
{
  SistemaReserva s;
  
  public CargaDatosPrueba(SistemaReserva sis)
  {
    s = sis;
  }
  
  public void CargarDatos()
  {
    //Carga de habitaciones
    String[] tipos = {"Individual", "Doble", "Triple"}; 
    double[] precios = { 50, 750, 1000 };
    int[] cantidades = { 5, 2, 1 };
    
    //loopear por cada tipo de habitacion
    for (int i = 0; i < tipos.length; i++)
    {
      //alta de tarifa
      s.altaTarifa(tipos[i], precios[i]);
      
      //crear cantidad cantidades[i] de habitaciones del tipo: tipo[i]
      for (int j = 0; j < cantidades[i]; j++)
        s.altaHabitacion((i * 100) + j + "", i + "",
            "Habitacion #" + j + " de tipo " + tipos[i], "caracteristicas?",
            tipos[i]);
    }
    
    s.altaHabitacion("110", "1", "descripcion", "caracteristicas", "Individual");
    s.altaHabitacion("200", "2", "descripcion", "caracteristicas", "Doble");
    
    
    int clientes = 4;
    for (int i = 0; i < clientes; i++)
      s.altaCliente("Cliente " + i, "Apellido " + i, "DNI", i * 1000 + "",
          "Libertador " + i*i, i*i*i + "", "email" + i + "@asd.com");
    
    s.altaCliente("Diego", "P\u00e9rez", "DNI", "12261201", "Lenguado", "4010-666", "diego@mail.com");
    
    s.altaTrabajador("Pepe", "Argento", "DNI", "666", "Desconocida", "Sin pagar",
        "La internet era cara", "pepe", "chevy");
    s.altaTrabajador("Maria Elena", "Fuseneko", "DNI", "667", "Y candela?",
        "???", "?@?.?", "maria", "whiskey");
    
    s.altaReserva(5, 1, "Individual", LocalDate.of(2017, 7, 18), LocalDate.of(2017, 7, 28), "observaciones");
  }
}