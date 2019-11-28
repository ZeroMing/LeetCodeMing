package org.ming.common;

/**
 * @author: LeoLee
 * @date: 2019/11/27 11:53
 */
public class Test {

    private static String[] array = {""};


    static int test(){
        System.out.println("test bug");
        return 0;
    }

    public static void main(String[] args) {
        array[test()] += "1";
        System.out.println(array[0]);
    }

}
