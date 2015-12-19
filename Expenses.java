public class Expenses {
	
	private String name;
	private double amount;
	
	public Expenses(String name, double amount) {
		this.name = name;
		this.amount = amount;
	}
	
	public String getName() { return name; }
	public double getAmount() { return amount; }
	
	@Override
	public String toString() {
		return name + ": $" + String.format("%.2f", amount);
	}

}
