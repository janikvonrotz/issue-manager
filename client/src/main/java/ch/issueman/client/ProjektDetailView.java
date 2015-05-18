package ch.issueman.client;

import java.net.URL;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
import ch.issueman.common.Adresse;
import ch.issueman.common.Arbeitstyp;
import ch.issueman.common.Bauherr;
import ch.issueman.common.Bauleiter;
import ch.issueman.common.Kontakt;
import ch.issueman.common.Login;
import ch.issueman.common.Ort;
import ch.issueman.common.Projekt;
import ch.issueman.common.Projektleitung;
import ch.issueman.common.Projekttyp;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Subunternehmen;

/**
 * class ProjektDetailView
 * 
 * @author Reno Meyer
 * @version 1.0.0
 * @since 1.0.0
 */
public class ProjektDetailView implements ViewableDetail<Projekt> {
	
	private static Controller<Projekttyp, Integer> projekttypcontroller = new Controller<Projekttyp, Integer>(Projekttyp.class);
	private static Controller<Ort, Integer> ortcontroller = new Controller<Ort, Integer>(Ort.class);
	private static Controller<Bauherr, Integer> bauherrcontroller = new Controller<Bauherr, Integer>(Bauherr.class);
	private static Controller<Projekt, Integer> projektcontroller = new Controller<Projekt, Integer>(Projekt.class);
	private static Controller<Arbeitstyp, Integer> arbeitstypcontroller = new Controller<Arbeitstyp, Integer>(Arbeitstyp.class);
	private static Controller<Kontakt, Integer> kontaktcontroller = new Controller<Kontakt, Integer>(Kontakt.class);
	private static Controller<Login, Integer> logincontroller = new Controller<Login, Integer>(Login.class);
	private Projekt projekt;
	private Subunternehmen subunternehmen;
	private String s = "";
	private Login lOld;
	private Login kaLogin;

	@FXML
	private Label lbProjekt;
	
	@FXML
	private TextField txTitel;
	
	@FXML
	private TextField txStrasse;
	
	@FXML
	private ComboBox<Ort> cbOrt;
	
	@FXML
	private ComboBox<Arbeitstyp> cbArbeitstyp;
	
	@FXML
	private ComboBox<Projekttyp> cbProjekttyp;
	
	@FXML
	private DatePicker dpBeginn;
	
	@FXML
	private DatePicker dpEnde;
	
	@FXML
	private ComboBox<Bauherr> cbBauherr;
	
	@FXML
	private TextField txProjektleiter;
	
	@FXML
	private Button btProjektleiter;
	
	@FXML
	private TextArea taSubunternehmen;
	
	@FXML
	private Button btSubunternehmen;
	
	@FXML
	private ComboBox<Login> cbKontakt;

	@FXML
	private CheckBox chArchivieren;
	
	@FXML
	private Button btAbbrechen;
	
	@FXML
	private Button btSpeichern;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		cbOrt.setCellFactory(new Callback<ListView<Ort>,ListCell<Ort>>(){
			@Override
			public ListCell<Ort> call(ListView<Ort> arg0) {		 
				final ListCell<Ort> cell = new ListCell<Ort>(){				 
                    @Override
                    protected void updateItem(Ort o, boolean bln) {
                        super.updateItem(o, bln);
                         
                        if(o != null){
                            setText(o.getPlz() + " - " + o.getOrt());
                        }else{
                            setText(null);
                        }
                    }
                };
				return cell;
			}
        });
		
		cbOrt.setConverter(new StringConverter<Ort>() {
            private Map<String, Object> map = new HashMap<>();

 			@Override
			public Ort fromString(String arg0) {
				return null;
			}

			public String toString(Ort o) {
               if (o != null) {
                    String str = o.getPlz() + " - " + o.getOrt();
                    map.put(str, o);
                    return str;
                } else {
                    return "";
                }
			}
		});
		
