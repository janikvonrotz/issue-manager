package ch.issueman.webservice;

import java.util.List;
import java.util.stream.Collectors;

import ch.issueman.common.Kommentar;

/**
 * Custom type filter for Kommentar.
 * 
 * @author Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 */
public class KommentarFilter extends TypeFilter<Kommentar, Integer> {
		
	public KommentarFilter() {
		super(Kommentar.class);
	}

	@Override
	public List<Kommentar> getAll() {
		return this.getController().getAll().stream()
			.filter(c -> c.getLogin().equals(this.getLogin()))
			.collect(Collectors.toList());
	}
}
