package aop.proxy;

import aop.TestAop;
import aop.advisor.Advisor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019-03-21.
 * DynamicProxy
 */
public class DynamicProxy implements  InvocationHandler {
    private Object target;
    private List<Advisor> targetList;
    private int size;
    private int index;

    private Method targetMethod;
    private Object[] objects;
    public  DynamicProxy(Object ob){
        this.target = ob;
    }
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        //拿出所有的拦截器  与目标代理对象进行匹配
        List<Advisor> listAdvisor = TestAop.listAdvisor;
        targetList = new ArrayList<>();
        //匹配
        for(Advisor ad:listAdvisor){
            if(this.target.getClass()==ad.getTarget()){//表示是要拦截的对象
                if(ad.getMethodName().equals(method.getName())){//查看是否是需要拦截的方法
                    targetList.add(ad);
                }
            }
        }

        if(targetList.size()==0){
            //标示该方法不需要拦截直接执行
            method.invoke(this.target,objects);
            return null;
        }else{
            this.size = targetList.size();
            this.index = 0;

            this.targetMethod = method;
            this.objects = objects;

            this.process();
        }


        return null;
    }


    public void process(){
        if(this.size==this.index){
            processTarget();
        }else{
            targetList.get(this.index++).processAspect(this);
        }
    }
    //执行拦截方法
    public void processTarget(){
        try {
            this.targetMethod.invoke(this.target,this.objects);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
