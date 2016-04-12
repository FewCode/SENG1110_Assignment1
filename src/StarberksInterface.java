import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

/*
Creates the interface (you can use TerminalIO or GUI, your choice).
It must have one Store object as instance variable.
This class will act as the interface for the program, meaning it will receive all input from the user, display all output, and check for invalid inputs & display all error messages. 
 */

public class StarberksInterface {
	Scanner scanner = new Scanner(System.in);
	private Store store;
	boolean exitProgram = false;

	private void run() {
		// Print welcome message
		System.out.println("Welcome!");

		// initiate program
		store = new Store();

		while (!exitProgram) {

			// show the menu to the user
			displayMenu();

			switch (waitForInput(4, true)) {
				case 1:
					inputProductData();
					break;
				case 2:
					showProductData();
					break;
				case 3:
					showReplenishStrategy();
					break;
				case 4:
					exitProgram();
					break;
			}

		}
	}

	/**
	 * @param options
	 *            - the amount of options that you wish to display with a
	 *            minimum of 2 and a maximum of 9
	 * @param exitClause
	 *            - if the last option is the exit clause
	 */
	private int waitForInput(int options, boolean exitClause) {
		System.out.print("Option: ");
		String input = scanner.next();
		System.out.println("you have chosen \"" + input + "\"");

		int chosenNumber = -1;

		switch (input.toLowerCase()) {
			case "1":
			case "one":
			case "1st":
				chosenNumber = 1;
				break;
			case "2":
			case "two":
			case "2nd":
				// option 2
				chosenNumber = 2;
				break;
			case "3":
			case "three":
			case "3rd":
				// option 3
				chosenNumber = 3;
				break;
			case "4":
			case "four":
			case "4th":
				chosenNumber = 4;
				break;
			case "5":
			case "five":
			case "5th":
				chosenNumber = 5;
				break;
			case "6":
			case "six":
			case "6th":
				chosenNumber = 6;
				break;
			case "7":
			case "seven":
			case "7th":
				chosenNumber = 7;
				break;
			case "8":
			case "eight":
			case "8th":
				chosenNumber = 8;
				break;
			case "9":
			case "nine":
			case "9th":
				chosenNumber = 9;
				break;
			case "exit":
			case "quit":
			case "close":
				if (exitClause) {
					chosenNumber = options;
				}
				break;
		}

		if (chosenNumber > 0 && chosenNumber <= options) {
			return chosenNumber;
		} else {
			System.out.println("\"" + input + "\" is not a valid input");
			return -1;
		}
	}

	private void inputProductData() {
		boolean exitProductDataLoop = false;
		while (!exitProductDataLoop) {
			System.out.print("Enter product name: ");
			String pName = scanner.next().toLowerCase();

			if (pName.length() < 3 || pName.length() > 10) {
				System.out.println("\"" + pName + "\" has " + pName.length() + " characters. The product name must have between 3 and 10 characters.");
			} else {
				int productNumber = store.findProduct(pName);
				if (productNumber > 0) {
					// found product, start rename or edit procedure
					System.out.print("Found product \"" + pName + "\". ");
					EditOrRenameProduct(productNumber);

					exitProductDataLoop = true;
				} else if (store.availableProducts() > 0) {
					// could not find product, add new product
					productNumber = store.createProduct(store.nextEmptyProduct(), pName);
					editProductData(productNumber, false);
					exitProductDataLoop = true;
				} else {
					// No more room for products, remove product or main menu
					System.out.println("There is no more room in the product database, what would you like to do?");
					System.out.println("1.\tRemove product 1: " + store.getProductName(1));
					System.out.println("2.\tRemove product 2: " + store.getProductName(2));
					System.out.println("3.\tRemove product 3: " + store.getProductName(3));
					System.out.println("4.\tBack to Main Menu");
					switch (waitForInput(4, true)) {
						case 1:
							store.removeProduct(1);
							productNumber = store.createProduct(1, pName);
							editProductData(productNumber, false);
							break;
						case 2:
							store.removeProduct(2);
							productNumber = store.createProduct(2, pName);
							editProductData(productNumber, false);
							break;
						case 3:
							store.removeProduct(3);
							productNumber = store.createProduct(3, pName);
							editProductData(productNumber, false);
							break;
					}
					exitProductDataLoop = true;
				}
			}
		}
	}

