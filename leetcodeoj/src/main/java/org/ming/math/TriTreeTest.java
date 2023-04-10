package org.ming.math;

import java.io.File;

/**
 * @date: 2022/4/18 23 <br>
 * @author: liming53 <br>
 * @company 58房产 · 交易技术部 <br>
 */
public class TriTreeTest {
    public static void main(String[] args) {
        String property = System.getProperty("user.dir");
        System.out.println(property);
        String s = "/temp/temp2/../../";
        s = property + s + "README.md";
        System.out.println("路径：" + s);
        File file = new File(s);
        if(file.exists()){
            System.out.println("存在 " + file.getName());
        }
        System.out.println("=====================");
        s = "/./";
        s = property + s + "README.md";
        System.out.println("路径：" + s);
        file = new File(s);
        if(file.exists()){
            System.out.println("存在 " + file.getName());
        }
    }
}
