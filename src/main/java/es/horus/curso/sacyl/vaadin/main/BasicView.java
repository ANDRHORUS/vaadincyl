package es.horus.curso.sacyl.vaadin.main;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Vista basica")
@Route(value = "")
@CssImport("./styles/shared-styles.css")
@CssImport(value="./styles/common-styles.css", themeFor = "*")
public class BasicView extends HorizontalLayout {

//	private Binder<PacienteBE> bi;

    public BasicView() {
        setMargin(true);
        
        TextField name = new TextField("Nombre");
        Button sayHello = new Button("Saludar");
        sayHello.addClickListener(e -> Notification.show("¡Hola " + name.getValue()+"!"));
        sayHello.addClickShortcut(Key.ENTER);
        Button inicioBtn = new Button("Inicio", VaadinIcon.HOME.create(), e -> getUI().ifPresent(ui -> ui.navigate("inicio")));
        
        add(name, sayHello, inicioBtn);

        setVerticalComponentAlignment(Alignment.END, name, sayHello, inicioBtn);
    }

}
