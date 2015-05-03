package ch.issueman.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Useful Methods to filter collections of entities.
 *
 * @author Erwin Willi, Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 */
public class FilterHelper {
	
	/**
	 * Filter a list by an array of index identifiers.
	 * 
	 * @param <T> generic type of the list.
	 * @param list the list to filter.
	 * @param ids the collection of index identifiers.
	 * @return the filtered list.
	 */
	public static <T> List<T> filterListIds(List<T> list, int[] ids){
		List<T> listtmp = new ArrayList<T>();
		for(int i : ids){
			listtmp.add((T) list.get(i));
		}
		return listtmp;		
	}
}
