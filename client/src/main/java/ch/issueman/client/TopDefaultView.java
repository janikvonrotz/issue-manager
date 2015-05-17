package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.SplitMenuButton;

/**
 * Default view for top pane.
 * 
 * @author Patrick Elsener, Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 */

public class TopDefaultView implements ViewableDetail<Object> {
	
	@FXML
	MenuButton mbBenutzer;
	
	@FXML
	MenuButton mbAdmin;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Standardmässig Left Mangel view anzeigen
		mbBenutzer.setText(Context.getLogin().getPerson().getEmail());
		MainView.showLeftView("LeftMangel");
		
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
	
	// egänzen button click methoden mit aufruf der left views.
	@FXML
	public void clickMangel() {
		MainView.showLeftView("LeftMangel");
	}
	
	@FXML
	public void clickProjekt() {
		MainView.showLeftView("LeftAdmin");
		MainView.showCenterView("Projekt");
	}
	
	@FXML
	public void clickSubunternehmen() {
		MainView.showLeftView("LeftAdmin");
		MainView.showCenterView("Subunternehmen");
	}
	
	@FXML
	public void clickPerson() {
		MainView.showLeftView("LeftAdmin");
		MainView.showCenterView("Person");
	}
	
	@FXML
	public void clickLogout() {
		Context.logout();
	}
	
	@FXML
	public void clickPasswortwechsel() {
		MainView.showCenterDetailView("Passwort");
	
	}
}
