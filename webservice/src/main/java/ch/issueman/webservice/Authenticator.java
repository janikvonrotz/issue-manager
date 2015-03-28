package ch.issueman.webservice;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.jboss.resteasy.util.Base64;

@Provider
@ServerInterceptor
public class Authenticator implements PreProcessInterceptor {
	
	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME = "Basic";
	private static final ServerResponse ACCESS_DENIED = new ServerResponse("Access denied for this resource", 401, new Headers<Object>());;
	private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("Nobody can access this resource", 403, new Headers<Object>());;
	private static final ServerResponse SERVER_ERROR = new ServerResponse("INTERNAL SERVER ERROR", 500, new Headers<Object>());;

	@Override
	public ServerResponse preProcess(HttpRequest request, ResourceMethodInvoker methodInvoked) throws Failure,	WebApplicationException {
		Method method = methodInvoked.getMethod();

		if (method.isAnnotationPresent(PermitAll.class)) {
			return null;
		}
		if (method.isAnnotationPresent(DenyAll.class)) {
			return ACCESS_FORBIDDEN;
		}

		final HttpHeaders headers = request.getHttpHeaders();
		final List<String> authorization = headers.getRequestHeader(AUTHORIZATION_PROPERTY);

		if (authorization == null || authorization.isEmpty()) {
			return ACCESS_DENIED;
		}

		final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

		String usernameAndPassword;
		try {
			usernameAndPassword = new String(Base64.decode(encodedUserPassword));
		} catch (IOException e) {
			return SERVER_ERROR;
		}

		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		System.out.println(username);
		System.out.println(password);

		if (method.isAnnotationPresent(RolesAllowed.class)) {
			RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
			Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

			if (!isUserAllowed(username, password, rolesSet)) {
				return ACCESS_DENIED;
			}
		}
		return null;
	}

	private boolean isUserAllowed(final String username, final String password,	final Set<String> rolesSet) {
		boolean isAllowed = false;
		String userRole = "ADMIN";

		if (rolesSet.contains(userRole)) {
			isAllowed = true;
		}
		return isAllowed;
	}
}
