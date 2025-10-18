package Practice5_10.Enums;

public enum Generation {
    i9(Brand.Intel, "i9"),
    i7(Brand.Intel, "i7"),
    i5(Brand.Intel, "i5"),
    i3(Brand.Intel, "i3"),
    AMD_3(Brand.AMD, "3"),
    AMD_5(Brand.AMD, "5"),
    AMD_7(Brand.AMD, "7"),
    AMD_9(Brand.AMD, "9"),
    M1(Brand.Apple, "M1"),
    M2(Brand.Apple, "M2"),
    M3(Brand.Apple, "M3")
    ;
    private final Brand brand;
    private final String generation;

    Generation(Brand brand, String generation){
        this.brand =brand;
        this.generation = generation;
    }

    public Brand getBrand(){
        return brand;
    }

    public String getGeneration(){
        return generation;
    }
    @Override
    public String toString(){
        return generation;
    }


}

