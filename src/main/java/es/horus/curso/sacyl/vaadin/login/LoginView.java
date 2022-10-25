package es.horus.curso.sacyl.vaadin.login;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.AbstractLogin.LoginEvent;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;

import es.horus.curso.sacyl.vaadin.main.MenuView;
import es.horus.curso.sacyl.vaadin.main.PacientesView;

@Route("login") 
@PageTitle("Login")
public class LoginView extends VerticalLayout {

	private LoginForm login;

	public LoginView(){
		setSizeFull(); 
		login = new LoginForm();
		login.addLoginListener(e->onLogin(e));
		login.setForgotPasswordButtonVisible(false);

		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);
		add(new H1("Vaadin Login"), login);
	}

	private void onLogin(LoginEvent e) {
		login.setError(true);
		VaadinSession.getCurrent().setAttribute("login", e.getUsername());
//		getUI().ifPresent(ui -> ui.navigate("pacientes"));
		RouteConfiguration.forSessionScope().setRoute("pacientes", PacientesView.class, MenuView.class);
	}
}