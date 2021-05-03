import java.awt.Dimension;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LoginGUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button;
	private static JButton cancel;
	private static JLabel status;
	private static int number;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @return 
	 */
	public LoginGUI(int number) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(80, 100, 330, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		//the main program will not be closed upon closing this frame
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setOptionShow(number);
		login();
	}
	
	public void login() {
		
		contentPane.setLayout(null);
		
		userLabel = new JLabel("Username");
		userLabel.setBounds(40, 20, 80, 25);
		contentPane.add(userLabel);
		
		userText = new JTextField(20);
		userText.setBounds(120, 20, 165, 25);
		contentPane.add(userText);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(40, 50, 80, 25);
		contentPane.add(passwordLabel);
		
		passwordText = new JPasswordField(20);
		passwordText.setBounds(120, 50, 165, 25);
		contentPane.add(passwordText);
		
		button = new JButton("Login");
		button.setBounds(60, 110, 80, 25);
		contentPane.add(button);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(160, 110, 80, 25);
		contentPane.add(cancel);
		
		status = new JLabel("");
		status.setBounds(10, 80, 300, 25);
        status.setForeground(Color.RED);
        status.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(status);
		
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String user = userText.getText();
				String password = passwordText.getText();
			
				if(user.equals("Admin") && password.equals("1234")) {
					status.setText("Login successful!");
					doOptionShow(number);
					dispose();
				}
				else if(userText.getText().trim().isEmpty() || passwordText.getText().trim().isEmpty()) {
					status.setText("Fields incomplete.");
				}
				else {
					status.setText("Invalid username or password.");
				}
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public void setOptionShow(int number)
	{
		LoginGUI.number = number;
	}
	
	private void doOptionShow(int number)
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		if(number==2)
		{
			CashRegGUI cashreg = new CashRegGUI();
			cashreg.setVisible(true);
			cashreg.setAlwaysOnTop(true);
			cashreg.setLocation(dim.width/2-cashreg.getSize().width/2, dim.height/2-cashreg.getSize().height/2);
		}
		else
		{
			InventoryGUI inventory = new InventoryGUI();
			inventory.setVisible(true);
			inventory.setAlwaysOnTop(true);
			inventory.setLocation(dim.width/2-inventory.getSize().width/2, dim.height/2-inventory.getSize().height/2);
		}
		this.dispose();
	}

}
