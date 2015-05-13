package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Mangel filter view for left pane.
 * 
 * @author Patrick Elsener, Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 */
public class LeftMangelView implements ViewableDetail<Object> {

	// Projekte Controller deklarieren
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// anzeigen center mangel view.
		MainView.showCenterView("Mangel");
		Refresh();
	}

	@Override
	public void Refresh() {
		// List View mit Projekten laden

	}

	@Override
	public void initData(Object t) {
	}

	@Override
	public void showList() {
	}
	
	// egänzen list view click methode mit aufruf der der center Mangel View mit Projekt als parameter
}