	private void EditOrRenameProduct(int productNumber) {
		boolean editOrRename = true;

		while (editOrRename) {
			System.out.println("How would you like to edit the product?");
			System.out.println("1.\tEdit Name");
			System.out.println("2.\tEdit Data");
			System.out.println("3.\tExit to Main Menu");

			switch (waitForInput(3, true)) {
				case 1:
					// edit name
					boolean validInput = false;
					while (!validInput) {
						System.out.print("Enter new product name: ");
						String pName = scanner.next().toLowerCase();

						if (pName.length() < 3 || pName.length() > 10) {
							System.out.println("\"" + pName + "\" has " + pName.length() + " characters. The product name must have between 3 and 10 characters.");
						} else {
							store.setProductName(productNumber, pName);
							validInput = true;
						}
					}
					editOrRename = false;
					break;
				case 2:
					// edit data
					editProductData(productNumber, true);
					editOrRename = false;
					break;
				case 3:
					// Back to main menu
					editOrRename = false;
					break;
			}

		}
	}

	private void editProductData(int product, boolean outputCurrentValues) {
		if (product > 0) {
			boolean validEOQ = false;
			do {
				int demandRate = outputCurrentValues ? store.getDemandRate(product) : -1;
				double setupCost = outputCurrentValues ? store.getSetupCost(product) : -1;
				double unitCost = outputCurrentValues ? store.getUnitCost(product) : -1;
				double inventoryCost = outputCurrentValues ? store.getInventoryCost(product) : -1;
				double sellingPrice = outputCurrentValues ? store.getSellingPrice(product) : -1;
				
				System.out.println("Editing product: " + store.getProductName(product));
				
				store.setDemandRate(product, (int) Math.round(getValidDouble("Demand Rate", demandRate)));
				store.setSetupCost(product, getValidDouble("Setup Cost", setupCost));
				store.setUnitCost(product, getValidDouble("Unit Cost", unitCost));
				store.setInventoryCost(product, getValidDouble("Inventory Cost", inventoryCost));
				store.setSellingPrice(product, getValidDouble("Selling Price", sellingPrice));
				
				if (store.calculateEOQ(product) > store.getDemandRate(product)){
					validEOQ = true;
				} else {
					System.out.println("It is not possible to have a replacement strategy with the inputs given. Please enter the data again.");
				}
				
			} while (!validEOQ);
			
		}
	}

	/**
	 * Gets a valid double from the user. The method will loop until this is
	 * achieved
	 * 
	 * @param valueName
	 *            the name of the value that you wish to print to the user
	 * @param currentValue
	 *            The value of any previous data that you wish to display to the
	 *            user
	 * @return A positive double
	 */
	private double getValidDouble(String valueName, double currentValue) {
		boolean validInput = false;
		while (!validInput) {

			// construct user prompt
			String output = "Enter " + valueName;
			if (currentValue != -1) {
				output += "(current: " + currentValue + ")";
			}
			output += ": ";

			// prompt the user for some input
			System.out.print(output);

			// get the input
			Double input = scanner.nextDouble();

			// validate input
			if (input >= 0) {
				return input;
			} else {
				System.out.println(input + " is not a valid input!");
			}
		}
		return 0;
	}

	/**
	 * walks through the process of showing product data to the user
	 */
	private void showProductData() {
		// make sure there are products to show
		if (store.availableProducts() >= store.AMOUNT_OF_PRODUCTS) {
			System.out.println("There is no Product Data available");
		} else {
			boolean exitProductDataLoop = false;
			while (!exitProductDataLoop) {

				// prompt the user for data then retrieve it
				System.out.print("Enter product name: ");
				String pName = scanner.next().toLowerCase();

				// validate product
				int productNumber = store.findProduct(pName);
				if (productNumber <= 0) {
					System.out.println("Could not find product \"" + pName + "\", please try again.");
				} else {
					outputProductData(productNumber);
					exitProductDataLoop = true;
				}
			}
		}
	}

