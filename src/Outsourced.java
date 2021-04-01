package sample;

public class Outsourced  extends Part {
    private String companyName;
/**
 * Supplied class Outsourced.java
 */

    /**
     *
     * @author Robert Bradbury
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max,String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName=companyName;
        System.out.println("I am from the OutSourced  Class " + this.getName() + " "  + this.getId() + " "  + this.getPrice() + " "  + this.getStock() + " "  + this.getMin() + " "  + this.getMax() + " " + this.getCompanyName() );
        //this.companyName= companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName){ this.companyName=companyName;}
    /**
     * @return the companyName
     */
    public String getCompanyName(){
        return companyName;
    }

}