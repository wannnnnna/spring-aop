package aop.target;

/**
 * Created by lenovo on 2019-03-21.
 * student
 */
public class Student implements Man {

    @Override
    public void getName() {
        System.out.println("我的名字XX");
    }

    public void study(){
        System.out.println("正在学习");
    }
}
