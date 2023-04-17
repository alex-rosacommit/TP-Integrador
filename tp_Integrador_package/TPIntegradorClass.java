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
		List<String> pronoEnArchivo;
		List<Ronda> listaRondas = new ArrayList<Ronda>();
		List<Persona> listaPersonas = new ArrayList<Persona>();
		
		//Lee archivo -resultados.txt-
		resultados = LeerArchivo(resultadosUrl);
		
		//Lee archivo -pronostico.txt-
		pronoEnArchivo = LeerArchivo(pronosticoUrl);
		
		// Se instancian las rondas con sus partidos (segun figure en el archivo -resultados.txt-) 
		listaRondas = instanciarRondas(resultados);
		
		// Se instancian las personas con sus pronosticos (Segun figure en el archivo -pronostico.txt)
		listaPersonas = instanciarPersonas(listaRondas,pronoEnArchivo);
		
		/* En las siguientes lineas podemos consultar los partidos de cada ronda
		for(Ronda rond : listaRondas) {
			rond.verPartidos();
		}
		*/
		
		/* En las siguientes lineas podemos consultar los pronosticos de cada persona.
		for(Persona per : listaPersonas) {
			per.verPronostico();
			per.verPuntos();
		}
		*/
		
		// 
		for(Persona per : listaPersonas) {
			per.verPuntos();
		}
	}
	
	// Metodo que se encarga de leer un archivo desde una ruta y devuelve un List<String> con los datos en el archivo.
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
	
	/*Metodo que se encarga de instanciar las rondas segun los partidos
	 * que recibe en su parametro como un List<String>
	 */
	public static List<Ronda> instanciarRondas(List<String> dataInFile) {
		
		List<Ronda> rondas = new ArrayList<Ronda>();
		
		for(int i = 0; i < cantRondas(dataInFile); i++) {
			
			//Instanciación de una nueva ronda.
			Ronda nuevaRonda = new Ronda(String.valueOf(i+1));
			
			for(String lineaArchivo : dataInFile) {
				String[] datosLineaArch = lineaArchivo.split(";");
				
				if(Integer.valueOf(nuevaRonda.getNumero()) == Integer.valueOf(datosLineaArch[0])) {
					// Instanciación de los equipos y goles de equipos respectivamente.
					Equipo nuevoEquipo1 = new Equipo(datosLineaArch[1]);
					Equipo nuevoEquipo2 = new Equipo(datosLineaArch[4]);
					
					int cantGoles1 = Integer.valueOf(datosLineaArch[2]);
					int cantGoles2 = Integer.valueOf(datosLineaArch[3]);
					
					//Instanciación de Partido a base de los equipos instanciados anteriormente.
					Partido nuevoPartido = new Partido(nuevoEquipo1, nuevoEquipo2, cantGoles1, cantGoles2 );
					
					// Adición de partido a la nueva ronda.
					nuevaRonda.agregarPartido(nuevoPartido);
				}
			}
			// Adición de ronda a la lista de rondas.
			rondas.add(nuevaRonda);
		}
		// Retorno de la lista de rondas
		return rondas;
	}
	
	/*Metodo que se encarga de instanciar cada persona con sus respectivos
	 * pronosticos según el List<String> que resibe en su parametro
	 */
	private static List<Persona> instanciarPersonas(List<Ronda>rondas, List<String> dataInFile){
		List<Persona> personas = new ArrayList<Persona>();
		boolean flag = false;
		
		for(int i = 0; i < dataInFile.size(); i++) {
			String[] dataInLine = dataInFile.get(i).split(";");
			Partido nuevoPartido = null;
			
			for(Ronda ronda : rondas) {
				nuevoPartido = ronda.getPartido(dataInLine[1], dataInLine[5]);
				
				if(nuevoPartido != null) {
					if(dataInLine[3].equals("X")) {
						Pronostico nuevoPronostico = new Pronostico(nuevoPartido, ResultadoEnum.EMPATE);
						
						for(Persona perso : personas) {
							if(perso.getNombre().equals(dataInLine[0])) {
								perso.agregarPronostico(nuevoPronostico);
								flag = true;
							}
						}
						
						if(flag == false) {
							Persona nuevaPersona = new Persona(dataInLine[0], nuevoPronostico);
							personas.add(nuevaPersona);
						}
					}else {
						String nombreEquipo = "";
						flag = false;
						
						if(dataInLine[2].equals("X")) {
							nombreEquipo = dataInLine[1];
						}
						
						if(dataInLine[4].equals("X")) {
							nombreEquipo = dataInLine[5];
						}
						
						Equipo nuevoEquipo = new Equipo(nombreEquipo);
						
						Pronostico nuevoPronostico = new Pronostico(nuevoPartido, nuevoEquipo, ResultadoEnum.GANADOR);
						
						for(Persona perso : personas) {
							if(perso.getNombre().equals(dataInLine[0])) {
								perso.agregarPronostico(nuevoPronostico);
								flag = true;
							}
						}
						
						if(flag == false) {
							Persona nuevaPersona = new Persona(dataInLine[0], nuevoPronostico);
							personas.add(nuevaPersona);
						}
					}
					
				}
			}
		}
		
		return personas;
	}
	
	/*Metodo que se encarga de contar la cantidad de rondas que existen
	 * en el archivo -resultados.txt-.
	 * Se que es algo que podria mejor el programa para que no dependa de este metodo
	 * pero aun no he encontrado la manera.
	 */
	private static int cantRondas(List<String> dataInText) {
		int cantRond = 0;
		List<Ronda> rondas = new ArrayList<Ronda>();
		
		for(int i = 0; i < dataInText.size(); i++) {
			String rondaPartido = dataInText.get(i).split(";")[0];
			if(Integer.valueOf(rondaPartido) != cantRond) {
				cantRond++;
			}
		}
		return cantRond;
	}

}
