package chapter03;

import java.util.Map;

/**
 * 线程协作
 * @author 李旺
 * @version 1.0
 * @date 2023/9/30 10:56
 */
public class MultiThread extends Thread{

    private volatile boolean fire = false;

    @Override
    public void run() {
        try {
            synchronized (this){
                while (! fire){
                    wait();
                }
            }
            System.out.println("fired");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void fire(){
        this.fire = true;
        notify();
    }

    public static void main(String[] args) throws InterruptedException {
        MultiThread multiThread = new MultiThread();
        multiThread.start();
        Thread.sleep(1000);
        System.out.println("fire");
        multiThread.fire();
    }
}
