package chapter03;

/**
 * 多线程同时开始的场景Demo
 * @author 李旺
 * @version 1.0
 * @date 2023/10/3 00:38
 */
public class RaceDemo {

    public static void main(String[] args) throws InterruptedException {

        int racerNum = 10;
        FireFlag fireFlag = new FireFlag();
        Thread[] racers = new Thread[racerNum];

        for (int i = 0; i < racerNum; i++){
            racers[i] = new Racer(fireFlag);
            racers[i].start();
        }

        new Racer(fireFlag).join();

        Thread.sleep(1000);
        fireFlag.fire();
    }

    static class FireFlag{

        private volatile boolean fired = false;

        synchronized void waitForFire() throws InterruptedException {

            while (! fired){
                wait();
            }
        }

        synchronized void fire(){
            this.fired = true;
            notifyAll();
        }
    }

    static class Racer extends Thread{

        private final FireFlag fireFlag;

        Racer(FireFlag fireFlag){
            this.fireFlag = fireFlag;
        }

        @Override
        public void run() {
            try {
                this.fireFlag.waitForFire();
                System.out.println("start run" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
