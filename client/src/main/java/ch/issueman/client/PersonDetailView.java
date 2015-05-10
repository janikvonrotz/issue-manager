package ch.issueman.client;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;
import ch.issueman.common.Adresse;
import ch.issueman.common.Arbeitstyp;
import ch.issueman.common.Bauherr;
import ch.issueman.common.Bauleiter;
import ch.issueman.common.Kommentar;
import ch.issueman.common.Kontakt;
import ch.issueman.common.Login;
import ch.issueman.common.Mangel;
import ch.issueman.common.Mangelstatus;
import ch.issueman.common.Ort;
import ch.issueman.common.Person;
import ch.issueman.common.Projekt;
import ch.issueman.common.Projekttyp;
import ch.issueman.common.Rolle;
import ch.issueman.common.Sachbearbeiter;
import ch.issueman.common.Subunternehmen;
import ch.issueman.common.Unternehmen;

/**
 * class PersonDetailView
 * 
 * @author Reno Meyer
 * @version 1.0.0
 * @since 1.0.0
 */
public class PersonDetailView implements ViewableDetail<Person> {
	
	private static Controller<Person, Integer> personcontroller = new Controller<Person, Integer>(Person.class);
	private static Controller<Login, Integer> logincontroller = new Controller<Login, Integer>(Login.class);
	private static Controller<Sachbearbeiter, Integer> sachbearbeitercontroller = new Controller<Sachbearbeiter, Integer>(Sachbearbeiter.class);
	private static Controller<Bauleiter, Integer> bauleitercontroller = new Controller<Bauleiter, Integer>(Bauleiter.class);
	private static Controller<Kontakt, Integer> kontaktcontroller = new Controller<Kontakt, Integer>(Kontakt.class);
	private static Controller<Bauherr, Integer> bauherrcontroller = new Controller<Bauherr, Integer>(Bauherr.class);
	private static Controller<Subunternehmen, Integer> subunternehmencontroller = new Controller<Subunternehmen, Integer>(Subunternehmen.class);
	private static Controller<Unternehmen, Integer> unternehmencontroller = new Controller<Unternehmen, Integer>(Unternehmen.class);
	private static Controller<Rolle, Integer> rollecontroller = new Controller<Rolle, Integer>(Rolle.class);
	private static Controller<Ort, Integer> ortcontroller = new Controller<Ort, Integer>(Ort.class);
	private Person person;
	private Sachbearbeiter sachbearbeiter;
	private Bauleiter bauleiter;
	private Kontakt kontakt;
	private Bauherr bauherr;
	private Login login;
	
	@FXML
	private Label lbPerson;
	
	@FXML
	private TextField txNachname;
	
	@FXML
	private TextField txVorname;
	
	@FXML
	private TextField txEmail;
	
	@FXML
	private ComboBox<Rolle> cbRolle;
	
	@FXML
	private PasswordField pfPasswort;
	
	@FXML
	private ComboBox<Subunternehmen> cbSubunternehmen;

	@FXML
	private TextField txFirma;

	@FXML
	private TextField txStrasse;
	
	@FXML
	private ComboBox<Ort> cbOrt;
	
	@FXML
	private Button btAbbrechen;
	
	@FXML
	private Button btPasswort;
	
	@FXML
	private Button btSpeichern;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();
		
		cbRolle.setCellFactory(new Callback<ListView<Rolle>,ListCell<Rolle>>(){
			@Override
			public ListCell<Rolle> call(ListView<Rolle> arg0) {		 
				final ListCell<Rolle> cell = new ListCell<Rolle>(){				 
                    @Override
                    protected void updateItem(Rolle r, boolean bln) {
                        super.updateItem(r, bln);
                         
                        if(r != null){
                            setText(r.getBezeichnung());
                        }else{
                            setText(null);
                        }
                    }
                };
				return cell;
			}
        });
		
		cbRolle.setConverter(new StringConverter<Rolle>() {
            private Map<String, Object> map = new HashMap<>();

 			@Override
			public Rolle fromString(String arg0) {
				return null;
			}

			public String toString(Rolle r) {
               if (r != null) {
                    String str = r.getBezeichnung();
                    map.put(str, r);
                    return str;
                } else {
                    return "";
                }
			}
		});
		
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
		
