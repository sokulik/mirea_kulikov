package Practice5_11.Classes;
import Practice5_11.Interfaces.*;

public class CtK implements Convertable{
    @Override
    public double convert(double celsius){
        return celsius + 273.15;
    }
}
