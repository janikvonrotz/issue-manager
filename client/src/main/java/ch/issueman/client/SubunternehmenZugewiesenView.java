package ch.issueman.client;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import ch.issueman.common.Kontakt;
import ch.issueman.common.Login;
import ch.issueman.common.Projekt;
import ch.issueman.common.Subunternehmen;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * List view for Subunternehmen Zugewiesen
 * 
 * @author Sandro Klarer, Reno Meyer
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class SubunternehmenZugewiesenView implements Viewable<Projekt, Projekt> {

	private static Controller<Kontakt, Integer> kontaktcontroller = new Controller<Kontakt, Integer>(Kontakt.class);
	private static Controller<Login, Integer> logincontroller = new Controller<Login, Integer>(Login.class);
	private static Controller<Subunternehmen, Integer> subunternehmencontroller = new Controller<Subunternehmen, Integer>(Subunternehmen.class);

	private FilteredList<Login> filteredData = new FilteredList<Login>(FXCollections.observableArrayList(),	p -> true);

	private Projekt projekt;
	private List<Login> kpList;
	private List<Login> kaList;
	private List<Login> kSelection;
	
	@FXML
	private TableView<Login> tvData; 
	
	@FXML
	private TextField txFilter;
	
	@FXML
	private TableColumn<Login, String> tcSubunternehmen; 
	
	@FXML 
	private TableColumn<Login, String> tcPerson;
	
	@FXML 
	private ComboBox<Subunternehmen> cbSubunternehmen;
	
	@FXML 
	private ComboBox<Login> cbKontakt;	
	
	@FXML
	private Button btSpeichern; 
	
	@FXML 
	private Button btAbbrechen; 
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		tcSubunternehmen.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Login, String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Login, String> param) {
				return new SimpleStringProperty(((Kontakt) param.getValue().getPerson()).getSubunternehmen().getFirmenname());
			}  
		});	
				
		tcPerson.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Login,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Login, String> param) {
				return new SimpleStringProperty(((Kontakt) param.getValue().getPerson()).getDisplayName());
			}  
		});	
		
		txFilter.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					filteredData.setPredicate(t -> {
						
							if (newValue == null || newValue.isEmpty()) {
								return true;
							}

							String lowerCaseFilter = newValue.toLowerCase();
							String objectvalues = ((Kontakt) t.getPerson()).getSubunternehmen().getFirmenname()
									+ ((Kontakt) t.getPerson()).getDisplayName();
	
							if (objectvalues.toLowerCase().indexOf(lowerCaseFilter) != -1) {
								return true; 
							}

							return false;
						});
				});	
		
		cbSubunternehmen.setCellFactory(new Callback<ListView<Subunternehmen>,ListCell<Subunternehmen>>(){
			@Override
			public ListCell<Subunternehmen> call(ListView<Subunternehmen> arg0) {		 
				final ListCell<Subunternehmen> cell = new ListCell<Subunternehmen>(){				 
                    @Override
                    protected void updateItem(Subunternehmen s, boolean bln) {
                        super.updateItem(s, bln);
                         
                        if(s != null){
                            setText(s.getFirmenname());
                        }else{
                            setText(null);
                        }
                    }
                };
				return cell;
			}
        });
		
		cbSubunternehmen.setConverter(new StringConverter<Subunternehmen>() {
            private Map<String, Object> map = new HashMap<>();

 			@Override
			public Subunternehmen fromString(String arg0) {
				return null;
			}

			public String toString(Subunternehmen s) {
               if (s != null) {
                    String str = s.getFirmenname();
                    map.put(str, s);
                    return str;
                } else {
                    return "";
                }
			}
		});
				
		cbKontakt.setCellFactory(new Callback<ListView<Login>,ListCell<Login>>(){
			@Override
			public ListCell<Login> call(ListView<Login> arg0) {		 
				final ListCell<Login> cell = new ListCell<Login>(){				 
                    @Override
                    protected void updateItem(Login k, boolean bln) {
                        super.updateItem(k, bln);
                         
                        if(k != null){
                            setText(k.getPerson().getDisplayName());
                        }else{
                            setText(null);
                        }
                    }
                };
				return cell;
			}
        });
		
		cbKontakt.setConverter(new StringConverter<Login>() {
            private Map<String, Object> map = new HashMap<>();

 			@Override
			public Login fromString(String arg0) {
				return null;
			}

			public String toString(Login k) {
               if (k != null) {
                    String str = k.getPerson().getDisplayName();
                    map.put(str, k);
                    return str;
                } else {
                    return "";
                }
			}
		});
		
		Refresh();
	}

	@Override
	public void Refresh() {
		
		try {
			
			if(projekt != null){
				kpList = logincontroller.getAll().stream().filter(l -> (l.getRolle().
						getBezeichnung().equals("Kontaktperson")) && (((Kontakt) l.getPerson()).
						getProjekte().contains(projekt))).collect(Collectors.toList());
				
				kaList = logincontroller.getAll().stream().filter(l -> (l.getRolle().
						getBezeichnung().equals("Kontaktadmin")) && (((Kontakt) l.getPerson()).
						getProjekte().contains(projekt))).collect(Collectors.toList());
				
				List<Login> list = kaList;
				
				for(Login p : kpList){
					for(Login l : list){
						if(((Kontakt) l.getPerson()).getSubunternehmen().equals(((Kontakt) p.
								getPerson()).getSubunternehmen())){
							list.remove(l);
							list.add(p);
						}
					}
				}
				
				filteredData = new FilteredList<Login>(FXCollections.observableArrayList(list),	p -> true);
				SortedList<Login> sortedData = new SortedList<Login>(filteredData);
				sortedData.comparatorProperty().bind(tvData.comparatorProperty());
				tvData.setItems(sortedData);
				
				List<Subunternehmen> sSelection = subunternehmencontroller.getAll();
				kaList.forEach(k -> sSelection.remove(((Kontakt) k.getPerson()).getSubunternehmen()));
						
				cbSubunternehmen.setItems(FXCollections.observableArrayList(sSelection));
			}
			
		} catch (Exception e) {
			MainView.showError(e);
		}
	}
	
	@FXML
	public void refreshCbKontakt(){
		
		if(cbSubunternehmen.getValue() != null){
			try {
				kSelection = logincontroller.getAll().stream().filter(l -> ((l.getRolle().
						getBezeichnung().contains("Kontakt")) && (((Kontakt) l.getPerson()).getSubunternehmen().
						equals(cbSubunternehmen.getValue())))).collect(Collectors.toList());
				
				cbKontakt.setItems(FXCollections.observableArrayList(kSelection));
			} catch (Exception e) {
				MainView.showError(e);
			}
		}
		
	}

	@FXML
	public void clickSpeichern(){
		
		try {
			List<Login> lList = logincontroller.getAll().stream().filter(l -> l.getPerson() instanceof
					Kontakt).collect(Collectors.toList());
			
			Login kaLogin = lList.stream().filter(k -> (((Kontakt) k.getPerson()).
					getSubunternehmen().equals(cbSubunternehmen.getValue())) && k.getRolle().
					getBezeichnung().equals("Kontaktadmin")).collect(Collectors.toList()).get(0);
			
			Kontakt kaKontakt = (Kontakt) kaLogin.getPerson();
			kaKontakt.getProjekte().add(projekt);
			
			kontaktcontroller.update(kaKontakt);
		} catch (Exception e) {
			MainView.showError(e);
		}

		List<Login> lOldList = kSelection.stream().filter(p -> (((Kontakt) p.getPerson()).getProjekte().
				contains(projekt)) && p.getRolle().getBezeichnung().equals("Kontaktperson")).
				collect(Collectors.toList());
		try {

			if(lOldList.size() > 0){
				Kontakt kOld = ((Kontakt) lOldList.get(0).getPerson());
				kOld.getProjekte().remove(projekt);
				kontaktcontroller.update(kOld);
			}
			if(cbKontakt.getValue() != null && cbKontakt.getValue().getRolle().
					getBezeichnung().equals("Kontaktperson")){
				Kontakt kNew = (Kontakt) cbKontakt.getValue().getPerson();
				kNew.getProjekte().add(projekt);
				kontaktcontroller.update(kNew);
			}
		} catch (Exception e) {
			MainView.showError(e);
		}
		
		showDetail(projekt);
	}
	
	@FXML
	public void clickAbbrechen(){
		showDetail(projekt);
	}
	@Override
	public void initData(Projekt t) {
		projekt = t;
		Refresh();
	}

	@Override
	public void showDetail(Projekt t) {
		ViewableDetail<Projekt> view = MainView.showCenterDetailView("ProjektDetail");
		view.initData(t);
	}
}
