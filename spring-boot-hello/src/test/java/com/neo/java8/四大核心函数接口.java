package com.neo.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Administrator
 * @date 2020/8/9 12:05
 * @desc
 * 理解四大核心函数接口
 * Consumer<T> ：消费型接口，传入一个参数，没有返回值，参见test_001
 * Supplier<T> :  供给型接口 无参数，有一个返回值 参见test_002
 * Function<T,R> : 功能性接口  一个入参，一个返回参数 参见test_003
 * Predicate<T> : 断言接口  一个入参 一个boolean 出参
 * 自定义
 * MyFunctionInterface<T,U,R>
 *
 */
public class 四大核心函数接口 {

    /**
     * @desc 测试 consumer<T> 消费型接口 使用
     */
    @Test
    public void test_001() {

        consumer1("12345",t-> System.out.println("消费了消息："+t));

    }

    public void consumer1(String megId, Consumer<String> stringConsumer){
        stringConsumer.accept(megId);
    }

    /**
     * @desc 测试 Supplier<T> 供给型接口 使用
     */
    @Test
    public void test_002() {

        supplier(()->"查询数据库返回：12345");
    }

    public void supplier(Supplier<String> stringSupplier){
        String s = stringSupplier.get();
        System.out.println(s);
    }

    /**
     * @desc 测试 Supplier<T> 供给型接口 使用
     */
    @Test
    public void test_003() {

        function("hello",(t)->{
            return t.length()*5;
        });
    }

    public void function(String str,Function<String, Integer> function){
        Integer length = function.apply(str);
        System.out.println(length);
    }

    /**
     * @desc 测试自定义函数式接口
     * 两个list合并
     */
    @Test
    public void test_004() {

        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<Integer> list3 = myfunction(list1, list2, (t, u) -> {
            List list = new ArrayList();
            for (Integer integer : list1) {
                list.add(integer);
            }
            for (Integer j : list2) {
                list.add(j);
            }
            return list;
        });
        System.out.println(list3);
    }

    public List<Integer> myfunction( List<Integer> list1, List<Integer> list2,MyFunctionInterface<List,List,List> myFunctionInterface) {
       return  myFunctionInterface.myfunction(list1,list2);
    }


}
