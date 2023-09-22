import java.util.Arrays;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/6/1 22:29
 */
public class Demo01 {

    public static void main(String[] args) {

        int[][] ints = new int[][]{{97, 98, 99}, {11, 1}};

        System.out.println(Arrays.deepToString(ints));

    }

    public static class Pair{

        int maxValue;

        int minValue;

        public Pair(){
            maxValue = Integer.MIN_VALUE;
            minValue = Integer.MAX_VALUE;
        }
    }

    private final int[] inputArr;

    private final Pair pair = new Pair();

    public Demo01(int[] arr){
        inputArr = Arrays.copyOf(arr, arr.length);
    }

    private void getTarget(){

        for (int current : inputArr) {

            pair.maxValue = Math.max(pair.maxValue, current);
            pair.minValue = Math.min(pair.minValue, current);
        }
    }

    public int getMaxVal(){
        return pair.maxValue;
    }

    public int getMinVal(){
        return pair.minValue;
    }

}
