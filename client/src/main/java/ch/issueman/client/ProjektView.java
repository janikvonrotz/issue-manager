package ch.issueman.client;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

import ch.issueman.common.FormatHelper;
import ch.issueman.common.Mangel;
import ch.issueman.common.Projekt;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import ch.issueman.client.MainView;
/**
 * List view for Projekt
 * 
 * @author Aathavan Theivendram
 * @version 1.0.0
 * @since 1.0.0
 */
public class ProjektView implements Viewable<Projekt, Projekt> {

	private static Controller<Projekt, Integer> controller = new Controller<Projekt, Integer>(Projekt.class);

	private FilteredList<Projekt> filteredData = new FilteredList<Projekt>(FXCollections.observableArrayList(),	p -> true);

	@FXML
	private TableView<Projekt> tvData;

	@FXML
	private TextField txFilter;
	
	@FXML
	private Button btAddProjekt;
	
	@FXML
	private Button btExport;

	@FXML
	private TableColumn<Projekt, String> tcReferenz;

	@FXML
	private TableColumn<Projekt, String> tcTitel;

	@FXML
	private TableColumn<Projekt, String> tcProjekttyp;

	@FXML
	private TableColumn<Projekt, String> tcArbeitstyp;

	@FXML
	private TableColumn<Projekt, String> tcBauleiter;

	@FXML
	private TableColumn<Projekt, String> tcBauherr;

	@FXML
	private TableColumn<Projekt, String> tcEnddatum;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tcReferenz.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Projekt,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Projekt, String> param) {
				return new SimpleStringProperty(param.getValue().getDisplayName());
			}
		});
		tcTitel.setCellValueFactory(new PropertyValueFactory<Projekt, String>("title"));
		tcProjekttyp.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Projekt,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Projekt, String> param) {
				return new SimpleStringProperty(param.getValue().getProjekttyp().getProjekttyp());
			}  
		});
		tcArbeitstyp.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Projekt,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Projekt, String> param) {
				return new SimpleStringProperty(param.getValue().getArbeitstyp().getArbeitstyp());
			}  
		});
		tcBauleiter.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Projekt,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Projekt, String> param) {
				if(param.getValue().getCurrentProjektleiter() != null){
					return new SimpleStringProperty(param.getValue().getCurrentProjektleiter().getDisplayName());
				} else {
					return new SimpleStringProperty("");
				}
			}  
		});
		tcBauherr.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Projekt,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Projekt, String> param) {
				return new SimpleStringProperty(param.getValue().getBauherr().getDisplayName());
			}  
		});	
		tcEnddatum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Projekt,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Projekt, String> param) {
				return new SimpleStringProperty(FormatHelper.formatDate(param.getValue().getEnde()));
			}  
		});

		txFilter.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					filteredData.setPredicate(t -> {
						
							if (newValue == null || newValue.isEmpty()) {
								return true;
							}

							String lowerCaseFilter = newValue.toLowerCase();
							String objectvalues = t.getTitle() 
									+ t.getArbeitstyp().getArbeitstyp() 
									+ t.getProjekttyp().getProjekttyp()
									+ t.getBauherr().getUnternehmen().getFirmenname()
									+ t.getDisplayName()
									+ t.getCurrentProjektleiter().getDisplayName()
									+ t.getBauherr().getDisplayName()
									+ t.getEnde();
							
							if (objectvalues.toLowerCase().indexOf(lowerCaseFilter) != -1) {
								return true; 
							}

							return false;
						});
				});		

		Refresh();
	}

	public void Refresh() {
		if(Context.getLogin().getRolle().getBezeichnung().equals("Bauleiter")){
			btAddProjekt.setVisible(false);
		} else if(Context.getLogin().getRolle().getBezeichnung().equals("Kontaktperson")){
			btAddProjekt.setVisible(false);
		} else if(Context.getLogin().getRolle().getBezeichnung().equals("Kontaktadmin")){
			btAddProjekt.setVisible(false);
		}
		
		// Comparator für die Sortierung nach Enddatum
	    Comparator<? super Projekt> comparatorProjektByDate = new Comparator<Projekt>() {
			@Override
			public int compare(Projekt p1, Projekt p2) {
				return p1.getEnde().compareTo(p2.getEnde());
			}
	    };
		
		try {
			ObservableList<Projekt> list = FXCollections.observableArrayList(filteredData);
			Collections.sort(list, comparatorProjektByDate);
			list = new FilteredList<Projekt>(FXCollections.observableArrayList(controller.getAll()), p -> true);
			SortedList<Projekt> sortedData = new SortedList<Projekt>(list);
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
		        	Projekt t = tvData.getSelectionModel().getSelectedItem();
		        	showDetail(t);
		        }
		    }
		});
	}
	
	@FXML
	public void clickNeu() {
		 MainView.showCenterDetailView("ProjektDetail");
	}
	
	@SuppressWarnings("serial")
	@FXML
	public void clickExport(){
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		// add header
		list.add(new ArrayList<String>(){{
		    add("Referenz");
		    add("Titel");
		    add("Projekttyp");
		    add("Arbeitstyp");
		    add("Bauherr");
		    add("End Datum");
		}});
		// add content from list view
		tvData.getItems().stream().forEach(p -> list.add(new ArrayList<String>(){{
			add(p.getDisplayName());
			add(p.getTitle());
			add(p.getProjekttyp().getProjekttyp());
			add(p.getArbeitstyp().getArbeitstyp());
			add(p.getBauherr().getDisplayName());
			add(FormatHelper.formatDate(p.getEnde()));
		}}));
		
		MainView.exportData(list);
	}
	
	@Override
	public void initData(Projekt t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showDetail(Projekt t) {
		ViewableDetail<Projekt> view = MainView.showCenterDetailView("ProjektDetail");
		view.initData(t);
	}
}
