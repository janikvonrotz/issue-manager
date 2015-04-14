package ch.issueman.webservice;

import java.util.List;
import java.util.stream.Collectors;

import ch.issueman.common.Comment;
import ch.issueman.common.User;

public class CommentFilter extends TypeFilter<Comment, Integer> {
		
	public CommentFilter() {
		super(Comment.class);
	}

	@Override
	public List<Comment> getAllByUser(Controller<Comment, Integer> controller, User user) {
		System.out.println("Mail:" + user.getEmail());
		return controller.getAll().stream()
			.filter(c -> c.getUser().equals(user))
			.collect(Collectors.toList());
	}
}
