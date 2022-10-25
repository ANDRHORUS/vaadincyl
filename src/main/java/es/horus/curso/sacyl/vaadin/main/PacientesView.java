package es.horus.curso.sacyl.vaadin.main;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import es.horus.curso.sacyl.vaadin.utils.PacienteBE;

//@Route(value = "/pacientes", layout = MenuView.class)
@CssImport("./styles/shared-styles.css")
@CssImport(value="./styles/common-styles.css", themeFor = "vaadin-grid")
public class PacientesView extends VerticalLayout {
	
	private Grid<PacienteBE> tabla;
	private List<PacienteBE> datos;

	public PacientesView() {
		datos = PacienteBE.DATOS_PRUEBA;
		tabla = new Grid<>();
		tabla.setId("");
		tabla.addClassName("fondo-azul");
		tabla.addColumn(p->p.getNombre()).setHeader("Nombre").setResizable(true).setFlexGrow(2).setSortable(true);
		tabla.addColumn(PacienteBE::getApe1).setHeader("Apellido 1").setResizable(true).setFlexGrow(1).setClassNameGenerator(p->calculaClase(p));
		tabla.addColumn(p->p.getFhNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).setHeader("Fh. Nac.").setResizable(true).setFlexGrow(0).setWidth("100px");
		tabla.addComponentColumn(p->buildIcon(p));
		
		tabla.asSingleSelect().addValueChangeListener(p-> {
			if (p.getValue() != null) {
				new EdicionPacienteView(p.getValue(), this);
			}
		});
		add(tabla);
		recargarTabla();
	}

	public void recargarTabla() {
		tabla.setItems(datos);
	}

	private Component buildIcon(PacienteBE p) {
		Icon icon;
		if (p.getGenero().equals("H")) {
			icon = VaadinIcon.MALE.create();
			icon.setColor("lightblue");
		} else {
			icon = VaadinIcon.FEMALE.create();
			icon.setColor("lightpink");
		}
		return icon;
	}

	private String calculaClase(PacienteBE p) {
		if (p.getGenero().equals("H")) {
			return "azul";
		}
		return "rosa";
	}

	
}
