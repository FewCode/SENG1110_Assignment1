import java.util.Scanner;

/*
Creates the interface (you can use TerminalIO or GUI, your choice).
It must have one Store object as instance variable.
This class will act as the interface for the program, meaning it will receive all input from the user, display all output, and check for invalid inputs & display all error messages. 
 */
public class StarberksInterface {
	Scanner scanner = new Scanner(System.in);
	boolean exitProgram = false;
	
	private void run()
	{
		
		System.out.println("Welcome!");
		
		while (!exitProgram) {
			displayMenu();
			waitForInput();
		}
		
		
		
	}

	private void waitForInput() {
		System.out.print("Option: ");
		String input = scanner.next();
		System.out.println("you have chosen \"" + input + "\"");
		
		switch (input.toLowerCase()) {
			case "1":
			case "one":
			case "1st":
				//option 1
				inputProductData();
				break;
			case "2":
			case "two":
			case "2nd":
				//option 2
				showProductData();
				break;
			case "3":
			case "three":
			case "3rd":
				//option 3
				showReplentishStrategy();
				break;
			case "4":
			case "four":
			case "4th":
			case "exit":
			case "quit":
				//option 4
				exitProgram();
				break;
			default:
				System.out.println("\"" + input + "\" is not a valid input");
		}
	}

	private void inputProductData() {
		// TODO Auto-generated method stub
		
	}

	private void showProductData() {
		// TODO Auto-generated method stub
		
	}

	private void showReplentishStrategy() {
		// TODO Auto-generated method stub
		
	}

	private void exitProgram() {
		exitProgram = true;
		
		System.out.println("Thank you for using our program!");
		
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
