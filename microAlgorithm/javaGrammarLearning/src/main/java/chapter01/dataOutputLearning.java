import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/8/13 14:37
 */
public class dataOutputLearning {

    public static void main(String[] args) {

        Clothes clothes01 = new Clothes();
        clothes01.setId("11");
        clothes01.setSize(Size.LARGE);

        Clothes clothes02 = new Clothes();
        clothes02.setId("12");
        clothes02.setSize(Size.SMALL);

        List<Clothes> clothesList = Arrays.asList(clothes01, clothes02);

        writeClothes(clothesList);

    }

    public static Size getSizeByCode(int code){
        switch (code){
            case 0:
                return Size.SMALL;
            case 1:
                return Size.MEDIUM;
            case 2:
                return Size.LARGE;
            default:
                return null;
        }
    }

    public static void writeClothes(List<Clothes> clothesList){

        String path = "/Users/lilovegao/IdeaProjects/microAlgorithm/src/main/resources".concat("/info.txt");
        System.out.println(path);

        DataOutputStream dataOutputStream = null;

        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(path));
            dataOutputStream.writeInt(clothesList.size());
            for (Clothes clothes: clothesList){
                dataOutputStream.writeUTF(clothes.getId());
                dataOutputStream.writeInt(clothes.getSize().code);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dataOutputStream != null){
                try {
                    dataOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
