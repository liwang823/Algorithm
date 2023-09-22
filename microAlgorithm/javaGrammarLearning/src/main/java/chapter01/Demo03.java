package chapter01;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/6/24 23:07
 */
public class Demo03 {

    public static void main(String[] args) {

        TreeMap<String, String> map = new TreeMap<>();

        map.put("a", "abstract");
        map.put("c", "call");
        map.put("b", "basic");
        map.put("T", "tree");

        for (Map.Entry<String, String> entry: map.entrySet()){
            System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
        }

    }

    public static Map<Size, Integer> countBySize(List<Clothes> clothesList){

        Map<Size, Integer> enumMap = new EnumMap<>(Size.class);

        for (Clothes c: clothesList){
            Size size = c.getSize();
            String id = c.getId();

            if (! enumMap.containsKey(size)){
                enumMap.put(size, 1);
            }else{
                enumMap.put(size, enumMap.get(size) + 1);
            }
        }

        return enumMap;
    }
}
