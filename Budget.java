import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;

public class Budget {
	
	private double wage;
	private double weeklyHours;
	private double taxRate;
	
	private HashMap<String, LinkedList<Expenses>> recurringExpenses;
	private HashMap<String, LinkedList<Expenses>> splurges;
	
	public Budget(double wage, double weeklyHours, double taxRate) {
		this.wage = wage;
		this.weeklyHours = weeklyHours;
		this.taxRate = taxRate;
		recurringExpenses = new HashMap<>();
		splurges = new HashMap<>();
		
	}
	
	public void addExpense(String category, String name, double amount) {
		if(recurringExpenses.get(category) == null) {
			recurringExpenses.put(category, new LinkedList<Expenses>());
		}
		recurringExpenses.get(category).add(new Expenses(name, amount));
	}
	
	private double WeeklyExpenses() {
		return MonthlyExpenses() * 12.0 / 52.0;
	}
	
	private double MonthlyExpenses() {
		double total = 0;
		for(String key: recurringExpenses.keySet()) {
			for (Expenses expense: recurringExpenses.get(key)) {
				total += expense.getAmount();
			}
		}
		return total;
	}
	
	public double MonthlyBudget() {
		double gross = wage * (1.0 - taxRate) * weeklyHours * 52.0 / 12.0;
		return gross - MonthlyExpenses();
	}
	
	public double WeeklyBudget() {
		double gross = wage * (1.0 - taxRate) * weeklyHours;
		return gross - WeeklyExpenses();
	}
	
	public String printRecurringExpenses() {
		String expenses = "";
		for(String key: recurringExpenses.keySet()) {
			expenses += key + ":\n";
			for (Expenses expense: recurringExpenses.get(key)) {
				expenses += "\t" + expense + "\n";
			}
		}
		return expenses;
	}
	
	public void splurge(GregorianCalendar todaysDate, String splurge, Double amount) {
		String weekNum = "week" + todaysDate.get(Calendar.WEEK_OF_YEAR);
		if(splurges.get(weekNum) == null) {
			splurges.put(weekNum, new LinkedList<Expenses>());
		}
		splurges.get(weekNum).add(new Expenses(splurge, amount));
	}
	
	public String printSplurges() {
		String expenses = "";
		for(String key: splurges.keySet()) {
			expenses += key + ":\n";
			for (Expenses expense: splurges.get(key)) {
				expenses += "\t" + expense + "\n";
			}
		}
		return expenses;
	}

}
