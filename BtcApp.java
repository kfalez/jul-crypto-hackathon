
public class BtcApp {
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			String result = CoindeskConnection.connect("https://api.coindesk.com/v1/bpi/currentprice.json");
			result = "[" + result + "]";
			System.out.print(CoindeskConnection.parseBitcoin(result));

	}

}
