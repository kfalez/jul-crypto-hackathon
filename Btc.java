import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Btc {

	private String currentPrice;
	private static double bitcoins;
	private double timeToMine;
	private double rate;
	public static double sumRates = 0;
	public static ArrayList<String> miners = new ArrayList<String>();
	public static ArrayList<Integer> wattage = new ArrayList<Integer>();
	public static ArrayList<Double> rates = new ArrayList<Double>();

	public static double time;
	
	public String getPrice() {
		String result = "[" + CoindeskConnection.connect("https://api.coindesk.com/v1/bpi/currentprice.json")+"]";
		return currentPrice = CoindeskConnection.parseBitcoin(result);
	}
	
	public static void setAmount(double amount) {
		bitcoins = amount;
	}
	
	
	public final static void getRate() {
		try {
			Scanner s = new Scanner(new File("MiningSetup.csv"), "UTF-8");
			while (s.hasNextLine()) {
				String line = s.nextLine();
				miners.add(line.trim().split(",")[0]);
				rates.add(Double.parseDouble(line.trim().split(",")[1]));
				wattage.add(Integer.parseInt(line.trim().split(",")[2]));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		}
		for(int i = 0; i < rates.size(); i++)
		    sumRates += rates.get(i);
		
	}
	
	public static double getTime() {
		return time = (bitcoins/sumRates)/24;
		
	}
	

	
	public String toString() {
		
		 return "\n\nIt will take: " + time + " days to mine " + bitcoins + " BTC." ;
}
	
	public String testToString() {
		return getPrice();
	}
}

