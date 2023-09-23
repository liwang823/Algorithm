package chapter02Test;

import chapter02.SerializationLearning;
import chapter02.entity.PersonalAttr;
import chapter02.entity.Student;
import chapter02.utils.FileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SerializationLearning测试
 * @author 李旺
 * @version 1.0
 * @date 2023/9/22 23:48
 */


public class SerializationLearningTest {

    /**
     * public void writeObjectToFile(Object object, String fileName)
     */
    @Test
    public void testWriteObjectToFile(){

        // 定义对象
        PersonalAttr personalAttr = new PersonalAttr("shein", "QualityAssurance");
        Student joy = new Student("joy", "29", personalAttr);

        // 测试方法
        SerializationLearning sl = new SerializationLearning();
        sl.writeObjectToFile(joy, "joy.json");
    }

    /**
     * public Object readObjectInFile(String filePath)
     */
    @Test
    public void testReadObjectInFile(){

        // 路径
        String filePath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath()
                + "joy.json";

        // 创建对象
        SerializationLearning serializationLearning = new SerializationLearning();
        Object o = serializationLearning.readObjectInFile(filePath);

        System.out.println(o);
    }

    /**
     * 将对象序列化成字符串测试
     */
    @Test
    public void testWriteObjectAsString(){

        // 定义对象
        PersonalAttr personalAttr = new PersonalAttr("shein", "QualityAssurance");
        Student joy = new Student("joy", "29", personalAttr);

        // 调用
        SerializationLearning serializationLearning = new SerializationLearning();
        String joyStr = serializationLearning.writeObjectAsString(joy);

        System.out.println(joyStr);
    }

    /**
     * 测试对象创建 -> 写入 -> 读取
     */
    @Test
    public void testObjectWriteAndRead(){

        // 创建对象
        int size = 10;
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < size; i++){
            PersonalAttr personalAttr = new PersonalAttr("zte", "RD");
            Student student = new Student("student" + i, "" + i + 28, personalAttr);
            students.add(student);
        }

        // 序列化
        String fileName = "studentList.txt";
        SerializationLearning serializationLearning = new SerializationLearning();
        serializationLearning.writeObjectToFile(students, fileName);

        // 反序列化
        String dirPath = Objects.requireNonNull
                (serializationLearning.getClass().getClassLoader().getResource("")).getPath();
        Object o = serializationLearning.readObjectInFile(dirPath + fileName);

        assert o instanceof List;

        for (Object e: (List) o){
            assert e instanceof Student;
        }

    }

    /**
     * 测试对象序列化成xml
     */
    @Test
    public void testWriteObjectToXmlFile(){

        // 定义对象
        PersonalAttr personalAttr = new PersonalAttr("byteDance", "RD");
        Student georage = new Student("georage", "32", personalAttr);

        // 调用方法
        String fileName = "georage.xml";
        SerializationLearning serializationLearning = new SerializationLearning();
        serializationLearning.writeObjectToFileIgnoreFormat(georage, fileName);

        // 读取文件
        String dirPath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath();
        Object o = serializationLearning.readObjectIgnoreFormat(dirPath + fileName, Student.class);

        System.out.println(o);
    }

    /**
     * 测试list序列化成json
     */
    @Test
    public void testWriteListToJson(){

        // 初始化对象
        int size = 10;
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < size; i++){
            PersonalAttr personalAttr = new PersonalAttr("shein", "RD");
            Student student = new Student("student" + i, "29", personalAttr);
            studentList.add(student);
        }

        // 写文件
        String fileName = "studentList.json";
        String dirPath = FileUtils.getClassPath(this);
        SerializationLearning serializationLearning = new SerializationLearning();
        serializationLearning.writeObjectToFileIgnoreFormat(studentList, dirPath + fileName);

    }
}
