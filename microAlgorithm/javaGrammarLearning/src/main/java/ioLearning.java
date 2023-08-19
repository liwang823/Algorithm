import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/8/5 11:46
 */
public class ioLearning {

    public static void main(String[] args) throws IOException {

        String path = Objects.requireNonNull(ioLearning.class.getClassLoader().getResource("")).getPath();
        path = path.concat("hello.txt");

        FileInputStream fileInputStream = new FileInputStream(path);
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        byte[] buff = new byte[1024];

        int byteRead = 0;

        while ((byteRead = fileInputStream.read(buff)) != -1){
            output.write(buff, 0 , byteRead);
        }

        String data = output.toString("UTF-8");
        System.out.println(data);


    }
}
