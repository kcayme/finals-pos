import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.text.DecimalFormat;
/*
======================================================================
 CLASS NAME  : CashRegGUI
 DESCRIPTION : Displays the current value of Cash On Hand. This is where the user will see the total amount money in stored.
 COPYRIGHT   : April 30, 2021
 REVISION HISTORY
 Date:               			By:          				Description:
 April 30, 2021			Miguel Edwin P. Salubre			Creation of the class. Adding JLabels, JSeparator and Background for GUI.
 May 1, 2021			Miguel Edwin P. Salubre			Updated the Logic to display the Cash On Hand from FileHandling.
 May 3, 2021			Karl Jensen F. Cayme			Changing the Data Field Format on Cash On Hand.				
======================================================================
*/
public class CashRegGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public CashRegGUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//the main program will not be closed upon closing this frame
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Image cashRegImg = new ImageIcon(this.getClass().getResource("/cashregister.png")).getImage();
		setIconImage(cashRegImg);
		setTitle("Cash Register");
		
		JLabel Header = new JLabel("Cash Register");
		Header.setBackground(Color.WHITE);
		Header.setHorizontalAlignment(SwingConstants.CENTER);
		Header.setForeground(Color.YELLOW);
		Header.setFont(new Font("Helvetica", Font.BOLD, 50));
		Header.setBounds(10, 36, 412, 66);
		contentPane.add(Header);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLUE);
		separator.setBounds(20, 113, 402, 13);
		contentPane.add(separator);
		
		JLabel Label = new JLabel("Current Cash On Hand:");
		Label.setFont(new Font("Tahoma", Font.PLAIN, 19));
		Label.setBounds(10, 137, 207, 38);
		contentPane.add(Label);
		
		DecimalFormat formatter = new DecimalFormat("#,###.00");
		@SuppressWarnings("unused")
		CashRegister cashReg = new CashRegister();
		String displayCash = formatter.format(CashRegister.getCurrentBalance());
		JLabel contentCashOnHand= new JLabel("P " + displayCash);
		contentCashOnHand.setFont(new Font("Tahoma", Font.BOLD, 19));
		contentCashOnHand.setBounds(218, 137, 177, 38);
		contentPane.add(contentCashOnHand);
		
	}
}
