
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TimeRequired {
	public static ArrayList<String> miners = new ArrayList<String>();
	public static ArrayList<Integer> wattage = new ArrayList<Integer>();
	public static ArrayList<Double> rates = new ArrayList<Double>();

	public static double time;
	
	public TimeRequired() {

	}
	
	public static void getMinerInfo(){
		try {
			Scanner s = new Scanner(new File("MiningSetup.csv"), "UTF-8");
			while (s.hasNextLine()) {
				String line = s.nextLine();
				miners.add(line.trim().split(",")[0]);
				rates.add(Double.parseDouble(line.trim().split(",")[1]));
				wattage.add(Integer.parseInt(line.trim().split(",")[3]));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void getTime(double bitcoins) {
		double sumRates = 0;
		for(int i = 0; i < rates.size(); i++)
		    sumRates += rates.get(i);
		time = bitcoins/sumRates;
	}

	
	public static void main(String[] args) {
		
	}
	
}
