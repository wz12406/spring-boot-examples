package com.neo.dynamicProxy;

import java.lang.reflect.Proxy;

/**
 * @author Administrator
 * @date 2020/8/12 21:44
 * @desc
 */
public class App {

    public static void main(String[] args) {
        IVehical car = new Car();

        IVehical vehical = (IVehical) Proxy.newProxyInstance(car.getClass().getClassLoader(), Car.class.getInterfaces(), new VehicalInvacationHandler(car));
        String speed = vehical.run();
    }

}
