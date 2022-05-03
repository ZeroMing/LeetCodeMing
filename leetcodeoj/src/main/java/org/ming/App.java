package org.ming;

import java.util.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        System.out.println("Hello World!");
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            list.add(s);
        }
        System.out.println(list);
    }

    public List<String> unique(List<String> phones) {
        if (phones == null || phones.size() == 0) {
            return new ArrayList<>(0);
        }
        List<String> list = new ArrayList<>(phones.size());
        for (String phone : phones) {
            if (list.contains(phone)) {
                continue;
            } else {
                list.add(phone);
            }
        }
        return list;
    }
}
