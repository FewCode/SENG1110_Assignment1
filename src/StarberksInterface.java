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
	
	private void run()
	{
		System.out.println("Welcome!");
	
		store = new Store();
		
		while (!exitProgram) {
			displayMenu();
			
			switch (waitForInput(4, true)) {
				case 1:
					inputProductData();
					break;
				case 2:
					showProductData();
					break;
				case 3:
					showReplentishStrategy();
					break;
				case 4:
					exitProgram();
					break;
			}
			
		}
	}

	/**
	 * @param options - the amount of options that you wish to display with a minimum of 2 and a maximum of 9
	 * @param exitClause - if the last option is the exit clause
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
				//option 2
				chosenNumber = 2;
				break;
			case "3":
			case "three":
			case "3rd":
				//option 3
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
				int productNumber = store.FindProduct(pName);
				if (productNumber > 0) {
					//found product, start rename or edit procedure
					System.out.print("Found product \"" + pName + "\". ");
					EditOrRenameProduct(productNumber);
					
					exitProductDataLoop = true;
				} else if (store.AvailableProducts() > 0) {
					//could not find product, add new product
					productNumber = store.CreateProduct(store.NextEmptyProduct(), pName);
					editProductData(productNumber, false);
					exitProductDataLoop = true;
				} else {
					//No more room for products, remove product or main menu
					System.out.println("There is no more room in the product database, what would you like to do?");
					System.out.println("1.\tRemove product 1: " + store.getProductName(1));
					System.out.println("2.\tRemove product 2: " + store.getProductName(2));
					System.out.println("3.\tRemove product 3: " + store.getProductName(3));
					System.out.println("4.\tBack to Main Menu");
					switch (waitForInput(4, true)){
						case 1:
							store.RemoveProduct(1);
							productNumber = store.CreateProduct(1, pName);
							editProductData(productNumber, false);
							break;
						case 2:
							store.RemoveProduct(2);
							productNumber = store.CreateProduct(2, pName);
							editProductData(productNumber, false);
							break;
						case 3:
							store.RemoveProduct(3);
							productNumber = store.CreateProduct(3, pName);
							editProductData(productNumber, false);
							break;
						case 4:
							exitProductDataLoop = true;
							break;
					}
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
					//edit name
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
					//edit data
					editProductData(productNumber, true);
					editOrRename = false;
					break;
				case 3:
					//Back to main menu
					editOrRename = false;
					break;
			}
			
		}
	}
	
	private void editProductData(int product, boolean outputCurrentValues) {
		if (product > 0) {
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
		}
	}
	
	private double getValidDouble(String valueName, double currentValue) {
		boolean validInput = false;
		while (!validInput) {
			String output = "Enter " + valueName;
			if (currentValue != -1) {
				output += "(current: " + currentValue + ")";
			}
			output += ": ";
			
			System.out.print(output);
			Double input = scanner.nextDouble();
			if (input >= 0) {
				return input;
			} else {
				System.out.println(input + " is not a valid input!");
			}
		}
		return 0;
	}
	
	private void showProductData() {
		if (store.AvailableProducts() >= 3) {
			System.out.println("There is no Product Data available");
		} else {
			boolean exitProductDataLoop = false;
			while (!exitProductDataLoop) {
				System.out.print("Enter product name: ");
				String pName = scanner.next().toLowerCase();
				int productNumber = store.FindProduct(pName);

				if (productNumber <= 0) {
					System.out.println("Could not find product \"" + pName +"\", please try again.");
				} else {
					outputProductData(productNumber);
				}
			}
		}
	}

	private void outputProductData(int productNumber) {
		System.out.println("Data for product \"" + store.getProductName(productNumber) + "\"");
		System.out.println("----------------------------");
		System.out.println("Demand Rate: " + store.getDemandRate(productNumber));
		System.out.println("Setup Cost: " + store.getSetupCost(productNumber));
		System.out.println("Unit Cost: " + store.getUnitCost(productNumber));
		System.out.println("Inventory Cost: " + store.getInventoryCost(productNumber));
		System.out.println("Selling Price: " + store.getSellingPrice(productNumber));
		
	}

	//TODO: complete the method
	private void showReplentishStrategy() {
		if (store.AvailableProducts() >= 3) {
			System.out.println("There is no Product Data available");
		} else {
			
		}
	}

	private void exitProgram() {
		exitProgram = true;
		
		System.out.println("Thank you for using our program!");
		//TODO: exit program correctly
	}

	private void displayMenu(){
		System.out.println("1.\tInput data for one product");
		System.out.println("2.\tShow data from one product");
		System.out.println("3.\tShow the replentishment strategy for a product");
		System.out.println("4.\tExit program");
	}
	
	public static void main(String[] args)
	{
		StarberksInterface intFace = new StarberksInterface();
		intFace.run();
	}
}
