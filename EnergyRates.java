import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EnergyRates {
    ArrayList<Integer> hours = new ArrayList<Integer>();
    static ArrayList<Double> energyRate = new ArrayList<Double>();
	
	public EnergyRates() {}
	
	public void getEnergyInfo(){
		
		try {
			Scanner s = new Scanner(new File("EnergyRates.csv"), "UTF-8");
			while (s.hasNextLine()) {
				String line = s.nextLine();
				hours.add(Integer.parseInt(line.trim().split(",")[0]));
				energyRate.add(Double.parseDouble(line.trim().split(",")[1]));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EnergyRates test = new EnergyRates();
		test.getEnergyInfo();
		System.out.println(energyRate);
	}
	
}

