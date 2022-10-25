package es.horus.curso.sacyl.vaadin.main;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;

public class DemoJavaScript extends Dialog {
	
	public DemoJavaScript() {
		add("Texto recibido JS: ");
		String js = """
				var inputValue = prompt($0);
				this.$server.recibirJS(inputValue);
				""";
//		UI.getCurrent().getPage().executeJs(js, "Introduzca un valor:");
		getElement().executeJs(js, "Introduzca un valor:");
		
		open();
	}

	@ClientCallable
	public void recibirJS(String inputValueJS) {
		add(new Span(inputValueJS));
	}

}
