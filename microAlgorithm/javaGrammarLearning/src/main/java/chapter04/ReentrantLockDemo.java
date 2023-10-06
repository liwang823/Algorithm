package chapter04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于lock接口实现的可重入锁
 * @author 李旺
 * @version 1.0
 */
public class ReentrantLockDemo {

    static class Counter{

        private static final Lock lock = new ReentrantLock();

        private static volatile int count;

        public void incr(){
            lock.lock();
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }

        public int getCount(){
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();

        Thread thread01 = new Thread(() -> {

            for (int i = 0; i < 1000000; i++){
                counter.incr();
                System.out.println(Thread.currentThread().getName() + " count: " + counter.getCount());
            }
        });

        Thread thread02 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++){
                counter.incr();
                System.out.println(Thread.currentThread().getName() + " count: " + counter.getCount());
            }
        });

        thread01.start();
        thread02.start();

        thread01.join();
        thread02.join();

        System.out.println(counter.getCount());
    }
}
