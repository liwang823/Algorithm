package sortAlgorithm;

import utils.ArrayUtils;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/4 15:34
 */
public class heapSort {

    public static void solution(int[] arr){
        if (arr == null){
            return;
        }

        for (int i = 0; i < arr.length; i++){
            ArrayUtils.heapInsert(arr, i);
        }

        int heapSize = arr.length;
        ArrayUtils.swap(arr, 0, --heapSize);

        while (heapSize > 0){
            ArrayUtils.heapify(arr, 0, heapSize);
            ArrayUtils.swap(arr, 0, --heapSize);
        }
    }
}
