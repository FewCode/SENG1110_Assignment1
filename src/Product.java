/*
	The file name needs to be Product.java
	This class will hold the required instance data for a product and it will have suitable methods to access and modify the data for a product.
	This class should have two constructors.
 */
public class Product {
	
	private String name;
	private int demandRate;
	private double setupCost;
	private double unitCost;
	private double inventoryCost;
	private double sellingPrice;
	
	public Product() {
		//TODO: find out why 2 constructors are needed
	}
	
	public Product(String name) {
		this.name = name;
	}

	public int getDemandRate() {
		return demandRate;
	}

	public void setDemandRate(int demandRate) {
		this.demandRate = demandRate;
	}

	public double getSetupCost() {
		return setupCost;
	}

	public void setSetupCost(double setupCost) {
		this.setupCost = setupCost;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	public double getInventoryCost() {
		return inventoryCost;
	}

	public void setInventoryCost(double inventoryCost) {
		this.inventoryCost = inventoryCost;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getName() {
		return name;
	}

	
}
