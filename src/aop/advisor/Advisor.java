package aop.advisor;

import aop.proxy.DynamicProxy;

/**
 * Created by wannnnnna on 2019/3/21.
 */
public interface Advisor {
    void processAspect(DynamicProxy obj);
    Object getTarget();
    String getMethodName();

    Object getAspect();
}
