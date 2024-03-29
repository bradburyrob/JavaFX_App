package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Supplied class Product.java
 */

/**
 *
 * @author Robert Bradbury
 */
public class Product {

    private  ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int ID;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product()
    {
        this.ID = 0;
        this.name = "";
        this.price = 0;
        this.stock=0;
        this.min=0;
        this.max=0;
    }

    public Product(int ID, String name, double price,int stock , int min, int max)
    {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.min=min;
        this.max=max;
        this.stock = stock;
    }

    public Product(int ID, String name, double price,int stock , int min, int max, ObservableList associatedParts)
    {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.min=min;
        this.max=max;
        this.stock = stock;
        this.associatedParts = associatedParts;
    }


    //////////////////////////////////////
    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }
    //////////////////////////////////////
    public String getName() {return name;}
    public void setName(String name) {this.name = name; }
    //////////////////////////////////////
    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}
    //////////////////////////////////////
    public int getStock() {return stock;}
    public void setStock(int stock) {this.stock = stock;}
    //////////////////////////////////////
    public int getMin() {return min;}
    public void setMin(int min) {this.min = min;}
    //////////////////////////////////////
    public int getMax() {return max;}
    public void setMax(int max) {this.max = max;}

    public  ObservableList<Part> getAllAssociatedParts() {
        return this.associatedParts;
    }

    public  ObservableList addAssociatedPart (Part part) {
        associatedParts.add(part);
        return  associatedParts;

    }

}
