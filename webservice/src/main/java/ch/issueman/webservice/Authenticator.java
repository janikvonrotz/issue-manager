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

import lombok.extern.slf4j.Slf4j;

import org.jboss.resteasy.util.Base64;

import ch.issueman.common.Login;

/**
 * For every incoming requrest this class checks the header for authentication information.
 * 
 * @author Janik von Rotz
 * @version 1.0.0
 * @since 1.0.0
 */
@Provider
@Slf4j
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
				log.error(e.getMessage(), e);
			}
	
			StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
			String username = tokenizer.nextToken();
			String password = tokenizer.nextToken();
			
			List<Login> logins = (new Controller<Login, Integer>(Login.class)).getAll().stream()
					.filter(l -> l.getUsername().equals(username))
					.filter(l -> l.getPasswort().equals(password))
					.collect(Collectors.toList());
			
			if(logins.size() > 0 && logins.get(0) != null){
				session.setAttribute("login", logins.get(0));
			}else{
				session.setAttribute("login", null);
			}
		}
	}
}