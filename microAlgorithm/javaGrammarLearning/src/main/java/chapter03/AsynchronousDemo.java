package chapter03;

import java.util.concurrent.Callable;

/**
 * 异步编程
 * @author 李旺
 * @version 1.0
 * @date 2023/10/3 22:12
 */
public class AsynchronousDemo {

    public static void main(String[] args) {
        MyExecutor executor = new MyExecutor();
        Callable<Integer> subTask = () -> {
            int millis = (int)(Math.random() * 1000);
            Thread.sleep(millis);
            return millis;
        };

        MyFuture<Integer> future = executor.execute(subTask);
        try {
            Integer result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    interface MyFuture<V> {
        V get() throws Exception;
    }

    static class MyExecutor{
        public <V> MyFuture<V> execute(final Callable<V> task) {
            final Object lock = new Object();
            final ExecuteThread<V> thread = new ExecuteThread<>(task, lock);
            thread.start();
            return () -> {
                synchronized (lock) {
                    while(! thread.isDone()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    if(thread.getException() != null) {
                        throw thread.getException();
                    }
                    return thread.getResult();
                }
            };
        }
    }

    static class ExecuteThread<V> extends Thread {
        private V result = null;
        private Exception exception = null;
        private boolean done = false;
        private Callable<V> task;
        private final Object lock;

        ExecuteThread(Callable<V> task, Object lock){
            this.task = task;
            this.lock = lock;
        }

        V getResult(){
            return result;
        }

        boolean isDone(){
            return done;
        }

        Exception getException() {
            return exception;
        }

        @Override
        public void run() {
            try {
                result = task.call();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                synchronized (lock){
                    done = true;
                    lock.notifyAll();
                }
            }
        }
    }
}
