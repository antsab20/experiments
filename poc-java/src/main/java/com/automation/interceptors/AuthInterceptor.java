package com.automation.interceptors;

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ServerResponse;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.*;
import java.util.List;

@Provider
public class AuthInterceptor implements ContainerRequestFilter {
    private static final String AUTHENTICATION_SCHEME    = "secret";
    private static final String AUTHORIZATION_PROPERTY   = "Authorization";
    private static final ServerResponse ACCESS_DENIED    = new ServerResponse("Access denied for this resource", 401, new Headers<Object>());
    private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("Nobody can access this resource", 403, new Headers<Object>());

    @Override
    public void filter(ContainerRequestContext context) {
        /*
        final MultivaluedMap<String, String> headers = context.getHeaders();
        final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

        if(authorization == null || authorization.isEmpty()) {
            context.abortWith(ACCESS_DENIED);
        }

        if (AUTHENTICATION_SCHEME.equals(authorization.get(0))) {
            context.abortWith(ACCESS_FORBIDDEN);
        }
        */
    }
}