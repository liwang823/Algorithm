package algorithmApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 将N皇后问题的所有解法打印出来
 * @author 李旺
 * @version 1.0
 * @date 2023/3/12 12:53
 */
public class NQueensPrint {

    public static void main(String[] args) {
        int queenNum = 4;
        System.out.println(solution(queenNum));
    }

    public static List<List<String>> solution(int n){
        List<List<String>> result = new ArrayList<>();

        if (n < 1){
            return result;
        }

        int[] record = new int[n];
        process(result, record, n, 0);
        return result;
    }

    public static void process(List<List<String>> result, int[] record, int n, int row){

        if (row == n){
            List<String> solution = new ArrayList<>();
            for (int i = 0; i < n; i++){
                solution.add(queenPosition(n, record[i]));
            }

            result.add(solution);
            return;
        }

        for (int column = 0; column < n; column++){
            if (isValid(record, row, column)){
                record[row] = column;
                process(result, record, n, row + 1);
            }
        }
    }

    public static String queenPosition(int n, int column){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++){
            sb.append(".");
        }

        sb.replace(column, column + 1, "Q");
        return sb.toString();
    }

    public static boolean isValid(int[] record, int row, int column){
        for (int k = 0; k < row; k++){
            if (record[k] == column || Math.abs(record[k] - column) == Math.abs(k - row)){
                return false;
            }
        }
        return true;
    }
}
