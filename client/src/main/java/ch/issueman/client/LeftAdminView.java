package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Admin filter view for left pane.
 * 
 * @author Patrick Elsener, Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 */
public class LeftAdminView implements ViewableDetail<Object> {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// anzeigen default center view
		Refresh();
	}

	@Override
	public void Refresh() {
	}

	@Override
	public void initData(Object t) {
	}

	@Override
	public void showList() {
	}
	
	// ergänzen button methoden zum anzeigen der entsprechenden center stammdaten view
}
