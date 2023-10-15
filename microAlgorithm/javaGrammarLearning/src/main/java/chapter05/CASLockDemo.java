package chapter05;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基于CAS的乐观锁
 * @author 李旺
 * @version 1.0
 * @date 2023/10/15 18:51
 */
public class CASLockDemo {

    private static int totalTickets = 100000;

    private static final UserDefineLock lock = new UserDefineLock();

    static class GetTickets extends Thread{

        private final Integer userNum;
        private Integer ticketNum = 0;

        private GetTickets(Integer userNum){
            this.userNum = userNum;
        }

        public Integer getTicketNum(){
            return ticketNum;
        }

        @Override
        public void run() {

            UserDefineLock userDefineLock = new UserDefineLock();
            userDefineLock.lock();

            try {
                for (int i = 0; i < userNum; i++){
                    if (totalTickets <= 0){
                        break;
                    }
                    totalTickets--;
                    ticketNum++;
                }
                System.out.println("线程 " + Thread.currentThread() + " 执行完毕");
            } finally {
                userDefineLock.unlock();
            }

        }
    }

    static class UserDefineLock{

        AtomicInteger lock = new AtomicInteger(0);

        public void lock(){
            while (! lock.compareAndSet(0, 1)){
                Thread.yield();
            }
        }

        public void unlock(){
            lock.set(0);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int poolNum = 10;
        Thread[] threads = new Thread[poolNum];

        for (int i = 0; i < threads.length; i++){
            threads[i] = new GetTickets(totalTickets);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        for (Thread thread: threads){
            GetTickets getTickets = (GetTickets)thread;
            System.out.println(getTickets.getTicketNum());
        }

    }
}
