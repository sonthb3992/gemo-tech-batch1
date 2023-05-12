package Unit_5;

public class SandwichDecorator extends BreakfastItemDecorator {

    private boolean hasTurkey;

    public void setHasTurkey(boolean hasTurkey) {
        this.hasTurkey = hasTurkey;
    }

    public SandwichDecorator(IMenuItem item) {
        super(item);
        this.basePrice = 3;
    }

    @Override
    public String getDesc() {
        String sandwichTopping = this.hasTurkey ? "turkey" : "egg";
        return String.format("%s, with %s", this.item.getDesc(), sandwichTopping);
    }

    @Override
    public double getPrice() {
        return this.item.getPrice() + (this.hasTurkey ? 1 : 0);
    }

}
