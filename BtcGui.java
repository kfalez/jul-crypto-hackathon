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
	private JButton reset, displayTime;
	private Btc btc;
	

	public BtcGui(String title) { 

		btc = new Btc();
		
		inField = new JTextField(4);
		inField.addActionListener(this);
				
		display = new JTextArea("*The Current Price of BTC is: $31,000.00 USD*", 5, 20);
		
		reset = new JButton("RESET");
		reset.addActionListener(this);
		
		displayTime = new JButton("OBTAIN INFO");
		displayTime.addActionListener(this);

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(0, 2));
		inputPanel.add(prompt);
		inputPanel.add(inField);


		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 2));
		buttonPanel.add(displayTime);
		buttonPanel.add(reset);

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add("North", inputPanel);
		contentPane.add("Center", display);
		contentPane.add("South", buttonPanel);


		setSize(500, 200);
		setTitle(title);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == inField) {
			double amount= Double.parseDouble(inField.getText());
			// TODO: ADD THE GRADE TO stats
			 double addAmount = btc.setAmount(amount);
			 display.append("\n\nCurrent rate: "+ btc.setRate() + " BTC/hr");
			 display.append(addAmount + "\n");
			 
		}

		if (e.getSource() == displayTime) {
			display.append(btc.toString());
		
		}
		
		if (e.getSource() == reset) {
			display.setText("*The Current Price of BTC is: $31,000.00 USD*");
			btc = new Btc();
		}
	}


	public static void main(String[] args) {
		new BtcGui("Mean and median");
		
	}

}
