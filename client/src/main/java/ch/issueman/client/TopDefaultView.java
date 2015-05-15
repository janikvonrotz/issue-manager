package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
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
	SplitMenuButton smButton;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Standardmässig Left Mangel view anzeigen
		smButton.setText("Eingeloggt als: " + Context.getLogin().getPerson().getEmail());
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
	public void clickAdmin() {
		MainView.showLeftView("LeftAdmin");
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
