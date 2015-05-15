package ch.issueman.client;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import ch.issueman.common.Kommentar;
import ch.issueman.common.Mangel;
import ch.issueman.common.Projekt;

/**
 * List view for Mangel
 * 
 * @author Aathavan Theivendram
 * @version 1.0.0
 * @since 1.0.0
 */
public class MangelView implements Viewable<Mangel, Projekt> {

	private static Controller<Mangel, Integer> controller = new Controller<Mangel, Integer>(Mangel.class);

	private FilteredList<Mangel> filteredData = new FilteredList<Mangel>(FXCollections.observableArrayList(), p -> true);

	@FXML
	private TextField txFilter;
	
	@FXML
	private Button btAddMangel;
	
	// Tabelle "abzuklären"
	@FXML
	private TableView<Mangel> tvDataAbzuklären;
	
	@FXML
	private TableColumn<Mangel, Integer> tcReferenzAbzuklären;

	@FXML
	private TableColumn<Mangel, String> tcMangelAbzuklären;
	
	@FXML
	private TableColumn<Mangel, String> tcSubunternehmenAbzuklären;
	
	@FXML
	private TableColumn<Mangel, String> tcKommentarAbzuklären;
	
	// Tabelle "beauftragt"
	@FXML
	private TableView<Mangel> tvDataBeauftragt;
	
	@FXML
	private TableColumn<Mangel, Integer> tcReferenzBeauftragt;

	@FXML
	private TableColumn<Mangel, String> tcMangelBeauftragt;
	
	// Tabelle "angenommen"
	@FXML
	private TableView<Mangel> tvDataAngenommen;
	
	@FXML
	private TableColumn<Mangel, Integer> tcReferenzAngenommen;

	@FXML
	private TableColumn<Mangel, String> tcMangelAngenommen;

	// Tabelle "behoben"
	@FXML
	private TableView<Mangel> tvDataBehoben;
	
	@FXML
	private TableColumn<Mangel, Integer> tcReferenzBehoben;

	@FXML
	private TableColumn<Mangel, String> tcMangelBehoben;
	
	// Tabelle "abgeschlossen"
	@FXML
	private TableView<Mangel> tvDataAbgeschlossen;
	
	@FXML
	private TableColumn<Mangel, Integer> tcReferenzAbgeschlossen;

	@FXML
	private TableColumn<Mangel, String> tcMangelAbgeschlossen;
	
	@FXML
	private TableColumn<Mangel, String> tcSubunternehmenAbgeschlossen;
	
	@FXML
	private TableColumn<Mangel, String> tcKommentarAbgeschlossen;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO entfernen
//		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
//		Context.login();
	
		// Tabelle "abzuklären"
		tcReferenzAbzuklären.setCellValueFactory(new PropertyValueFactory<Mangel, Integer>("referenz"));
		
