package tp_Integrador_package;

public class Persona {
	private String nombre;
	private Pronostico[] miPronostico = new Pronostico[2];
	private int misPuntos;
	
	public Persona(String nom, Pronostico[] prono) {
		this.miPronostico = prono;
		this.nombre = nom;
		this.misPuntos = 0;
	}
	
	public void verPuntos() {
		for(int i = 0; i < miPronostico.length; i++) {
			misPuntos = misPuntos + miPronostico[i].puntos();
		}
		System.out.println();
		System.out.println();
		System.out.printf("\t\t\t\tPuntaje = %5s", misPuntos);
		System.out.println();
	}
	
	public void verPRonostico() {
		System.out.printf("\t\t\t\tPronosticos de: %2s", nombre );
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.printf("%15s\t\t %8s\t\t %8s \t\t%8s \t\t%15s", "Equipo 1", "Gana 1", "Empata", "Gana 2", "Equipo 2");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        
        Partido part = null;
        String gana = null;
    	String empata = null;
    	String pierde = null;
        
        for(int i = 0; i < miPronostico.length; i++) {
        	part = miPronostico[i].getPartido();
        	gana = "";
        	empata = "";
        	pierde = "";
        	
        	if(miPronostico[i].getResultado() == ResultadoEnum.GANADOR) {
        		gana = "X";
        	}
        	if(miPronostico[i].getResultado() == ResultadoEnum.PERDEDOR) {
        		pierde = "X";
        	}
        	if(miPronostico[i].getResultado() == ResultadoEnum.EMPATE) {
        		empata = "X";
        	}
        	
            System.out.printf("%15s\t\t %8s\t\t %8s \t\t%8s \t\t%15s", part.getNombreEquipo1(), gana, empata, pierde, part.getNombreEquipo2());
            System.out.println();
            System.out.println();
        }
	}
	
	
}
