package tp_Integrador_package;

public class Ronda {
	private String nro;
	private Partido[] partidos;
	
	public Ronda(Partido[] matchs, String matchNumber) {
		this.nro = matchNumber;
		this.partidos = matchs;
	}
	
	public int puntos() {
		return 0;
	}
	
	public void verPartidos() {
		System.out.println("\t\t\t\tResultados: " + this.nro );
		System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%15s\t\t %12s\t\t %12s \t\t%15s", "Equipo 1", "Cant. goles 1", "Cant. goles 2", "Equipo 2");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------");
		for(int i = 0; i < partidos.length; i++) {
			this.partidos[i].verPartido();
		}
	}
}
