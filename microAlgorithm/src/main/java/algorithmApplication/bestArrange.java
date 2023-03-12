package algorithmApplication;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 安排会议室的最优解
 * @author 李旺
 * @version 1.0
 * @date 2023/3/9 01:28
 */
public class bestArrange {

    public static class program{
        public int start;

        public int end;

        public program(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    public static int solution(program[] programs, int timePoint){
        Arrays.sort(programs, Comparator.comparingInt(o -> o.end));
        int result = 0;
        for (bestArrange.program program : programs) {
            if (timePoint < program.start) {
                timePoint = program.end;
                result++;
            }
        }

        return result;
    }
}
