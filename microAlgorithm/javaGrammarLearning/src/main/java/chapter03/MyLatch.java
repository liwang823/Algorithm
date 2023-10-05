package chapter03;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 线程协作类
 * @author 李旺
 * @version 1.0
 * @date 2023/10/3 13:34
 */
public class MyLatch {

    public static void main(String[] args) throws InterruptedException {
        int workNum = 100;
        Worker[] workers = new Worker[workNum];
        MyLatch myLatch = new MyLatch(workNum, workers);

        for (int i = 0; i < workNum; i++){
            workers[i] = new Worker(myLatch);
        }

        // 先初始化再开始才能看某个线程执行时其他线程的状态，否则总数不对
        for (Worker worker: workers){
            worker.start();
        }

        myLatch.await();
        System.out.println("collect all results");
    }

    static class Worker extends Thread{

        MyLatch myLatch;

        Worker(MyLatch myLatch){
            this.myLatch = myLatch;
        }

        @Override
        public void run() {
            try {
                // business
                Thread.sleep((int) (Math.random()*200));
                this.myLatch.countDown(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + " finished...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int count;

    private final Worker[] workers;

    MyLatch(int count, Worker[] workers){
        this.count = count;
        this.workers = workers;
    }

    synchronized void await() throws InterruptedException {
        while (count > 0){
            wait();
        }
    }

    synchronized void countDown(String threadName) throws InterruptedException {
        count--;
        System.out.println("------------------ split ------------------");
        System.out.println("current thread: " + threadName + " unfinished thread num: " + count);

        // 计时器
        long timeStep = 10;
        while (timeStep > 0){
            System.out.println("other thread state" + Arrays.stream(this.workers).map(Thread::getState).collect(Collectors.toList()));
            Thread.sleep(1000);
            timeStep--;
        }
        if (count <= 0){
            notifyAll();
        }
    }
}
