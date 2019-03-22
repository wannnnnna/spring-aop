package aop.aspect;

import aop.annotations.After;
import aop.annotations.Aspect;
import aop.annotations.Before;
import aop.annotations.Pointcut;

/**
 * Created by lenovo on 2019-03-21.
 * AspectA
 */
@Aspect
public class LogAspect {
    //截取学习方法
    @Pointcut("execution(aop.target.Student.study())")
    public void log(){}

    @Before("execution(aop.target.Student.study())")
    public void beforePrint(){
        System.out.println("开始之前");
    }

    @After("execution(aop.target.Student.study())")
    public void afterPrint(){
        System.out.println("执行结束");
    }

}
