package chapter03;

/**
 * 线程终断
 * @author 李旺
 * @version 1.0
 */
public class ThreadInterruptDemo extends Thread{

    @Override
    public void run() {
        while (! Thread.currentThread().isInterrupted()){
            System.out.println("single step...");
        }

        System.out.println("done...");
    }

    public static void main(String[] args) {

        ThreadInterruptDemo thread = new ThreadInterruptDemo();

        if (thread.getState() == State.RUNNABLE){
            thread.interrupt();
        }
    }
}
