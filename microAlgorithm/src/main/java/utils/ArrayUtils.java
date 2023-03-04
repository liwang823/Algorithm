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
}
