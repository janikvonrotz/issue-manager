package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	private Button btMangel;
	
	@FXML
	private MenuButton mbBenutzer;
	
	@FXML
	private MenuButton mbAdmin;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Standardmässig Left Mangel view anzeigen
		mbBenutzer.setText(Context.getLogin().getPerson().getEmail());
		MainView.showLeftView("LeftMangel");
		
		btMangel.getStyleClass().clear();
		mbAdmin.getStyleClass().clear();
		mbBenutzer.getStyleClass().clear();
		btMangel.getStyleClass().add("menuselected");
		mbAdmin.getStyleClass().add("menuunselected");
		mbBenutzer.getStyleClass().add("menuunselected");	
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
		btMangel.getStyleClass().clear();
		mbAdmin.getStyleClass().clear();
		mbBenutzer.getStyleClass().clear();
		btMangel.getStyleClass().add("menuselected");
		mbAdmin.getStyleClass().add("menuunselected");
		mbBenutzer.getStyleClass().add("menuunselected");
	}
	
	@FXML
	public void clickProjekt() {
		MainView.showLeftView("LeftAdmin");
		MainView.showCenterView("Projekt");
		btMangel.getStyleClass().clear();
		mbAdmin.getStyleClass().clear();
		mbBenutzer.getStyleClass().clear();	
		btMangel.getStyleClass().add("menuunselected");
		mbAdmin.getStyleClass().add("menuselected");
		mbBenutzer.getStyleClass().add("menuunselected");
	}
	
	@FXML
	public void clickSubunternehmen() {
		MainView.showLeftView("LeftAdmin");
		MainView.showCenterView("Subunternehmen");
		btMangel.getStyleClass().clear();
		mbAdmin.getStyleClass().clear();
		mbBenutzer.getStyleClass().clear();
		btMangel.getStyleClass().add("menuunselected");
		mbAdmin.getStyleClass().add("menuselected");
		mbBenutzer.getStyleClass().add("menuunselected");
	}
	
	@FXML
	public void clickPerson() {
		MainView.showLeftView("LeftAdmin");
		MainView.showCenterView("Person");
		
		btMangel.getStyleClass().add("menuunselected");
		mbAdmin.getStyleClass().add("menuselected");
		mbBenutzer.getStyleClass().add("menuunselected");
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
