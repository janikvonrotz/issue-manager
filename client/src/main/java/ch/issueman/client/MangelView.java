package ch.issueman.client;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

	private static Controller<Mangel, Integer> mangelcontroller = new Controller<Mangel, Integer>(Mangel.class);
	private static Controller<Projekt, Integer> projektcontroller = new Controller<Projekt, Integer>(Projekt.class);

	private FilteredList<Mangel> filteredData = new FilteredList<Mangel>(FXCollections.observableArrayList(), p -> true);
	
	private Projekt projekt;

	@FXML
	private TextField txFilter;
	
	@FXML
	private Label lbProjekt;

	@FXML
	private Button btAddMangel;
	
	@FXML
	private Button btExport;
	
	// Tabelle "abzuklären"
	@FXML
	private TableView<Mangel> tvDataAbzuklären;
	
	@FXML
	private TableColumn<Mangel, String> tcReferenzAbzuklären;

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
	private TableColumn<Mangel, String> tcReferenzBeauftragt;

	@FXML
	private TableColumn<Mangel, String> tcMangelBeauftragt;
	
	// Tabelle "angenommen"
	@FXML
	private TableView<Mangel> tvDataAngenommen;
	
	@FXML
	private TableColumn<Mangel, String> tcReferenzAngenommen;

	@FXML
	private TableColumn<Mangel, String> tcMangelAngenommen;

	// Tabelle "behoben"
	@FXML
	private TableView<Mangel> tvDataBehoben;
	
	@FXML
	private TableColumn<Mangel, String> tcReferenzBehoben;

	@FXML
	private TableColumn<Mangel, String> tcMangelBehoben;
	
	// Tabelle "abgeschlossen"
	@FXML
	private TableView<Mangel> tvDataAbgeschlossen;
	
	@FXML
	private TableColumn<Mangel, String> tcReferenzAbgeschlossen;

	@FXML
	private TableColumn<Mangel, String> tcMangelAbgeschlossen;
	
	@FXML
	private TableColumn<Mangel, String> tcSubunternehmenAbgeschlossen;
	
	@FXML
	private TableColumn<Mangel, String> tcKommentarAbgeschlossen;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		// Tabelle "abzuklären"	
		tcReferenzAbzuklären.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mangel,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Mangel, String> param) {
				return new SimpleStringProperty("M" + ("000" + param.getValue().getReferenz()).substring((("000" + param.getValue().getReferenz()).length())-3));
			}
		});
		
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
		tcReferenzBeauftragt.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mangel,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Mangel, String> param) {
				return new SimpleStringProperty("M" + ("000" + param.getValue().getReferenz()).substring((("000" + param.getValue().getReferenz()).length())-3));
			}
		});
		
		tcMangelBeauftragt.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mangel,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Mangel, String> param) {
				return new SimpleStringProperty(param.getValue().getMangel());
			}
		});
		
		// Tabelle "angenommen"
		tcReferenzAngenommen.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mangel,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Mangel, String> param) {
				return new SimpleStringProperty("M" + ("000" + param.getValue().getReferenz()).substring((("000" + param.getValue().getReferenz()).length())-3));
			}
		});
				
		tcMangelAngenommen.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mangel,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Mangel, String> param) {
				return new SimpleStringProperty(param.getValue().getMangel());
			}
		});
		
		// Tabelle "behoben"
		tcReferenzBehoben.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mangel,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Mangel, String> param) {
				return new SimpleStringProperty("M" + ("000" + param.getValue().getReferenz()).substring((("000" + param.getValue().getReferenz()).length())-3));
			}
		});
				
		tcMangelBehoben.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mangel,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Mangel, String> param) {
				return new SimpleStringProperty(param.getValue().getMangel());
			}
		});
		
		// Tabelle "abgeschlossen"
		tcReferenzAbgeschlossen.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mangel,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Mangel, String> param) {
				return new SimpleStringProperty("M" + ("000" + param.getValue().getReferenz()).substring((("000" + param.getValue().getReferenz()).length())-3));
			}
		});
		
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
		if(Context.getLogin().getRolle().getBezeichnung().equals("Bauleiter")){
			btAddMangel.setVisible(false);
		} else if(Context.getLogin().getRolle().getBezeichnung().equals("Kontaktperson")){
			btAddMangel.setVisible(false);
		} else if(Context.getLogin().getRolle().getBezeichnung().equals("Kontaktadmin")){
			btAddMangel.setVisible(false);
		}
		
		if(projekt != null){
			
			lbProjekt.setText(projekt.getDisplayName() + " - " + projekt.getTitle());
			
			try {
				List<Mangel> mList = mangelcontroller.getAll().stream().filter(m -> m.getProjekt().equals(projekt)).collect(Collectors.toList());
				ObservableList<Mangel> list = FXCollections.observableArrayList(mList);
				
				filteredData = new FilteredList<Mangel>(list, p -> p.getMangelstatus().getStatus().equals("abzuklären"));
				SortedList<Mangel> sortedDataAbzuklären = new SortedList<Mangel>(filteredData);
				sortedDataAbzuklären.comparatorProperty().bind(tvDataAbzuklären.comparatorProperty());
				tvDataAbzuklären.setItems(sortedDataAbzuklären);
				
				filteredData = new FilteredList<Mangel>(list, p -> p.getMangelstatus().getStatus().equals("beauftragt"));
				SortedList<Mangel> sortedDataBeauftragt = new SortedList<Mangel>(filteredData);
				sortedDataBeauftragt.comparatorProperty().bind(tvDataBeauftragt.comparatorProperty());
				tvDataBeauftragt.setItems(sortedDataBeauftragt);
				
				filteredData = new FilteredList<Mangel>(list, p -> p.getMangelstatus().getStatus().equals("angenommen"));
				SortedList<Mangel> sortedDataAngenommen = new SortedList<Mangel>(filteredData);
				sortedDataAngenommen.comparatorProperty().bind(tvDataAngenommen.comparatorProperty());
				tvDataAngenommen.setItems(sortedDataAngenommen);
				
				filteredData = new FilteredList<Mangel>(list, p -> p.getMangelstatus().getStatus().equals("behoben"));
				SortedList<Mangel> sortedDataBehoben = new SortedList<Mangel>(filteredData);
				sortedDataBehoben.comparatorProperty().bind(tvDataBehoben.comparatorProperty());
				tvDataBehoben.setItems(sortedDataBehoben);
				
				filteredData = new FilteredList<Mangel>(list, p -> p.getMangelstatus().getStatus().equals("abgeschlossen"));
				SortedList<Mangel> sortedDataAbgeschlossen = new SortedList<Mangel>(filteredData);
				sortedDataAbgeschlossen.comparatorProperty().bind(tvDataAbgeschlossen.comparatorProperty());
				tvDataAbgeschlossen.setItems(sortedDataAbgeschlossen);
			} catch (Exception e) {
				MainView.showError(e);
			}
		} else {
			try {
				projekt = projektcontroller.getAll().get(0);
			} catch (Exception e) {
				MainView.showError(e);
			}
			
			lbProjekt.setText(projekt.getDisplayName() + " - " + projekt.getTitle());
			
			try {
				List<Mangel> mList = mangelcontroller.getAll().stream().filter(m -> m.getProjekt().equals(projekt)).collect(Collectors.toList());
				ObservableList<Mangel> list = FXCollections.observableArrayList(mList);
				
				filteredData = new FilteredList<Mangel>(list, p -> p.getMangelstatus().getStatus().equals("abzuklären"));
				SortedList<Mangel> sortedDataAbzuklären = new SortedList<Mangel>(filteredData);
				sortedDataAbzuklären.comparatorProperty().bind(tvDataAbzuklären.comparatorProperty());
				tvDataAbzuklären.setItems(sortedDataAbzuklären);
				
				filteredData = new FilteredList<Mangel>(list, p -> p.getMangelstatus().getStatus().equals("beauftragt"));
				SortedList<Mangel> sortedDataBeauftragt = new SortedList<Mangel>(filteredData);
				sortedDataBeauftragt.comparatorProperty().bind(tvDataBeauftragt.comparatorProperty());
				tvDataBeauftragt.setItems(sortedDataBeauftragt);
				
				filteredData = new FilteredList<Mangel>(list, p -> p.getMangelstatus().getStatus().equals("angenommen"));
				SortedList<Mangel> sortedDataAngenommen = new SortedList<Mangel>(filteredData);
				sortedDataAngenommen.comparatorProperty().bind(tvDataAngenommen.comparatorProperty());
				tvDataAngenommen.setItems(sortedDataAngenommen);
				
				filteredData = new FilteredList<Mangel>(list, p -> p.getMangelstatus().getStatus().equals("behoben"));
				SortedList<Mangel> sortedDataBehoben = new SortedList<Mangel>(filteredData);
				sortedDataBehoben.comparatorProperty().bind(tvDataBehoben.comparatorProperty());
				tvDataBehoben.setItems(sortedDataBehoben);
				
				filteredData = new FilteredList<Mangel>(list, p -> p.getMangelstatus().getStatus().equals("abgeschlossen"));
				SortedList<Mangel> sortedDataAbgeschlossen = new SortedList<Mangel>(filteredData);
				sortedDataAbgeschlossen.comparatorProperty().bind(tvDataAbgeschlossen.comparatorProperty());
				tvDataAbgeschlossen.setItems(sortedDataAbgeschlossen);
			} catch (Exception e) {
				MainView.showError(e);
			}
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
		 MainView.showCenterDetailView("MangelDetail");
	}

	@Override
	public void showDetail(Mangel t) {
		ViewableDetail<Mangel> view = MainView.showCenterDetailView("MangelDetail");
		view.initData(t);
	}

	@SuppressWarnings("serial")
	@FXML
	public void clickExport(){
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		List<Mangel> allMangelList = null;

		// add header
		list.add(new ArrayList<String>(){{
		    add("Referenz");
		    add("Status");
		    add("Mangel");
		    add("Subunternehmen");
		    add("Kommentar");
		}});
		
		//Filterung der Mängel
		try {
			allMangelList = mangelcontroller.getAll().stream().filter(m -> m.getProjekt().equals(projekt)).collect(Collectors.toList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MainView.showError(e);
		}
		// add content from list view
		allMangelList.forEach(p -> list.add(new ArrayList<String>(){{
			List<Kommentar> k = p.getKommentare();
			add(p.getProjekt().getDisplayName() + "M" + ("000" + 
	    		p.getReferenz()).substring((("000" + p.getReferenz()).length())-3));
			add(p.getMangelstatus().getStatus());
			add(p.getMangel());
			add(p.getSubunternehmen().getFirmenname());
			add(k.get(k.size()-1).getKommentar().toString());
		}}));
		
		MainView.exportData(list);
	}
	@Override
	public void initData(Projekt t) {
		projekt = t;
		Refresh();
	}

}
