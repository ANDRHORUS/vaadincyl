package es.horus.curso.sacyl.vaadin.main;

import java.time.LocalDate;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import es.horus.curso.sacyl.vaadin.utils.PacienteBE;

public class EdicionPacienteView extends Dialog {

	public EdicionPacienteView(PacienteBE pac, PacientesView pacientesView) {
		Binder<PacienteBE> bi = new Binder<>(PacienteBE.class);
		bi.setBean(pac);

		TextField nombre = new TextField("Nombre");
		bi.bind(nombre, PacienteBE::getNombre, PacienteBE::setNombre);
		TextField ape1 = new TextField("Apellido 1");
		// Binding con validacion
		bi.forField(ape1).asRequired("Apellido 1 obligatorio").withValidator(g -> g.length() < 20, "Demasiado largo")
				.bind(PacienteBE::getApe1, PacienteBE::setApe1);

		DatePicker fhNac = new DatePicker("Fh. nac.");
		fhNac.setMin(LocalDate.of(1800, 1, 1));
		bi.bind(fhNac, PacienteBE::getFhNac, PacienteBE::setFhNac);

		ComboBox<String> gen = new ComboBox<>("Genero");
		gen.setItems("H", "M");
		gen.setItemLabelGenerator(i -> "H".equals(i) ? "Hombre" : "Mujer");
		bi.bind(gen, PacienteBE::getGenero, PacienteBE::setGenero);

		FormLayout fl = new FormLayout();
		fl.setResponsiveSteps(new ResponsiveStep("800px", 3));
		fl.setResponsiveSteps(new ResponsiveStep("1000px", 4));
		fl.add(nombre, ape1, fhNac, gen);

		add(fl);
		open();

		Button aceptarBtn = new Button("Aceptar", e -> {
			if (bi.isValid()) {
				pacientesView.recargarTabla();
				close();
			}
		});
		bi.addValueChangeListener(e->{
			aceptarBtn.setEnabled(bi.isValid());
		});
		aceptarBtn.setEnabled(bi.isValid());
		Button cancelarBtn = new Button("Cancelar", e -> close());
		cancelarBtn.getStyle().set("color", "red");

		add(new HorizontalLayout(aceptarBtn, cancelarBtn));
	}

}
