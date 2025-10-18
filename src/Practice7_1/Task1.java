package Practice7_1;
import java.util.*;
import java.text.SimpleDateFormat;

public class Task1 {
    public static void task1() {
        String developer = "Гнатюк Даниил Александрович";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy, MMMM, dd | HH:mm:ss");

        Calendar cal1 = Calendar.getInstance();
        cal1.set(2025, Calendar.AUGUST, 25, 11,25, 43);

        Date now = new Date();
        Date startDate = cal1.getTime();

        System.out.println("Разработчик: "+developer+
                "\nДата начала выполнения проекта: "+sdf.format(startDate)+
                "\nДата окончания выполения проекта: "+sdf.format(now));

    }
}
