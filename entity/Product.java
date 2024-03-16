package entity;

public class Product {

    private int referenceProduct;
    private String name;
    private double price;

    public Product(int referenceProduct, String name, double price) {
        this.referenceProduct = referenceProduct;
        this.name = name;
        this.price = price;
    }

    public int getReferenceProduct() {
        return referenceProduct;
    }

    public void setReferenceProduct(int referenceProduct) {
        this.referenceProduct = referenceProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
