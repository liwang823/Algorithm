package chapter01;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/6/11 12:43
 */
public class Demo02 {

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINESE);

        Date now = calendar.getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

        System.out.println(simpleDateFormat.format(now));

        String string = "2023年06月12日 23:34:00";

        try {
            System.out.println(simpleDateFormat.format(simpleDateFormat.parse(string)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
