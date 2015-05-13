package ch.issueman.client;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import ch.issueman.common.Bauherr;
import ch.issueman.common.Kontakt;
import ch.issueman.common.Login;
import ch.issueman.common.Person;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * List view for Person
 * 
 * @author Aathavan Theivendram
 * @version 1.0.0
 * @since 1.0.0
 */
public class PersonView implements Viewable<Login, Login> {

	private static Controller<Login, Integer> loginController = new Controller<Login, Integer>(Login.class);
	private static Controller<Bauherr, Integer> bauherrController = new Controller<Bauherr, Integer>(Bauherr.class);
	
	private FilteredList<Login> filteredData = new FilteredList<Login>(FXCollections.observableArrayList(),	p -> true);

	@FXML
	private TableView<Login> tvData;

	@FXML
	private TextField txFilter;

	@FXML
	private TableColumn<Login, Integer> tcId;

	@FXML
	private TableColumn<Login, String> tcNachname;

	@FXML
	private TableColumn<Login, String> tcVorname;

	@FXML
	private TableColumn<Login, String> tcEmail;

	@FXML
	private TableColumn<Login, String> tcRolle;

	@FXML
	private TableColumn<Login, String> tcFirma;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO entfernen
//		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
//		Context.login();

		tcId.setCellValueFactory(new PropertyValueFactory<Login, Integer>("id"));
		tcNachname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Login,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Login, String> param) {
				return new SimpleStringProperty(param.getValue().getPerson().getNachname());
			}  
		});
		tcVorname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Login,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Login, String> param) {
				return new SimpleStringProperty(param.getValue().getPerson().getVorname());
			}  
		});
		tcEmail.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Login,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Login, String> param) {
				return new SimpleStringProperty(param.getValue().getPerson().getEmail());
			}  
		});
		tcRolle.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Login,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Login, String> param) {
				return new SimpleStringProperty(param.getValue().getRolle().getBezeichnung());
			}  
		});
		tcFirma.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Login,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Login, String> param) {
				if(param.getValue().getPerson() instanceof Kontakt){
					return new SimpleStringProperty(((Kontakt)param.getValue().getPerson()).getSubunternehmen().getFirmenname());
				}else if(param.getValue().getPerson() instanceof Bauherr){
					return new SimpleStringProperty(((Bauherr)param.getValue().getPerson()).getUnternehmen().getFirmenname());
				}else{
					return new SimpleStringProperty("-");
				}
			}  
		});

		txFilter.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					filteredData.setPredicate(t -> {
						
							if (newValue == null || newValue.isEmpty()) {
								return true;
							}

							String lowerCaseFilter = newValue.toLowerCase();
							String objectvalues = t.getPerson().getNachname() 
									+ t.getPerson().getVorname()
									+ t.getPerson().getEmail()
									+ t.getRolle().getBezeichnung()
//									+ (((Kontakt) t.getPerson()).getSubunternehmen().getFirmenname())
//									+ (((Bauherr) t.getPerson()).getUnternehmen().getFirmenname())
									+ t.getPerson().getId();
		
							if (objectvalues.toLowerCase().indexOf(lowerCaseFilter) != -1) {
								return true; 
							}

							return false;
						});
				});		

		Refresh();
	}

	public void Refresh() {
		try {
			List<Login> list = loginController.getAll();
			bauherrController.getAll().stream().forEach(b -> list.add(new Login(b, "", null)));
			
			filteredData = new FilteredList<Login>(FXCollections.observableArrayList(loginController.getAll()),	p -> true);
			SortedList<Login> sortedData = new SortedList<Login>(filteredData);
			sortedData.comparatorProperty().bind(tvData.comparatorProperty());
			tvData.setItems(sortedData);
		} catch (Exception e) {
			MainView.showError(e);
		}
	}
	
	@FXML
	public void doubleClickData() {
		tvData.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	Login t = tvData.getSelectionModel().getSelectedItem();
		        	showDetail(t);
		        }
		    }
		});
	}

	@Override
	public void initData(Login t) {
		System.out.println(t.getClass().getSimpleName());
	}

	@Override
	public void showDetail(Login t) {
		ViewableDetail<Person> view = MainView.showCenterDetailView("PersonDetail");
		view.initData(t.getPerson());
	}
}