	/**
	 * Outputs data to the user based on the associated productNumber
	 * 
	 * @param productNumber
	 *            the product to find data for
	 */
	private void outputProductData(int productNumber) {
		System.out.println("Data for product \"" + store.getProductName(productNumber) + "\"");
		System.out.println("----------------------------");
		System.out.println("Demand Rate: " + store.getDemandRate(productNumber));
		System.out.println("Setup Cost: " + store.getSetupCost(productNumber));
		System.out.println("Unit Cost: " + store.getUnitCost(productNumber));
		System.out.println("Inventory Cost: " + store.getInventoryCost(productNumber));
		System.out.println("Selling Price: " + store.getSellingPrice(productNumber));

	}

	private void showReplenishStrategy() {
		if (store.availableProducts() >= store.AMOUNT_OF_PRODUCTS) {
			System.out.println("There is no Product Data available");
		} else {
			boolean exitReplenishmentStrategyLoop = false;
			while (!exitReplenishmentStrategyLoop) {

				// prompt the user for data then retrieve it
				System.out.print("Enter product name: ");
				String pName = scanner.next().toLowerCase();

				// validate product
				int productNumber = store.findProduct(pName);
				if (productNumber <= 0) {
					System.out.println("Could not find product \"" + pName + "\", please try again.");
				} else {				
					replenishStrategy(productNumber, (int) getValidDouble("Weeks", -1));
					exitReplenishmentStrategyLoop = true;
				}
			}
		}
	}

	/**
	 * Calculates replenish strategy and displays a table of the strategy to the user
	 * @param productNumber the product to use
	 * @param weeks the number of weeks this should continue
	 */
	private void replenishStrategy(int productNumber, int weeks) {
		int EOQ = store.calculateEOQ(productNumber);
		int demandRate = store.getDemandRate(productNumber);
		int inventory = 0;
		int totalOrder = 0;
		int totalOrders = 0;
		int totalInventory = 0;
		
		//display top of table
		System.out.printf("%5s | %14s | %6s | %9s\n", "Week", "Quantity Order", "Demand", "Inventory");
		
		//looping the number of weeks
		for (int i = 0; i < weeks; i++) {
			int order = 0;
			
			if (inventory - demandRate < 0) {
				order = EOQ;
				int demandWeeks = demandRate * (weeks - i);
				if (order + inventory > demandWeeks) {
					order = demandWeeks - inventory;
				}
				inventory += order;
				totalOrder += order;
				totalOrders++;
			}
			inventory -= demandRate;
			totalInventory += inventory;
			
			System.out.printf("%5d | %14d | %6d | %9d\n", i + 1, order, demandRate, inventory);
		}
		
		
		
		double purchasePrice = (store.getSetupCost(productNumber) * totalOrders) + totalOrder * store.getUnitCost(productNumber);
		double inventoryCost = totalInventory * store.getInventoryCost(productNumber);
		double totalCost = purchasePrice + inventoryCost;
		System.out.println("Total Cost: $" + totalCost);
		
		double profit = (demandRate * weeks * store.getSellingPrice(productNumber)) - totalCost;
		store.setProductProfit(productNumber, profit);
		System.out.println("Profit: $" + (new DecimalFormat("#.##")).format(profit));
		System.out.println("Total Order: " + totalOrder);
		System.out.println("EOQ: " + EOQ);
		if (profit >= 0) {
			System.out.println("Press Enter to Continue...");
			
			try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		} else {
			System.out.println("Profit was negative, please re-enter product data.");
			editProductData(productNumber, true);
		}
		
	}

	/**
	 * Indicates that the program should be closed and shows the best
	 * replacement strategy
	 */
	private void exitProgram() {
		exitProgram = true;

		System.out.println("Thank you for using our program!");

		//get the most
		int mostProfitableProduct = store.getMostProfitableProduct();
		while (mostProfitableProduct > 0) {
			String productName = store.getProductName(mostProfitableProduct);
			double profit = store.getProfit(mostProfitableProduct);
			System.out.println(productName + " was the most profitable product with $" + (new DecimalFormat("#.##")).format(profit));
			
			//check for another round
			mostProfitableProduct = store.getMostProfitableProduct(mostProfitableProduct);
		}
			
		
	}

	/**
	 * Displays the main menu to the user when called
	 */
	private void displayMenu() {
		System.out.println("1.\tInput data for one product");
		System.out.println("2.\tShow data from one product");
		System.out.println("3.\tShow the replentishment strategy for a product");
		System.out.println("4.\tExit program");
	}

	public static void main(String[] args) {
		StarberksInterface intFace = new StarberksInterface();
		intFace.run();
	}
}
