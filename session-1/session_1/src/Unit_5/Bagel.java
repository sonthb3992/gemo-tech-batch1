package Unit_5;

public class Bagel extends BreakfastItem {

    public Bagel() {
        super();
        this.basePrice = 3;
    }

    @Override
    public String getDesc() {
        return "Bagel";
    }
    
}
