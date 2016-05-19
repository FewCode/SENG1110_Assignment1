/**
 * @author Alexander Brown
 * @studentID 3260691
 * @date 22/04/2016
 * @file Product.java
 * Object that holds data for an item.
 */
public class Product {
	
	private String name;
	private int demandRate;
	private double setupCost;
	private double unitCost;
	private double inventoryCost;
	private double sellingPrice;
	
	private boolean profitCalculated = false;
	private double profit;
	
	public Product(String name) {
		this.name = name;
	}

	public double getProfit() {
		return profit;
	}

	public boolean isProfitCalculated() {
		return profitCalculated;
	}

	public void setProfit(double profit) {
		this.profit = profit;
		this.profitCalculated = true;
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

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		// TODO Complete the toString Method
		return super.toString();
	}
}
