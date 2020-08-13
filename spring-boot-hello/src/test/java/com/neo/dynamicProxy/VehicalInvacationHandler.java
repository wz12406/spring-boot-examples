package com.neo.dynamicProxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Administrator
 * @date 2020/8/12 21:39
 * @desc
 */
@Slf4j
public class VehicalInvacationHandler implements InvocationHandler {

    private IVehical iVehical;

    public VehicalInvacationHandler(IVehical iVehical) {
        this.iVehical = iVehical;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("前置处理器代理...");
        Object invoke =  method.invoke(iVehical,args);
        log.info("后置处理器代理...");
        return invoke;
    }
}
