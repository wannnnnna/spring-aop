package aop.proxy;

import aop.TestAop;
import aop.advisor.Advisor;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by wannnnnna on 2019/3/21.
 * bean注册中心
 */
public class FactoryInstallerBean {
    public static Object makeBean(Object obj){
        for(Advisor advisor: TestAop.listAdvisor){
            if(advisor.getTarget() == obj.getClass()){
                //表示是一个目标对象需要被代理 生成此类的代理类  在此使用JDK代理
                DynamicProxy proxy = new DynamicProxy(obj);
                return Proxy.newProxyInstance(proxy.getClass().getClassLoader(),obj.getClass().getInterfaces(),proxy);
            }
        }
        return obj;
    }
}
