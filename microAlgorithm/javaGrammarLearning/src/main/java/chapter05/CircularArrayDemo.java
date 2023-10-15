package chapter05;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/10/15 20:14
 */
public class CircularArrayDemo {

    private int[] array;
    private int capacity;
    private int size;
    private int rear;
    private int front;

    public CircularArrayDemo(int capacity){
        this.capacity = capacity;
        this.array = new int[capacity];
        this.size = 0;
        this.rear = 0;
        this.front = 0;
    }

    public void enqueue(int val){
        if (size == capacity){
            System.out.println("Queue is full.");
        }else {
            array[rear] = val;
            rear = (rear + 1) % capacity;
            size++;
        }
    }

    public Integer dequeue(){
        if (size == 0){
            System.out.println("Queue is Empty");
            return null;
        }else {
            int val = array[front];
            front = (front + 1) % capacity;
            size--;
            return val;
        }
    }

    public Integer size(){
        return size;
    }

    public static void main(String[] args) {

        CircularArrayDemo circularArrayDemo = new CircularArrayDemo(10);

        for (int i = 0; i < 5; i++){
            circularArrayDemo.enqueue(i);
        }

        System.out.println(circularArrayDemo.dequeue());
        System.out.println(circularArrayDemo.dequeue());
        System.out.println(circularArrayDemo.dequeue());
        System.out.println(circularArrayDemo.dequeue());
        System.out.println(circularArrayDemo.dequeue());
    }

}
