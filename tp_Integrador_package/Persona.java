package tp_Integrador_package;

public class Persona {
	private String nombre;
	private Pronostico miPronostico;
	private int misPuntos;
	
	public Persona(String nom, Pronostico prono) {
		this.miPronostico = prono;
		this.nombre = nom;
		this.misPuntos = 0;
	}
	
	public void verPuntos() {
		misPuntos =  miPronostico.puntos();
		System.out.println("Puntaje = " + this.misPuntos);
	}
	
	public void verPronostico() {
		System.out.println("\t\t\t\tPronostico de " + nombre);
		System.out.println("\t\t\t\tPronostico para " + this.miPronostico.partidoPronostico());
		System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%15s\t\t %12s\t\t %12s \t\t %12s \t\t%15s", "Equipo 1", "Gana 1", "Empata", "Gana 2", "Equipo 2");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------");
        miPronostico.partidoPronostico();
	}
}