		tcMangelAbzuklären.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mangel,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Mangel, String> param) {
				return new SimpleStringProperty(param.getValue().getMangel());
			}
		});

		tcSubunternehmenAbzuklären.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mangel,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Mangel, String> param) {
				return new SimpleStringProperty(param.getValue().getSubunternehmen().getFirmenname());
			}  
		});
		
		tcKommentarAbzuklären.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mangel,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Mangel, String> param) {
				List<Kommentar> k = param.getValue().getKommentare();
				return new SimpleStringProperty(param.getValue().getKommentare().get(k.size() - 1).getKommentar());
			}
		});
		
		// Tabelle "beauftragt"
		tcReferenzBeauftragt.setCellValueFactory(new PropertyValueFactory<Mangel, Integer>("referenz"));
		
		tcMangelBeauftragt.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mangel,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Mangel, String> param) {
				return new SimpleStringProperty(param.getValue().getMangel());
			}
		});
		
		// Tabelle "angenommen"
		tcReferenzAngenommen.setCellValueFactory(new PropertyValueFactory<Mangel, Integer>("referenz"));
				
		tcMangelAngenommen.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mangel,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Mangel, String> param) {
				return new SimpleStringProperty(param.getValue().getMangel());
			}
		});
		
		// Tabelle "behoben"
		tcReferenzBehoben.setCellValueFactory(new PropertyValueFactory<Mangel, Integer>("referenz"));
				
		tcMangelBehoben.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mangel,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Mangel, String> param) {
				return new SimpleStringProperty(param.getValue().getMangel());
			}
		});
		
		// Tabelle "abgeschlossen"
		tcReferenzAbgeschlossen.setCellValueFactory(new PropertyValueFactory<Mangel, Integer>("referenz"));
		
		tcMangelAbgeschlossen.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mangel,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Mangel, String> param) {
				return new SimpleStringProperty(param.getValue().getMangel());
			}
		});

		tcSubunternehmenAbgeschlossen.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mangel,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Mangel, String> param) {
				return new SimpleStringProperty(param.getValue().getSubunternehmen().getFirmenname());
			}  
		});
		
		tcKommentarAbgeschlossen.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mangel,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Mangel, String> param) {
				List<Kommentar> k = param.getValue().getKommentare();
				return new SimpleStringProperty(param.getValue().getKommentare().get(k.size() - 1).getKommentar());
			}
		});

		// Filterung der Tabelle
		txFilter.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					filteredData.setPredicate(t -> {
						
							if (newValue == null || newValue.isEmpty()) {
								return true;
							}
							List<Kommentar> k = t.getKommentare();
							String lowerCaseFilter = newValue.toLowerCase();
							// Angabe nach welchen Attributen es gefiltert werden soll
							String objectvalues = t.getMangel() 
									+ t.getSubunternehmen().getFirmenname()
									+ t.getKommentare().get(k.size() - 1).getKommentar()
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
			filteredData = new FilteredList<Mangel>(FXCollections.observableArrayList(controller.getAll()),	p -> p.getMangelstatus().getStatus().equals("abzuklären"));
			SortedList<Mangel> sortedDataAbzuklären = new SortedList<Mangel>(filteredData);
			sortedDataAbzuklären.comparatorProperty().bind(tvDataAbzuklären.comparatorProperty());
			tvDataAbzuklären.setItems(sortedDataAbzuklären);
			
			filteredData = new FilteredList<Mangel>(FXCollections.observableArrayList(controller.getAll()),	p -> p.getMangelstatus().getStatus().equals("beauftragt"));
			SortedList<Mangel> sortedDataBeauftragt = new SortedList<Mangel>(filteredData);
			sortedDataBeauftragt.comparatorProperty().bind(tvDataBeauftragt.comparatorProperty());
			tvDataBeauftragt.setItems(sortedDataBeauftragt);
			
			filteredData = new FilteredList<Mangel>(FXCollections.observableArrayList(controller.getAll()),	p -> p.getMangelstatus().getStatus().equals("angenommen"));
			SortedList<Mangel> sortedDataAngenommen = new SortedList<Mangel>(filteredData);
			sortedDataAngenommen.comparatorProperty().bind(tvDataAngenommen.comparatorProperty());
			tvDataAngenommen.setItems(sortedDataAngenommen);
			
			filteredData = new FilteredList<Mangel>(FXCollections.observableArrayList(controller.getAll()),	p -> p.getMangelstatus().getStatus().equals("behoben"));
			SortedList<Mangel> sortedDataBehoben = new SortedList<Mangel>(filteredData);
			sortedDataBehoben.comparatorProperty().bind(tvDataBehoben.comparatorProperty());
			tvDataBehoben.setItems(sortedDataBehoben);
			
			filteredData = new FilteredList<Mangel>(FXCollections.observableArrayList(controller.getAll()),	p -> p.getMangelstatus().getStatus().equals("abgeschlossen"));
			SortedList<Mangel> sortedDataAbgeschlossen = new SortedList<Mangel>(filteredData);
			sortedDataAbgeschlossen.comparatorProperty().bind(tvDataAbgeschlossen.comparatorProperty());
			tvDataAbgeschlossen.setItems(sortedDataAbgeschlossen);
		} catch (Exception e) {
			MainView.showError(e);
		}
	}

	@FXML
	public void doubleClickDataAbzuklären() {
		tvDataAbzuklären.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	Mangel t = tvDataAbzuklären.getSelectionModel().getSelectedItem();;
		        	showDetail(t);
		        }
		    }
		});
	}
	
	@FXML
	public void doubleClickDataBeauftragt() {
		tvDataBeauftragt.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	Mangel t = tvDataBeauftragt.getSelectionModel().getSelectedItem();;
		        	showDetail(t);
		        }
		    }
		});
	}
	
	@FXML
	public void doubleClickDataAngenommen() {
		tvDataAngenommen.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	Mangel t = tvDataAngenommen.getSelectionModel().getSelectedItem();;
		        	showDetail(t);
		        }
		    }
		});
	}
	
	@FXML
	public void doubleClickDataBehoben() {
		tvDataBehoben.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	Mangel t = tvDataBehoben.getSelectionModel().getSelectedItem();;
		        	showDetail(t);
		        }
		    }
		});
	}
	
	@FXML
	public void doubleClickDataAbgeschlossen() {
		tvDataAbgeschlossen.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		        	Mangel t = tvDataAbgeschlossen.getSelectionModel().getSelectedItem();;
		        	showDetail(t);
		        }
		    }
		});
	}
	
	@FXML
	public void clickNeu() {
		btAddMangel.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown()) {
		        	MainView.showCenterDetailView("MangelDetail");
		        }
		    }
		});
	}

	@Override
	public void showDetail(Mangel t) {
		ViewableDetail<Mangel> view = MainView.showCenterDetailView("MangelDetail");
		view.initData(t);
	}

	@Override
	public void initData(Projekt t) {
		// TODO Auto-generated method stub
		
	}

}
