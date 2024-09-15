package com.tuxt.mytest;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CommonTest {

    @Test
    public void test(){
        System.out.println(Math.random());
    }

    @Test
    public void test2(){
        int[] startState = {0, 0, 0};
        int[] clone = startState.clone();
        clone[0]=1;
        System.out.println(Arrays.toString(startState));
        System.out.println(Arrays.toString(clone));
    }
}
