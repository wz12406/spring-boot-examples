package com.neo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @date 2020/8/12 21:02
 * @desc
 */
@Configuration
public class MybatisConfig {


    @Bean
    public SqlCostTimeInterceptor sqlCostTimeInterceptor(){
        return new SqlCostTimeInterceptor();
    }
}
