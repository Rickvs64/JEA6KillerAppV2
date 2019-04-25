package Interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class RepoInterceptor {
    @AroundInvoke
    public Object interceptorMethod(InvocationContext context) throws Exception {
        System.out.println("Method has been requested: "+context.getMethod().getName() );

        Object result = context.proceed();

        System.out.println("Method has been confirmed: "+context.getMethod().getName() );

        return result;
    }
}
