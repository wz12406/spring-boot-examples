package com.neo.java8;

/**
 * @author Administrator
 * @date 2020/8/9 12:20
 * @desc
 */
@FunctionalInterface
public interface MyFunctionInterface<T,U,R> {

    public R myfunction(T t,U u);
}
