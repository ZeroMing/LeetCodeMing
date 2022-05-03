package org.ming;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader classLoader = new MyClassLoader("E:\\Technology\\Github\\LeetCodeMing\\leetcodeoj\\target\\test-classes\\org\\ming");
        Class<?> aClass = classLoader.loadClass("org.ming.Hello");
        Class<?> aClass1 = classLoader.loadClass("org.ming.Hello");
        System.out.println(aClass == aClass1);
        Hello hello = (Hello) aClass.newInstance();
        hello.setName("111");
        System.out.println(hello.getName());
        System.out.println(classLoader.getClass().getClassLoader());
        System.out.println(classLoader.getParent());
        System.out.println(hello.getClass().getClassLoader());
        System.out.println(classLoader);

    }
}
