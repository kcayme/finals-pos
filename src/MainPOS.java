import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.SpringLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JSlider;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPOS {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPOS window = new MainPOS();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void clock(){
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		frame.getContentPane().add(panel, gbc_panel);
		JLabel lblNewLabel = new JLabel("    Date");
		lblNewLabel.setFont(new Font("Helvetica", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 0, 234, 32);
		panel.add(lblNewLabel);
		
		Thread clock = new Thread(){
			@SuppressWarnings("deprecation")
			public void run(){
				try {
					for(;;) {
					Date date = java.util.Calendar.getInstance().getTime();
					lblNewLabel.setText("  " + date.toLocaleString());
					sleep(1000);
					}
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(null, "Current time cannot be shown.", "Clock Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		};
		
		clock.start();
	}
	/**
	 * Create the application.
	 */
	public MainPOS() {
		initialize();
		clock();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1400, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu adminMenu = new JMenu("Administrator");
		menuBar.add(adminMenu);
		JMenuItem inventory = new JMenuItem("Inventory");
		inventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InventoryGUI inventory = new InventoryGUI();
				inventory.setVisible(true);
				inventory.setAlwaysOnTop(true);
				inventory.setLocation(dim.width/2-inventory.getSize().width/2, dim.height/2-inventory.getSize().height/2);
			}
		});
		JMenuItem cashReg = new JMenuItem("Cash Register");
		cashReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CashRegGUI cashReg = new CashRegGUI();
				cashReg.setVisible(true);
				cashReg.setAlwaysOnTop(true);
				cashReg.setLocation(dim.width/2-cashReg.getSize().width/2, dim.height/2-cashReg.getSize().height/2);
			}
		});
		adminMenu.add(inventory);
		adminMenu.add(cashReg);
		
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{153, 317, 0, 0};
		gridBagLayout.rowHeights = new int[]{61, 689, 37, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(3);
		splitPane.setDividerLocation(900);
		splitPane.setResizeWeight(0.5);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane.gridwidth = 3;
		gbc_splitPane.gridheight = 2;
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 0;
		frame.getContentPane().add(splitPane, gbc_splitPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.scrollbar);
		panel.setForeground(Color.WHITE);
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel checkoutPanel = new JPanel();
		splitPane.setRightComponent(checkoutPanel);
		GridBagLayout gbl_checkoutPanel = new GridBagLayout();
		gbl_checkoutPanel.columnWidths = new int[]{24, 77, 113, 121, 74, 14, -15, 0};
		gbl_checkoutPanel.rowHeights = new int[]{0, 23, 22, 0, 43, 0, 522, 0, 149, 0};
		gbl_checkoutPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_checkoutPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		checkoutPanel.setLayout(gbl_checkoutPanel);
		
		JLabel lblCheckout = new JLabel("CHECKOUT");
		lblCheckout.setFont(new Font("Helvetica", Font.BOLD, 46));
		GridBagConstraints gbc_lblCheckout = new GridBagConstraints();
		gbc_lblCheckout.anchor = GridBagConstraints.WEST;
		gbc_lblCheckout.gridheight = 2;
		gbc_lblCheckout.gridwidth = 3;
		gbc_lblCheckout.insets = new Insets(0, 0, 5, 5);
		gbc_lblCheckout.gridx = 1;
		gbc_lblCheckout.gridy = 1;
		checkoutPanel.add(lblCheckout, gbc_lblCheckout);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.BOTH;
		gbc_separator_1.gridwidth = 7;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 3;
		checkoutPanel.add(separator_1, gbc_separator_1);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setFont(new Font("Helvetica", Font.PLAIN, 12));
		GridBagConstraints gbc_lblProduct = new GridBagConstraints();
		gbc_lblProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblProduct.gridx = 1;
		gbc_lblProduct.gridy = 4;
		checkoutPanel.add(lblProduct, gbc_lblProduct);
		
		JLabel lblQty = new JLabel("Qty");
		lblQty.setFont(new Font("Helvetica", Font.PLAIN, 12));
		GridBagConstraints gbc_lblQty = new GridBagConstraints();
		gbc_lblQty.insets = new Insets(0, 0, 5, 5);
		gbc_lblQty.gridx = 3;
		gbc_lblQty.gridy = 4;
		checkoutPanel.add(lblQty, gbc_lblQty);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Helvetica", Font.PLAIN, 12));
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 4;
		gbc_lblPrice.gridy = 4;
		checkoutPanel.add(lblPrice, gbc_lblPrice);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.gridwidth = 7;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 5;
		checkoutPanel.add(separator, gbc_separator);
		
		JScrollBar scrollBar = new JScrollBar();
		GridBagConstraints gbc_scrollBar = new GridBagConstraints();
		gbc_scrollBar.insets = new Insets(0, 0, 5, 0);
		gbc_scrollBar.fill = GridBagConstraints.VERTICAL;
		gbc_scrollBar.anchor = GridBagConstraints.WEST;
		gbc_scrollBar.gridx = 6;
		gbc_scrollBar.gridy = 6;
		checkoutPanel.add(scrollBar, gbc_scrollBar);
		
		JPanel checkoutBottomPanel = new JPanel();
		checkoutBottomPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		GridBagConstraints gbc_checkoutBottomPanel = new GridBagConstraints();
		gbc_checkoutBottomPanel.gridwidth = 7;
		gbc_checkoutBottomPanel.fill = GridBagConstraints.BOTH;
		gbc_checkoutBottomPanel.gridx = 0;
		gbc_checkoutBottomPanel.gridy = 8;
		checkoutPanel.add(checkoutBottomPanel, gbc_checkoutBottomPanel);
		GridBagLayout gbl_checkoutBottomPanel = new GridBagLayout();
		gbl_checkoutBottomPanel.columnWidths = new int[]{0, 209, 28, 102, 97, 0};
		gbl_checkoutBottomPanel.rowHeights = new int[]{23, 26, 22, 19, 0};
		gbl_checkoutBottomPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_checkoutBottomPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		checkoutBottomPanel.setLayout(gbl_checkoutBottomPanel);
		
		JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setFont(new Font("Helvetica", Font.BOLD, 30));
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.anchor = GridBagConstraints.SOUTH;
		gbc_lblTotal.gridheight = 2;
		gbc_lblTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotal.gridx = 1;
		gbc_lblTotal.gridy = 0;
		checkoutBottomPanel.add(lblTotal, gbc_lblTotal);
		
		JLabel lblNewLabel_1 = new JLabel("P0.00");
		lblNewLabel_1.setFont(new Font("Helvetica", Font.BOLD, 30));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridheight = 2;
		gbc_lblNewLabel_1.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 0;
		checkoutBottomPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JButton btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.anchor = GridBagConstraints.SOUTH;
		gbc_btnNewButton.gridheight = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		checkoutBottomPanel.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridwidth = 2;
		gbc_btnNewButton_1.anchor = GridBagConstraints.SOUTHWEST;
		gbc_btnNewButton_1.gridheight = 2;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 2;
		checkoutBottomPanel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		
		
		
		
		/*frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	//prompt if user is sure to close the program
		        if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Exit Application?", JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
		            	System.exit(0);
		        }
		        else {
		        	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		        }
		    }
		});*/
	}
}
