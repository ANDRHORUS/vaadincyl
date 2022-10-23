package es.horus.curso.sacyl.vaadin.main;

import java.time.LocalDate;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import es.horus.curso.sacyl.vaadin.utils.PacienteBE;

@PageTitle("Vista basica")
@Route(value = "")
@CssImport("./styles/shared-styles.css")
@CssImport(value="./styles/common-styles.css", themeFor = "*")
public class BasicView extends HorizontalLayout {

//	private Binder<PacienteBE> bi;

    public BasicView() {
        setMargin(true);
        
        Div div = new Div();
        HorizontalLayout hl;
        VerticalLayout vl = new VerticalLayout();
        TextField name = new TextField("Nombre");
        
        FormLayout fl = new FormLayout(name, new TextField("Textfield 1"), new TextField("Textfield 2"),new TextField("Textfield 3"));
        fl.setResponsiveSteps(new ResponsiveStep("800px", 3));
        fl.setResponsiveSteps(new ResponsiveStep("1000px", 4));
        Dialog dialog = new Dialog();
        dialog.add(fl, new Button("Cerrar", e->dialog.close()));
//        dialog.open();
        dialog.setCloseOnOutsideClick(false);
		dialog.addDialogCloseActionListener(e -> {
			Notification.show("Adios");
			dialog.close();
		});
		
		PacienteBE pac = new PacienteBE("Nombre", "Ape", LocalDate.of(1999, 1, 2), "H");
		Binder<PacienteBE> bi = new Binder<>(PacienteBE.class);
		bi.setBean(pac);
		
		TextField nombre = new TextField("Nombre");
		bi.bind(nombre, PacienteBE::getNombre, PacienteBE::setNombre);
		nombre.addValueChangeListener(e->Notification.show("¡Hola " + pac.getNombre()+" !"));
		nombre.setValueChangeMode(ValueChangeMode.EAGER);
		
        Button sayHello = new Button("Saludar");
        sayHello.addClickListener(e -> {
        	Notification.show("¡Hola " + pac.getNombre()+" !");
        	dialog.open();
        });
        
        DatePicker fhNac = new DatePicker("Fh nac");
        fhNac.setMax(LocalDate.of(3000, 1, 1));
		bi.bind(fhNac, PacienteBE::getFhNac, PacienteBE::setFhNac);
//        sayHello.addClickShortcut(Key.ENTER);
        Button inicioBtn = new Button("Inicio", VaadinIcon.HOME.create(), e -> getUI().ifPresent(ui -> ui.navigate("inicio")));
//        
//        add(sayHello, combo, comboPaciente, checksPac, date);

		add(sayHello, inicioBtn, nombre, fhNac);
		
		
		Grid<PacienteBE> tabla = new Grid<>();
		tabla.addColumn(p->p.getNombre()).setHeader("Nombre").setResizable(true).setFlexGrow(1).setSortable(true);
		tabla.addColumn(PacienteBE::getApe1).setHeader("Apellido 1").setResizable(true).setFlexGrow(2).setClassNameGenerator(p->calculaClase(p));
		tabla.addComponentColumn(p->buildBtn(p));
		
		tabla.asSingleSelect().addValueChangeListener(p->bi.setBean(p.getValue()));
		
		
		tabla.setItems(PacienteBE.DATOS_PRUEBA);
		add(tabla);
		
		
//        setVerticalComponentAlignment(Alignment.END, name, sayHello, inicioBtn);
    }

	private Component buildBtn(PacienteBE p) {
		return VaadinIcon.AMBULANCE.create();
	}

	private String calculaClase(PacienteBE p) {
		if (p.getGenero().equals("H")) {
			return "azul";
		}
		return "rosa";
	}

}
