package ch.issueman.client;

import javafx.fxml.Initializable;

/**
 * Interface for list view controller.
 * 
 * @author Janik von Rotz
 *
 * @param <T> the type of the viewable entities
 * @version 1.0.0
 * @param <Filter>
 * @since 1.0.0
 */
public interface Viewable<T, Filter> extends Initializable {

	public void Refresh();
	public void initData(Filter t);
	public void showDetail(T t);
}
