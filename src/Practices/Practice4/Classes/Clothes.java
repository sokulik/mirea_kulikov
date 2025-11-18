package Practices.Practice4.Classes;



    public abstract class Clothes {
        protected Size size;
        protected double price;
        protected String color;

        public Clothes(Size size, double price, String color) {
            this.size = size;
            this.price = price;
            this.color = color;
        }

        public Size getSize() {
            return size;
        }

        public void setSize(Size size) {
            this.size = size;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }


        public static class Pants extends Clothes implements MenClothing, WomenClothing{
            public Pants(Size size, double price, String color){
                super (size, price, color);
            }

            @Override
            public void DressMan() {
                System.out.println("Мужские штаны: размер "+size+"("+size.getEuroSize()+
                        ") - "+size.getDescription()+", цвет "
                        +color+", цена "+price+" руб.");

            }

            @Override
            public void DressWoman() {
                System.out.println("Женские штаны: размер "+size+"("+size.getEuroSize()+
                        ") - "+size.getDescription()+", цвет "
                        +color+", цена "+price+" руб.");

            }
        }

        public static class Skirt extends Clothes implements WomenClothing{
            public Skirt(Size size, double price, String color){
                super (size, price, color);
            }

            @Override
            public void DressWoman() {
                System.out.println("Юбка: размер "+size+"("+size.getEuroSize()+") - "
                        +size.getDescription()+", цвет "
                        +color+", цена "+price+" руб.");

            }
        }

        public static class Tie extends Clothes implements MenClothing{
            public Tie(Size size, double price, String color){
                super (size, price, color);
            }

            @Override
            public void DressMan() {
                System.out.println("Галстук: размер "+size+"("+size.getEuroSize()+") - "
                        +size.getDescription()+", цвет "
                        +color+", цена "+price+" руб.");

            }
        }

        public static class TShirt extends Clothes implements MenClothing, WomenClothing {
            public TShirt(Size size, double price, String color) {
                super(size, price, color);
            }

            @Override
            public void DressMan() {
                System.out.println("Мужская футболка: размер "+size+"("+size.getEuroSize()+") - "
                        +size.getDescription()+", цвет "
                        +color+", цена "+price+" руб.");

            }

            @Override
            public void DressWoman() {
                System.out.println("Женская футболка: размер "+size+"("+size.getEuroSize()+") - "
                        +size.getDescription()+", цвет "
                        +color+", цена "+price+" руб.");

            }
        }
    }

