package Unit_5;

public abstract class MenuItemDecorator implements IMenuItem {

    protected IMenuItem item;

    public MenuItemDecorator(IMenuItem item) {
        super();
        this.item = item;
    }
}
