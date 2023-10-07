package chapter05;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap的应用
 * @author 李旺
 * @version 1.0
 */
public class ConcurrentHashMapDemo {

    public static void main(String[] args) {

        ConcurrentHashMapDemo.unsafeConcurrentUpdate();

        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        Integer first = map.putIfAbsent(2, 0);

        System.out.println("key = 2, value = " + map.get(2));

        Integer second = map.putIfAbsent(2, 3);

        System.out.println("key = 2, value = " + map.get(2));

        boolean replace = map.replace(2, 3, 4);
        System.out.println("replace result " + replace);
    }

    public static void unsafeConcurrentUpdate(){

        final Map<Integer, Integer> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(){

                Random random = new Random();

                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        map.put(random.nextInt(), 1);
                    }
                }
            };

            thread.start();
        }
    }
}
