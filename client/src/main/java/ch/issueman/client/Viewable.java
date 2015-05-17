package ch.issueman.client;

import javafx.fxml.Initializable;

/**
 * Interface for list view controller.
 * 
 * @author Janik von Rotz
 *
 * @param <T> the type of the viewable entities
 * @param <Filter> the type of the initializable variable.
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Viewable<T, Filter> extends Initializable {

	public void Refresh();
	public void initData(Filter t);
	public void showDetail(T t);
}
