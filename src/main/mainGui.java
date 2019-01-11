package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class mainGui extends JFrame {

	public static String startNumberValue, amountNumberValue, inputLinkValue;
	public static Boolean isEntered = false;
		
	public static void init() {
		
		JFrame frame = new JFrame("Barcode Generator");
		frame.setBounds(100, 100, 825, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		//Labels
		JLabel header = new JLabel("Barcode Generator");
		header.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 30));
		header.setBounds(1, 0, 300, 30);
		frame.getContentPane().add(header);
		
		JLabel inputStartNumberText = new JLabel("Start at:");
		inputStartNumberText.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 25));
		inputStartNumberText.setBounds(1, 40, 100, 30);
		frame.getContentPane().add(inputStartNumberText);

		JLabel inputAmountText = new JLabel("Amount:");
		inputAmountText.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 25));
		inputAmountText.setBounds(1, 80, 100, 30);
		frame.getContentPane().add(inputAmountText);
		
		JLabel inputLinkText = new JLabel("URL:");
		inputLinkText.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 25));
		inputLinkText.setBounds(1, 120, 100, 30);
		frame.getContentPane().add(inputLinkText);
		
		
		//Text Fields
		JTextField inputStartNumber = new JTextField("");
		inputStartNumber.setBounds(100, 44, 50, 30);
		frame.getContentPane().add(inputStartNumber);
		
		JTextField inputAmount = new JTextField("");
		inputAmount.setBounds(100, 84, 50, 30);
		frame.getContentPane().add(inputAmount);
		
		JTextField inputLink = new JTextField("");
		inputLink.setBounds(100, 124, 300, 30);
		frame.getContentPane().add(inputLink);

		//Button
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setBounds(1, 160, 100, 30);
		frame.getContentPane().add(confirmButton);
		
		frame.setVisible(true);

		confirmButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startNumberValue = inputStartNumber.getText();
				amountNumberValue = inputAmount.getText();
				inputLinkValue = inputLink.getText();
				isEntered = true;
				
				System.out.print(startNumberValue + " " + amountNumberValue + " " + inputLinkValue);
				main.checkInput();
			}
		});
	}
}
