package es.horus.curso.sacyl.vaadin.conf;

import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

import es.horus.curso.sacyl.vaadin.main.PacientesView;

public class ServiceConfigurator implements VaadinServiceInitListener {

	@Override
	public void serviceInit(ServiceInitEvent event) {
		event.getSource().addUIInitListener(e -> {
			e.getUI().addBeforeEnterListener(ev->comprobarLogin(ev));
		});
	}

	private void comprobarLogin(BeforeEnterEvent ev) {
		if (PacientesView.class.equals(ev.getNavigationTarget())) {
//			ev.rerouteTo(LoginView.class);
		}
	}

}
