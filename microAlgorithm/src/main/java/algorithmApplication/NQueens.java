package algorithmApplication;

import java.util.Map;

/**
 * 在一个N * N的棋盘上摆N个皇后，要求所有皇后不共行、不共列、不共斜线
 * @author 李旺
 * @version 1.0
 * @date 2023/3/11 23:02
 */
public class NQueens {

    public static void main(String[] args) {
        System.out.println(solution(4));
    }

    public static int solution(int n){

        if (n < 1){
            return 0;
        }

        // 准备数组：记录第i行选择的哪一列
        int[] record = new int[n];
        return process(record, 0, n);
    }

    public static int process(int[] record, int row, int n){
        if (row == n){
            return 1;
        }

        int res = 0;

        for (int column = 0; column < n; column++){
            if (isValid(record, row, column)){
                record[row] = column;
                res += process(record, row + 1, n);
            }
        }

        return res;
    }

    public static boolean isValid(int[] record, int row, int column){
        for (int k = 0; k < row; k++){
            if (column == record[k] || Math.abs(record[k] - column) == Math.abs(row - k)){
                return false;
            }
        }

        return true;
    }
}
