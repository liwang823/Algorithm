package sortAlgorithm;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/4 15:44
 */
public class mergeSort {

    public static void solution(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R){
        if (L == R){
            return;
        }

        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int M, int R){
        int[] help = new int[R - L + 1];
        int index = 0;

        int p1 = L;
        int p2 = M + 1;

        while (p1 <= M && p2 <= R){
            help[index++] = arr[p1] < arr[p2] ? arr[p1++]: arr[p2++];
        }

        while (p1 <= M){
            help[index++] = arr[p1++];
        }

        while (p2 <= R){
            help[index++] = arr[p2++];
        }

        System.arraycopy(help, 0, arr, L, help.length);
    }
}
