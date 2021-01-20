package ohmypatt.patt.behavioral.memento.foodorder.model;

public class Order {
    private Menu menu;
    private int quantity;

    public Order(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public void execute() {
        System.out.printf("Executing %s (x%d)...\n", menu.getName(), quantity);
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return String.format("%s (Rp%d x %d)",
                    menu.getName(), menu.getPrice(), quantity);
    }
}