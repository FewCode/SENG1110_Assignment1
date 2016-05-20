import java.io.IOException;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;


//TODO: make sure comments
//TODO: scanner use must use "nextLine" rather than "nextInt" or "nextString"
/**
 * @author Alexander Brown
 * @studentID 3260691
 * @date 22/04/2016
 * @file StarberksInterface.java
 * Interface for the program. Controls flow and deals with Input and Output
 */
public class StarberksInterface {
	Scanner scanner = new Scanner(System.in);
	private Store[] stores;
	
	boolean exitProgram = false;

	private void run() {
		// Print welcome message
		System.out.println("Welcome!");

		// initiate program
		stores = new Store[2];
		stores[0] = new Store("callaghan");
		stores[1] = new Store("lambton");

		while (!exitProgram) {

			// show the menu to the user
			displayMenu();

			switch (waitForInput(5, true)){
				case 1:
					runSecondTier(chooseStore());
					break;
				case 2:
					//TODO: Implement - Display Stores
					break;
				case 3:
					//TODO: Implement - Open
					break;
				case 4:
					//TODO: Implement - Save
					break;
				case 5:
					exitProgram();
					break;
					
			}
		}
	}

	private void runSecondTier(int storeToUse){
		boolean exitStore = false;
		while (!exitStore) {
			displayStoreMenu();
			//TODO: implement menu
			switch (waitForInput(5, true)) {
				case 1:
					inputProductData(storeToUse);
					break;
				case 2:
					deleteProduct(storeToUse);
					break;
				case 3:
					showProductData(storeToUse);
					break;
				case 4:
					showAllProductData(storeToUse);
					break;
				case 5:
					exitStore = true;
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
		String input = "";
		while (input.isEmpty()) {
			input = scanner.nextLine();
		}
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

	private void inputProductData(int storeToUse) {
		boolean exitProductDataLoop = false;
		while (!exitProductDataLoop) {
			System.out.print("Enter product name: ");
			String pName = scanner.nextLine().toLowerCase();
			System.out.println("User typed: \"" + pName + "\"");
			if (pName.length() < 3 || pName.length() > 10) {
				System.out.println("\"" + pName + "\" has " + pName.length() + " characters. The product name must have between 3 and 10 characters.");
			} else {
				int productNumber = stores[storeToUse].findProduct(pName);
				if (productNumber >= 0) {
					// found product, start rename or edit procedure
					System.out.print("Found product \"" + pName + "\". ");
					EditOrRenameProduct(storeToUse, productNumber);

					exitProductDataLoop = true;
				} else {
					// could not find product, add new product
					productNumber = stores[storeToUse].addProduct(pName);
					editProductData(storeToUse, productNumber, false);
					exitProductDataLoop = true;
				} 
			}
		}
	}

	private void EditOrRenameProduct(int storeToUse, int productNumber) {
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
						String pName = scanner.nextLine().toLowerCase();

						if (pName.length() < 3 || pName.length() > 10) {
							System.out.println("\"" + pName + "\" has " + pName.length() + " characters. The product name must have between 3 and 10 characters.");
						} else {
							stores[storeToUse].setProductName(productNumber, pName);
							validInput = true;
						}
					}
					editOrRename = false;
					break;
				case 2:
					// edit data
					editProductData(storeToUse, productNumber, true);
					editOrRename = false;
					break;
				case 3:
					// Back to main menu
					editOrRename = false;
					break;
			}

		}
	}

