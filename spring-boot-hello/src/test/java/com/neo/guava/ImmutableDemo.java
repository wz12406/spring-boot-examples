package com.neo.guava;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Administrator
 * @date 2020/8/11 23:04
 * @desc
 */
public class ImmutableDemo {

    /**
     * @desc 测试ImmutableList 与 Collections.unmodifiableList 的差别
     */
    @Test
    public void test_001() {

        List<String> orgList = new ArrayList<>();
        orgList.add("a");
        orgList.add("b");
        orgList.add("c");

        List<String> jdkUnmodifyList = Collections.unmodifiableList(orgList);
        List<String> immutableList = ImmutableList.copyOf(orgList);

        //jdkUnmodifyList.add("d");
        //immutableList.add("d");
        orgList.add("d");
        printList(jdkUnmodifyList);
        System.out.println("---");
        printList(immutableList);
    }

    private void printList(List<String> list) {
        for (String s : list) {
            System.out.print(s);
        }
        
    }
}
