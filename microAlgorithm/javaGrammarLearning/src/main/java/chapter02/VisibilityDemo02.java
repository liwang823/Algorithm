package chapter02;

import chapter02.entity.Switcher;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/9/27 21:30
 */
public class VisibilityDemo02 {

    static class AnotherThread extends Thread{

        private Switcher switcher;

        public AnotherThread(Switcher switcher){
            this.switcher = switcher;
        }

        @Override
        public void run() {
            System.out.println("anotherThread start ...");

            this.switcher.setOn(true);
            while (this.switcher.isOn()){
                //do nothing
            }

            System.out.println("anotherThread end ...");
        }
    }

    static class SwitchThread extends Thread{

        private Switcher switcher;

        public SwitchThread(Switcher switcher){
            this.switcher = switcher;
            this.switcher.setOn(true);
        }

        @Override
        public void run() {
            System.out.println("switchThread start ...");

            while (this.switcher.isOn()){
                // do nothing
            }

            System.out.println("switchThread end ...");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Switcher switcher = new Switcher();
        SwitchThread switchThread = new SwitchThread(switcher);
        AnotherThread anotherThread = new AnotherThread(switcher);
        switchThread.start();
        anotherThread.start();
        Thread.sleep(1000);

        // 修改后并不能结束，此时的开关的变量值从寄存器读取
        switchThread.switcher.setOn(false);
        System.out.println("main thread end...");
    }
}
