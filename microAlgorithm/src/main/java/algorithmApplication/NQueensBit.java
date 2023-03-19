package algorithmApplication;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/3/13 22:36
 */
public class NQueensBit {

    public static int solution(int n){
        if (n < 1 || n > 32){
            return 0;
        }

        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process(limit, 0, 0, 0);
    }

    public static int process(int limit, int columnLim, int leftLim, int rightLim){
        if (columnLim == limit){
            return 1;
        }

        int pos = limit & (~(columnLim | leftLim | rightLim));
        int res = 0;

        while (pos != 0){
            int mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            columnLim = columnLim | mostRightOne;

            // 通过这个保证 Math.abs(record[k] - column) == Math.abs(k - row)的限制
            leftLim = (leftLim | mostRightOne) << 1;
            rightLim = (rightLim | mostRightOne) >>>1;
            res += process(limit, columnLim, leftLim, rightLim);
        }

        return res;
    }
}
