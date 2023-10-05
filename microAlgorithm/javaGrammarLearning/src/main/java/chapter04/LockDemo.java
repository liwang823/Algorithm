package chapter04;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子变量对悲观锁的实现
 * 1：持有锁 0：释放锁
 * 锁定过程：
 * 当前状态未持有锁：则状态从0更新成1
 * @author 李旺
 * @version 1.0
 */
public class LockDemo {

    private static AtomicInteger status = new AtomicInteger(0);

    public void lock() {

    }

    public void unlock(){
        Thread.yield();
    }

    public static void main(String[] args) {


    }
}
