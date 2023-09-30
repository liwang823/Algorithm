package chapter02.entity;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/9/27 21:20
 */
public class Switcher {

    private boolean on;

    public synchronized boolean isOn(){
        return on;
    }

    public synchronized void setOn(boolean on){
        synchronized (this){
            this.on = on;
        }
    }
}
