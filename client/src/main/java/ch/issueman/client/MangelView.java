package ch.issueman.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import ch.issueman.common.Login;
import ch.issueman.common.Mangel;
import ch.issueman.common.Sachbearbeiter;

/**
 * List view for Mangel
 * 
 * @author Aathavan Theivendram
 * @version 1.0.0
 * @since 1.0.0
 */
public class MangelView implements Viewable<Mangel, Mangel> {

	private static Controller<Mangel, Integer> controller = new Controller<Mangel, Integer>(Mangel.class);

	private FilteredList<Mangel> filteredData = new FilteredList<Mangel>(FXCollections.observableArrayList(), p -> true);

	@FXML
	private TableView<Mangel> tvDataAbzuklären;
	
	@FXML
	private TableView<Mangel> tvDataAbgeschlossen;

	@FXML
	private TextField txFilter;

	@FXML
	private TableColumn<Mangel, Integer> tcId;

	@FXML
	private TableColumn<Mangel, String> tcMangelAbzuklären;

	@FXML
	private TableColumn<Mangel, String> tcSubunternehmenAbzuklären;

	@FXML
	private TableColumn<Mangel, String> tcKommentarAbzuklären;

	@FXML
	private TableColumn<Mangel, String> tcMangelAbgeschlossen;
	
	@FXML
	private TableColumn<Mangel, String> tcSubunternehmenAbgeschlossen;
	
	@FXML
	private TableColumn<Mangel, String> tcKommentarAbgeschlossen;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO entfernen
		Context.setLogin(new Login(new Sachbearbeiter("", "", "sb@im.ch"), "1", null));
		Context.login();
	
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
		
//		tcKommentarAbzuklären.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Mangel,String>,ObservableValue<String>>() {  
//			public ObservableValue<String> call(CellDataFeatures<Mangel, String> param) {
//				return new SimpleStringProperty(param.getValue().getKommentare().lastIndexOf(param));
//			}
//		});

		txFilter.textProperty().addListener(
				(observable, oldValue, newValue) -> {
					filteredData.setPredicate(t -> {
						
							if (newValue == null || newValue.isEmpty()) {
								return true;
							}

							String lowerCaseFilter = newValue.toLowerCase();
							String objectvalues = t.getMangel() 
									+ t.getSubunternehmen().getFirmenname()
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
			filteredData = new FilteredList<Mangel>(FXCollections.observableArrayList(controller.getAll()),	p -> true);
			SortedList<Mangel> sortedData = new SortedList<Mangel>(filteredData);
			sortedData.comparatorProperty().bind(tvDataAbzuklären.comparatorProperty());
			tvDataAbzuklären.setItems(sortedData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void clickData() {

	}

	@Override
	public void showDetail(Mangel t) {
		ViewableDetail<Mangel> view = MainView.showCenterDetailView("MangelDetailView");
		view.initData(t);
	}

	@Override
	public void initData(Mangel t) {
		// TODO Auto-generated method stub
		
	}

}
