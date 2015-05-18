package ch.issueman.client;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;
import ch.issueman.common.ConfigHelper;
import ch.issueman.common.FormatHelper;
import ch.issueman.common.Kommentar;
import ch.issueman.common.Mangel;
import ch.issueman.common.Mangelstatus;
import ch.issueman.common.Projekt;
import ch.issueman.common.Subunternehmen;

/**
 * class MangelDetailView
 * 
 * @author Reno Meyer
 * @version 1.0.0
 * @since 1.0.0
 */
public class MangelDetailView implements ViewableDetail<Mangel> {
	
	private static Controller<Mangel, Integer> mangelcontroller = new Controller<Mangel, Integer>(Mangel.class);
	private static Controller<Kommentar, Integer> kommentarcontroller = new Controller<Kommentar, Integer>(Kommentar.class);
	private static Controller<Projekt, Integer> projektcontroller = new Controller<Projekt, Integer>(Projekt.class);
	private static Controller<Subunternehmen, Integer> subunternehmencontroller = new Controller<Subunternehmen, Integer>(Subunternehmen.class);
	private static Controller<Mangelstatus, Integer> statuscontroller = new Controller<Mangelstatus, Integer>(Mangelstatus.class);
	private Mangel mangel;
	private List<Mangelstatus> sList;
	
	@FXML
	private Label lbMangel;
	
	@FXML
	private ComboBox<Projekt> cbProjekt;
	
	@FXML
	private Button btShowProjekt;
	
	@FXML
	private TextField txBeschreibung;
	
	@FXML
	private ComboBox<Subunternehmen> cbSubunternehmen;
	
	@FXML
	private Button btShowSubunternehmen;
	
	@FXML
	private ComboBox<Mangelstatus> cbStatus;
	
	@FXML
	private TextField txErfasst;
	
	@FXML
	private DatePicker dpFrist;
	
	@FXML
	private TableView<Kommentar> tvKommentar;
	
	@FXML
	private TableColumn<Kommentar, String> tcKommentar;
	
	@FXML
	private TableColumn<Kommentar, String> tcAutor;
	
	@FXML
	private TableColumn<Kommentar, String> tcZeit;
	
	@FXML
	private Button btSend;
	
	@FXML
	private TextArea taKommentar;
	
	@FXML
	private Button btAbbrechen;
	
	@FXML
	private Button btSpeichern;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		cbProjekt.setCellFactory(new Callback<ListView<Projekt>,ListCell<Projekt>>(){
			@Override
			public ListCell<Projekt> call(ListView<Projekt> arg0) {		 
				final ListCell<Projekt> cell = new ListCell<Projekt>(){				 
                    @Override
                    protected void updateItem(Projekt p, boolean bln) {
                        super.updateItem(p, bln);
                         
                        if(p != null){
                            setText(p.getDisplayName() + " - " + p.getTitle());
                        }else{
                            setText(null);
                        }
                    }
                };
				return cell;
			}
        });
		