		cbArbeitstyp.setCellFactory(new Callback<ListView<Arbeitstyp>,ListCell<Arbeitstyp>>(){
			@Override
			public ListCell<Arbeitstyp> call(ListView<Arbeitstyp> arg0) {		 
				final ListCell<Arbeitstyp> cell = new ListCell<Arbeitstyp>(){				 
                    @Override
                    protected void updateItem(Arbeitstyp a, boolean bln) {
                        super.updateItem(a, bln);
                         
                        if(a != null){
                            setText(a.getArbeitstyp());
                        }else{
                            setText(null);
                        }
                    }
                };
				return cell;
			}
        });
		
		cbArbeitstyp.setConverter(new StringConverter<Arbeitstyp>() {
            private Map<String, Object> map = new HashMap<>();

 			@Override
			public Arbeitstyp fromString(String arg0) {
				return null;
			}

			public String toString(Arbeitstyp a) {
               if (a != null) {
                    String str = a.getArbeitstyp();
                    map.put(str, a);
                    return str;
                } else {
                    return "";
                }
			}
		});

		
		cbProjekttyp.setCellFactory(new Callback<ListView<Projekttyp>,ListCell<Projekttyp>>(){
			@Override
			public ListCell<Projekttyp> call(ListView<Projekttyp> arg0) {		 
				final ListCell<Projekttyp> cell = new ListCell<Projekttyp>(){				 
                    @Override
                    protected void updateItem(Projekttyp t, boolean bln) {
                        super.updateItem(t, bln);
                         
                        if(t != null){
                            setText(t.getProjekttyp());
                        }else{
                            setText(null);
                        }
                    }
                };
				return cell;
			}
        });

		cbProjekttyp.setConverter(new StringConverter<Projekttyp>() {
            private Map<String, Object> map = new HashMap<>();

 			@Override
			public Projekttyp fromString(String arg0) {
				return null;
			}

			public String toString(Projekttyp p) {
               if (p != null) {
                    String str = p.getProjekttyp();
                    map.put(str, p);
                    return str;
                } else {
                    return "";
                }
			}
		});
	
		cbBauherr.setCellFactory(new Callback<ListView<Bauherr>,ListCell<Bauherr>>(){
			@Override
			public ListCell<Bauherr> call(ListView<Bauherr> arg0) {		 
				final ListCell<Bauherr> cell = new ListCell<Bauherr>(){				 
                    @Override
                    protected void updateItem(Bauherr t, boolean bln) {
                        super.updateItem(t, bln);
                         
                        if(t != null){
                            setText(t.getDisplayName());
                        }else{
                            setText(null);
                        }
                    }
                };
				return cell;
			}
        });
		
