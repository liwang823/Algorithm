import org.junit.Test;
import sortAlgorithm.*;
import utils.ArrayUtils;

import java.util.Arrays;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/4 15:25
 */
public class sortAlgorithmTest {

    public static final int[] arr = new int[]{2, 44, 1, 22, 5};

    @Test
    public void bubbleSortTest(){
        System.out.println(Arrays.toString(arr));
        bubbleSort.solution(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void insertionSortTest(){
        System.out.println(Arrays.toString(arr));
        insertionSort.solution(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void heapSortTest(){
        System.out.println(Arrays.toString(arr));
        heapSort.solution(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void mergeSortTest(){
        System.out.println(Arrays.toString(arr));
        mergeSort.solution(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void quickSortTest(){
        System.out.println(Arrays.toString(arr));
        quickSort.process(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
