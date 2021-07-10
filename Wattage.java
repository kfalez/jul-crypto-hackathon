import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Wattage {
	public static double [] price = new double [24];
	BufferedReader reader;
	
	
	
	public static void readWattage(String file) {
		BufferedReader reader = null;
		String row;
		int i = 0;
		
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			while((row = reader.readLine()) != null) {
				String[] data = row.split(",");
				price[i] = Double.valueOf(data[1]);
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public double[] wattageRates() {
		return price;
	}

	
	public static void main(String[] args) {
		Wattage.readWattage("EnergyRates.csv");
	}
}
