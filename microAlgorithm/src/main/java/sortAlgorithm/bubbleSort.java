package sortAlgorithm;

import utils.ArrayUtils;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/4 15:25
 */
public class bubbleSort {

    public static void solution(int[] arr){

        if (arr == null || arr.length < 2){
            return;
        }

        for (int i = 0; i < arr.length - 1; i++){
            for (int j = 0; j < arr.length - 1 - i; j++){
                if (arr[j] > arr[j + 1]){
                    ArrayUtils.swap(arr, j + 1, j);
                }
            }
        }
    }
}
