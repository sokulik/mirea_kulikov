package Practice6_7_and_8;

import Practice6_7_and_8.Interfaces.*;
import Practice6_7_and_8.Classes.*;

public class Task7_8 {
    public static void task7_8() {

        Printable[] printables = {
                new Book("Война и мир"),
                new Magazine("National Geographic"),
                new Book("Преступление и наказание"),
                new Magazine("Forbes"),
                new Book("Мастер и Маргарита")
        };


        Magazine.printMagazines(printables);
        System.out.println();
        Book.printBooks(printables);
    }
}

