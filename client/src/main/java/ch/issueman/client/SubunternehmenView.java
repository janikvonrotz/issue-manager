package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import ch.issueman.common.Login;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Subunternehmen;

/**
 * List view for Subunternehmen
 * 
 * @author Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 */
public class SubunternehmenView implements Viewable<Subunternehmen, Subunternehmen> {

	private static Controller<Subunternehmen, Integer> controller = new Controller<Subunternehmen, Integer>(Subunternehmen.class);

	private FilteredList<Subunternehmen> filteredData = new FilteredList<Subunternehmen>(FXCollections.observableArrayList(),	p -> true);

	@FXML
	private TableView<Subunternehmen> tvData;

	@FXML
	private TextField txFilter;

	@FXML
	private TableColumn<Subunternehmen, Integer> tcId;

	@FXML
	private TableColumn<Subunternehmen, String> tcFirmenname;

	@FXML
	private TableColumn<Subunternehmen, String> tcStrasse;

	@FXML
	private TableColumn<Subunternehmen, String> tcPlz;

	@FXML
	private TableColumn<Subunternehmen, String> tcOrt;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO entfernen
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();

		tcId.setCellValueFactory(new PropertyValueFactory<Subunternehmen, Integer>("id"));
		tcFirmenname.setCellValueFactory(new PropertyValueFactory<Subunternehmen, String>("firmenname"));
		
		tcStrasse.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Subunternehmen,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Subunternehmen, String> param) {
				return new SimpleStringProperty(param.getValue().getAdresse().getStrasse());
			}  
		});

		tcPlz.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Subunternehmen,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Subunternehmen, String> param) {
				return new SimpleStringProperty(Integer.toString(param.getValue().getAdresse().getOrt().getPlz()));
			}  
		});

		tcOrt.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Subunternehmen,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Subunternehmen, String> param) {
				return new SimpleStringProperty(param.getValue().getAdresse().getOrt().getOrt());
			}  
		});

		txFilter.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					filteredData.setPredicate(t -> {
						
							if (newValue == null || newValue.isEmpty()) {
								return true;
							}

							String lowerCaseFilter = newValue.toLowerCase();
							String objectvalues = t.getFirmenname() 
									+ t.getAdresse().getStrasse()
									+ t.getAdresse().getOrt().getPlz()
									+ t.getAdresse().getOrt().getOrt() 
									+ t.getId();
							
							if (objectvalues.toLowerCase().indexOf(lowerCaseFilter) != -1) {
								return true; 
							}

							return false;
						});
				});		

		Refresh();
	}

	@Override
	public void Refresh() {
		try {
			filteredData = new FilteredList<Subunternehmen>(FXCollections.observableArrayList(controller.getAll()),	p -> true);
			SortedList<Subunternehmen> sortedData = new SortedList<Subunternehmen>(filteredData);
			sortedData.comparatorProperty().bind(tvData.comparatorProperty());
			tvData.setItems(sortedData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void clickData() {
		Subunternehmen t = tvData.getSelectionModel().getSelectedItem();
//		showDetail(t);
		System.out.println(t.getFirmenname());      
	}
	
	@FXML
	public void doubleClickData() {
		tvData.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	Subunternehmen t = tvData.getSelectionModel().getSelectedItem();
		        	System.out.println(t.getFirmenname());
		        	showDetail(t);
		        	
		        }
		    }
		});
	}
	
	@Override
	public void showDetail(Subunternehmen t) {
		ViewableDetail<Subunternehmen> view = MainView.showCenterDetailView("SubunternehmenDetail");
		view.initData(t);
	}

	@Override
	public void initData(Subunternehmen t) {
		// TODO Auto-generated method stub
		
	}
}
