package Practice5_11.Classes;
import Practice5_11.Interfaces.*;

public class CtF implements Convertable{
    @Override
    public double convert(double celsius){
        return (celsius * 9/5) + 32;
    }

}
