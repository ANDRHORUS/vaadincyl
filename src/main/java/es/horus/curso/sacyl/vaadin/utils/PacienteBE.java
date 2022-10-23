package es.horus.curso.sacyl.vaadin.utils;

import java.time.LocalDate;
import java.util.List;

public class PacienteBE {
	public static List<PacienteBE> DATOS_PRUEBA = List.of(
			new PacienteBE("Manuel", "Sanchez", LocalDate.of(1983, 3, 5), "H"),
			new PacienteBE("Sara", "Perez", LocalDate.of(1913, 5, 15), "M"),
			new PacienteBE("Juan", "Jimenez", LocalDate.of(1956, 7, 23), "H"),
			new PacienteBE("Elena", "Rodriguez", LocalDate.of(1932, 6, 11), "M")
			);
	
	private String nombre;
	private String ape1;
	private LocalDate fhNac;
	private String genero;
	public PacienteBE() {
		super();
	}
	public PacienteBE(String nombre, String ape1, LocalDate fhNac, String genero) {
		super();
		this.nombre = nombre;
		this.ape1 = ape1;
		this.fhNac = fhNac;
		this.genero = genero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApe1() {
		return ape1;
	}
	public void setApe1(String ape1) {
		this.ape1 = ape1;
	}
	public LocalDate getFhNac() {
		return fhNac;
	}
	public void setFhNac(LocalDate fhNac) {
		this.fhNac = fhNac;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
}
