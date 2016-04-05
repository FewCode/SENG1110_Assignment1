/*
	It must have three Product objects as instance variables: product1, product2 and product3.
 	It must use the Product class’s methods for accessing and modifying the data in a product object.

 */
//TODO: change method names to correct format
public class Store {

	private Product product1;
	private Product product2;
	private Product product3;
	
	/**
	 * Cycles through products to count how many products are available to create
	 * @return the amount of products that can be created
	 */
	public int AvailableProducts() {
		int amount = 0;
		
		if (product1 == null) {
			amount++;
		}
		if (product2 == null) {
			amount++;
		}
		if (product3 == null) {
			amount++;
		}
		
		return amount;
	}
	
	/**
	 * Cycles through the products to see what the next available product to create is.
	 * @return the next product that can be created
	 */
	public int NextEmptyProduct(){
		if (product1 == null) {
			return 1;
		}
		if (product2 == null) {
			return 2;
		}
		if (product3 == null) {
			return 3;
		}
		return 0;
	}
	
	/**
	 * Searches the products to find if there is a matching name
	 * @param name the name of the product to search for
	 * @return the product number or 0 if there is no matching product
	 */
	public int FindProduct(String name) {
		if (product1 != null && product1.getName().equals(name)) {
			return 1;
		}
		if (product2 != null && product2.getName().equals(name)) {
			return 2;
		}
		if (product3 != null && product3.getName().equals(name)) {
			return 3;
		}
		return 0;
	}
	
	public int CreateProduct(int number, String name) {
		//get next available product and create a new one with the name
		//cycle through and add product data
		if (number == 1) {
			product1 = new Product(name);
			return 1;
		} else if (number == 2) {
			product2 = new Product(name);
			return 2;
		} else if (number == 3) {
			product3 = new Product(name);
			return 3;
		} else {
			return - 1;
		}
	}
	
	public void RemoveProduct(int number) {
		//get next available product and create a new one with the name
		//cycle through and remove the product
		if (number == 1) {
			product1 = null;
		} else if (number == 2) {
			product2 = null;
		} else if (number == 3) {
			product3 = null;
		} else {
			return;
		}
	}

	public String getProductName(int product) {
		if (product == 1) {
			return product1.getName();
		} else if (product == 2) {
			return product2.getName();
		} else if (product == 3) {
			return product3.getName();
		} else {
			return "INVALID PRODUCT";
		}
	}
	
	public void setProductName(int product, String name) {
		if (product == 1) {
			product1.setName(name);
		} else if (product == 2) {
			product2.setName(name);
		} else if (product == 3) {
			product3.setName(name);
		}
	}
	
	public int getDemandRate(int product) {
		if (product == 1) {
			return product1.getDemandRate();
		} else if (product == 2) {
			return product2.getDemandRate();
		} else if (product == 3) {
			return product3.getDemandRate();
		} else {
			return -1;
		}
	}
	
	public void setDemandRate(int product, int demandRate) {
		if (product == 1) {
			product1.setDemandRate(demandRate);
		} else if (product == 2) {
			product2.setDemandRate(demandRate);
		} else if (product == 3) {
			product3.setDemandRate(demandRate);
		}
	}
	
	public double getSetupCost(int product) {
		if (product == 1) {
			return product1.getSetupCost();
		} else if (product == 2) {
			return product2.getSetupCost();
		} else if (product == 3) {
			return product3.getSetupCost();
		} else {
			return -1;
		}
	}
	
	public void setSetupCost(int product, double setupCost) {
		if (product == 1) {
			product1.setSetupCost(setupCost);
		} else if (product == 2) {
			product2.setSetupCost(setupCost);
		} else if (product == 3) {
			product3.setSetupCost(setupCost);
		}
	}
	
	public double getUnitCost(int product) {
		if (product == 1) {
			return product1.getUnitCost();
		} else if (product == 2) {
			return product2.getUnitCost();
		} else if (product == 3) {
			return product3.getUnitCost();
		} else {
			return -1;
		}
	}
	
	public void setUnitCost(int product, double unitCost) {
		if (product == 1) {
			product1.setUnitCost(unitCost);
		} else if (product == 2) {
			product2.setUnitCost(unitCost);
		} else if (product == 3) {
			product3.setUnitCost(unitCost);
		}
	}
	
	public double getInventoryCost(int product) {
		if (product == 1) {
			return product1.getInventoryCost();
		} else if (product == 2) {
			return product2.getInventoryCost();
		} else if (product == 3) {
			return product3.getInventoryCost();
		} else {
			return -1;
		}
	}
	
	public void setInventoryCost(int product, double inventoryCost) {
		if (product == 1) {
			product1.setInventoryCost(inventoryCost);
		} else if (product == 2) {
			product2.setInventoryCost(inventoryCost);
		} else if (product == 3) {
			product3.setInventoryCost(inventoryCost);
		}
	}
	
	public double getSellingPrice(int product) {
		if (product == 1) {
			return product1.getSellingPrice();
		} else if (product == 2) {
			return product2.getSellingPrice();
		} else if (product == 3) {
			return product3.getSellingPrice();
		} else {
			return -1;
		}
	}
	
	public void setSellingPrice(int product, double sellingPrice) {
		if (product == 1) {
			product1.setSellingPrice(sellingPrice);
		} else if (product == 2) {
			product2.setSellingPrice(sellingPrice);
		} else if (product == 3) {
			product3.setSellingPrice(sellingPrice);
		}
	}
}
