package chapter03;

/**
 * BLOCKED状态下线程中断
 * @author 李旺
 * @version 1.0
 */
public class InterruptSynchronizedDemo {

    private static final Object lock = new Object();

    private static class BusinessThread extends Thread {

        @Override
        public void run() {

            System.out.println("thread is not dead...");
            synchronized (lock){
                // 获取锁之后做逻辑

                System.out.println("business ...");
            }

        }
    }

    public static void test() throws InterruptedException {
        synchronized (lock){

            // test方法会在 businessThread拿到锁之前先持有锁

            BusinessThread businessThread = new BusinessThread();
            businessThread.start();

            // 这里会中断BusinessThread吗？

            businessThread.stop();
            businessThread.join();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        test();
    }
}
