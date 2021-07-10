import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BtcGui extends JFrame implements ActionListener {

	private JLabel prompt = new JLabel("Amount of BTC to Mine (press ENTER)");
	private JTextField inField;
	private JTextArea display;
	private JButton reset, displayTime, costAndProfit;
	private static Btc btc;
	

	public BtcGui(String title) { 

		btc = new Btc();
		
		inField = new JTextField(4);
		inField.addActionListener(this);
				
		display = new JTextArea(btc.testToString(), 5, 20);
		
		reset = new JButton("RESET");
		reset.addActionListener(this);
		
		displayTime = new JButton("OBTAIN INFO");
		displayTime.addActionListener(this);
		
		costAndProfit = new JButton("COSTS AND PROFITS");
		costAndProfit.addActionListener(this);
		
		JScrollPane scrollPane = new JScrollPane(display);

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(0, 2));
		inputPanel.add(prompt);
		inputPanel.add(inField);


		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 3));
		buttonPanel.add(displayTime);
		buttonPanel.add(reset);
		buttonPanel.add(costAndProfit);

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add("North", inputPanel);
		contentPane.add("South", buttonPanel);
		contentPane.add("Center",scrollPane);


		setSize(500, 200);
		setTitle(title);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == inField) {
			double amount= Double.parseDouble(inField.getText());
			
			 display.append("\n\nCurrent rate: "+ Btc.sumRates + " BTC/hr, utilizing all available miners.\n");
			 Btc.setAmount(amount);
			 
		}

		if (e.getSource() == displayTime) {
			Btc.getTime();
			display.append(btc.toString());
		}
		
		if (e.getSource() == reset) {
			display.setText(btc.testToString());
			btc = new Btc();
			
		}
		
		if (e.getSource() == costAndProfit) {
			Calculations test = new Calculations();
			Wattage.readWattage("EnergyRates.csv");
			display.append("\n" + test.calc(Btc.rates, CoindeskConnection.getBitcoinPrice(), Btc.wattage));
			
		}
	}

	public static void main(String[] args) {
		new BtcGui("Crypto Mining Evaluator");
		Btc.getRate();		
	}

}
