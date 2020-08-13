package com.neo.bean;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Administrator
 * @date 2020/8/12 0:11
 * @desc
 */
@Data
public class Student implements InitializingBean {
    private  String name ;
    private  Integer age ;

    public Student() {
        System.out.println("Student 构造器执行... ");
    }

    public void init(){
        System.out.println("Student 初始化方法Student.init... ");
    }


    public void desotry(){
        System.out.println("Student 初始化方法Student.desotry... ");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Student 的afterPropertiesSet 执行...");
    }
}
