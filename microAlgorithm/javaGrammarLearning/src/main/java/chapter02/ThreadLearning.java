package chapter02;

import java.util.ArrayList;
import java.util.List;

/**
 * 并发知识的学习
 * @author 李旺
 * @version 1.0
 * @date 2023/9/23 23:15230821
 */
public class ThreadLearning {

    static class GetAJ{

        public synchronized void success() throws InterruptedException {

            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + "success" + Thread.currentThread().getState());
                Thread.sleep(2000);
            }
        }
    }

    static class UserBuyingProcess extends Thread{

        private final GetAJ getAJ;
        private final Thread nextThread;

        public UserBuyingProcess(GetAJ getAJ, Thread nextThread){
            this.getAJ = getAJ;
            this.nextThread = nextThread;
        }

        @Override
        public void run() {

            try {
                getAJ.success();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) throws InterruptedException {

            // 买鞋主程序
            int userNum = 10;
            GetAJ getAJ = new GetAJ();
            Thread[] users = new Thread[userNum];

            for (int i = 0; i < users.length - 1; i++){
                users[i] = new UserBuyingProcess(getAJ, users[i + 1]);
                System.out.println("开始执行线程" + users[i].getName());
                users[i].start();
                System.out.println("----------------------------split----------------------------");
            }

            for (Thread user: users){
                user.join();
            }
        }
    }

    /**
     * 计数器在计数方法上面加了锁
     */
    static class Counter{
        private int count;
        public synchronized void incr(){
            count++;
        }

        public synchronized int getCount(){
            return count;
        }
    }

    /**
     * 计数功能的主程序，根据传入的计数器实例化
     */
    static class CounterThread extends Thread{
        Counter counter;
        public CounterThread(Counter counter){
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++){
                counter.incr();
            }
        }

        public static void main(String[] args) throws InterruptedException {
            int num = 1000;
            Counter counter = new Counter();
            Thread[] threads = new Thread[num];
            for (int i = 0; i < threads.length; i++){
                threads[i] = new CounterThread(counter);
                threads[i].start();
            }

            for (Thread thread : threads) {
                thread.join();
            }
            System.out.println(counter.getCount());
        }
    }

    static class HelloRunnable implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 10; i++){
                System.out.println("hello");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ShareMemoryDemo{
        private static int shared = 0;
        private static void incrShared(){
            shared++;
        }

        static class ChildThread extends Thread{
            List<String> list;
            public ChildThread(List<String> list){
                this.list = list;
            }

            @Override
            public void run() {
                incrShared();
                list.add(Thread.currentThread().getName());
            }
        }

        public static void main(String[] args) throws InterruptedException {

            List<String> list = new ArrayList<>();
            Thread t1 = new ChildThread(list);
            Thread t2 = new ChildThread(list);

            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println(shared);
            System.out.println(list);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new HelloRunnable());
        thread.start();
        thread.join();
        System.out.println("main thread end..");
    }
}
