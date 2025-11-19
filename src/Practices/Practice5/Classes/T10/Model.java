package Practices.Practice5.Classes.T10;

public enum Model {
    // Intel модели
    I3_12100(Generation.i3, "12100", 15000),
    I3_13100(Generation.i3, "13100", 17000),
    I5_12600K(Generation.i5, "12600K", 25000),
    I5_13600K(Generation.i5, "13600K", 30000),
    I7_12700K(Generation.i7, "12700K", 35000),
    I7_13700K(Generation.i7, "13700K", 40000),
    I9_12900K(Generation.i9, "12900K", 50000),
    I9_13900K(Generation.i9, "13900K", 60000),

    // AMD модели
    RYZEN_3_3300X(Generation.AMD_3, "3300X", 12000),
    RYZEN_3_3100(Generation.AMD_3, "3100", 10000),
    RYZEN_5_5600X(Generation.AMD_5, "5600X", 20000),
    RYZEN_5_5600G(Generation.AMD_5, "5600G", 18000),
    RYZEN_5_7600X(Generation.AMD_5, "7600X", 28000),
    RYZEN_7_5800X(Generation.AMD_7, "5800X", 30000),
    RYZEN_7_5700G(Generation.AMD_7, "5700G", 26000),
    RYZEN_7_7700X(Generation.AMD_7, "7700X", 38000),
    RYZEN_9_5950X(Generation.AMD_9, "5950X", 60000),
    RYZEN_9_5900X(Generation.AMD_9, "5900X", 45000),
    RYZEN_9_7950X(Generation.AMD_9, "7950X", 70000),

    // Apple
    M1(Generation.M1, "", 80000),
    M1_PRO(Generation.M1, "PRO", 120000),
    M1_MAX(Generation.M1, "MAX", 150000),
    M1_ULTRA(Generation.M1, "ULTRA", 200000),
    M2(Generation.M2, "", 100000),
    M2_PRO(Generation.M2, "PRO", 140000),
    M2_MAX(Generation.M2, "MAX", 180000),
    M2_ULTRA(Generation.M2, "ULTRA", 240000),
    M3(Generation.M3, "", 120000),
    M3_PRO(Generation.M3, "PRO", 160000),
    M3_MAX(Generation.M3, "MAX", 200000),
    M3_ULTRA(Generation.M3, "ULTRA", 260000);

    private final Generation generation;
    private final String modelName;
    private final int price;

    Model(Generation generation, String modelName, int price) {
        this.generation = generation;
        this.modelName = modelName;
        this.price = price;
    }

    public Generation getGeneration() {
        return generation;
    }

    public String getModel() {
        return modelName;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        if (modelName.isEmpty()) {
            return price + " руб.";
        }
        return modelName + " - " + price + " руб.";
    }
    }
