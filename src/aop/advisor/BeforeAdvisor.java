package aop.advisor;

import aop.proxy.DynamicProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wannnnnna on 2019/3/21.
 */
public class BeforeAdvisor implements Advisor {
    public Method a;
    public Class aspectClass;
    public Class target;
    public String targetMethodName;
    public BeforeAdvisor (Method b,Class aspectClass,Class c,String methodName){
        this.a = b;
        this.aspectClass = aspectClass;
        this.target = c;
        this.targetMethodName = methodName;
    }
    @Override
    //由于是before所以先运行拦截 再进行下一次循环
    public void processAspect(DynamicProxy obj) {
        try {
            a.invoke(this.aspectClass.newInstance());
            obj.process();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getTarget() {
        return this.target;
    }

    @Override
    public String getMethodName() {
        return this.targetMethodName;
    }

    @Override
    public Object getAspect() {
        return this.aspectClass;
    }
}
