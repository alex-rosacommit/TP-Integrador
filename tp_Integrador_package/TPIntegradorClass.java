package tp_Integrador_package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TPIntegradorClass {

	public static void main(String[] args) {
		String resultadosUrl = "resultados.txt";
		String pronosticoUrl = "pronostico.txt";
		List<String> resultados;
		List<String> resultadosPronostico;
		String[] dataRow;
		Ronda nuevaRonda;
		
		//Lee archivo -resultados.txt-
		resultados = LeerArchivo(resultadosUrl);
		
		//Array para almacenar los partidos de una ronda
		Partido[] arrayPartidos = new Partido[resultados.size()];
		
		//El bucle instancia los equipos, intancia el partido y lo ubica en un array
		for(int i = 0; i < resultados.size(); i++) {
			dataRow = resultados.get(i).split(";");
			
			Equipo nuevoEquipo1 = new Equipo(dataRow[0]);
			Equipo nuevoEquipo2 = new Equipo(dataRow[3]);
			
			int cantGoles1 = Integer.valueOf(dataRow[1]);
			int cantGoles2 = Integer.valueOf(dataRow[2]);
			Partido nuevoPartido = new Partido(nuevoEquipo1, nuevoEquipo2, cantGoles1, cantGoles2 );
			
			arrayPartidos[i] = nuevoPartido; 
		}
		
		// Instanciacion de Ronda con el array de partidos leidos desde el archivo resultados.txt
		nuevaRonda = new Ronda(arrayPartidos, "Ronda 1");
		
		//Muestra resultados de los partidos
		nuevaRonda.verPartidos();

		//Lee archivo -pronostico.txt- instancia equipos
		resultadosPronostico = LeerArchivo(pronosticoUrl);
		Equipo equipoPronostico = new Equipo("Argentina");
		Equipo equipoPronostico2 = new Equipo("Polonia");
		
		// Crea los pronosticos
		Pronostico pronostico1 = new Pronostico(arrayPartidos[0], equipoPronostico, ResultadoEnum.GANADOR);
		Pronostico pronostico2 = new Pronostico(arrayPartidos[1], ResultadoEnum.EMPATE);
		
		Pronostico[] pronosticoArray = new Pronostico[] {pronostico1, pronostico2};
		
		// Instancia una persona se le asigna un nombre y los pronosticos leidos de archivo
		Persona nuevaPersona = new Persona("Alex", pronosticoArray);
		
		//Muestra el pronostico realizado por la persona
		nuevaPersona.verPRonostico();
		//Consulta la cantidad de aciertos en los pronosticos de la persona.
		nuevaPersona.verPuntos();
		
	}
	
	private static List<String> LeerArchivo(String rutaArchivo) {
		File archivo = null;
		FileReader lectorArchivo = null;
		BufferedReader lectorBuffer = null;
		List<String> lineas = new ArrayList<String>();
		
		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			
			archivo = new File(rutaArchivo);
			lectorArchivo = new FileReader(archivo);
			lectorBuffer = new BufferedReader(lectorArchivo);
			
			//Lectura del fichero
			
			String linea = "";
			
			while( (linea = lectorBuffer.readLine() ) != null) {
				//System.out.println(linea);
				lineas.add(linea);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			 // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
			
			try {
				if(lectorBuffer != null) {
					lectorBuffer.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return lineas;
	}

}
