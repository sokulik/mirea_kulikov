package ru.mirea.kulikov.pr2;

public class Main {
    public static void main(String[] args) {
        Author author = new Author();
        System.out.println(author);
    author.setName("Достоевский");
    author.setEmail("dostal_vstavil@mail.ru");
    author.setAge(40);
    author.setGender('м');
        System.out.println(author);
        Author author1 = new Author("Марк Твен", "s@mail.ru", 'м', 18);
        System.out.println(author1);



    }
}
