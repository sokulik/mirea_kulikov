package Practice6_7_and_8.Classes;

import Practice6_7_and_8.Interfaces.Printable;

public class Book implements Printable {
    private String title;

    public Book(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void print() {
        System.out.println("Печатаем книгу: " + title);
    }

    public static void printBooks(Printable[] printable) {
        System.out.println("Книги:");
        for (Printable item : printable) {
            if (item instanceof Book) {
                Book book = (Book) item;
                System.out.println(book.getTitle());
            }
        }
    }


}
