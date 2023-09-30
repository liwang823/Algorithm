package chapter02;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/9/27 21:26
 */
public class VisibilityDemo {

    private static boolean shutdown = false;

    static class DemoThread extends Thread{

        @Override
        public void run() {

            while (! shutdown){
                // do nothing
            }
            System.out.println("say hello...");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        new DemoThread().start();
        Thread.sleep(1000);
        shutdown = true;
        System.out.println("exit main");
    }

}
