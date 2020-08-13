package com.neo.config;

import com.neo.bean.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @date 2020/8/12 0:12
 * @desc
 */
@Configuration
public class BeanConfig {


    @Bean(initMethod = "init",destroyMethod = "desotry")
    public Student student(){
        return new Student();
    }

}