	private void editProductData(int storeToUse, int product, boolean outputCurrentValues) {
		if (product >= 0) {
			boolean validEOQ = false;
			do {
				//if we are outputting data. get the default value, set it so we can use it in a prompt later. If not, just set to -1
				int demandRate = outputCurrentValues ? stores[storeToUse].getDemandRate(product) : -1;
				double setupCost = outputCurrentValues ? stores[storeToUse].getSetupCost(product) : -1;
				double unitCost = outputCurrentValues ? stores[storeToUse].getUnitCost(product) : -1;
				double inventoryCost = outputCurrentValues ? stores[storeToUse].getInventoryCost(product) : -1;
				double sellingPrice = outputCurrentValues ? stores[storeToUse].getSellingPrice(product) : -1;
				
				System.out.println("Editing product: " + stores[storeToUse].getProductName(product));
				
				//get data from users
				stores[storeToUse].setDemandRate(product, (int) Math.round(getValidDouble("Demand Rate", demandRate)));
				stores[storeToUse].setSetupCost(product, getValidDouble("Setup Cost", setupCost));
				stores[storeToUse].setUnitCost(product, getValidDouble("Unit Cost", unitCost));
				stores[storeToUse].setInventoryCost(product, getValidDouble("Inventory Cost", inventoryCost));
				stores[storeToUse].setSellingPrice(product, getValidDouble("Selling Price", sellingPrice));
				
				//validate EOQ
				if (stores[storeToUse].calculateEOQ(product) > stores[storeToUse].getDemandRate(product)){
					validEOQ = true;
				} else {
					System.out.println("It is not possible to have a replacement strategy with the inputs given. Please enter the data again.");
				}
				
			} while (!validEOQ);
			
		}
	}

	
	private void deleteProduct(int storeToUse) {
		if (stores[storeToUse].numberOfProducts() <= 0) {
			System.out.println("No products");
		} else {
			System.out.println("Products: " + stores[storeToUse].getProductNames());
			System.out.print("Please input the product name you want to delete: ");
			
			String input = scanner.nextLine().toLowerCase();
			int product = stores[storeToUse].findProduct(input);
			if (product >= 0) {
				
				stores[storeToUse].removeProduct(product);
				System.out.println("The product was deleted");
			} else {
				System.out.println("The product does not exist");
			}
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
			String userInput = scanner.nextLine();
			
			try {
				Double input = Double.parseDouble(userInput);
				// validate input
				if (input >= 0) {
					return input;
				} else {
					System.out.println(input + " is not a valid input!");
				}
			} catch (Exception e) {
				System.out.println("Please input a valid number.");
			}
			
		}
		return 0;
	}

