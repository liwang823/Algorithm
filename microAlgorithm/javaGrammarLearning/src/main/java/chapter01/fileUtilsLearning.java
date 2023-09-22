package chapter01;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.*;
import java.util.*;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/8/19 17:53
 */
public class fileUtilsLearning {

    public void saveAsExcel(String path, List<Clothes> list){

        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        for (int i = 0; i < list.size(); i++){
            Clothes clothes = list.get(i);
            Row row = sheet.createRow(i);

            row.createCell(0).setCellValue(clothes.getId());
            row.createCell(1).setCellValue(clothes.getSize().code);
        }

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(path);
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Map<String, List<Object>> readCSVFiles(String path) {

        CSVFormat csvFormat = CSVFormat.newFormat(',').withQuote('"').
                withNullString("N/A").withIgnoreSurroundingSpaces(true);

        FileReader fileReader;
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

        String dir = Objects.requireNonNull(instance.getClass().getClassLoader().getResource("")).getPath();

        String path = dir.concat("info.csv");

        Map<String, List<Object>> map = fileUtilsLearning.getInstance().readCSVFiles(path);

        System.out.println(map);

        String excelPath = dir.concat("info.xls");

        System.out.println(excelPath);

        Clothes c1 = new Clothes();
        c1.setId("gxg");
        c1.setSize(Size.SMALL);

        Clothes c2 = new Clothes();
        c2.setId("peaceBird");
        c2.setSize(Size.MEDIUM);

        List<Clothes> clothesList = new ArrayList<Clothes>() {{
            add(c1);
            add(c2);
        }};

        fileUtilsLearning.getInstance().saveAsExcel(excelPath, clothesList);
    }
}
