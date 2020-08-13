package com.neo.config;

import com.neo.bean.Student;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @date 2020/8/12 0:07
 * @desc
 */
@Component
public class MyPostProcess implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Student){
            System.out.println("bean 初始化之前，调用MyPostProcess.postProcessBeforeInitialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Student){
            System.out.println("bean 初始化之后，调用MyPostProcess.postProcessAfterInitialization");
        }
        return bean;
    }
}
