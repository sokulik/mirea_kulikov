package Practice6_7_and_8.Classes;

import Practice6_7_and_8.Interfaces.Printable;

public class Magazine implements Printable {
    private String title;

    public Magazine(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void print() {
        System.out.println("Печатаем журнал: " + title);
    }

    public static void printMagazines(Printable[] printable) {
        System.out.println("Журналы:");
        for (Printable item : printable) {
            if (item instanceof Magazine) {
                Magazine magazine = (Magazine) item;
                System.out.println(magazine.getTitle());
            }
        }
    }


}
