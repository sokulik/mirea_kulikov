package Practice7_3.Classes;

import java.util.*;
import java.text.SimpleDateFormat;

public class Student {
    private String name;
    private int age;
    private Date birthDate;

    public Student(String name, int age, Date birthDate){
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
    }

    public Student(String name, Date birthDate){
        this.name = name;
        this.birthDate = birthDate;
        this.age = calculateAge();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFormattedBirthDate(String formatType){
        if (birthDate == null){
            return "Дата рождения не указана";
        }
        switch (formatType.toLowerCase()){
            case "short":
                return new SimpleDateFormat("dd.MM.yyyy").format(birthDate);
            case "medium":
                return new SimpleDateFormat("dd MMM yyyy").format(birthDate);
            case "long":
                return new SimpleDateFormat("d MMMM yyyy 'г.'").format(birthDate);
            case "full":
                return new SimpleDateFormat("EEEE, d MMMM yyyy 'г.'").format(birthDate);
            case "system":
                return new SimpleDateFormat("yyyy-MM-dd").format(birthDate);
            default:
                return new SimpleDateFormat("dd.MM.yyyy").format(birthDate);
        }

    }

    @Override
    public String toString(){
        return toString("medium");
    }

    public String toString(String dateFormat){
        if (birthDate == null){
            return String.format("Студент: %s, Возраст: %d, Дата рождения: не указана", name, age);
        }
        String formattedDate = getFormattedBirthDate(dateFormat);
        return String.format("Студент: %s, Возраст: %d, Дата рождения: %s", name, age, formattedDate);
    }

    private int calculateAge() {
        if (birthDate == null) {
            return 0;
        }

        Calendar birth = Calendar.getInstance();
        birth.setTime(birthDate);
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return age;
    }

    public boolean isBirthdayToday() {
        if (birthDate == null) {
            return false;
        }

        Calendar today = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthDate);

        return today.get(Calendar.MONTH) == birth.get(Calendar.MONTH) &&
                today.get(Calendar.DAY_OF_MONTH) == birth.get(Calendar.DAY_OF_MONTH);
    }

    public int getDaysUntilBirthday() {
        if (birthDate == null) {
            return -1;
        }

        Calendar today = Calendar.getInstance();
        Calendar nextBirthday = Calendar.getInstance();
        nextBirthday.setTime(birthDate);
        nextBirthday.set(Calendar.YEAR, today.get(Calendar.YEAR));

        if (nextBirthday.before(today)) {
            nextBirthday.set(Calendar.YEAR, today.get(Calendar.YEAR) + 1);
        }

        long diff = nextBirthday.getTimeInMillis() - today.getTimeInMillis();
        return (int) (diff / (24 * 60 * 60 * 1000));
    }

}