package Practice4_2.Classes;

import Practice4_2.Interfaces.*;
import Practice4_2.Enums.*;

public class Atelier {
    public void dressWoman(Clothes[] clothes){
        System.out.println("=== Женская одежда ===");
        for (Clothes item: clothes){
            if (item instanceof WomenClothing){
                ((WomenClothing) item).DressWoman();
            }
        }
    }
    public void dressMan(Clothes[] clothes){
        System.out.println("=== Мужская одежда ===");
        for (Clothes item: clothes){
            if (item instanceof MenClothing){
                ((MenClothing) item).DressMan();
            }
        }
    }
}
