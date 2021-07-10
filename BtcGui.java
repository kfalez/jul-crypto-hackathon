import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BtcGui extends JFrame implements ActionListener {
	/**
	 * The prompt for the input text.
	 */
	private JLabel prompt = new JLabel("Amount of BTC to Mine (press ENTER)");
	/**
	 * The output for the calculations and results.
	 */
	private JTextField inField;
	/**
	 * Obtains the user input grades.
	 */
	private JTextArea display;
	/**
	 *  One button shows results/stats and the other button clears the GUI.
	 */
	private JButton reset, displayTime;
	// TODO: Add stats instance variable
	/**
	 * Keeps track of the grades that are input and performs the
	 * calculation for the mean and median.
	 */
	private Btc btc;
	
	/**
	 * Constructs the user interface design. It JPanel for the component layout.
	 * Sets the title of the interface and buttons.
	 * @param title Mean and median
	 */
	public BtcGui(String title) { 
		
		//TODO: Instantiate stats object
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

	/**
	 * Handles three different events when the user presses return/enter,
	 * when the STATS button is pressed and when RESET button is pressed.
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == inField) {
			double amount= Double.parseDouble(inField.getText());
			// TODO: ADD THE GRADE TO stats
			 double addAmount = btc.setAmount(amount);
			 display.append("\n\nCurrent rate: "+ btc.setRate() + " BTC/hr");
			 display.append(addAmount + "\n");
			 
		}
		//TODO: Add handling of display button -  DISPLAY THE RESULTS
		//display.append(stats.toString());
		if (e.getSource() == displayTime) {
			display.append(btc.toString());
		
		}
		// TODO: Add handling of reset button - CREATE A NEW stats
		if (e.getSource() == reset) {
			display.setText("*The Current Price of BTC is: $31,000.00 USD*");
			btc = new Btc();
		}
	}

	/**
	 * Creates an instance of the StatsGUI object.
	 * @param args is am array of sequence of characters.
	 */
	public static void main(String[] args) {
		new BtcGui("Mean and median");
		
	}

}
