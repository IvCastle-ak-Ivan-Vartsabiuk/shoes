package shoesorter;

public class Shoe {
    private int number;
    private String name;
    private int size;
    private double price;

    public Shoe(int number, String name, int size, double price) {
        this.number = number;
        this.name = name;
        this.size = size;
        this.price = price;
    }

    public int getNumber() { return number; }
    public String getName() { return name; }
    public int getSize() { return size; }
    public double getPrice() { return price; }
}
