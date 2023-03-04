package sortAlgorithm;

import utils.ArrayUtils;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/4 17:09
 */
public class buttonSort {

    public static void solution(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        process(arr, 0, arr.length - 1, ArrayUtils.getMaxBit(arr));
    }

    public static void process(int[] arr, int L, int R, int digit){
        final int TEN = 10;
        int[] help = new int[R - L + 1];

        for (int d = 1; d <= digit; d++){
            int[] count = new int[TEN];

            for (int i = L; i <= R; i++){
                int bit = ArrayUtils.getBit(arr[i], d);
                count[bit]++;
            }

            for (int j = 1; j < count.length; j++){
                count[j] += count[j - 1];
            }

            for (int k = R; k >= L; k--){
                int bit = ArrayUtils.getBit(arr[k], d);
                help[count[bit] - 1] = arr[k];
                count[bit]--;
            }

            System.arraycopy(help, 0, arr, L, help.length);
        }
    }
}
