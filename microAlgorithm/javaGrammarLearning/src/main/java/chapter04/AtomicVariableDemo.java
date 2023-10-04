package chapter04;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子变量
 * @author 李旺
 * @version 1.0
 */
public class AtomicVariableDemo {

    private static AtomicInteger counter = new AtomicInteger(0);

    private static final Object lock = new Object();

    static class Visitor extends Thread{

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++){
                    counter.incrementAndGet();
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        int num = 10;

        Thread[] threads = new Thread[num];

        for (int i = 0; i < num; i++){
            threads[i] = new Visitor();
            threads[i].start();
        }

        for (int i = 0; i < num; i++){
            threads[i].join();
        }
        System.out.println(counter.get());

        long end = System.currentTimeMillis();

        System.out.println("execute time: " + (end - start));
    }
}
