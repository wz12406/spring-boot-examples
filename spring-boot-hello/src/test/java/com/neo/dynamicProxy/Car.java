package com.neo.dynamicProxy;

/**
 * @author Administrator
 * @date 2020/8/12 21:39
 * @desc
 */
public class Car implements IVehical {
    @Override
    public String run() {
        System.out.println("Car会跑");
        return "123km/h";
    }
}
