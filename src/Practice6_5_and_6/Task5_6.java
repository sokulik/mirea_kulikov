package Practice6_5_and_6;
import Practice6_5_and_6.Classes.ProcessString;
import Practice6_5_and_6.Interfaces.CommandsString;
import java.util.Scanner;

public class Task5_6 {
    public static void task5_6() {
        Scanner scanner = new Scanner(System.in);
        ProcessString process = new ProcessString();


        System.out.println("=== ОПЕРАЦИИ СО СТРОКАМИ ===");
        System.out.print("Введите строку для анализа: ");

        String input = scanner.nextLine();
        process.PrintStringInfo(input);
    }
}

