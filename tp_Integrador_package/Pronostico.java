package tp_Integrador_package;

public class Pronostico {
	private Partido match;
	private Equipo team;
	private ResultadoEnum result;
	
	public Pronostico(Partido partido, Equipo equipo, ResultadoEnum resultado) {
		this.match = partido;
		this.team = equipo;
		this.result = resultado;
	}
	
	public int puntos() {
		System.out.println(match.resultado(team));
		ResultadoEnum prediccion = match.resultado(team);
		
		if(result == prediccion) {
			return 1;
		}
		return 0;
	}
	
	public String partidoPronostico() {
		String enfrentamiento = match.getNombreEquipo1() + " vs " + match.getNombreEquipo1(); 
		return enfrentamiento;
	}
}
