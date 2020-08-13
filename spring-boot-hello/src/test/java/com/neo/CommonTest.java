package com.neo;

import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Administrator
 * @date 2020/8/5 22:30
 * @desc
 */
public class CommonTest {


    @Test
    public void test_001(){
        Integer[] a = {5,3,1,2,4,1,2};
        List<Integer> collect = Stream.of(a).sorted((o1, o2) -> {
            if (o1 == 2) {
                return -1;
            }
            return o1 - o2;
        }).collect(Collectors.toList());

        System.out.println(collect);
        InputStream in;
    }

    /**
     * @desc 测试迭代时删除元素
     */
    @Test
    public void test_002() {
        List names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");
        Iterator iterator1 = names.iterator();
        Iterator iterator2 = names.iterator();
        iterator1.next();
        iterator1.remove();
        iterator2.next(); // 运行结果？

    }
}
