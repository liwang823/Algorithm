package chapter02;

import chapter02.entity.Employee;
import chapter02.entity.Student;
import chapter02.utils.FileUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;

/**
 * 序列化和反序列化的学习
 * @author 李旺
 * @version 1.0
 * @date 2023/9/22 23:30
 */
public class SerializationLearning {

    /**
     * 将对象写入文件
     * @param object: 写入文件的任意对象
     * @param fileName: 写入的文件名
     */
    public void writeObjectToFile(Object object, String fileName) {

        // 获取绝对路径
        String dirPath = FileUtils.getClassPath(this);
        String absolutePath = dirPath + fileName;

        // 对象反序列化写入到文件
        ObjectOutputStream outputStream;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(absolutePath));
            outputStream.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件中的对象
     * @param filePath: 文件绝对路径
     * @return 反序列化后的对象
     */
    public Object readObjectInFile(String filePath){

        // 创建对象输入流
        ObjectInputStream objectInputStream;
        Object result = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
            result = objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 将对象序列化为字符串
     * @param object: 对象
     * @return 序列化之后的字符串
     */
    public String writeObjectAsString(Object object){

        // 定义序列化对象
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // 反序列化
        String s = null;
        try {
            s = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return s;
    }

    /**
     * 将对象写成xml文件
     * @param object: 待写成xml文件的类
     * @param fileName: 文件名
     */
    public void writeObjectToFileIgnoreFormat(Object object, String fileName){

        // 定义序列化对象
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // 反序列化
        try {
            if (! (object instanceof  String)){
                mapper.writeValue(new File(fileName),
                        this.writeObjectAsString(object));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件中的对象忽略文件格式
     * @param filePath: 文件路径
     * @param objectClass: 对象的类
     * @return 读取的对象
     */
    public Object readObjectIgnoreFormat(String filePath, Class objectClass){

        // 反序列化的类
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // 读取
        Object o = null;
        try {
            o = mapper.readValue(new File(filePath), objectClass);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return o;
    }

}
