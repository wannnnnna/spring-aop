package aop;

import aop.advisor.Advisor;
import aop.advisor.AfterAdvisor;
import aop.advisor.BeforeAdvisor;
import aop.proxy.DynamicProxy;
import aop.proxy.FactoryInstallerBean;
import aop.target.Man;
import aop.target.Student;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019-03-21.
 * testJdkProxy
 */
public class TestAop {
    public static List<Advisor> listAdvisor;
    public static void main(String[] args) {
        Class aspect;
        listAdvisor = new ArrayList<>();
        //扫描所有的aop生成List 本身应该是
        try {
            aspect = Thread.currentThread().getContextClassLoader().loadClass("aop.aspect.LogAspect");
            //匹配他的注解  生成AdvisorList
            Method[] list = aspect.getMethods();

            for(Method method:list){
                Annotation[] xx = method.getAnnotations();
                for(Annotation ano:xx){
                    String advisorName = ano.annotationType().getName();
                    if(advisorName.contains("Before")){
                        String pointcut = ano.toString();
                        Class targetClass = Thread.currentThread().getContextClassLoader().loadClass(pointcut.substring(40,58));
                        listAdvisor.add(new BeforeAdvisor(method,aspect,targetClass,pointcut.substring(59,64)));
                    }else if(advisorName.contains("After")){
                        String pointcut = ano.toString();
                        Class targetClass = Thread.currentThread().getContextClassLoader().loadClass(pointcut.substring(39,57));
                        listAdvisor.add(new AfterAdvisor(method,aspect,targetClass,pointcut.substring(58,63)));
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //模拟spring先在工厂生成bean
        Man student = new Student();
        //判断是否需要代理
        student = (Man)FactoryInstallerBean.makeBean(student);
        //调用方法
        student.study();


      /*  Student b = new Student();
        DynamicProxy a = new DynamicProxy(b);
        Man t = (Man)Proxy.newProxyInstance(a.getClass().getClassLoader(),b.getClass().getInterfaces(),a);
        t.getName();*/

    }
}
