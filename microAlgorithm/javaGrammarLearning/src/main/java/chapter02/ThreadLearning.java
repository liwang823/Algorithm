package chapter02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/9/23 23:15
 */
public class ThreadLearning {

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
