package es.horus.curso.sacyl.vaadin.main;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "/inicio", layout = MenuView.class)
@CssImport("./styles/shared-styles.css")
@CssImport(value="./styles/common-styles.css", themeFor = "*")
public class InicioView extends VerticalLayout {
	
	public InicioView() {
		add("Inicio");
	}

}
