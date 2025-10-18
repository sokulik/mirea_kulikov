package Practice5_6_to_9;
import Practice5_6_to_9.Classes.*;
import Practice5_6_to_9.Interfaces.*;
public class Task6_9 {
    public static void task6_9() {
    Printable[] printables = new Printable[20];
    // Книги
    printables[0] = new Book("Преступление и наказание", "Фёдор Достоевский", "Роман", 3500);
    printables[1] = new Book("Мастер и Маргарита", "Михаил Булгаков", "Фантастика", 4200);
    printables[2] = new Book("1984", "Джордж Оруэлл", "Антиутопия", 2800);
    printables[3] = new Book("Гарри Поттер и философский камень", "Джоан Роулинг", "Фэнтези", 3200);
    printables[4] = new Book("Три товарища", "Эрих Мария Ремарк", "Роман", 3100);
    printables[5] = new Book("Маленький принц", "Антуан де Сент-Экзюпери", "Притча", 1900);
    printables[6] = new Book("Убить пересмешника", "Харпер Ли", "Роман", 2700);
    printables[7] = new Book("Властелин колец", "Джон Р.Р. Толкин", "Фэнтези", 5100);
    printables[8] = new Book("Анна Каренина", "Лев Толстой", "Классика", 3800);
    printables[9] = new Book("Сто лет одиночества", "Габриэль Гарсиа Маркес", "Магический реализм", 3600);

// Журналы
    printables[10] = new Magazine("Forbes", "Бизнес", 800);
    printables[11] = new Magazine("National Geographic", "Наука", 750);
    printables[12] = new Magazine("Vogue", "Мода", 900);
    printables[13] = new Magazine("Time", "Новости", 600);
    printables[14] = new Magazine("Popular Mechanics", "Технологии", 550);
    printables[15] = new Magazine("Cosmopolitan", "Женский", 700);
    printables[16] = new Magazine("GQ", "Мужской", 850);
    printables[17] = new Magazine("The Economist", "Политика", 950);
    printables[18] = new Magazine("Sports Illustrated", "Спорт", 500);
    printables[19] = new Magazine("Scientific American", "Научно-популярный", 720);

    System.out.println("\n=== Содержимое массива Printable ===");
    for (int i=0; i < printables.length; i++) {
        printables[i].print();
    }
}
}
