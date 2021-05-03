import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;

public class CashRegGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CashRegGUI frame = new CashRegGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CashRegGUI() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//the main program will not be closed upon closing this frame
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel Header = new JLabel("Cash Register");
		Header.setBackground(Color.WHITE);
		Header.setHorizontalAlignment(SwingConstants.CENTER);
		Header.setForeground(Color.YELLOW);
		Header.setFont(new Font("Berlin Sans FB", Font.PLAIN, 50));
		Header.setBounds(10, 11, 412, 66);
		contentPane.add(Header);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLUE);
		separator.setBounds(20, 80, 402, 13);
		contentPane.add(separator);
		
		JLabel Label = new JLabel("Current Cash On Hand:");
		Label.setFont(new Font("Tahoma", Font.PLAIN, 19));
		Label.setBounds(20, 104, 207, 38);
		contentPane.add(Label);
		
		JLabel contentCashOnHand= new JLabel(String.valueOf(CashRegister.getCurrentBalance()));
		contentCashOnHand.setFont(new Font("Tahoma", Font.BOLD, 19));
		contentCashOnHand.setBounds(259, 104, 73, 38);
		contentPane.add(contentCashOnHand);
		
	}
}
