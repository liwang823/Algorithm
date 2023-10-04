package chapter04;

import java.util.ArrayList;
import java.util.Vector;

/**
 * 比较回调函数和多线程对异步的实现
 * @author 李旺
 * @version 1.0
 */
public class CompareCallbackAndMultiThread {

    static class childModule extends Thread{

        @Override
        public void run() {

            try {
                Thread.sleep(10000);
                System.out.println("child module done...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    interface CallBack{
        void finish();
    }

    public static void operation(CallBack callBack){
        new Thread(() -> {
            try {
                Thread.sleep(10000);
                callBack.finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {


    }
}
