package utils;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/4 15:28
 */
public class ArrayUtils {

    private ArrayUtils(){

    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void heapInsert(int[] arr, int index){

        while (arr[index] > arr[(index - 1)/2]){
            ArrayUtils.swap(arr, index, (index - 1)/2);
            index = (index - 1) /2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize){
        int left = 2 * index + 1;
        while (left < heapSize){
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;

            if (largest == index){
                break;
            }

            ArrayUtils.swap(arr, index, largest);
            index = largest;
            left = 2 * index + 1;
        }
    }

    public static int getMaxBit(int[] arr){

        int max = Integer.MIN_VALUE;

        for (int num: arr){
            max = Math.max(max, num);
        }

        int res = 0;
        while (max != 0){
            max /= 10;
            res++;
        }

        return res;
    }

    public static int getBit(int x, int d){
        return (x / (int) Math.pow(10, d - 1)) % 10;
    }
}
