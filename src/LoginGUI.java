import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
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

public class LoginGUI extends JFrame implements ActionListener {

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
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @return 
	 */
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(80, 100, 330, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		//the main program will not be closed upon closing this frame
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
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
		button.addActionListener(this);
		contentPane.add(button);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(160, 110, 80, 25);
		cancel.addActionListener(this);
		contentPane.add(cancel);
		
		status = new JLabel("");
		status.setBounds(10, 80, 300, 25);
        status.setForeground(Color.RED);
        status.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(status);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String user = userText.getText();
		@SuppressWarnings("deprecation")
		String password = passwordText.getText();
		
		if(user.equals("Admin") && password.equals("1234")) {
			status.setText("Login successful!");
		}
		else {
			status.setText("Invalid username or password");
		}
		
	}

}

