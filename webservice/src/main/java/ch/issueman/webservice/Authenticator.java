package ch.issueman.webservice;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.util.Base64;

import ch.issueman.common.User;

@Provider
public class Authenticator implements ContainerRequestFilter {

	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME = "Basic";
	private static final Response ACCESS_DENIED =  Response.status(Status.UNAUTHORIZED).entity( "Access denied for this resource." ).build( );
	private static final Response ACCESS_FORBIDDEN =  Response.status(Status.FORBIDDEN).entity( "Nobody can access this resource." ).build( );
	
	@Override
	public void filter(ContainerRequestContext requestContext) {
		ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
        Method method = methodInvoker.getMethod();
		
		if (!method.isAnnotationPresent(PermitAll.class)) {
			
			if (method.isAnnotationPresent(DenyAll.class)) {
				requestContext.abortWith(ACCESS_FORBIDDEN);
				return;
			}

			final MultivaluedMap<String, String> headers = requestContext.getHeaders();
			final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

			if (authorization == null || authorization.isEmpty()) {
				requestContext.abortWith(ACCESS_DENIED);
				return;
			}

			final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

			String usernameAndPassword = null;
			try {
				usernameAndPassword = new String(Base64.decode(encodedUserPassword));
			} catch (IOException e) {
				e.printStackTrace();
			}

			final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
			final String username = tokenizer.nextToken();
			final String password = tokenizer.nextToken();

			if (method.isAnnotationPresent(RolesAllowed.class)) {
				RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
				Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

				if (!isUserAllowed(username, password, rolesSet)) {
					requestContext.abortWith(ACCESS_DENIED);
					return;
				}
			}
		}
	}

	private boolean isUserAllowed(final String username, final String password,	final Set<String> rolesSet) {
		boolean isAllowed = false;

		Controller<User, Integer> usercontroller = new Controller<User, Integer>(User.class);
		
		List<User> users = usercontroller.getAll().stream()
				.filter(p -> p.getEmail().equals(username))
				.filter(p -> p.getPassword().equals(password))
				.collect(Collectors.toList());
		
		if(users.get(0) != null){
			String userRole = users.get(0).getRole();
			
			if (rolesSet.contains(userRole)) {
				isAllowed = true;
			}
		}
		return isAllowed;
	}
}