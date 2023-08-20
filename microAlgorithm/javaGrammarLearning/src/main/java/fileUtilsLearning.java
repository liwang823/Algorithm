import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/8/19 17:53
 */
public class fileUtilsLearning {

    public Map<String, List<Object>> readCSVFiles(String path) {

        CSVFormat csvFormat = CSVFormat.newFormat(',').withQuote('"').
                withNullString("N/A").withIgnoreSurroundingSpaces(true);

        FileReader fileReader = null;
        Map<String, List<Object>> csvMap = new HashMap<>();
        try {
            fileReader = new FileReader(path);
            CSVParser parseResult = csvFormat.parse(fileReader);

            Iterator<CSVRecord> iterator = parseResult.iterator();
            CSVRecord header = iterator.next();

            for (String field: header){
                csvMap.put(field, new ArrayList<>());
            }

            while (iterator.hasNext()){
                CSVRecord record = iterator.next();
                for (int i = 0; i < record.size(); i++){
                    csvMap.get(header.get(i)).add(record.get(i));
                }
            }

            return csvMap;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return csvMap;
    }

    public Map<String, Object> readProperties(String path, List<String> fieldList){

        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Object> map = new HashMap<>();

        for (String field: fieldList){
            map.put(field, properties.get(field));
        }

        return map;
    }

    public static fileUtilsLearning getInstance(){
        return new fileUtilsLearning();
    }

    public static void main(String[] args) {

        fileUtilsLearning instance = fileUtilsLearning.getInstance();

        String path = Objects.requireNonNull(instance.getClass().getClassLoader().getResource("")).getPath();

        path = path.concat("info.csv");

        Map<String, List<Object>> map = fileUtilsLearning.getInstance().readCSVFiles(path);

        System.out.println(map);
    }
}
