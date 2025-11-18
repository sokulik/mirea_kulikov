package Practices.Practice2.Classes;

public class Author {
    private String name;
    private String email;
    private char gender;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Author(String name) {
        this.name = name;
    }

    public Author(String name, String email, char gender, int age) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }
    public Author(){
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}