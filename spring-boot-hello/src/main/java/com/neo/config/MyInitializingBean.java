package com.neo.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @date 2020/8/12 0:06
 * @desc
 */
@Component
public class MyInitializingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
      //  System.out.println("bean InitializingBean被调用，afterPropertiesSet...");
    }
}
