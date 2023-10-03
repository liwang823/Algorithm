package chapter03;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 生产者和消费者
 * @author 李旺
 * @version 1.0
 * @date 2023/10/2 22:25
 */
public class ProducerAndConsumer {

    public static void main(String[] args) throws InterruptedException {

        MyBlockingQueue<String> queue = new MyBlockingQueue<>(10);
        new Producer(queue).start();
        Thread.sleep(10000);
        new Consumer(queue).start();
    }

    static class Consumer extends Thread{

        MyBlockingQueue<String> queue;

        Consumer(MyBlockingQueue<String> queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (true){
                    String task = String.valueOf(num);
                    queue.take();
                    System.out.println("handle task" + task);
                    num++;
                    Thread.sleep(10000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class Producer extends Thread{

        MyBlockingQueue<String> queue;

        Producer(MyBlockingQueue<String> queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (true){
                    String task = String.valueOf(num);
                    queue.put(task);
                    System.out.println("produce task" + task);
                    num++;
                    Thread.sleep( (int)(Math.random() * 100));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class MyBlockingQueue<E> {

        private Queue<E> queue = null;
        private final int limit;

        MyBlockingQueue(int limit){
            this.limit = limit;
            queue = new ArrayDeque<>(limit);
        }

        public synchronized void put(E e) throws InterruptedException {
            while (queue.size() == limit){
                System.out.println("queue is full, please wait ...");
                wait();
            }
            queue.add(e);
            notifyAll();
        }

        public synchronized E take() throws InterruptedException {

            while (queue.isEmpty()){
                System.out.println("queue is empty, please wait ...");
                wait();
            }

            E e = queue.poll();
            notifyAll();
            return e;
        }
    }
}
