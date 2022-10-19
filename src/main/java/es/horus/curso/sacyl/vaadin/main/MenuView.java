package es.horus.curso.sacyl.vaadin.main;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

public class MenuView extends AppLayout implements RouterLayout  {
	private Tabs tabsMenu;

	public MenuView() {
		DrawerToggle drawerToggle = new DrawerToggle();
		addToNavbar(drawerToggle);
		
		VerticalLayout menuLayout = new VerticalLayout();
        menuLayout.setPadding(false);
        menuLayout.add(buildMenu());
        addToDrawer(menuLayout);
	}
	
	private Tabs buildMenu() {
    	tabsMenu = new Tabs();
    	tabsMenu.setOrientation(Tabs.Orientation.VERTICAL);
    	tabsMenu.setSizeFull();
    	
    	tabsMenu.add(buildTab("inicio", VaadinIcon.HOME.create(), InicioView.class));
    	tabsMenu.add(buildTab("pacientes", VaadinIcon.USERS.create(), PacientesView.class));
    	return tabsMenu;
    }
    
    private Tab buildTab(String txt, Icon icon, Class<? extends Component> view) {
        RouterLink routerLink = new RouterLink(view);
        routerLink.add(new HorizontalLayout(icon, new Span(StringUtils.capitalize(txt))));
        return new Tab(routerLink);
    }
}
