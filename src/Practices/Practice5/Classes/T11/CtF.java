package Practices.Practice5.Classes.T11;

public class CtF implements Convertable{
    @Override
    public double convert(double celsius){
        return (celsius * 9/5) + 32;
    }

}
