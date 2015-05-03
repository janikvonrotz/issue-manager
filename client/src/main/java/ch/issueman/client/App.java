package ch.issueman.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Issue Manager");
			primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Projekt.fxml"))));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static void showMain() {
		// TODO Auto-generated method stub
		
	}
}
