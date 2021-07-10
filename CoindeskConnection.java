import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import org.json.*;

/**
 * The class connects to the coindesk API and retrieves updated
 *  Bitcoin data through the application of JSON parsing.
 *  
 * @author Kody, Brandon, Tahsin, Kelton
 * @version 1.0
 * @since July 10th, 2021
 */
public class CoindeskConnection{

	private static HttpURLConnection connection;
	private static BufferedReader reader;
	private static String line;
	private static double bitcoinUSD;
	
	/**
	 * This method connects to an API through a address provided
	 * @param address the URL to connect to
	 * @return response A string of the retrieved JSON file
	 */
	public static String connect(String address) {
		line = new String();
		StringBuffer response = new StringBuffer();
		try {
			URL url = new URL(address);
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(10000);
			int status = connection.getResponseCode();
			
			if (status>299){
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while((line = reader.readLine()) != null) {
					response.append(line);
				}				
			}else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while((line = reader.readLine()) != null) {
					response.append(line);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			connection.disconnect();
		}
		return response.toString();
	} 
	
	/**
	 * Parses the current Bitcoin related data and saves it as a string
	 * 	Also updates the stored bitcoin price in USD.
	 * @param jsonString The JSON string which contains bitcoin data
	 * @return string containing bitcoin data
	 */
	public static String parseBitcoin(String jsonString) {
		JSONArray coindeskJson = new JSONArray(jsonString);
		JSONObject bitcoinInfo = coindeskJson.getJSONObject(0);
		String time = bitcoinInfo.getJSONObject("time").getString("updated");
		String chartName = bitcoinInfo.getString("chartName");
		String currencyUSD = bitcoinInfo.getJSONObject("bpi").getJSONObject("USD").getString("rate");
		NumberFormat usd = NumberFormat.getInstance(Locale.US); 
		try {
			bitcoinUSD = usd.parse(currencyUSD).doubleValue(); // Converts USD currency to double
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Value provided can not be converted to a double.");
		}
		String currencyGBP = bitcoinInfo.getJSONObject("bpi").getJSONObject("GBP").getString("rate");
		String currencyEUR = bitcoinInfo.getJSONObject("bpi").getJSONObject("EUR").getString("rate");
			

		return ("On " + time + ", the price for " + chartName + " is: \n" + 
				"USD: " + currencyUSD + "\n" + 
				"GBP: " + currencyGBP + "\n" + 
				"EUR: " + currencyEUR);
	}
	/** 
	 * Retrieves the Bitcoin price in USD as a double
	 * @return bitcoinUSD the Bitcoin price in USD
	 */
	public static double getBitcoinPrice() {
		return bitcoinUSD;
	}
		
	
	public static void main(String[] args) {
		String result = CoindeskConnection.connect("https://api.coindesk.com/v1/bpi/currentprice.json");
		result = "[" + result + "]";
		System.out.print(parseBitcoin(result));
	}

}
