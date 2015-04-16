package ch.issueman.webservice;

import java.util.List;
import java.util.stream.Collectors;

import ch.issueman.common.Comment;

public class CommentFilter extends TypeFilter<Comment, Integer> {
		
	public CommentFilter() {
		super(Comment.class);
	}

	@Override
	public List<Comment> getAll() {
		return this.getController().getAll().stream()
			.filter(c -> c.getUser().equals(this.getUser()))
			.collect(Collectors.toList());
	}
}
