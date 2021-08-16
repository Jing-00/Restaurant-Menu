import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class Order extends Menu implements OrderClean {
	private static int orderNum = 0;
	private static String fileN = "order.txt";
	private static double total;
	public Size size;
	private static String MESSAGE = "Which way do you want to order?\n\n1."
			+ "Order by product number\n2.Order by product name\n: ";
	ArrayList<String> menuArray = new ArrayList<String>(Arrays.asList(menu));

	public Order() {
		total = 0;
		size = new Size();
	}

	public void addOrderNumber() {
		++orderNum;
	}

	@Override
	public void displayMenu() {

		System.out.println(super.toString());
	}

	public static void makeOrder(Order con, Scanner in) {
		int numChoice, menuChoice, qtyChoice;
		String menuChoice1;
		boolean checker = true;
		System.out.print(MESSAGE);
		while (!in.hasNextInt()) {
			System.out.println("Enter only numbers 1 or 2!");
			System.out.print(MESSAGE);
			in.nextLine();
		}
		numChoice = in.nextInt();
		while (checker) {
			if (numChoice == 1) {
				System.out.println("You chose to order by product number.");

				con.size.displayMenu();
				Scanner in1 = new Scanner(System.in);
				System.out.print("Enter the number for the item that you would like to buy: ");
				while (!in1.hasNextDouble()) {
					System.out.println("Enter numbers only!");
					System.out.print("Enter the number for the item that you would like to buy: ");
					in1.nextLine();
				}
				menuChoice = in1.nextInt();
				while (menuChoice < 1 || menuChoice > 5) {
					System.out.print("Enter a number from 1 to 5: ");
					menuChoice = in1.nextInt();
				}
				String str = getMenu(menuChoice);
				System.out.print("Enter the quantity of " + str.substring(3, str.indexOf("(")) + "from 1 to 20: ");
				while (!in1.hasNextDouble()) {
					System.out.println("Enter numbers only!");
					System.out.print("Enter the quantity again: ");
					in1.nextLine();
				}
				qtyChoice = in1.nextInt();
				while (qtyChoice < 1 || qtyChoice > 20) {
					System.out.print("Enter a number from 1 to 20: ");
					qtyChoice = in1.nextInt();
				}
				con.size.addNumItems(qtyChoice);
				con.size.addOrderCost(qtyChoice * cost[menuChoice - 1]);
				checker = false;
			} else if (numChoice == 2) {
				System.out.println("You chose to order by product name.");
				con.size.displayMenu();
				con.sortList();

				Scanner in2 = new Scanner(System.in);
				System.out.print("Enter the name of the item that you would like to buy: ");
				while (in2.hasNextDouble()) {
					System.out.println("Enter name only!");
					System.out.print("Enter the name of the item that you would like to buy: ");
					in2.nextLine();
				}
				menuChoice1 = in2.nextLine();
				String s = con.searchInList(menuChoice1);
				while (s.equals(null)) {
					System.out.println("Enter the food listed in the table.");
					System.out.print("Enter the name of the item that you would like to buy: ");
					in2.nextLine();
				}
				System.out.print(
						"Enter the quantity of " + s + " from 1 to 20: ");

				while (!in2.hasNextDouble()) {
					System.out.println("Enter numbers only!");
					System.out.print("Enter the quantity again: ");
					in2.nextLine();
				}
				qtyChoice = in2.nextInt();
				while (qtyChoice < 1 || qtyChoice > 20) {
					System.out.print("Enter a number from 1 to 20: ");
					qtyChoice = in2.nextInt();
				}

				con.size.addNumItems(qtyChoice);
				con.size.addOrderCost(qtyChoice * cost[con.searchIndex(menuChoice1)]);
				checker = false;
			} else {
				System.out.println("Enter only numbers 1 or 2!");
				System.out.print("Enter the number again: ");
				numChoice = in.nextInt();
			}
		}
	}

	public String searchInList(String s) {
		for (int i = 0; i < menuArray.size(); i++) {
			String str = menuArray.get(i);
			int lastIndex = str.lastIndexOf(" ");
			if (s.toLowerCase().contains(str.substring(3, lastIndex).toLowerCase())) {
				return str.substring(3, lastIndex);
			}
		}
		return null;
	}

	public int searchIndex(String s) {
		ArrayList<String> menuArray = new ArrayList<String>(Arrays.asList(menu));
		for (int i = 0; i < menuArray.size(); i++) {
			String str = menuArray.get(i);
			int lastIndex = str.lastIndexOf(" ");
			if (s.toLowerCase().contains(str.substring(3, lastIndex).toLowerCase())) {
				return i;
			}
		}
		return -1;
	}

	public void sortList() {
		menuArray.sort(null);
	}

	public static void readFile(Scanner in) {
		int i = 0;
		while (in.hasNextDouble()) {
			total += in.nextDouble();
			i++;
		}
		System.out.print("\nYou have made " + i + " orders");

	}

	public void orderInformation() {
		System.out.println("Order for: " + size.getCustomerName() + "!");
		System.out.println("Your order number is " + (Order.orderNum + 1) + ".");
		System.out.println("You have ordered " + size.getNumItems() + " items from our menu.");
		System.out.println("The total cost of your order comes out to be $" + size.getOrderCost() + "."); // add
																											// decimalFormat
	}

	public static void main(String[] args) {
		String choice = "";
		String name = "";
		Scanner input = new Scanner(System.in);
		ArrayList<Order> list = new ArrayList<Order>();
		Order consumer = new Order();
		PrintWriter out = null;

		try {
			File newfile = new File(fileN);
			out = new PrintWriter(newfile);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.print("Welcome to the Tantalus Restaurant!\n");
		do {
			System.out.print("Please enter your name: ");
			if (orderNum > 0) {
				input.nextLine();
			}
			name = input.nextLine();
			consumer.size.setCustomerName(name);
			System.out.println("Please make an order below: ");
			do {
				makeOrder(consumer, input);
				do {
					System.out.print("Enter y to buy more, or enter n to quit: ");
					choice = input.next();
				} while (!(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n")));

			} while (!(choice.equalsIgnoreCase("n")));
			consumer.orderInformation();
			list.add(consumer);
			out.println(consumer.size.getOrderCost());
			do {
				System.out.print("Would you like to make another order?(y/n) ");
				choice = input.nextLine();
			} while (!(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n")));
			consumer.size.setNumItems(0);
			consumer.size.setOrderCost(0);
			consumer.addOrderNumber();

		} while (!(choice.equalsIgnoreCase("n")));

		out.close();

		try {
			File infile = new File(fileN);
			input = new Scanner(infile);
			readFile(input);
		} catch (IOException e) {
			System.out.println(e);
		}

		System.out.print("\nThe total of all the orders are $" + total + "\nGoodbye!");


		input.close();
	}
}