		Refresh();	

	}
	
	@Override
	public void Refresh(){
		
		if(person != null){
			
			lbPerson.setText(person.getDisplayName());
			txNachname.setText(person.getNachname());
			txVorname.setText(person.getVorname());
			txEmail.setText(person.getEmail());
			cbRolle.setDisable(true);

			try {
				
				cbRolle.setItems(FXCollections.observableArrayList(rollecontroller.getAll()));
				cbOrt.setItems(FXCollections.observableArrayList(ortcontroller.getAll()));
				
				sachbearbeiter = (Sachbearbeiter) FXCollections.observableArrayList(sachbearbeitercontroller.getById(person.getId()));			
				bauleiter = (Bauleiter) FXCollections.observableArrayList(bauleitercontroller.getById(person.getId()));			
				kontakt = (Kontakt) FXCollections.observableArrayList(kontaktcontroller.getById(person.getId()));
				bauherr = (Bauherr) FXCollections.observableArrayList(bauherrcontroller.getById(person.getId()));
				
				ObservableList<Login> logins = FXCollections.observableArrayList(logincontroller.getAll());
				for(Login l : logins){
					if(l.getPerson() == person){
						cbRolle.setValue(l.getRolle());
						login = l;
					}
				}
				
				if(kontakt != null){
					cbSubunternehmen.setValue(kontakt.getSubunternehmen());
				}
				
				if(bauherr != null){
					txFirma.setText(bauherr.getUnternehmen().getFirmenname());
					txStrasse.setText(bauherr.getUnternehmen().getAdresse().getStrasse());
					cbOrt.setValue(bauherr.getUnternehmen().getAdresse().getOrt());
				}
								
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			lbPerson.setText("neue person");
		}
	}

	@FXML
	public void clickAbbrechen(){
		showList();
	}
	
	@FXML
	public void clickPasswort(){
		clickSpeichern();
		Viewable<Login, Login> view = MainView.showCenterView("Passwort");
		view.initData(login);
	}
	
	@FXML
	public void clickSpeichern(){
		if (person != null) {
	        
			if (sachbearbeiter != null){
				sachbearbeiter.setNachname(txNachname.getText());
				sachbearbeiter.setVorname(txVorname.getText());
				sachbearbeiter.setEmail(txEmail.getText());
				
				try {
					sachbearbeitercontroller.update(sachbearbeiter);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (bauleiter != null){
				bauleiter.setNachname(txNachname.getText());
				bauleiter.setVorname(txVorname.getText());
				bauleiter.setEmail(txEmail.getText());
				
				try {
					bauleitercontroller.update(bauleiter);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (kontakt != null){
				kontakt.setNachname(txNachname.getText());
				kontakt.setVorname(txVorname.getText());
				kontakt.setEmail(txEmail.getText());
				kontakt.setSubunternehmen(cbSubunternehmen.getValue());				
				
				try {
					kontaktcontroller.update(kontakt);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (bauherr != null){
				bauherr.setNachname(txNachname.getText());
				bauherr.setVorname(txVorname.getText());
				bauherr.setEmail(txEmail.getText());
				
				Unternehmen u = bauherr.getUnternehmen();
				Adresse a = u.getAdresse();
				a.setStrasse(txStrasse.getText());
				a.setOrt(cbOrt.getValue());
				u.setAdresse(a);
				bauherr.setUnternehmen(u);
				
				try {
					bauherrcontroller.update(bauherr);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}else{

			switch (cbRolle.getValue().getBezeichnung()) {
			case "Sachbearbeiter":
				sachbearbeiter = new Sachbearbeiter(txNachname.getText(),
						txVorname.getText(), txEmail.getText());
				login = new Login(sachbearbeiter, pfPasswort.getText(), cbRolle.getValue());
				
				try {
					sachbearbeitercontroller.persist(sachbearbeiter);
					logincontroller.persist(login);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				break;

			case "Bauleiter":
				bauleiter = new Bauleiter(txNachname.getText(),
						txVorname.getText(), txEmail.getText());
				login = new Login(bauleiter, pfPasswort.getText(), cbRolle.getValue());
				
				try {
					bauleitercontroller.persist(bauleiter);
					logincontroller.persist(login);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				break;

			case "Kontaktperson":
				kontakt = new Kontakt(txNachname.getText(),
						txVorname.getText(), txEmail.getText(), cbSubunternehmen.getValue(), null);
				login = new Login(kontakt, pfPasswort.getText(), cbRolle.getValue());
				
				try {
					kontaktcontroller.persist(kontakt);
					logincontroller.persist(login);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				break;

			case "Kontaktadmin":
				kontakt = new Kontakt(txNachname.getText(),
						txVorname.getText(), txEmail.getText(), cbSubunternehmen.getValue(), null);
				login = new Login(kontakt, pfPasswort.getText(), cbRolle.getValue());
				
				try {
					kontaktcontroller.persist(kontakt);
					logincontroller.persist(login);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				break;

			case "Bauherr":
				Ort o = null;
				try {
					o = ortcontroller.getById(cbOrt.getValue().getId());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				bauherr = new Bauherr(txNachname.getText(),
						txVorname.getText(), txEmail.getText(), new Unternehmen(txFirma.getText(),
								new Adresse(txStrasse.getText(), o)));
				
				try {
					bauherrcontroller.persist(bauherr);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				break;
			}
		}	
	}

	@Override
	public void initData(Person t) {
		person = t;
		Refresh();
	}

	@Override
	public void showList() {
		Viewable<Person, Person> view = MainView.showCenterView("Person");
		view.initData(null);
	}

}
