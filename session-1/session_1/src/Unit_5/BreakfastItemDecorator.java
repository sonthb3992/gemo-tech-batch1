package Unit_5;

public abstract class BreakfastItemDecorator extends BreakfastItem {
    protected IMenuItem item;
    public BreakfastItemDecorator(IMenuItem item) {
        super();
        this.item = item;
    }
    
}
