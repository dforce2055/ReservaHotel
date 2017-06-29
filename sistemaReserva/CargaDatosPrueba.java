package sistemaReserva;

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
		String[] tipos = {"Doble", "Triple"}; 
		double[] precios = { 750, 1000 };
		int[] cantidades = { 2, 1 };
		
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
		
		int clientes = 4;
		for (int i = 0; i < clientes; i++)
			s.altaCliente("Cliente " + i, "Apellido " + i, "DNI", i * 1000 + "",
					"Libertador " + i*i, i*i*i + "", "email" + i + "@asd.com");
		
		s.altaTrabajador("Pepe", "Argento", "DNI", "666", "Desconocida", "Sin pagar",
				"La internet era cara", "pepe", "chevy");
		s.altaTrabajador("Maria Elena", "Fuseneko", "DNI", "667", "Y candela?",
				"???", "?@?.?", "maria", "whiskey");
	}
}
