package es.horus.curso.sacyl.vaadin.main;

import com.vaadin.componentfactory.pdfviewer.PdfViewer;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.WildcardParameter;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinSession;
import com.wontlost.ckeditor.Config;
import com.wontlost.ckeditor.Constants.EditorType;
import com.wontlost.ckeditor.VaadinCKEditor;
import com.wontlost.ckeditor.VaadinCKEditorBuilder;

@Route(value = "/inicio", layout = MenuView.class)
@CssImport("./styles/shared-styles.css")
@CssImport(value="./styles/common-styles.css", themeFor = "*")
public class InicioView extends VerticalLayout implements HasUrlParameter<String> {
	
	public InicioView() {
		
		setSizeFull();
		add(VaadinSession.getCurrent().getBrowser().getAddress());
		add(" Inicio ");
		H1 h1 = new H1("cabecera");
		add(h1);
		Image im = new Image("favicon.ico", "imagen");
		add(im);
		
		add(new Button("PDF", e->showPdf()));
		
		VaadinCKEditor inlineEditor = new VaadinCKEditorBuilder().with(builder->{
			builder.editorType=EditorType.INLINE;
			builder.editorData="Inline";
			builder.config = new Config();
		}).createVaadinCKEditor();
		
		add(new Button("Exportar", e->Notification.show(inlineEditor.getValue())));
		add(inlineEditor);
		inlineEditor.setSizeFull();
	}

	private void showPdf() {
		PdfViewer pdfViewer = new PdfViewer();
		StreamResource resource = new StreamResource("example.pdf", () -> getClass().getResourceAsStream("/document.pdf"));
		pdfViewer.setSrc(resource);
		pdfViewer.setSizeFull();
		Dialog dialog = new Dialog(pdfViewer);
		dialog.open();
		dialog.setSizeFull();
	}

	@Override
	public void setParameter(BeforeEvent event, @WildcardParameter String parameter) {
//		add("Par: "+parameter);
	}

}
