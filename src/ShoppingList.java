import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ShoppingList {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		Map<String, Double> items = new HashMap<>();
		
		items.put("Apple", 0.99);
		items.put("Banana", 0.59);
		items.put("Cauliflower", 1.59);
		items.put("Dragonfruit", 2.19);
		items.put("Elderberry", 1.79);
		items.put("Figs", 2.09);
		items.put("Grapefruit", 1.99);
		items.put("Honeydew", 3.49);
		
		
		ArrayList<String> itemSelection = new ArrayList<>();
		ArrayList<Double> itemPrice = new ArrayList<>();
		ArrayList<Integer> quantity = new ArrayList<>();
		
		System.out.println("Welcome to Grand Circus Market!");
		
		boolean isValid = false;
		do {
		System.out.println("Item\t\tPrice");
		System.out.println("==========================");
		int counter = 1;
		for (Map.Entry<String, Double> my : items.entrySet()) {
			System.out.println(counter + ". " + my.getKey() + "  \t$" + my.getValue());
			counter++;
		}
		
		System.out.println("What item would you like to order? ");
		String itemInput = scnr.nextLine();
		
		//validate proper input
		while(!items.containsKey(itemInput)) {
			System.out.println("Sorry, we dont have those. Please try again. ");
			counter = 1;
			for (Map.Entry<String, Double> my : items.entrySet()) {
				System.out.println(counter + ". " + my.getKey() + "  \t$" + my.getValue());
				counter++;
			}
			System.out.println("What item would you like to order? ");
			itemInput = scnr.nextLine();
		}
		//System.out.println(items.get(itemInput));
		int amount = 0;
		boolean valid = false;
		while(!valid) {
			try {
				System.out.println("How many " + itemInput + " would you like? ");
				amount = scnr.nextInt();
			} catch (InputMismatchException ex) {
				System.out.println("That is not a quantity, try again.");
				scnr.nextLine();
				continue;
			}
			valid = true;
		}
		//Add items to separate Arrays
		itemSelection.add(itemInput);
		itemPrice.add(items.get(itemInput));
		quantity.add(amount);
		
		System.out.println("Adding (" + amount + ")" + itemInput + " to cart at $" + items.get(itemInput));
		scnr.nextLine();
		
		System.out.println("Would you like to order anything else? (y/n): ");
		String userInput = scnr.nextLine();
		if(userInput.equalsIgnoreCase("y")) {
			isValid = true;
		} else {
			isValid = false;
		}
		}while(isValid);
		
		System.out.println("Thanks for your order!\nHere's what you got:");
		for (int i = 0; i < itemSelection.size(); i++) {
			System.out.println(itemSelection.get(i) + "     \t$" + itemPrice.get(i) + "(" + quantity.get(i) + ")");
		}
		
		System.out.println("Average price per item in order was " + df2.format(getAverage(itemPrice, quantity)));
		getHighest(itemPrice, itemSelection);
		getLowest(itemPrice, itemSelection);
		getTotal(itemPrice, quantity	);
		
	}//main
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	public static double getAverage(ArrayList<Double> nums, ArrayList<Integer> quant) {
		double average = 0;
		double sum = 0;
		int total = 0;
		for (int i = 0; i < nums.size(); i++) {
			sum += (nums.get(i) * quant.get(i));
			total += quant.get(i);
		}
		average = sum / total;
		return average;
	}
	
	public static void getHighest(ArrayList<Double> nums, ArrayList<String> string) {
		double highestNum = nums.get(0);
		String temp = "";
		for (int i = 0; i < nums.size(); i++) {
			if(nums.get(i) > highestNum) {
				highestNum = nums.get(i);
				temp = string.get(i);
			}else {
			}
		}
		System.out.println("Highest priced item was " + temp + " at $" + df2.format(highestNum));
	}
	
	public static void getLowest(ArrayList<Double> nums, ArrayList<String> string) {
		double lowestNum = nums.get(0);
		String temp = "";
		for (int i = 0; i < nums.size(); i++) {
			if(nums.get(i) < lowestNum) {
				lowestNum = nums.get(i);
				temp = string.get(i);
			} else {
			}
		}
		System.out.println("Lowest priced item was " + temp + " at $" + df2.format(lowestNum));
	}
	
	public static void getTotal(ArrayList<Double> nums, ArrayList<Integer> quantity) {
		double total = 0;
		for (int i = 0; i < nums.size(); i++) {
			total += nums.get(i) * quantity.get(i);
		}
		System.out.println("Order total is: $" + df2.format(total));
	}

}//class
