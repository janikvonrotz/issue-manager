package ch.issueman.client;

import javafx.fxml.Initializable;

/**
 * Interface for detail view controller.
 * 
 * @author Janik von Rotz
 *
 * @param <T> the type of the viewable entities
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ViewableDetail<T> extends Initializable  {
	
	public void Refresh();
	public void initData(T t);
	public void showList();

}
