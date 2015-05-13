package ch.issueman.client;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import javafx.util.StringConverter;
import ch.issueman.common.Adresse;
import ch.issueman.common.Bauherr;
import ch.issueman.common.Bauleiter;
import ch.issueman.common.Kontakt;
import ch.issueman.common.Login;
import ch.issueman.common.Ort;
import ch.issueman.common.Person;
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
	
	private static Controller<Login, Integer> logincontroller = new Controller<Login, Integer>(Login.class);
	private static Controller<Sachbearbeiter, Integer> sachbearbeitercontroller = new Controller<Sachbearbeiter, Integer>(Sachbearbeiter.class);
	private static Controller<Bauleiter, Integer> bauleitercontroller = new Controller<Bauleiter, Integer>(Bauleiter.class);
	private static Controller<Kontakt, Integer> kontaktcontroller = new Controller<Kontakt, Integer>(Kontakt.class);
	private static Controller<Bauherr, Integer> bauherrcontroller = new Controller<Bauherr, Integer>(Bauherr.class);
	private static Controller<Rolle, Integer> rollecontroller = new Controller<Rolle, Integer>(Rolle.class);
	private static Controller<Subunternehmen, Integer> subunternehmencontroller = new Controller<Subunternehmen, Integer>(Subunternehmen.class);
	private static Controller<Ort, Integer> ortcontroller = new Controller<Ort, Integer>(Ort.class);
	private Person person = null;
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
	private Label lbPasswort;
	
	@FXML
	private Label lbSubunternehmen;
	
	@FXML
	private Label lbFirma;
	
	@FXML
	private Label lbStrasse;
	
	@FXML
	private Label lbOrt;
	
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
		
		cbRolle.setOnAction((event) -> {
			if(cbRolle.getValue().getBezeichnung().equals("Sachbearbeiter")){
				sbForm();
			} else if(cbRolle.getValue().getBezeichnung().equals("Bauleiter")){
				blForm();
			} else if(cbRolle.getValue().getBezeichnung().contains("Kontakt")){
				koForm();
			} else if(cbRolle.getValue().getBezeichnung().equals("Bauherr")){
				bhForm();
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
		
		try {
			ObservableList<Rolle> rollenList = FXCollections.observableArrayList(rollecontroller.getAll());
			rollenList.add(new Rolle("Bauherr"));
			cbRolle.setItems(rollenList);
			cbSubunternehmen.setItems(FXCollections.observableArrayList(subunternehmencontroller.getAll()));
			cbOrt.setItems(FXCollections.observableArrayList(ortcontroller.getAll()));
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MainView.showError(e);
		}

		
		if(person != null){
			
			lbPerson.setText(person.getDisplayName());
			txNachname.setText(person.getNachname());
			txVorname.setText(person.getVorname());
			txEmail.setText(person.getEmail());
			cbRolle.setDisable(true);
			
			try {
				login = logincontroller.getAll().stream().filter(p -> p.getPerson()
						.equals(person)).collect(Collectors.toList()).get(0);												
			} catch (Exception e) {
				// TODO Auto-generated catch block
				MainView.showError(e);
			}
			
			cbRolle.setValue(login.getRolle());
			
			if(person instanceof Sachbearbeiter){
				sbForm();
			} else if(person instanceof Bauleiter){
				blForm();
			} else if(person instanceof Kontakt){
				koForm();
				cbSubunternehmen.setValue(((Kontakt) person).getSubunternehmen());
			} else if(person instanceof Bauherr){
				bhForm();
				txFirma.setText(((Bauherr) person).getUnternehmen().getFirmenname());
				txStrasse.setText(((Bauherr) person).getUnternehmen().getAdresse().getStrasse());
				cbOrt.setValue(((Bauherr) person).getUnternehmen().getAdresse().getOrt());
			}
			
			if(!(Context.getLogin().equals(login))){
				btPasswort.setVisible(false);
			}
			
			if(!(Context.getLogin().getPerson() instanceof Sachbearbeiter)){
				pfPasswort.setVisible(false);
			}
			
		} else {
			lbPerson.setText("neue person");
			cbSubunternehmen.setVisible(false);
			txFirma.setVisible(false);
			txStrasse.setVisible(false);
			cbOrt.setVisible(false);
			btPasswort.setVisible(false);
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
		view.initData(Context.getLogin());
	}
	
	@FXML
	public void clickSpeichern(){
		if (person != null) {
	        
			if (person instanceof Sachbearbeiter){
				((Sachbearbeiter) person).setNachname(txNachname.getText());
				((Sachbearbeiter) person).setVorname(txVorname.getText());
				((Sachbearbeiter) person).setEmail(txEmail.getText());
				
				try {
					sachbearbeitercontroller.update(((Sachbearbeiter) person));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (person instanceof Bauleiter){
				((Bauleiter) person).setNachname(txNachname.getText());
				((Bauleiter) person).setVorname(txVorname.getText());
				((Bauleiter) person).setEmail(txEmail.getText());
				
				try {
					bauleitercontroller.update(((Bauleiter) person));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (person instanceof Kontakt){
				((Kontakt) person).setNachname(txNachname.getText());
				((Kontakt) person).setVorname(txVorname.getText());
				((Kontakt) person).setEmail(txEmail.getText());
				((Kontakt) person).setSubunternehmen(cbSubunternehmen.getValue());				
				
				try {
					kontaktcontroller.update(((Kontakt) person));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (person instanceof Bauherr){
				((Bauherr) person).setNachname(txNachname.getText());
				((Bauherr) person).setVorname(txVorname.getText());
				((Bauherr) person).setEmail(txEmail.getText());
				
				Unternehmen u = ((Bauherr) person).getUnternehmen();
				Adresse a = u.getAdresse();
				a.setStrasse(txStrasse.getText());
				a.setOrt(cbOrt.getValue());
				u.setAdresse(a);
				((Bauherr) person).setUnternehmen(u);
				
				try {
					bauherrcontroller.update(((Bauherr) person));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		} else {

			switch (cbRolle.getValue().getBezeichnung()) {
			case "Sachbearbeiter":
				Sachbearbeiter sachbearbeiter = new Sachbearbeiter(txNachname.getText(),
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
				Bauleiter bauleiter = new Bauleiter(txNachname.getText(),
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
				Kontakt kontaktperson = new Kontakt(txNachname.getText(),
						txVorname.getText(), txEmail.getText(), cbSubunternehmen.getValue(), null);
				login = new Login(kontaktperson, pfPasswort.getText(), cbRolle.getValue());
				
				try {
					kontaktcontroller.persist(kontaktperson);
					logincontroller.persist(login);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				break;

			case "Kontaktadmin":
				Kontakt kontaktadmin = new Kontakt(txNachname.getText(),
						txVorname.getText(), txEmail.getText(), cbSubunternehmen.getValue(), null);
				login = new Login(kontaktadmin, pfPasswort.getText(), cbRolle.getValue());
				
				try {
					kontaktcontroller.persist(kontaktadmin);
					logincontroller.persist(login);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				break;

			case "Bauherr":
				Bauherr bauherr = new Bauherr(txNachname.getText(),
						txVorname.getText(), txEmail.getText(), new Unternehmen(txFirma.getText(),
								new Adresse(txStrasse.getText(), cbOrt.getValue())));
				
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
		Viewable<Login, Login> view = MainView.showCenterView("Person");
		view.initData(null);
	}
	
	@FXML
	public void sbForm(){
		cbSubunternehmen.setVisible(false);
		txFirma.setVisible(false);
		txStrasse.setVisible(false);
		cbOrt.setVisible(false);
	}
	
	@FXML
	public void blForm(){
		cbSubunternehmen.setVisible(false);
		txFirma.setVisible(false);
		txStrasse.setVisible(false);
		cbOrt.setVisible(false);
	}
	
	@FXML
	public void koForm(){
		cbSubunternehmen.setVisible(true);
		txFirma.setVisible(false);
		txStrasse.setVisible(false);
		cbOrt.setVisible(false);
	}
	
	@FXML
	public void bhForm(){
		cbSubunternehmen.setVisible(false);
		txFirma.setVisible(true);
		txStrasse.setVisible(true);
		cbOrt.setVisible(true);
	}
}
