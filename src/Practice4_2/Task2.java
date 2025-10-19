package Practice4_2;

import Practice4_2.Interfaces.*;
import Practice4_2.Enums.*;
import Practice4_2.Classes.*;

public class Task2 {
    public static void task2() {
        Clothes[] clothes = {
                new Clothes.TShirt(Size.S, 1500, "blue"),
                new Clothes.TShirt(Size.XXS, 1000, "black"),
                new Clothes.TShirt(Size.L, 2000, "white"),
                new Clothes.Pants(Size.M, 3000, "black"),
                new Clothes.Pants(Size.L, 3500, "black"),
                new Clothes.Pants(Size.M, 3000, "darkBlue"),
                new Clothes.Skirt(Size.S, 2200, "pink"),
                new Clothes.Skirt(Size.XXS, 2500, "red"),
                new Clothes.Tie(Size.L, 1500, "yellow"),
                new Clothes.Tie(Size.M, 6000, "black")
        };
        Atelier atelier = new Atelier();
        atelier.dressWoman(clothes);
        System.out.println();
        atelier.dressMan(clothes);

    }
}
