package Practice4_1;

public class Task1 {
        public enum Seasons {
            Winter,
            Spring,
            Summer,
            Autumn
        }

        public static String iLove(Seasons season) {
            switch (season) {
                case Winter -> System.out.println("Я не люблю Зиму");
                case Spring -> System.out.println("Я не очень люблю Весну");
                case Summer -> System.out.println("Я очень люблю лето!");
                case Autumn -> System.out.println("Я люблю Осень");
            }
            return "";
        }

        private static int averageTemp;
        private static String description;

        public static void setInfoSeasons(Seasons season, int temp, String disc) {
            averageTemp = temp;
            description = disc;
        }

        public static int getAverageTemp(Seasons seasons) {
            switch (seasons) {
                case Winter:
                    return -20;
                case Spring:
                    return 5;
                case Summer:
                    return 20;
                case Autumn:
                    return 10;
                default:
                    return 0;
            }
        }

        public static String getDescription(Seasons season) {
            if (season == Seasons.Summer) {
                return "Теплое время года";
            } else if (season == Seasons.Autumn) {
                return "Прохладное время года";
            } else if (season == Seasons.Spring) {
                return "Прохладное время года";
            } else {
                return "Холодное время года";
            }
        }


        public static void task1() {
            Seasons favorite = Seasons.Summer;
            System.out.println(iLove(favorite)+"Средняя температура = "+getAverageTemp(favorite)
                    +" градусов - это "+getDescription(favorite));

            System.out.println("\n=== Все времена года ===");
            for (Seasons season : Seasons.values()){
                System.out.println(iLove(season)+"Средняя температура = "+getAverageTemp(season)
                        +" градусов - это "+getDescription(season));
            }
        }
    }