		cbProjekt.setConverter(new StringConverter<Projekt>() {
            private Map<String, Object> map = new HashMap<>();

 			@Override
			public Projekt fromString(String arg0) {
				return null;
			}

			public String toString(Projekt p) {
               if (p != null) {
                    String str = p.getDisplayName() + " - " + p.getTitle();
                    map.put(str, p);
                    return str;
                } else {
                    return "";
                }
			}
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

		cbStatus.setCellFactory(new Callback<ListView<Mangelstatus>,ListCell<Mangelstatus>>(){
			@Override
			public ListCell<Mangelstatus> call(ListView<Mangelstatus> arg0) {		 
				final ListCell<Mangelstatus> cell = new ListCell<Mangelstatus>(){				 
                    @Override
                    protected void updateItem(Mangelstatus m, boolean bln) {
                        super.updateItem(m, bln);
                         
                        if(m != null){
                            setText(m.getStatus());
                        }else{
                            setText(null);
                        }
                    }
                };
				return cell;
			}
        });
		
		cbStatus.setConverter(new StringConverter<Mangelstatus>() {
            private Map<String, Object> map = new HashMap<>();

 			@Override
			public Mangelstatus fromString(String arg0) {
				return null;
			}

			public String toString(Mangelstatus m) {
               if (m != null) {
                    String str = m.getStatus();
                    map.put(str, m);
                    return str;
                } else {
                    return "";
                }
			}
		});
		
		tcKommentar.setCellValueFactory(new PropertyValueFactory<Kommentar, String>("kommentar"));
		tcAutor.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Kommentar,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Kommentar, String> param) {
				return new SimpleStringProperty(param.getValue().getLogin().getPerson().getDisplayName());
			}  
		});
		tcZeit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Kommentar,String>,ObservableValue<String>>() {  
			public ObservableValue<String> call(CellDataFeatures<Kommentar, String> param) {
				return new SimpleStringProperty((new SimpleDateFormat(ConfigHelper.getConfig("format.date", "dd.MM.yyyy"))).format(param.getValue().getErstelltam().getTime()));
			} 
		});
		
		Refresh();	
	}
	
	@Override
	public void Refresh(){
		
		try {
			cbProjekt.setItems(FXCollections.observableArrayList(projektcontroller.getAll()));
			cbSubunternehmen.setItems(FXCollections.observableArrayList(subunternehmencontroller.getAll()));
			
			sList = statuscontroller.getAll().stream().filter(s -> s.getRollen().
					contains(Context.getLogin().getRolle())).collect(Collectors.toList());
			cbStatus.setItems(FXCollections.observableArrayList(sList));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MainView.showError(e);
		}

		
		if(mangel != null){
			
	    	lbMangel.setText(mangel.getProjekt().getDisplayName() + "-M" + ("000" + 
	    			mangel.getReferenz()).substring((("000" + mangel.getReferenz()).length())-3));
	    	cbProjekt.setValue(mangel.getProjekt());
	    	cbProjekt.setDisable(true);
	    	txBeschreibung.setText(mangel.getMangel());
	    	cbSubunternehmen.setValue(mangel.getSubunternehmen());
	    	txErfasst.setText(FormatHelper.formatDate(mangel.getErstelltam()));
	    	dpFrist.setValue(mangel.getErledigenbis().toInstant()
	    			.atZone(ZoneId.systemDefault()).toLocalDate());

	    	cbStatus.setValue(mangel.getMangelstatus());
			tvKommentar.setItems(FXCollections.observableArrayList(mangel.getKommentare()));
	    	tvKommentar.setVisible(true);
	    	taKommentar.setVisible(true);
	    	btSend.setVisible(true);
	    	txErfasst.setDisable(true);
			
			if(Context.getLogin().getRolle().getBezeichnung().contains("Kontakt")){
		    	txBeschreibung.setDisable(true);
		    	
//		    	if(mangel.getMangelstatus().getStatus().equals("abgeschlossen")){
//		    		cbStatus.setDisable(true);
//		    	}
		    	
		    	cbSubunternehmen.setDisable(true);
		    	dpFrist.setDisable(true);
			}

		} else {
	    	lbMangel.setText("neuer mangel");
	    	cbStatus.getSelectionModel().select(0);
//	    	cbStatus.setDisable(true);
	    	tvKommentar.setVisible(false);
	    	taKommentar.setVisible(false);
	    	btSend.setVisible(false);
		}
	}
	    
	@FXML
	public void clickShowProject(){
		ViewableDetail<Projekt> view = MainView.showCenterDetailView("ProjektDetail");
		view.initData(mangel.getProjekt());
	}
	
	@FXML
	public void clickShowSubunternehmen(){
		ViewableDetail<Subunternehmen> view = MainView.showCenterDetailView("SubunternehmenDetail");
		view.initData(mangel.getSubunternehmen());
	}
	
	@FXML
	public void clickSend(){
		if (mangel != null) {
			mangel.getKommentare().add(new Kommentar(taKommentar.getText(), Context.getLogin()));
		
			try {
				mangelcontroller.update(mangel);
				tvKommentar.setItems(FXCollections.observableArrayList(kommentarcontroller.getAll()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				MainView.showError(e);
			}
		}
	}

	@FXML
	public void clickAbbrechen(){
		showList();
	}
	
	@FXML
	public void clickSpeichern(){
		if (mangel != null) {
	        
			mangel.setProjekt(cbProjekt.getValue());
			mangel.setMangel(txBeschreibung.getText());
			mangel.setSubunternehmen(cbSubunternehmen.getValue());
			mangel.setMangelstatus(cbStatus.getValue());
			mangel.getErledigenbis().setTime(Date.from(dpFrist.getValue().atStartOfDay()
					.atZone(ZoneId.systemDefault()).toInstant()));

			try {
				mangelcontroller.update(mangel);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				MainView.showError(e);
			}
			
		}else{
			
			int ref = 1;
			try {
				List<Mangel> mList = mangelcontroller.getAll().stream().filter(m -> m.getProjekt().
						equals(cbProjekt.getValue())).collect(Collectors.toList());
				ref = mList.size() + 1;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				MainView.showError(e);
			}
			
			Calendar c = new GregorianCalendar();
			c.setTime(Date.from(dpFrist.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
			
			List<Kommentar> kList = new ArrayList<Kommentar>();
			
			mangel = new Mangel(ref, txBeschreibung.getText(), Context.getLogin().getPerson(),
					kList, cbStatus.getValue(), cbSubunternehmen.getValue(), c,
					cbProjekt.getValue());

			try {
				mangelcontroller.persist(mangel);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				MainView.showError(e);
			}
		}
		
		showList();
	}

	@Override
	public void initData(Mangel t) {
		mangel = t;
		Refresh();
	}

	@Override
	public void showList() {
		Viewable<Projekt, Projekt> view = MainView.showCenterView("Mangel");
		view.initData(mangel.getProjekt());
	}
}
