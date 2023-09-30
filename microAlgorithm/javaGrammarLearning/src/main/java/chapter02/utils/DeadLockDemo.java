package chapter02.utils;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/9/28 21:03
 */
public class DeadLockDemo {

    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    private static void startThreadA(){

        Thread aThread = new Thread(() -> {
            synchronized (lockA){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lockB){

                }
            }
        });

        aThread.start();
    }

    private static void startThreadB(){

        Thread bThread = new Thread(() -> {
            synchronized (lockB){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lockA){

                }
            }
        });

        bThread.start();
    }

    public static void main(String[] args) {

        startThreadA();
        startThreadB();
    }

}
