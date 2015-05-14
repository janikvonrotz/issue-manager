package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

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
//		MainView.showCenterView("Default");
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
	
	@FXML
	public void clickProjekte() {
		MainView.showCenterView("Projekt");
	}
	
	@FXML
	public void clickSubunternehmen() {
		MainView.showCenterView("Subunternehmen");
	}
	
	@FXML
	public void clickBauherr() {
		MainView.showCenterView("Person");
	}
	
}
