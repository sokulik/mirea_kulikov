package Practice5_10.Enums;

public enum M_name {
    // Phillips
    Evnia(M_Brand.Phillips, "Evnia", "32\"", "165Hz", 45000),
    Brilliance(M_Brand.Phillips, "Brilliance", "27\"", "60Hz", 25000),
    E_Line(M_Brand.Phillips, "E-Line", "24\"", "75Hz", 15000),
    S_Line(M_Brand.Phillips, "S-Line", "22\"", "60Hz", 12000),
    B_Line(M_Brand.Phillips, "B-Line", "24\"", "60Hz", 13000),
    P_Line(M_Brand.Phillips, "P-Line", "27\"", "75Hz", 18000),

    // Samsung
    Odyssey(M_Brand.Samsung, "Odyssey", "34\"", "165Hz", 55000),
    Smart_Monitor(M_Brand.Samsung, "Smart Monitor", "32\"", "60Hz", 35000),
    UR59(M_Brand.Samsung, "UR59", "32\"", "60Hz", 28000),
    ViewFinity(M_Brand.Samsung, "ViewFinity", "27\"", "75Hz", 22000),
    S8(M_Brand.Samsung, "S8", "32\"", "60Hz", 40000),
    CF39(M_Brand.Samsung, "CF39", "24\"", "60Hz", 14000),

    // Acer
    Nitro(M_Brand.Acer, "Nitro", "27\"", "144Hz", 30000),
    Predator(M_Brand.Acer, "Predator", "34\"", "180Hz", 60000),
    ConceptD(M_Brand.Acer, "ConceptD", "27\"", "60Hz", 32000),
    Vero(M_Brand.Acer, "Vero", "24\"", "75Hz", 16000),
    B_Series(M_Brand.Acer, "B Series", "24\"", "60Hz", 13000),
    R_Series(M_Brand.Acer, "R Series", "27\"", "75Hz", 19000),

    // Asus
    ROG_Swift(M_Brand.Asus, "ROG Swift", "27\"", "240Hz", 50000),
    TUF_Gaming(M_Brand.Asus, "TUF Gaming", "24\"", "144Hz", 22000),
    ProArt(M_Brand.Asus, "ProArt", "32\"", "60Hz", 45000),
    Designo(M_Brand.Asus, "Designo", "27\"", "60Hz", 20000),
    ZenScreen(M_Brand.Asus, "ZenScreen", "15.6\"", "60Hz", 12000),
    VG_Series(M_Brand.Asus, "VG Series", "24\"", "144Hz", 18000),

    // HP
    Pavilion(M_Brand.HP, "Pavilion", "24\"", "75Hz", 17000),
    Omen(M_Brand.HP, "Omen", "27\"", "165Hz", 38000),
    Z_Display(M_Brand.HP, "Z Display", "32\"", "60Hz", 42000),
    E_Series(M_Brand.HP, "E Series", "24\"", "60Hz", 14000),
    P_Series(M_Brand.HP, "P Series", "27\"", "75Hz", 21000),
    EliteDisplay(M_Brand.HP, "EliteDisplay", "24\"", "60Hz", 16000);

    private final M_Brand m_brand;
    private final String m_name;
    private final String size;        // переименовано с m_diag
    private final String refreshRate; // переименовано с m_hertz
    private final int price;

    M_name(M_Brand m_brand, String m_name, String size, String refreshRate, int price){
        this.m_brand = m_brand;
        this.m_name = m_name;
        this.size = size;
        this.refreshRate = refreshRate;
        this.price = price;
    }

    public M_Brand getBrand() {  // переименовано с getM_brand()
        return m_brand;
    }

    public String getName() {    // переименовано с getM_name()
        return m_name;
    }

    public String getSize() {    // новый метод
        return size;
    }

    public String getRefreshRate() { // новый метод
        return refreshRate;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString(){
        return m_name + " " + size + " " + refreshRate + " - " + price + " руб.";
    }
}