	/**
	 * walks through the process of showing product data to the user
	 */
	private void showProductData(int storeToUse) {
		// make sure there are products to show
		if (stores[storeToUse].numberOfProducts() <= 0) {
			System.out.println("No products");
		} else {
			boolean exitProductDataLoop = false;
			while (!exitProductDataLoop) {
				System.out.println("Products: " + stores[storeToUse].getProductNames());
				System.out.print("Please input the product name you want to display: ");
				
				String pName = scanner.nextLine().toLowerCase();

				// validate product
				int productNumber = stores[storeToUse].findProduct(pName);
				if (productNumber < 0) {
					System.out.println("Could not find product \"" + pName + "\", please try again.");
				} else {
					outputProductData(storeToUse, productNumber);
					
					showReplenishStrategy(storeToUse, productNumber);
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
	private void outputProductData(int storeToUse, int productNumber) {
		System.out.println("Data for product \"" + stores[storeToUse].getProductName(productNumber) + "\"");
		System.out.println("----------------------------");
		System.out.println("Demand Rate: " + stores[storeToUse].getDemandRate(productNumber));
		System.out.println("Setup Cost: " + stores[storeToUse].getSetupCost(productNumber));
		System.out.println("Unit Cost: " + stores[storeToUse].getUnitCost(productNumber));
		System.out.println("Inventory Cost: " + stores[storeToUse].getInventoryCost(productNumber));
		System.out.println("Selling Price: " + stores[storeToUse].getSellingPrice(productNumber));

	}
	
	private void showAllProductData(int storeToUse) {
		//Display all products in a table
		// make sure there are products to show
		if (stores[storeToUse].numberOfProducts() <= 0) {
			System.out.println("No products");
		} else {
			System.out.printf("%11s | %6s | %10s | %9s | %14s | %13s\n", "Name", "Demand", "Setup Cost", "Unit Cost", "Inventory Cost", "Selling Price");
			System.out.printf("%11s-+-%6s-+-%10s-+-%9s-+-%14s-+-%13s\n", "-----------", "------", "----------", "---------", "--------------", "-------------");
			for (int i = 0; i < stores[storeToUse].numberOfPossibleProducts(); i++) {
				if (stores[storeToUse].doesProductExist(i)) {
					String pName = stores[storeToUse].getProductName(i);
					int pDemand = stores[storeToUse].getDemandRate(i);
					double pSetupCost = stores[storeToUse].getSetupCost(i);
					double pUnitCost = stores[storeToUse].getUnitCost(i);
					double pInventoryCost = stores[storeToUse].getInventoryCost(i);
					double pSellingPrice = stores[storeToUse].getSellingPrice(i);
					System.out.printf("%11s | %6s | %10s | %9s | %14s | %13s\n", pName, pDemand, pSetupCost, pUnitCost, pInventoryCost, pSellingPrice);
				}
			}
			System.out.println("Press Enter to Continue...");
			try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}	
			
		}
		
		
	}

	private void showReplenishStrategy(int storeToUse, int product) {
		//TODO: test this lots
		if (stores[storeToUse].numberOfProducts() <= 0) {
			System.out.println("There is no Product Data available");
		} else {
			// prompt the user for data then retrieve it
			System.out.print("Would you like to see the replacement strategy for this product? ");
			String pName = scanner.nextLine().toLowerCase();
			
			//TODO: own method?
			boolean show = false;
			switch (pName) {
				case "1":
				case "y":
				case "yes":
				case "affirmative": 
					show = true;
			}
			
			if (show) {
				replenishStrategy(storeToUse, product, (int) getValidDouble("Weeks", -1));
			}
		}
	}

	/**
	 * Calculates replenish strategy and displays a table of the strategy to the user
	 * @param productNumber the product to use
	 * @param weeks the number of weeks this should continue
	 */
	private void replenishStrategy(int storeToUse, int productNumber, int weeks) {
		int EOQ = stores[storeToUse].calculateEOQ(productNumber);
		int demandRate = stores[storeToUse].getDemandRate(productNumber);
		int inventory = 0;
		int totalOrder = 0;
		int totalOrders = 0;
		int totalInventory = 0;
		
		//display top of table
		System.out.printf("%5s | %14s | %6s | %9s\n", "Week", "Quantity Order", "Demand", "Inventory");
		System.out.printf("%5s-+-%14s-+-%6s-+-%9s\n", "----", "--------------", "------", "---------");
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
		
		
		
		double purchasePrice = (stores[storeToUse].getSetupCost(productNumber) * totalOrders) + totalOrder * stores[storeToUse].getUnitCost(productNumber);
		double inventoryCost = totalInventory * stores[storeToUse].getInventoryCost(productNumber);
		double totalCost = purchasePrice + inventoryCost;
		System.out.println("Total Cost: $" + totalCost);
		
		double profit = (demandRate * weeks * stores[storeToUse].getSellingPrice(productNumber)) - totalCost;
		stores[storeToUse].setProductProfit(productNumber, profit);
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
			editProductData(storeToUse, productNumber, true);
		}
	}

	/**
	 * Indicates that the program should be closed and shows the best
	 * replacement strategy
	 */
	private void exitProgram() {
		exitProgram = true;

		System.out.println("Thank you for using our program!");

		//TODO: check if needed.
		//get the most
		for (int i  = 0; i < stores.length; i++) {
			int mostProfitableProduct = stores[i].getMostProfitableProduct();
			while (mostProfitableProduct > 0) {
				String productName = stores[i].getProductName(mostProfitableProduct);
				double profit = stores[i].getProfit(mostProfitableProduct);
				System.out.println(productName + " was the most profitable product " + stores[i].NAME + " with $" + (new DecimalFormat("#.##")).format(profit));
				
				//check for another round
				mostProfitableProduct = stores[i].getMostProfitableProduct(mostProfitableProduct);
			}
		}
	}
	
	private int chooseStore(){
		boolean validInput = false;
		int chosenStore = -1;
		
		//start loop to get user input
		while (!validInput) {
			
			//ask the user for the store
			System.out.print("Enter store to chose {" + stores[0].NAME + "," + stores[1].NAME + "}: ");
			String storeToPick = scanner.nextLine().toLowerCase();
			
			//validate input
			if (stores[0].NAME.equals(storeToPick)) {
				chosenStore = 0;
				validInput = true;
			} else if (stores[1].NAME.equals(storeToPick)) {
				chosenStore = 1;
				validInput = true;
			} else {
				//invalid input - error
				System.out.println("Please enter a valid store.");
			}
		}
		
		//return the store that the user chose
		return chosenStore;
	}
	
	/**
	 * Displays the main menu to the user when called
	 */
	private void displayMenu() {
		System.out.println("1.\tChoose Store");
		System.out.println("2.\tDisplay stores");
		System.out.println("3.\tOpen");
		System.out.println("4.\tSave");
		System.out.println("5.\tExit");
	}
	
	private void displayStoreMenu() {
		System.out.println("1.\tAdd/Edit product");
		System.out.println("2.\tDelete product");
		System.out.println("3.\tDisplay product");
		System.out.println("4.\tDisplay all products");
		System.out.println("5.\tExit Store");
	}

	public static void main(String[] args) {
		StarberksInterface intFace = new StarberksInterface();
		intFace.run();
	}
}
