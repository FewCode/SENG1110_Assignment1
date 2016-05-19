//TODO: update javaDoc
/**
 * @author Alexander Brown
 * @studentID 3260691
 * @date 22/04/2016
 * @file Store.java
 * Controls use of Product Objects and performs calculations 
 * @see Product
 */
public class Store {

	public final int MINIMUM_PRODUCTS = 3;
	public final String NAME;
	
	private Product[] products;

	public enum SortType{
		NAME, DEMAND_RATE
	}
	
	public Store(String name) {
		this.NAME = name.toLowerCase();
		products = new Product[MINIMUM_PRODUCTS];
	}
	
	public int numberOfProducts(){
		int amount = 0;
		
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
	 * Creates a product in the associated product slot with the name provided.
	 * Will override any other product current there
	 * 
	 * @param number
	 *            the product slot to create a new product in
	 * @param name
	 *            the name of the new product
	 * @return the slot of the product used or -1 if an error
	 */
	public int addProduct(String name) {
		if (numberOfProducts() == products.length) {
			resizeProductsArray(products.length + 1);
		}
		for (int i = 0; i < products.length; i++) {
			if (products[i] == null) {
				products[i] = new Product(name);
				return i;
			}
		}
		return -1;
	}

	private void resizeProductsArray(int length) {
		// TODO resize array to size given
		
	}

	/**
	 * Removes the product in the slot indicated by nulling it
	 * 
	 * @param number
	 *            the slot to remove the product
	 */
	public void removeProduct(int number) {
		//TODO: find a better way. Possible of resizing array if greater than the minimum size
		if (number >= 0 && number < products.length) {
			products[number] = null;
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
		//TODO: comments and lots of testing
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
	
	public void sort(SortType type) {
		if (type == SortType.NAME) {
			sortByName();
		} else if (type == SortType.DEMAND_RATE) {
			sortByDemandRate();
		}
	}
	
	public String getProductNames() {
		String names = "";
		boolean firstProduct = true;
		
		for (int i = 0; i < products.length; i++) {
			if (products[i] != null) {
				if (firstProduct) {
					firstProduct = false;
				} else {
					names += ", ";
				}
				names += products[i].getName();
			}
		}
		return names;
	}
	
	private void sortByName() {
		//TODO: Implement sort - Extra 5 marks
	}
	
	private void sortByDemandRate() {
		//TODO: implement sort - Extra 5 marks
	}
	
	@Override
	public String toString() {
		// TODO Complete the toString Method
		return super.toString();
	}
}
