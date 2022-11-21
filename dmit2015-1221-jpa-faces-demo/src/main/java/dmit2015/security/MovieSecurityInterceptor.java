package dmit2015.security;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;
import jakarta.security.enterprise.SecurityContext;

public class MovieSecurityInterceptor {

    @Inject
    private SecurityContext _securityContext;

    @AroundInvoke
    public Object authorize(InvocationContext ic) throws Exception {
        String methodName = ic.getMethod().getName();

        // Verify that only Sales group has permission to call the add() or update() method
        if (methodName.equals("add") || methodName.equals("update")) {
            boolean isSalesRole = _securityContext.isCallerInRole("Sales");
            if (!isSalesRole) {
                String username = _securityContext.getCallerPrincipal().getName();
                String message = String.format("Access denied! %s do not have permission to execute this method.", username);
                throw new RuntimeException(message);
            }
        }

        // Verify that only IT group has permission to call methods that starts with delete keyword
        if (methodName.startsWith("delete")) {
            boolean isItRole = _securityContext.isCallerInRole("IT");
            if (!isItRole) {
                String username = _securityContext.getCallerPrincipal().getName();
                String message = String.format("Access denied! %s do not have permission to execute this method.", username);
                throw new RuntimeException(message);
            }
        }

        return ic.proceed();
    }
}
