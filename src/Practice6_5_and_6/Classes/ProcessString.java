package Practice6_5_and_6.Classes;


import Practice6_5_and_6.Interfaces.CommandsString;

public class ProcessString implements CommandsString {

    @Override
    public int Count(String s) {
        return s.length();
    }

    @Override
    public String Position(String s) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                result.append(s.charAt(i));
            }
        }
        return result.toString();
    }

    @Override
    public String Reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public void PrintStringInfo(String s){
        System.out.println("Исходная строка: " + s);
        System.out.println("Количество символов: " + Count(s));
        System.out.println("Символы на нечетных позициях: " + Position(s));
        System.out.println("Инвертированная строка: " + Reverse(s));
        System.out.println();
    }
}
