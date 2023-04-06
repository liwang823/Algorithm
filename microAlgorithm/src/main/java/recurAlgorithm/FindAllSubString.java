package recurAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 打印一个字符串的所有子串，包括空
 * @author 李旺
 * @version 1.0
 * @date 2023/4/2 10:59
 */
public class FindAllSubString {

    public static void main(String[] args) {
        solution("1234");
    }

    public static void solution(String s){

        if (s == null){
            return;
        }

        process(0, new ArrayList<>(), s);
    }

    public static void process(int index, List<Character> buffer, String s){
        if (index == s.length()){
            System.out.println(printList(buffer));
            return;
        }

        List<Character> includeCur = new ArrayList<>(buffer);
        char cur = s.charAt(index);
        includeCur.add(cur);
        process(index + 1, includeCur, s);

        List<Character> notIncludeCur = new ArrayList<>(buffer);
        process(index + 1, notIncludeCur, s);

    }

    public static String printList(List<Character> list){

        StringBuilder sb = new StringBuilder();

        for (Character ch: list){
            sb.append(ch);
        }

        return sb.toString();
    }
}
