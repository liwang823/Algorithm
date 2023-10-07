package chapter04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList学习
 * @author 李旺
 * @version 1.0
 */
public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {

        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

        List<Integer> list01 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0){
                list01.add(i);
            } else {
                list.add(i);
            }
        }

        int num = list.addAllAbsent(list01);
        System.out.println(num);

    }
}
