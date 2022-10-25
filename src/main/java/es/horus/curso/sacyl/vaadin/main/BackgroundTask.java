package es.horus.curso.sacyl.vaadin.main;

import java.util.Timer;
import java.util.TimerTask;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.progressbar.ProgressBar;

public class BackgroundTask extends Dialog {
	private ProgressBar pb;
	private Span span;

	public BackgroundTask() {
		pb = new ProgressBar();
		add(pb);
		span = new Span("0%");
		add(span);
		open();
		
		programarTarea();
	}
	
	private void programarTarea() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				pb.getUI().ifPresent(ui->ui.access(()->{
					if (pb.getValue() + 0.05D < 1) {
						pb.setValue(pb.getValue()+ 0.05D);
						span.setText(Math.round(pb.getValue()*100)+"%");
						programarTarea();
					} else {
						pb.setValue(1D);
						span.setText("100%");
					}
					ui.push();
				}));
			}
		};
		new Timer().schedule(task, 200);
	}
}
