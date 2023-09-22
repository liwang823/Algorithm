package chapter02;

import chapter02.entity.Student;
import chapter02.utils.FileUtils;

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

}
