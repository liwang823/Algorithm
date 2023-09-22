package chapter02Test;

import chapter02.SerializationLearning;
import chapter02.entity.PersonalAttr;
import chapter02.entity.Student;
import org.junit.Test;

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

        // 创建对象
        Student student = new Student();
        student.setAge("28");
        student.setName("joy");

        // 测试方法
        SerializationLearning sl = new SerializationLearning();
        sl.writeObjectToFile(student, "studentFile.txt");
    }

    /**
     * public Object readObjectInFile(String filePath)
     */
    @Test
    public void testReadObjectInFile(){

        // 路径
        String filePath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath()
                + "studentFile.txt";

        // 创建对象
        SerializationLearning serializationLearning = new SerializationLearning();
        Object o = serializationLearning.readObjectInFile(filePath);

        System.out.println(o);
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
}
