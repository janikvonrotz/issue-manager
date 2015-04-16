package ch.issueman.webservice;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.util.Base64;

import ch.issueman.common.User;

@Provider
public class Authenticator implements ContainerRequestFilter {

	@Context
    HttpServletRequest request;

	@Override
	public void filter(ContainerRequestContext requestContext) {
		
        HttpSession session = request.getSession();
		MultivaluedMap<String, String> headers = requestContext.getHeaders();
		List<String> authorization = headers.get("Authorization");
		
		if(authorization != null){
			
			String encodedUserPassword = authorization.get(0).replaceFirst("Basic" + " ", "");
			String usernameAndPassword = null;
			try {
				usernameAndPassword = new String(Base64.decode(encodedUserPassword));
			} catch (IOException e) {
				e.printStackTrace();
			}
	
			StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
			String username = tokenizer.nextToken();
			String password = tokenizer.nextToken();
			
			Controller<User, Integer> usercontroller = new Controller<User, Integer>(User.class);
			
			List<User> users = usercontroller.getAll().stream()
					.filter(p -> p.getEmail().equals(username))
					.filter(p -> p.getPassword().equals(password))
					.collect(Collectors.toList());
			
			if(users.get(0) != null){
				
				User user = users.get(0);
				session.setAttribute("user", user);
			}
		}
	}
}