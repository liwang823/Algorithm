package chapter04;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author 李旺
 * @version 1.0
 */


public class UnsafeArrayListDemo {
    public static void main(String[] args) throws InterruptedException {
        Vector<Integer> list = new Vector<>();

        // 创建并启动两个线程，同时向列表中添加元素
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 1000; i < 2000; i++) {
                list.add(i);
            }
        });

        thread1.start();
        thread2.start();

        // 等待两个线程完成
        thread1.join();
        thread2.join();

        // 打印列表的大小
        System.out.println("List size: " + list.size());
    }
}

