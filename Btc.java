
public class Btc {
	
	private double currentPrice;
	private double amountToMine;
	private double timeToMine;
	private double rate;
	
	public void getPrice() {
		currentPrice = 31000.03;
	}
	
	public double setAmount(double amount) {
		amountToMine = amount;
		return amount;
	}
	
	public double setRate() {
		rate = 0.00010294;
		return rate;
	}
	
	
	public double getTime() {
		timeToMine = (amountToMine / rate) / 24;
		return timeToMine;
	}

	
	public String toString() {
		
		 return "\nIt will take: " + getTime() + " days to mine " + amountToMine + " BTC." ;
}
}