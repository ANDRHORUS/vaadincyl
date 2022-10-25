package es.horus.curso.sacyl.vaadin.main;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.Lumo;

public class MenuView extends AppLayout implements RouterLayout {
	private Tabs tabsMenu;

	public MenuView() {
		
		DrawerToggle drawerToggle = new DrawerToggle();
		addToNavbar(drawerToggle);
		
		Button btnJs = new Button("JS", e->new DemoJavaScript());
		btnJs.getStyle().set("margin-left", "auto");
		btnJs.getStyle().set("margin-right", "5px");
		addToNavbar(btnJs);
		addToNavbar(new Button("BackgroundTask", e->new BackgroundTask()));
		
		addToNavbar(buildThemeSelector());
		
		Object attribute = VaadinSession.getCurrent().getAttribute("login");
		if(attribute != null) {
			addToNavbar(new Span("Usr: "+attribute));
		}
		if (attribute != null) {
			addToNavbar(new Button("Salir", e->logout()));
		}
		if (attribute == null) {
			addToNavbar(new Button("Login", e->getUI().ifPresent(ui -> ui.navigate("login"))));
		}

		VerticalLayout menuLayout = new VerticalLayout();
		menuLayout.setPadding(false);
		menuLayout.add(buildMenu());
		addToDrawer(menuLayout);
	}

	private void logout() {
		getUI().ifPresent(ui -> ui.navigate("login"));
		RouteConfiguration.forSessionScope().removeRoute(PacientesView.class);
		VaadinSession.getCurrent().close();
	}

	private Component buildThemeSelector() {
		Checkbox toggleButton = new Checkbox("Tema oscuro", e -> {
			ThemeList themeList = UI.getCurrent().getElement().getThemeList();
			if (themeList.contains(Lumo.DARK)) {
				themeList.remove(Lumo.DARK);
			} else {
				themeList.add(Lumo.DARK);
			}
		});
		return toggleButton;
	}

	private Tabs buildMenu() {
		tabsMenu = new Tabs();
		tabsMenu.setOrientation(Tabs.Orientation.VERTICAL);
		tabsMenu.setSizeFull();

		tabsMenu.add(buildTab("inicio", VaadinIcon.HOME.create(), InicioView.class));
		Object attribute = VaadinSession.getCurrent().getAttribute("login");
		if(attribute != null) {
			tabsMenu.add(buildTab("pacientes", VaadinIcon.USERS.create(), PacientesView.class));
		}
		return tabsMenu;
	}

	private Tab buildTab(String txt, Icon icon, Class<? extends Component> view) {
		RouterLink routerLink = new RouterLink(view);
		routerLink.add(new HorizontalLayout(icon, new Span(StringUtils.capitalize(txt))));
		return new Tab(routerLink);
	}
}
