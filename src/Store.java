/**
 * @author Alexander Brown
 * @studentID 3260691
 * @date 22/05/2016
 * @file Store.java
 * Controls use of Product Objects and performs calculations 
 * @see Product
 */
public class Store {

	public final int MINIMUM_PRODUCTS = 3;
	public final String NAME;
	
	private Product[] products;
	
	public Store(String name) {
		this.NAME = name.toLowerCase();
		products = new Product[MINIMUM_PRODUCTS];
	}
	
	/**
	 * returns the number of spots for Products, used or not
	 * @return the products array length
	 */
	public int numberOfPossibleProducts() {
		return products.length;
	}
	
	/**
	 * calculates the number of valid products that are currently being used
	 * @return the number of valid products that are currently being used
	 */
	public int numberOfProducts(){
		int amount = 0;
		
		//loop through and increment on a non-null product
		for (int i = 0; i < products.length; i++) {
			if (products[i] != null) {
				amount++;
			}
		}
		
		return amount;
	}


	/**
	 * Searches the products to find if there is a matching name (case
	 * sensitive)
	 * 
	 * @param name
	 *            the name of the product to search for
	 * @return the product number or -1 if there is no matching product
	 */
	public int findProduct(String name) {
		for (int i = 0; i < products.length; i++) {
			if (products[i] != null && products[i].getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * adds a product to the first available spot. If there are no spots available, it will make room.
	 * 
	 * @param name
	 *            the name of the new product
	 * @return the slot of the product used or -1 if an error
	 */
	public int addProduct(String name) {
		
		//check that we are not at max capacity yet
		if (numberOfProducts() == products.length) {
			
			//if we are going to at max capacity, make some room for the new product
			resizeProductsArray(products.length + 1);
		}
		
		//add the product to the next empty spot. and return its spot in a sorted array
		for (int i = 0; i < products.length; i++) {
			if (products[i] == null) {
				products[i] = new Product(name);
				sortByName();
				return findProduct(name);
			}
		}
		return -1;
	}

	private void resizeProductsArray(int length) {
		
		//make new array
		Product[] newProducts = new Product[length];
		
		//loop through both arrays
		for (int i = 0; i < newProducts.length && i < products.length; i++) {
			
			//add old values to the new values
			newProducts[i] = products[i];
		}
		
		//overwrite the old array with the new array
		products = newProducts;
	}

	/**
	 * Removes the product in the slot indicated by nulling it. Also manages the products while doing so.
	 * 
	 * @param number
	 *            the slot to remove the product
	 */
	public void removeProduct(int number) {
		if (number >= 0 && number < products.length) {
			products[number] = null;
			sortByName();
			if (products.length > MINIMUM_PRODUCTS && products.length > numberOfProducts()) {
				resizeProductsArray(numberOfProducts());
			}
		}
	}

	public String getProductName(int product) {
		if (product >= 0 && product < products.length) {
			return products[product].getName();
		} else {
			return "INVALID PRODUCT";
		}
	}

	public void setProductName(int product, String name) {
		if (product >= 0 && product < products.length) {
			products[product].setName(name);
		}
	}

	public int getDemandRate(int product) {
		if (product >= 0 && product < products.length) {
			return products[product].getDemandRate();
		} else {
			return -1;
		}
	}

	public void setDemandRate(int product, int demandRate) {
		if (product >= 0 && product < products.length) {
			products[product].setDemandRate(demandRate);
		}
	}

	public double getSetupCost(int product) {
		if (product >= 0 && product < products.length) {
			return products[product].getSetupCost();
		} else {
			return -1;
		}
	}

	public void setSetupCost(int product, double setupCost) {
		if (product >= 0 && product < products.length) {
			products[product].setSetupCost(setupCost);
		}
	}

	public double getUnitCost(int product) {
		if (product >= 0 && product < products.length) {
			return products[product].getUnitCost();
		} else {
			return -1;
		}
	}

	public void setUnitCost(int product, double unitCost) {
		if (product >= 0 && product < products.length) {
			products[product].setUnitCost(unitCost);
		}
	}

	public double getInventoryCost(int product) {
		if (product >= 0 && product < products.length) {
			return products[product].getInventoryCost();
		} else {
			return -1;
		}
	}

	public void setInventoryCost(int product, double inventoryCost) {
		if (product >= 0 && product < products.length) {
			products[product].setInventoryCost(inventoryCost);
		}
	}

	public double getSellingPrice(int product) {
		if (product >= 0 && product < products.length) {
			return products[product].getSellingPrice();
		} else {
			return -1;
		}
	}

	public void setSellingPrice(int product, double sellingPrice) {
		if (product >= 0 && product < products.length) {
			products[product].setSellingPrice(sellingPrice);
		}
	}

	public double getProfit(int product) {
		if (product >= 0 && product < products.length) {
			return products[product].getProfit();
		} else {
			return -1;
		}
	}

	public void setProductProfit(int product, double profit) {
		if (product >= 0 && product < products.length) {
			products[product].setProfit(profit);
		}
	}
	
	/**
	 * Checks to see if a product exists and returns the result
	 * @param product the product you wish to check
	 * @return true if the product exists or false if not
	 * @see Product
	 */
	public boolean doesProductExist(int product) {
		if (product >= 0 && product < products.length ) {
			if (products[product] != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * calculates and returns the most profitable product that is available
	 * 
	 * @return the most profitable product or -1 if no product is found or if
	 *         profit has not been calculated
	 */
	public int getMostProfitableProduct() {
		return getMostProfitableProduct(-1);
	}

	/**
	 * calculates and returns the most profitable product that is available.
	 * Should be used in a loop to gain the most profitable products. if used
	 * without a loop, the first product that is the most profitable product is
	 * returned
	 * 
	 * @param lastProduct
	 *            the product that was the most profitable last run-through or
	 *            -1 for no product
	 * @return the most profitable product or -1 if no product is more
	 *         profitable then the last run-through or if profit has not been
	 *         calculated or no product is found
	 */
	public int getMostProfitableProduct(int lastProduct) {
		int mostProfitableProduct = -1;
		if (numberOfProducts() > 0) {
			return -1;
		} else {
			double mostProfitableAmount = Integer.MIN_VALUE;
			double previousMostProfitableAmount = Integer.MIN_VALUE;
			
			for (int i = 0; i < products.length; i++) {
				if (products[i] != null && products[i].isProfitCalculated()) {
					if (lastProduct < i) {
						if (products[i].getProfit() > mostProfitableAmount || products[i].getProfit() == previousMostProfitableAmount) {
							mostProfitableAmount = products[i].getProfit();
							mostProfitableProduct = i;
						}
					} else if (lastProduct == i) {
						previousMostProfitableAmount = products[i].getProfit();
					}
				}
			}
		}

		return mostProfitableProduct;
	}

	/**
	 * Calculates the Economic Order Quantity (EOQ) for a product. Assumes
	 * product not null
	 * 
	 * @param product
	 *            the product number to calculate
	 * @return the optimal EOQ
	 */
	public int calculateEOQ(int product) {
		int quantity;
		int demandRate = getDemandRate(product);
		double setupCost = getSetupCost(product);
		double inventoryCost = getInventoryCost(product);

		quantity = (int) Math.ceil(Math.sqrt((2 * setupCost * demandRate) / inventoryCost));
		return quantity;
	}
	
	/**
	 * Gets a list of all the current product names in a comma separated string
	 * @return all the current product names in a comma separated string 
	 */
	public String getProductNames() {
		String names = "";
		boolean firstProduct = true;
		
		//loop through products
		for (int i = 0; i < products.length; i++) {
			if (products[i] != null) {
				
				//dont add a comma before the first product
				if (firstProduct) {
					firstProduct = false;
				} else {
					names += ", ";
				}
				names += products[i];
			}
		}
		return names;
	}
	
	/**
	 * Sorts the Products by the Names.
	 */
	public void sortByName() {
		//Bubble sort by Name! Also, handles for nulls
		Product aux;
		for (int i = products.length - 1; i >= 0; i--) {
			for (int j=0; j<i; j++) {
				if (products[j+1] == null) {
					
				} else if (products[j] == null || products[j].getName().compareToIgnoreCase(products[j+1].getName()) > 0) { 
		 			aux = products[j]; 
		 			products[j] = products[j+1]; 
		 			products[j+1] = aux; 
				}
			}
		}
	}
	
	/**
	 * Sorts the Products by the Demand Rate.
	 */
	public void sortByDemandRate() {
		//Bubble sort by Demand Rate! Also, handles for nulls
		Product aux;
		for (int i = products.length - 1; i >= 0; i--) {
			for (int j=0; j<i; j++) {
				if (products[j+1] == null) {
					
				} else if (products[j] == null || products[j].getDemandRate() < products[j+1].getDemandRate()) { 
		 			aux = products[j]; 
		 			products[j] = products[j+1]; 
		 			products[j+1] = aux; 
				}
			}
		}
	}
	
	public String toString() {
		return NAME;
	}
}
