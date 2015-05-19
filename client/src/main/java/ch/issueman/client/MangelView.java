package ch.issueman.client;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import ch.issueman.common.FormatHelper;
import ch.issueman.common.Kommentar;
import ch.issueman.common.Kontakt;
import ch.issueman.common.Login;
import ch.issueman.common.Mangel;
import ch.issueman.common.Projekt;
import ch.issueman.common.Subunternehmen;

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
	private static Controller<Login, Integer> logincontroller = new Controller<Login, Integer>(Login.class);

	private FilteredList<Mangel> filteredData = new FilteredList<Mangel>(FXCollections.observableArrayList(), p -> true);
	
	private Projekt projekt;
	private List<Login> kpList;
	private List<Login> kaList;
	
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
				if(k.size() > 0){
					return new SimpleStringProperty(k.get(k.size() - 1).getKommentar());
				} else {
					return new SimpleStringProperty("");
				}
			}
		});
		
		// Mängel, bei welchen das Erledigungsdatum in der Vergangenheit liegen, werden rot markiert
		tvDataAbzuklären.setRowFactory(new Callback<TableView<Mangel>, TableRow<Mangel>>() {
	        @Override
	        public TableRow<Mangel> call(TableView<Mangel> tableView) {
	            final TableRow<Mangel> row = new TableRow<Mangel>() {
	                @Override
	                protected void updateItem(Mangel m, boolean empty){
	                    super.updateItem(m, empty);
	                    
	                    if(m != null){
	                    	if (m.getErledigenbis().before(new GregorianCalendar())) {
		                        if (! getStyleClass().contains("delay")) {
		                            getStyleClass().add("delay");
		                        }
		                    }
	                    }
	                }
	            };
          return row;
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
		
		// Mängel, bei welchen das Erledigungsdatum in der Vergangenheit liegen, werden rot markiert
		tvDataBeauftragt.setRowFactory(new Callback<TableView<Mangel>, TableRow<Mangel>>() {
	        @Override
	        public TableRow<Mangel> call(TableView<Mangel> tableView) {
	            final TableRow<Mangel> row = new TableRow<Mangel>() {
	                @Override
	                protected void updateItem(Mangel m, boolean empty){
	                    super.updateItem(m, empty);
	                    
	                    if(m != null){
	                    	if (m.getErledigenbis().before(new GregorianCalendar())) {
		                        if (! getStyleClass().contains("delay")) {
		                            getStyleClass().add("delay");
		                        }
		                    }
	                    }
	                }
	            };
          return row;
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
		
		// Mängel, bei welchen das Erledigungsdatum in der Vergangenheit liegen, werden rot markiert
		tvDataAngenommen.setRowFactory(new Callback<TableView<Mangel>, TableRow<Mangel>>() {
	        @Override
	        public TableRow<Mangel> call(TableView<Mangel> tableView) {
	            final TableRow<Mangel> row = new TableRow<Mangel>() {
	                @Override
	                protected void updateItem(Mangel m, boolean empty){
	                    super.updateItem(m, empty);
	                    
	                    if(m != null){
	                    	if (m.getErledigenbis().before(new GregorianCalendar())) {
		                        if (! getStyleClass().contains("delay")) {
		                            getStyleClass().add("delay");
		                        }
		                    }
	                    }
	                }
	            };
          return row;
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
		
		// Mängel, bei welchen das Erledigungsdatum in der Vergangenheit liegen, werden rot markiert
		tvDataBehoben.setRowFactory(new Callback<TableView<Mangel>, TableRow<Mangel>>() {
	        @Override
	        public TableRow<Mangel> call(TableView<Mangel> tableView) {
	            final TableRow<Mangel> row = new TableRow<Mangel>() {
	                @Override
	                protected void updateItem(Mangel m, boolean empty){
	                    super.updateItem(m, empty);
	                    
	                    if(m != null){
	                    	if (m.getErledigenbis().before(new GregorianCalendar())) {
		                        if (! getStyleClass().contains("delay")) {
		                            getStyleClass().add("delay");
		                        }
		                    }
	                    }
	                }
	            };
          return row;
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
				if(k.size() > 0){
					return new SimpleStringProperty(k.get(k.size() - 1).getKommentar());
				} else {
					return new SimpleStringProperty("");
				}
			}
		});
		
		// Mängel, bei welchen das Erledigungsdatum in der Vergangenheit liegen, werden rot markiert
		tvDataAbgeschlossen.setRowFactory(new Callback<TableView<Mangel>, TableRow<Mangel>>() {
	        @Override
	        public TableRow<Mangel> call(TableView<Mangel> tableView) {
	            final TableRow<Mangel> row = new TableRow<Mangel>() {
	                @Override
	                protected void updateItem(Mangel m, boolean empty){
	                    super.updateItem(m, empty);
	                    
	                    if(m != null){
	                    	if (m.getErledigenbis().before(new GregorianCalendar())) {
		                        if (! getStyleClass().contains("delay")) {
		                            getStyleClass().add("delay");
		                        }
		                    }
	                    }
	                }
	            };
          return row;
	        }
	    });

		Refresh();
	}

	@Override
	public void Refresh() {
		// Hinzufügen von neuen Mängel ist nur für Sachbearbeiter und Bauleiter möglich.
		// Bei anderen Benutzern wird der Button ausgeblendet.
		if(Context.getLogin().getRolle().getBezeichnung().equals("Bauleiter") ||
				Context.getLogin().getRolle().getBezeichnung().equals("Sachbearbeiter")){
			btAddMangel.setVisible(true);
		} else {
			btAddMangel.setVisible(false);
		}
		
		// Wenn links ein Projekt aus der Filterung ausgewählt wird
		if(projekt != null){
			
			// Anzeige der Projektreferenz und Projektname
			lbProjekt.setText(projekt.getDisplayName() + " - " + projekt.getTitle());
			
			// Comparator für die Sortierung nach Erledigungsdatum
		    Comparator<? super Mangel> comparatorMangelByDate = new Comparator<Mangel>() {
				@Override
				public int compare(Mangel m1, Mangel m2) {
					return m1.getErledigenbis().compareTo(m2.getErledigenbis());
				}
		    };
			
			try {
				// Alle Mängel auslesen, welche mit dem ausgwählten Projekt referenziert sind
				List<Mangel> mList = mangelcontroller.getAll().stream().filter(m -> m.getProjekt().equals(projekt)).collect(Collectors.toList());
				ObservableList<Mangel> list = FXCollections.observableArrayList(mList);
				// Sortierung nach Erledigungsdatum
				Collections.sort(list, comparatorMangelByDate);
				
				// Je nach Satatus werden die Mängel in der entsprechenden TableView angezeigt
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
				// Wenn kein Projekt angeklickt wurde, wird einfach das erste Projekt aus der Liste ausgewählt
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
		// Bei Doppelklick auf ein Mangel wird das Mangelobjekt mit der showDetail-Methode
		// an die Detail-Ansicht übergeben
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
		// Bei Doppelklick auf ein Mangel wird das Mangelobjekt mit der showDetail-Methode
		// an die Detail-Ansicht übergeben
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
		// Bei Doppelklick auf ein Mangel wird das Mangelobjekt mit der showDetail-Methode
		// an die Detail-Ansicht übergeben
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
		// Bei Doppelklick auf ein Mangel wird das Mangelobjekt mit der showDetail-Methode
		// an die Detail-Ansicht übergeben
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
		// Bei Doppelklick auf ein Mangel wird das Mangelobjekt mit der showDetail-Methode
		// an die Detail-Ansicht übergeben
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
		// Ansicht für die Erfassung eines neues Mangels wird geöffnet
		MainView.showCenterDetailView("MangelDetail");
	}

	@Override
	public void showDetail(Mangel t) {
		// Ansicht für die Anzeige von Mangel Details wird geöffnet und das Mangelobjekt
		// wird gleichzeitig übergeben
		ViewableDetail<Mangel> view = MainView.showCenterDetailView("MangelDetail");
		view.initData(t);
	}

	@SuppressWarnings("serial")
	@FXML
	public void clickExport(){
		// Export von Mängel
		try {
			if(projekt != null){
				kpList = logincontroller.getAll().stream().filter(l -> (l.getRolle().
						getBezeichnung().equals("Kontaktperson")) && (((Kontakt) l.getPerson()).
						getProjekte().contains(projekt))).collect(Collectors.toList());
				
				List<Subunternehmen> sList = new ArrayList<Subunternehmen>();
				kpList.forEach(p -> sList.add(((Kontakt) p.getPerson()).getSubunternehmen()));
				
				kaList = logincontroller.getAll().stream().filter(l -> (l.getRolle().
						getBezeichnung().equals("Kontaktadmin")) && (((Kontakt) l.getPerson()).
						getProjekte().contains(projekt))).collect(Collectors.toList());
				
				kaList.removeAll(kaList.stream().filter(a -> sList.contains(((Kontakt)
						a.getPerson()).getSubunternehmen())).collect(Collectors.toList()));
				
				kpList.addAll(kaList);
			}
		} catch (Exception e) {
			MainView.showError(e);
		}
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		List<Mangel> allMangelList = null;

		// add header
		list.add(new ArrayList<String>(){{
			add("Projekt");
			add("Referenz");
		    add("Status");
		    add("Mangel");
		    add("Erfasser");
		    add("Subunternehmen");
		    add("Kontaktperson");
		    add("Kommentar");
		    add("erledigen bis");
		}});
		
		//Filterung der Mängel
		try {
			allMangelList = mangelcontroller.getAll().stream().filter(m -> m.getProjekt().equals(projekt)).collect(Collectors.toList());
		} catch (Exception e) {
			MainView.showError(e);
		}
		// add content from list view
		allMangelList.forEach(p -> list.add(new ArrayList<String>(){{
			List<Kommentar> k = p.getKommentare();
			add(p.getProjekt().getDisplayName() + " - " + projekt.getTitle());
			add(p.getProjekt().getDisplayName() + "M" + ("000" + 
	    		p.getReferenz()).substring((("000" + p.getReferenz()).length())-3));
			add(p.getMangelstatus().getStatus());
			add(p.getMangel());
			add(p.getErfasser().getDisplayName());
			add(p.getSubunternehmen().getFirmenname());
			
			if(((kpList.stream().filter(a -> (((Kontakt) a.getPerson()).getSubunternehmen().
					equals(p.getSubunternehmen()))).collect(Collectors.toList()))).size() > 0){
				add(((kpList.stream().filter(a -> (((Kontakt) a.getPerson()).
						getSubunternehmen().equals(p.getSubunternehmen()))).collect(Collectors.toList()).
						get(0))).getPerson().getDisplayName());
			} else {
				add("");
			}
			add(k.get(k.size()-1).getKommentar());
			add((FormatHelper.formatDate(p.getErledigenbis())));
			
		}}));
		
		MainView.exportData(list);
	}
	@Override
	public void initData(Projekt t) {
		// Formular mit Projekt initilisieren
		projekt = t;
		Refresh();
	}

}
