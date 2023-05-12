package Unit_5;

public class Sandwich extends BreakfastItem {

    public Sandwich() {
        super();
        this.basePrice = 3;
    }

    @Override
    public String getDesc() {
        return "Sandwich";
    }    
}
