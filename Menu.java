public abstract class Menu {
	public static String[] menu;
    public static double[] cost;
    public static int[] numFood;
    
    
    public Menu(){
    	
        menu = new String[5];
        menu[0] = "1. Hamburger ($5.00)\n";
        menu[1] = "2. French Fries ($3.00)\n";
        menu[2] = "3. Soda ($1.50)\n";
        menu[3] = "4. Milkshake ($2.00)\n";
        menu[4] = "5. Salad ($3.00)\n";
        
        cost = new double[5];
        cost[0] = 5.00;
        cost[1] = 3.00;
        cost[2] = 1.50;
        cost[3] = 2.00;
        cost[4] = 3.00;
        
}
      
      
    @Override
    public String toString()
    {
    	String s = "";
    	for (int i = 0; i < menu.length; i++) {
    		s += menu[i];
    	}
        return s;
    }

	public static String getMenu(int i) {
    	return menu[i - 1];
    }
	
	
    public abstract void displayMenu();
}
