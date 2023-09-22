package chapter02.utils;

import java.util.Objects;

/**
 * 文件工具类
 * @author 李旺
 * @version 1.0
 * @date 2023/9/22 23:35
 */
public class FileUtils {

    /**
     * 获取某个对象对应类的类目录
     * @param object: 类
     * @return 类路径
     */
    public static String getClassPath(Object object){

        return Objects.requireNonNull(object.getClass().getClassLoader().getResource("")).getPath();
    }


}
