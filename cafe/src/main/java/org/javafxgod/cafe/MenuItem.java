package org.javafxgod.cafe;

public class MenuItem {
    private String name;
    private double price;
    private String imagePath;

    public MenuItem(String name, double price, String imagePath) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String toString() {
        return String.format("%-15s $%.2f", name, price);
    }
}
