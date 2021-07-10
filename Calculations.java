import java.util.ArrayList;

public class Calculations {
	private ArrayList <Double>bitcoinDaily;
	private ArrayList <Double>usdDaily;
	private double [][] electricityCost;
	private double [] usdHourly ;
	private double [][] hourlyGain;
	double [] dailyGain;
	
	

	public void calcBitcoin(ArrayList<Double> rates){ 
		bitcoinDaily = new ArrayList<Double>();
		for (int i = 0; i<rates.size(); i++) {
			bitcoinDaily.add(rates.get(i) * 24);
		}
		
	}
	
	public  void calcUSD(double price) {
		this.usdDaily = new ArrayList<Double>();
		for (int i = 0; i<this.bitcoinDaily.size(); i++) {
			this.usdDaily.add(this.bitcoinDaily.get(i) * price);
		}
	}
	
	public void calcUSDHourly(ArrayList<Double> rates, double price) {
		this.usdHourly = new double [rates.size()];
		for (int i = 0; i<rates.size(); i++) {
			this.usdHourly[i] = rates.get(i) * price;
		}
	}
	
	public void calcCostHourly(double [] wattageRates, ArrayList<Integer> wattage){
		int size = wattage.size();
		this.electricityCost = new double [size][24];
		for (int i = 0; i < wattage.size(); i++) {
			for (int j = 0; j < 24; j++) {
				this.electricityCost[i][j] = wattage.get(i) * wattageRates [j] /1000 / 100;
				}
			}	
	}
	
	public void calcGainHourly() {
		this.hourlyGain = new double [this.electricityCost.length][this.electricityCost[0].length];
		for (int i = 0; i<this.hourlyGain.length; i++) {
			for (int j = 0; j<this.hourlyGain[i].length; j++) {
				this.hourlyGain [i][j] = this.usdHourly[i] - this.electricityCost[i][j];				
			}
		}
	}
	
	public void calcGainDaily() {
		this.dailyGain = new double [this.hourlyGain.length];
		for (int i = 0; i<this.hourlyGain.length; i++) {
			double total = 0;
			for (int j = 0; j<this.hourlyGain[i].length; j++) {
				total = total + this.hourlyGain[i][j];
			}
			this.dailyGain[i] = total;	
		}
	}
	
	
	
	public String calc(ArrayList <Double> rates, double price,  ArrayList <Integer> wattage){
		this.calcBitcoin(rates);
        this.calcUSD(price);
        this.calcUSDHourly(rates, price);
        this.calcCostHourly(Wattage.price, wattage);
        this.calcGainHourly();
        this.calcGainDaily();
        String results = "The daily mined Bitcoins in USD is equivalent to the following values for each miner: \n";
        for (int i = 0; i<this.usdDaily.size(); i++) {
            results = results + this.usdDaily.get(i);
            results = results + "\t";
        }
        results = results +"\nThe hourly cost from electricity for each miner: \n";
        for (int i =0; i<this.electricityCost.length; i++) {
            for (int j = 0; j<this.electricityCost[i].length; j++) {
                results = results + this.electricityCost[i][j] + "\t";
            }
            results = results + "\n";
        }
        results = results + "The daily net gain for each minor in USD are as following:";
        for (int i = 0; i<this.dailyGain.length; i++) {
            results = results + this.dailyGain[i] + "\t";

        }
        return results;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String result = CoindeskConnection.connect("https://api.coindesk.com/v1/bpi/currentprice.json");
		result = "[" + result + "]";
		System.out.print(CoindeskConnection.parseBitcoin(result));
		Btc.getRate();
		Btc.getTime();
		Calculations test = new Calculations();
		test.calc(Btc.rates, CoindeskConnection.getBitcoinPrice(), Btc.wattage );
		System.out.println(test.dailyGain[1]);
		
	}

}
