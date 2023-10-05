package chapter04;

import javafx.util.Pair;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * CAS中的ABA问题
 * @author 李旺
 * @version 1.0
 */
public class AbaDemo {

    public static void main(String[] args) {

        Pair pair = new Pair(100, 200);
        int timeStamp = 1;
        AtomicStampedReference<Pair> pairRef = new AtomicStampedReference<>(pair, timeStamp);
        int nextTimeStamp = 2;
        pairRef.compareAndSet(pair, new Pair(200, 200), timeStamp, nextTimeStamp);

    }
}
