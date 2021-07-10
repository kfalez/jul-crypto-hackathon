
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TimeRequired {
	public ArrayList<String> miners = new ArrayList<String>();
	public ArrayList<Integer> wattage = new ArrayList<Integer>();
	public ArrayList<Double> rates = new ArrayList<Double>();
	public double bitcoins;
	public double time;
	
	public TimeRequired(double b) {
		bitcoins = b;
	}
	
	public void getMinerInfo(){
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
	
	public void getTime() {
		double sumRates = 0;
		for(int i = 0; i < rates.size(); i++)
		    sumRates += rates.get(i);
		time = bitcoins/sumRates;
	}

	
	public static void main(String[] args) {
		
	}
	
}
