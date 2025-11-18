package Practice5_11;

import java.util.Scanner;
import Practice5_11.Classes.*;
import Practice5_11.Interfaces.*;

public class Task11 {
    public static void task11() {
        Scanner scanner = new Scanner(System.in);
        Convertable toK = new CtK();
        Convertable toF = new CtF();
        System.out.println("\nВведите температуру в градусах Цельсия: ");
        double temp = scanner.nextDouble();

        System.out.println("\nРезультаты конвертации:\n"+
                temp+"°C = "+toK.convert(temp)+ "K\n"+
                temp+"°C = "+ toF.convert(temp)+"°F");


    }
}
