package sample;

public class InHouse extends Part {

    private  int machineId = 0;
/**
 * Supplied class InHouse.java
 */

    /**
     *
     * @author Robert Bradbury
     */

    public  InHouse(int id, String name, double price, int stock, int min, int max,int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
        System.out.println("I am from the In-House  Class " + this.getName() + " "  + this.getId() + " "  + this.getPrice() + " "  + this.getStock() + " "  + this.getMin() + " "  + this.getMax() + " " + this.getMachineId() );
    }

    /**
     * @param machineId the machineId to set
     */
    public void setMachineId(int machineId){
        this.machineId=machineId;
    }

    /**
     * @return the machineId
     */
    public int getMachineId(){
        return machineId;
    }


}
