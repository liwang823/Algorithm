package sortAlgorithm;

import utils.ArrayUtils;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/4 15:30
 */
public class insertionSort {

    public static void solution(int[] arr){

        if (arr == null || arr.length < 2){
            return;
        }

        for (int i = 1; i < arr.length; i++){
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--){
                ArrayUtils.swap(arr, j + 1, j);
            }
        }
    }
}
