package Practices.Practice4.Classes;

public class Atelier {
    public String dressWoman(Clothes[] clothes) {
        StringBuilder sb = new StringBuilder();
        for (Clothes item : clothes) {
            if (item instanceof WomenClothing) {
                // Здесь нужно адаптировать вывод для GUI
                // В оригинале используется System.out.println
            }
        }
        return sb.toString();
    }

    public String dressMan(Clothes[] clothes) {
        StringBuilder sb = new StringBuilder();
        for (Clothes item : clothes) {
            if (item instanceof MenClothing) {
                // Адаптация для GUI
            }
        }
        return sb.toString();
    }
}