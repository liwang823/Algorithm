package chapter03;

import java.util.Collections;
import java.util.Map;

/**
 * EnhanceMap
 * @author 李旺
 * @version 1.0
 * @date 2023/9/28 21:18
 */
public class EnhanceMapDemo {

    static class EnhanceMap<K, V>{

        Map<K, V> map;

        public EnhanceMap(Map<K, V> map){
            this.map = Collections.synchronizedMap(map);
        }

        public synchronized V putIfAbsent(K key, V value){
            synchronized (map){
                V old = map.get(key);

                if (old != null) {
                    return old;
                }

                return map.put(key, value);
            }
        }

        public synchronized V put(K key, V value){
            return map.put(key, value);
        }
    }
}
