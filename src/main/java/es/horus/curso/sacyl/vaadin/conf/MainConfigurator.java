package es.horus.curso.sacyl.vaadin.conf;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.AppShellSettings;
import com.vaadin.flow.theme.Theme;

@Theme(value = "custom-theme")
@Push
public class MainConfigurator implements AppShellConfigurator {

	@Override
	public void configurePage(AppShellSettings settings) {
		settings.setPageTitle("Pagina de prueba");
		settings.addFavIcon("icon", "favicon.ico", "32x32");
	}
}
