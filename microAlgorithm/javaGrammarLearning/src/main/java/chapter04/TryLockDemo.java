package chapter04;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock的实例
 * @author 李旺
 * @version 1.0
 */
public class TryLockDemo {

    public static void main(String[] args) {

        AccountManagement.simulateDeadLock();
    }

    static class AccountManagement{

        public static void simulateDeadLock() {
            final int accountNum = 10;
            final Account[] accounts = new Account[accountNum];
            final Random rnd = new Random();
            for(int i = 0; i < accountNum; i++) {
                accounts[i] = new Account(rnd.nextInt(10000));
            }
            int threadNum = 100;
            Thread[] threads = new Thread[threadNum];
            for(int i = 0; i < threadNum; i++) {
                threads[i] = new Thread(() -> {
                    int loopNum = 100;
                    for(int k = 0; k < loopNum; k++) {
                        int i1 = rnd.nextInt(accountNum);
                        int j = rnd.nextInt(accountNum);
                        int money = rnd.nextInt(10);
                        if(i1 != j) {
                            try {
                                transfer(accounts[i1], accounts[j], money);
                            } catch (NoEnoughMoneyException e) {
                            }
                        }
                    }
                });
                threads[i].start();
            }
        }

        public static class NoEnoughMoneyException extends Exception{

        }

        public static boolean tryTransfer(Account from, Account to, double money) throws NoEnoughMoneyException{
            if (from.tryLock()){
                try {
                    if (to.tryLock()){
                        try {
                            if (from.getMoney() >= money){
                                from.reduce(money);
                                to.add(money);
                            } else {
                                throw new NoEnoughMoneyException();
                            }

                            return true;
                        } finally {
                            to.unlock();
                        }
                    }
                } finally {
                    from.unlock();
                }

            }

            return false;
        }

        public static void transfer(Account from, Account to, double money) throws NoEnoughMoneyException{
            boolean success;

            do {
                success = tryTransfer(from, to, money);
                if (! success){
                    Thread.yield();
                }
            } while (! success);
        }
    }

    static class Account{

        private Lock lock = new ReentrantLock();
        private volatile double money;

        Account(double initialMoney){
            this.money = initialMoney;
        }

        public void add(double money){
            lock.lock();
            try {
                this.money += money;
            } finally {
                lock.unlock();
            }
        }

        public void reduce(double money){
            lock.lock();
            try {
                this.money = money;
            } finally {
                lock.unlock();
            }
        }

        public double getMoney(){
            return money;
        }

        void lock(){
            lock.lock();
        }

        void unlock(){
            lock.unlock();
        }

        boolean tryLock(){
            return lock.tryLock();
        }

    }
}
