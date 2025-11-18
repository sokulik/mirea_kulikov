package Practice6_4.Classes;

import Practice6_4.Interfaces.MathCalculable;

public class MathFunc implements MathCalculable {
    @Override
    public double Exponentiation(double base, double extent) {
        return Math.pow(base, extent);
    }

    @Override
    public double Complex(double real, double imaginary) {
        return Math.sqrt(real*real+imaginary*imaginary);
    }

    public double circleLenght(double radius){
        return 2*PI*radius;
    }
    public double circleArea(double radius){
        return PI*Exponentiation(radius, 2);
    }
}