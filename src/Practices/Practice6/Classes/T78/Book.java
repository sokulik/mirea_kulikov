package Practices.Practice6.Classes.T78;

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