		cbBauherr.setConverter(new StringConverter<Bauherr>() {
            private Map<String, Object> map = new HashMap<>();

 			@Override
			public Bauherr fromString(String arg0) {
				return null;
			}

			public String toString(Bauherr b) {
               if (b != null) {
                    String str = b.getDisplayName();
                    map.put(str, b);
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
	
	public void Refresh(){
		
		try {
			List<Ort> oList = ortcontroller.getAll();
			cbOrt.setItems(new SortedList<Ort>(FXCollections.observableArrayList(oList)));
			
			cbArbeitstyp.setItems(FXCollections.observableArrayList(arbeitstypcontroller.getAll()));
			cbProjekttyp.setItems(FXCollections.observableArrayList(projekttypcontroller.getAll()));
			cbBauherr.setItems(FXCollections.observableArrayList(bauherrcontroller.getAll()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MainView.showError(e);
		}
    	txProjektleiter.setEditable(false);
    	taSubunternehmen.setEditable(false);
		
		if(projekt != null){
		
			lbProjekt.setText(projekt.getDisplayName() + " - " + projekt.getTitle());
	    	txTitel.setText(projekt.getTitle());
	    	txStrasse.setText(projekt.getAdresse().getStrasse());
	    	cbOrt.setValue(projekt.getAdresse().getOrt());
	    	cbArbeitstyp.setValue(projekt.getArbeitstyp());
	    	cbProjekttyp.setValue(projekt.getProjekttyp());
	    	dpBeginn.setValue(projekt.getBeginn().toInstant()
	    			.atZone(ZoneId.systemDefault()).toLocalDate());
	    	dpEnde.setValue(projekt.getEnde().toInstant()
	    			.atZone(ZoneId.systemDefault()).toLocalDate());
	    	cbBauherr.setValue(projekt.getBauherr());
	    	
	    	if(projekt.getCurrentProjektleiter() != null){
	    		txProjektleiter.setText(projekt.getCurrentProjektleiter().getDisplayName());
	    	} else {
	    		txProjektleiter.setText("");
	    	}
	    	
	    	
			try {
				List<Login> lList = logincontroller.getAll().stream().filter(l -> (l.getRolle().
						getBezeichnung().equals("Kontaktadmin")) && (((Kontakt) l.getPerson()).
								getProjekte().contains(projekt))).collect(Collectors.toList());
				if(lList.size() > 0){
					lList.forEach(l -> s += ((Kontakt) l.getPerson()).getSubunternehmen().
							getFirmenname() + ", ");
					taSubunternehmen.setText(s.substring(0, s.length()-2));
				}	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				MainView.showError(e);
			}

			if (Context.getLogin().getPerson() instanceof Sachbearbeiter){
				sbForm();
			} else if (Context.getLogin().getPerson() instanceof Bauleiter){
				cbKontakt.setVisible(false);
			} else if (Context.getLogin().getPerson() instanceof Kontakt){
				
				try {
					subunternehmen = ((Kontakt) Context.getLogin().getPerson()).getSubunternehmen();
					List<Login> lList = logincontroller.getAll().stream().filter(l ->
							((Kontakt) l.getPerson()).getSubunternehmen().equals(subunternehmen)).
							collect(Collectors.toList());
					cbKontakt.setItems(FXCollections.observableArrayList(lList));

					lOld = lList.stream().filter(p -> (((Kontakt) p.getPerson()).getProjekte().
							contains(projekt)) && p.getRolle().getBezeichnung().
							equals("Kontaktperson")).collect(Collectors.toList()).get(0);
					
					kaLogin = lList.stream().filter(p -> (((Kontakt) p.getPerson()).getProjekte().
							contains(projekt)) && p.getRolle().getBezeichnung().
							equals("Kontaktadmin")).collect(Collectors.toList()).get(0);
					
					if(lOld != null){
						cbKontakt.setValue(lOld);
					} else if(kaLogin != null){
						cbKontakt.setValue(kaLogin);
					}
					
				} catch (Exception e) {
					MainView.showError(e);
				}
				
				if(Context.getLogin().getRolle().getBezeichnung().equals("Kontaktadmin")){
					kaForm();
				} else {
					kpForm();
				}
			}

		} else {
			sbForm();
			lbProjekt.setText("neues projekt");
			txProjektleiter.setVisible(false);
			btProjektleiter.setVisible(false);
			taSubunternehmen.setVisible(false);
			btSubunternehmen.setVisible(false);
			chArchivieren.setVisible(false);
	    }
	}
	
	@FXML
	public void clickAbbrechen(){
		showList();
	}
		
	@FXML
	public void clickSpeichern(){
		
		if (projekt != null) {
	        
			projekt.setTitle(txTitel.getText());
			
			Adresse a = projekt.getAdresse();
			a.setStrasse(txStrasse.getText());
			a.setOrt(cbOrt.getValue());
			projekt.setAdresse(a);
			
			projekt.setArbeitstyp(cbArbeitstyp.getValue());
			projekt.setProjekttyp(cbProjekttyp.getValue());
			
			projekt.getBeginn().setTime(Date.from(dpBeginn.getValue().atStartOfDay()
					.atZone(ZoneId.systemDefault()).toInstant()));
			projekt.getEnde().setTime(Date.from(dpEnde.getValue().atStartOfDay()
					.atZone(ZoneId.systemDefault()).toInstant()));
			
			projekt.setBauherr(cbBauherr.getValue());
						
			try {
				if(Context.getLogin().getPerson() instanceof Sachbearbeiter || 
						Context.getLogin().getPerson() instanceof Bauleiter){
					projektcontroller.update(projekt);
				} else if((Context.getLogin().getRolle().getBezeichnung().equals("Kontaktadmin"))){
					if(lOld != null){
						Kontakt kOld = ((Kontakt) lOld.getPerson());
						kOld.getProjekte().remove(projekt);
						kontaktcontroller.update(kOld);
					}
					
					Kontakt kNew = (Kontakt) cbKontakt.getValue().getPerson();
					kNew.getProjekte().add(projekt);
					kontaktcontroller.update(kNew);			
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				MainView.showError(e);
			}
		
		} else {
			
			Calendar beginn = new GregorianCalendar();
			beginn.setTime(Date.from(dpBeginn.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

			Calendar ende = new GregorianCalendar();
			ende.setTime(Date.from(dpEnde.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
					
			try {
				projektcontroller.persist(new Projekt(txTitel.getText(),
						new Adresse(txStrasse.getText(), cbOrt.getValue()), cbArbeitstyp.getValue(),
						cbProjekttyp.getValue(), cbBauherr.getValue(), new ArrayList<Projektleitung>(), beginn, ende));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				MainView.showError(e);
			}
		}	
		showList();
	}
	
	@Override
	public void initData(Projekt p) {
		projekt = p;
		Refresh();
	}

	@Override
	public void showList() {
		Viewable<Projekt, Projekt> view = MainView.showCenterView("Projekt");
		view.initData(null);
	}

	public void showProjektleitung() {
		Viewable<Projekt, Projekt> view = MainView.showCenterView("Projektleitung");
		view.initData(projekt);
	}

	public void showZugewiesene() {
		Viewable<Projekt, Projekt> view = MainView.showCenterView("SubunternehmenZugewiesen");
		view.initData(projekt);
	}

	public void sbForm(){
		txTitel.setDisable(false);
		txStrasse.setDisable(false);
		cbOrt.setDisable(false);
		cbArbeitstyp.setDisable(false);
		cbProjekttyp.setDisable(false);
		dpBeginn.setDisable(false);
		dpEnde.setDisable(false);
		cbBauherr.setVisible(true);
		txProjektleiter.setVisible(true);
		btProjektleiter.setVisible(true);
		taSubunternehmen.setVisible(true);
		cbKontakt.setVisible(false);
		chArchivieren.setVisible(true);
	}

	public void blForm(){
		txTitel.setDisable(false);
		txStrasse.setDisable(false);
		cbOrt.setDisable(false);
		cbArbeitstyp.setDisable(false);
		cbProjekttyp.setDisable(false);
		dpBeginn.setDisable(false);
		dpEnde.setDisable(false);
		cbBauherr.setVisible(true);
		txProjektleiter.setVisible(true);
		taSubunternehmen.setVisible(true);
		cbKontakt.setVisible(false);
		chArchivieren.setVisible(false);
	}

	public void kpForm(){
		txTitel.setDisable(true);
		txStrasse.setDisable(true);
		cbOrt.setDisable(true);
		cbArbeitstyp.setDisable(true);
		cbProjekttyp.setDisable(true);
		dpBeginn.setDisable(true);
		dpEnde.setDisable(true);
		cbBauherr.setVisible(false);
		txProjektleiter.setVisible(true);
		taSubunternehmen.setVisible(false);
		cbKontakt.setVisible(false);
		chArchivieren.setVisible(false);
	}

	public void kaForm(){
		txTitel.setDisable(true);
		txStrasse.setDisable(true);
		cbOrt.setDisable(true);
		cbArbeitstyp.setDisable(true);
		cbProjekttyp.setDisable(true);
		dpBeginn.setDisable(true);
		dpEnde.setDisable(true);
		cbBauherr.setVisible(false);
		txProjektleiter.setVisible(true);
		taSubunternehmen.setVisible(false);
		cbKontakt.setVisible(true);
		chArchivieren.setVisible(false);
	}
}
