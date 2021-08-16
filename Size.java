public class Size extends Menu implements Comparable<Size> {
    private int numItems;
    private double orderCost;
    private String customerName;
   
    Size(){
        super();
        numItems = 0;
        orderCost = 0;
        customerName = "";
    }
    
    Size(int items, double cost, String name){
        super();
        numItems = items;
        orderCost = cost;
        customerName = name;
    }
    
    @Override
    public void displayMenu(){
        
        System.out.println(super.toString());
    }

    public void setOrderCost(double cost){
        orderCost = cost;
    }
    
    public void setNumItems(int items){
        numItems = items;
    }
    
    public void setCustomerName(String name){
    	customerName = name;
    }
    
    public int getNumItems(){
        return numItems;
    }
    
    public double getOrderCost(){
        return orderCost;
    }
    
    public String getCustomerName(){
    	return customerName;
    }
    
    public void addNumItems(){
        numItems++;
    }
    
    public void addNumItems(int items) {
    	numItems += items;
    }
    public void addOrderCost(double cost){
        orderCost += cost;
    }
    
    public void orderInformation(){ 
        StringBuilder sb1 = new StringBuilder();

        String s = "You have ordered";
        sb1.append(s);

        System.out.print(sb1 +" "+numItems + " items from our menu.\n");
        sb1.replace(0,16, "The ");
        StringBuilder sb2 = new StringBuilder("total cost of your order ");
        sb1.append(sb2);
        sb1.append("comes out to be $" + getOrderCost() + ".");
        System.out.println(sb1);
    }
    
    @Override
    public int compareTo(Size o) {
        if(this.equals(o)){
            return 0;
        } else if(!(customerName.equals(o.customerName))){
            return customerName.compareTo(o.customerName);
        } else if(numItems != o.numItems){
            return Integer.compare(numItems, o.numItems);
        } else{
            return Double.compare(orderCost, o.orderCost);
        }
    }
    

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        } else if(!(o instanceof Size)){
            return false;
        } else{
            Size s = (Size) o;
            return (super.equals(o) && numItems == s.numItems && orderCost == s.orderCost
                    && customerName.equals(((Size) o).customerName));
        }
    }
}