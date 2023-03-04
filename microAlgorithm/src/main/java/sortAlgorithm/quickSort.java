package sortAlgorithm;

import utils.ArrayUtils;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/4 15:51
 */
public class quickSort {

    public static void solution(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R){

        if (L >= R){
            return;
        }

        // 随机交换一个元素成为基准值
        ArrayUtils.swap(arr, (int) (L + (R - L + 1) * Math.random()), R);

        // 计算边界
        int[] partition = partition(arr, L, R);
        process(arr, L, partition[0] - 1);
        process(arr, partition[1] + 1, R);
    }

    public static int[] partition(int[] arr, int L, int R){
        int less = L - 1;
        int more = R;

        while (L < more){
            if (arr[L] < arr[R]){
                ArrayUtils.swap(arr, ++less, L++);
            }else if (arr[L] > arr[R]){
                ArrayUtils.swap(arr, --more, L);
            }else if (arr[L] == arr[R]){
                L++;
            }
        }

        ArrayUtils.swap(arr, more, R);

        return new int[]{less + 1, more};
    }
}
