import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JSplitPane;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.SystemColor;

import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/*
======================================================================
 CLASS NAME  : MainPOS
 DESCRIPTION : Displays the main interface of the POS specifically the items list and checkout list and where the transaction takes place
 COPYRIGHT   : April 27, 2021
 REVISION HISTORY
 Date:               			By:          				Description:
 April 27, 2021			Karl Jensen F. Cayme			Creation of the MainPOS Class and template of JFrames for Inventory and Cash Register and a clock function with date
 April 30, 2021			Miguel Edwin P. Salubre			Integration of Login GUI to this class.
 May 6, 2021			Karl Jensen F. Cayme			Addition of search bar function and layouting for the checkout panel.
 May 7, 2021			Karl Jensen F. Cayme			Adding a refresh algorithm for menu table and checkout table.
 May 8, 2021			Karl Jensen F. Cayme			Addition of JButtons for filtering the item list by category and for transactions.
 														
======================================================================
*/
public class MainPOS {

	private JFrame frame;
	private JTable menuTable;
	private JTextField searchTF;
	private JTable checkoutTable;
	private JFrame messageFrame;
	private JLabel lblTotalValue;
	

	/**
	======================================================================
	METHOD : main
	DESCRIPTION : brief description of what the method does
	PRE-CONDITION : states conditions that must be true before method
	is invoked
	POST-CONDITION : tells what will be true after method executed
	======================================================================
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
	/**
	======================================================================
	METHOD : clock
	DESCRIPTION : brief description of what the method does
	PRE-CONDITION : states conditions that must be true before method
	is invoked
	POST-CONDITION : tells what will be true after method executed
	======================================================================
	*/
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
	======================================================================
	METHOD : initialize
	DESCRIPTION : brief description of what the method does
	PRE-CONDITION : states conditions that must be true before method
	is invoked
	POST-CONDITION : tells what will be true after method executed
	======================================================================
	*/
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1400, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//this object is used to display JFrames at the center of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		//this object is used to output a comma-separated amount for the total in the checkout
		DecimalFormat formatter = new DecimalFormat("#,###.00");
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
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
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{80, 633, 74};
		gbl_panel.rowHeights = new int[]{156, 433, 215, -62};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 3;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		panel.add(panel_2, gbc_panel_2);
		
		searchTF = new JTextField();
		searchTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DefaultTableModel model = (DefaultTableModel)menuTable.getModel();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				menuTable.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(searchTF.getText().toUpperCase().trim()));
			}
		});
		searchTF.setFont(new Font("Helvetica", Font.PLAIN, 12));
		searchTF.setColumns(10);
		
		JLabel searchlbl = new JLabel("Search");
		searchlbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton homeButton = new JButton("*HOME BUTTON*");
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)menuTable.getModel();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				menuTable.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(""));
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(homeButton)
					.addPreferredGap(ComponentPlacement.RELATED, 277, Short.MAX_VALUE)
					.addComponent(searchlbl, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(searchTF, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
					.addGap(110))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(21)
					.addComponent(homeButton, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap(55, Short.MAX_VALUE)
					.addComponent(searchlbl)
					.addGap(46))
				.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
					.addGap(53)
					.addComponent(searchTF)
					.addGap(44))
		);
		panel_2.setLayout(gl_panel_2);
		
		JScrollPane scrollPane = new JScrollPane(null, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);
		
		menuTable = new JTable() {
			private static final long serialVersionUID = 1L;
			@Override
			public Component prepareRenderer(TableCellRenderer tr, int row, int column) {
				Component c = super.prepareRenderer(tr, row, column);
				String value = getModel().getValueAt(row, column).toString();
				if(column == 4) {
					if(value.equals("IN STOCK")){
						c.setForeground(Color.BLACK);
						c.setBackground(Color.GREEN);
					}
					else {
						c.setForeground(Color.BLACK);
						c.setBackground(Color.RED);
					}
				}
				else {
					c.setForeground(Color.BLACK);
					c.setBackground(Color.WHITE);
				}
				return c;
			}
		};
		
		menuTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Brand", "Product", "Category", "Price", "Availability"
				}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, Double.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		menuTable.getColumnModel().getColumn(0).setPreferredWidth(70);
		menuTable.getColumnModel().getColumn(1).setPreferredWidth(220);
		menuTable.getColumnModel().getColumn(2).setPreferredWidth(170);
		menuTable.getColumnModel().getColumn(3).setPreferredWidth(60);
		menuTable.getColumnModel().getColumn(4).setPreferredWidth(60);
		menuTable.setFont(new Font("Helvetica", Font.PLAIN, 14));
		menuTable.setRowHeight(30);
		scrollPane.setViewportView(menuTable);
		menuTable.setAutoCreateRowSorter(true);
		
		String fileName = "inventoryData.csv";
		MenuManager manager = new MenuManager(fileName,menuTable);
		manager.createFile();
		
		
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel)menuTable.getModel());
		menuTable.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
			
		//mouse listener for adding items from menu to checkout
		menuTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table =(JTable) e.getSource();
				DefaultTableModel model = (DefaultTableModel)checkoutTable.getModel();
		        Point point = e.getPoint();
		        int row = table.rowAtPoint(point);
		        if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
		        	if(table.getValueAt(row,4).toString().equals("IN STOCK")) {
		        		String name = table.getValueAt(row, 1).toString();
			        	int index = existsInTable(checkoutTable,name);
			        	double price = Double.parseDouble(table.getValueAt(row, 3).toString());
			        	if(index != -1) {
			        		int quantityUpdate = Integer.parseInt(model.getValueAt(index, 2).toString()) + 1;
			        		int inventoryStocks = manager.checkStocks(name);
				        	if(inventoryStocks >= quantityUpdate) {
				        		double subTotalUpdate = Double.parseDouble(model.getValueAt(index, 3).toString()) + price;
				        		model.setValueAt(quantityUpdate, index, 2);
				        		model.setValueAt(subTotalUpdate, index, 3);
				        	}
				        	else {
				        		JOptionPane.showMessageDialog(null, "Desired quantity exceeds current stocks.", "Insufficient Stocks", JOptionPane.ERROR_MESSAGE);
				        	}
			        	}
			        	else {
				        	int quantity = 1;
				        	double subTotal = quantity*price;
				        	Object[] data = {name,price,quantity,subTotal};
				        	model.addRow(data);
			        	}
			        	double total = manager.getTotal(checkoutTable);
			        	lblTotalValue.setText("Php " + formatter.format(total));
		        	}
		        	else {
		        		JOptionPane.showMessageDialog(messageFrame, "Cannot be added to checkout.", "Item Out of Stock", JOptionPane.ERROR_MESSAGE);
		        	}
		        }
			}
		});
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridwidth = 3;
		gbc_panel_4.insets = new Insets(0, 0, 0, 5);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 2;
		panel.add(panel_4, gbc_panel_4);
		
		JButton menButton = new JButton("Men's Apparel");
		menButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)menuTable.getModel();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				menuTable.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter("^MEN'S APPAREL$"));
			}
		});
		menButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
		
		JButton womenButton = new JButton("Women's Apparel");
		womenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)menuTable.getModel();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				menuTable.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter("^WOMEN'S APPAREL$"));
			}
		});
		womenButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
		
		JButton appButton = new JButton("Appliances & Electronics");
		appButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)menuTable.getModel();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				menuTable.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter("^APPLIANCES & ELECTRONICS$"));
			}
		});
		appButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
		
		JButton kidsButton = new JButton("Kids & Toys");
		kidsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)menuTable.getModel();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				menuTable.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter("^KIDS & TOYS$"));
			}
		});
		kidsButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
		
		JButton schoolButton = new JButton("School Supplies");
		schoolButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)menuTable.getModel();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				menuTable.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter("^SCHOOL SUPPLIES$"));
			}
		});
		schoolButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
		
		JButton accButton = new JButton("Accessories");
		accButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)menuTable.getModel();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				menuTable.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter("^ACCESSORIES$"));
			}
		});
		accButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(57)
							.addComponent(menButton, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(55)
							.addComponent(kidsButton, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)))
					.addGap(26)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(schoolButton, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
						.addComponent(womenButton, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(appButton, GroupLayout.PREFERRED_SIZE, 246, Short.MAX_VALUE)
						.addComponent(accButton, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
					.addGap(55))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(womenButton, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(menButton, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
							.addGap(8))
						.addComponent(appButton, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(accButton, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
						.addComponent(schoolButton, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
						.addComponent(kidsButton, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
					.addGap(39))
		);
		panel_4.setLayout(gl_panel_4);
		
		
		JMenu adminMenu = new JMenu("Administrator");
		menuBar.add(adminMenu);
		JMenuItem inventory = new JMenuItem("Inventory");
		inventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI(1,menuTable, checkoutTable, lblTotalValue);
				login.setVisible(true);
				login.setLocation(dim.width/2-login.getSize().width/2, dim.height/2-login.getSize().height/2);
			}
		});
		JMenuItem cashReg = new JMenuItem("Cash Register");
		cashReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI(2,menuTable, checkoutTable, lblTotalValue);
				login.setVisible(true);
				login.setLocation(dim.width/2-login.getSize().width/2, dim.height/2-login.getSize().height/2);
			}
		});
		adminMenu.add(inventory);
		adminMenu.add(cashReg);
		
		JPanel checkoutPanel = new JPanel();
		splitPane.setRightComponent(checkoutPanel);
		GridBagLayout gbl_checkoutPanel = new GridBagLayout();
		gbl_checkoutPanel.columnWidths = new int[]{32, 199, 191, 27, 0};
		gbl_checkoutPanel.rowHeights = new int[]{57, 46, 388, 149, 0};
		gbl_checkoutPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_checkoutPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		checkoutPanel.setLayout(gbl_checkoutPanel);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridheight = 2;
		gbc_panel_3.gridwidth = 4;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		checkoutPanel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{76, 0, 158, 102, 204, -159, 0};
		gbl_panel_3.rowHeights = new int[]{14, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel checkoutHeader = new JLabel("CHECKOUT");
		checkoutHeader.setFont(new Font("Helvetica", Font.BOLD, 44));
		GridBagConstraints gbc_checkoutHeader = new GridBagConstraints();
		gbc_checkoutHeader.insets = new Insets(0, 0, 5, 5);
		gbc_checkoutHeader.gridwidth = 2;
		gbc_checkoutHeader.gridx = 2;
		gbc_checkoutHeader.gridy = 1;
		panel_3.add(checkoutHeader, gbc_checkoutHeader);
		
		JScrollPane scrollPane_1 = new JScrollPane(null, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 2;
		checkoutPanel.add(scrollPane_1, gbc_scrollPane_1);
		
		checkoutTable = new JTable();
		checkoutTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)checkoutTable.getModel();
		        Point point = e.getPoint();
		        int row = checkoutTable.rowAtPoint(point);
		        if(e.getClickCount() == 2) {
		        	String input = JOptionPane.showInputDialog(messageFrame, "Enter new value: ", model.getValueAt(row, 2));
		        	int quantity = 0;
		        	if(input!=null) {
			        	try {
				        	quantity = Integer.parseInt(input.trim());
				        	String name = model.getValueAt(row, 0).toString();
				        	int inventoryStocks = manager.checkStocks(name);
				        	if(inventoryStocks >= quantity) {
				        		double price = findPrice(menuTable, name);
					        	double subTotal = quantity*price;
					        	model.setValueAt(quantity, row, 2);
					        	model.setValueAt(subTotal, row, 3);
				        	}
				        	else {
				        		JOptionPane.showMessageDialog(null, "Desired quantity exceeds current stocks.", "Insufficient Stocks", JOptionPane.ERROR_MESSAGE);
				        	}
				        }
				        catch(Exception e1){
				        	JOptionPane.showMessageDialog(null, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
				        }
		        	}
		        }
			}
		});

		checkoutTable.setShowVerticalLines(false);
		checkoutTable.setShowGrid(false);
		checkoutTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product", "Price", "Qty.", "Subtotal"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, Double.class, Integer.class, Double.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		checkoutTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		checkoutTable.getColumnModel().getColumn(1).setPreferredWidth(80);
		checkoutTable.getColumnModel().getColumn(2).setPreferredWidth(60);
		checkoutTable.getColumnModel().getColumn(3).setPreferredWidth(90);
		checkoutTable.getColumnModel().getColumn(2).setResizable(false);
		checkoutTable.setFont(new Font("Helvetica", Font.PLAIN, 16));
		checkoutTable.setRowHeight(40);
		scrollPane_1.setViewportView(checkoutTable);
		
		JPanel checkoutBottomPanel = new JPanel();
		checkoutBottomPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		GridBagConstraints gbc_checkoutBottomPanel = new GridBagConstraints();
		gbc_checkoutBottomPanel.gridwidth = 4;
		gbc_checkoutBottomPanel.fill = GridBagConstraints.BOTH;
		gbc_checkoutBottomPanel.gridx = 0;
		gbc_checkoutBottomPanel.gridy = 3;
		checkoutPanel.add(checkoutBottomPanel, gbc_checkoutBottomPanel);
		GridBagLayout gbl_checkoutBottomPanel = new GridBagLayout();
		gbl_checkoutBottomPanel.columnWidths = new int[]{0, 69, 28, 41, 0, 188, 39, 0};
		gbl_checkoutBottomPanel.rowHeights = new int[]{76, 53, 0, 0};
		gbl_checkoutBottomPanel.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_checkoutBottomPanel.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		checkoutBottomPanel.setLayout(gbl_checkoutBottomPanel);
		
		JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setFont(new Font("Helvetica", Font.BOLD, 34));
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.gridwidth = 3;
		gbc_lblTotal.anchor = GridBagConstraints.SOUTH;
		gbc_lblTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotal.gridx = 1;
		gbc_lblTotal.gridy = 0;
		checkoutBottomPanel.add(lblTotal, gbc_lblTotal);
		
		lblTotalValue = new JLabel("P0.00");
		lblTotalValue.setFont(new Font("Helvetica", Font.BOLD, 36));
		GridBagConstraints gbc_lblTotalValue = new GridBagConstraints();
		gbc_lblTotalValue.gridwidth = 2;
		gbc_lblTotalValue.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblTotalValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalValue.gridx = 4;
		gbc_lblTotalValue.gridy = 0;
		checkoutBottomPanel.add(lblTotalValue, gbc_lblTotalValue);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 2;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridwidth = 7;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		checkoutBottomPanel.add(panel_1, gbc_panel_1);
		
		
		JButton purchaseBtn = new JButton("PURCHASE");
		purchaseBtn.setFont(new Font("Helvetica", Font.PLAIN, 20));
		purchaseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double total = manager.getTotal(checkoutTable);
				String input = JOptionPane.showInputDialog(messageFrame, "Input Payment: ", "Checkout", 
						JOptionPane.INFORMATION_MESSAGE);
				if(input!=null) {
		        	try {
		        		double payment = Double.parseDouble(input.trim());
		        		CashRegister cr = new CashRegister(payment,total,checkoutTable,fileName);
		        		DefaultTableModel checkoutModel = (DefaultTableModel)checkoutTable.getModel();
		        		DefaultTableModel menuModel = (DefaultTableModel)menuTable.getModel();
		        		checkoutModel.setRowCount(0);
		        		menuModel.setRowCount(0);
		        		manager.readFile();
		        	}
			        catch(Exception e1){
			        	JOptionPane.showMessageDialog(null, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
			        }
	        	}
			}
		});
		
		JButton cancelBtn = new JButton("CANCEL");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)checkoutTable.getModel();
				if(model.getRowCount() != 0) {
				model.setRowCount(0);
				lblTotalValue.setText("Php " + formatter.format(0));
				JOptionPane.showMessageDialog(messageFrame, "Checkout emptied.");
				}
				else {
					JOptionPane.showMessageDialog(messageFrame, "Checkout already empty.");
				}
			}
		});
		cancelBtn.setFont(new Font("Helvetica", Font.PLAIN, 20));
		
		JButton deleteBtn = new JButton("DELETE");
		deleteBtn.setFont(new Font("Helvetica", Font.PLAIN, 20));
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)checkoutTable.getModel();	
					if(model.getRowCount() != 0) {
						int i = checkoutTable.getSelectedRow();
						model.removeRow(i);
						double total = manager.getTotal(checkoutTable);
			        	lblTotalValue.setText("Php " + formatter.format(total));
					}
					else {
						JOptionPane.showMessageDialog(messageFrame, "Checkout already empty.");
					}
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(35)
					.addComponent(purchaseBtn, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
					.addGap(32)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(deleteBtn, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
						.addComponent(cancelBtn, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
					.addGap(40))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(deleteBtn, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cancelBtn, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
						.addComponent(purchaseBtn, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Exit Application?", JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
		            	System.exit(0);
		        }
		        else {
		        	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		        }
		    }
		});
	}
	/**
	======================================================================
	METHOD : existsInTable
	DESCRIPTION : brief description of what the method does
	PRE-CONDITION : states conditions that must be true before method
	is invoked
	POST-CONDITION : tells what will be true after method executed
	======================================================================
	*/
	private int existsInTable(JTable table, String name) {
		int exists = -1;
		
		for(int i=0; i<table.getRowCount(); i++) {
			if(name.equals(table.getValueAt(i, 0).toString())) {
				exists = i;
				return exists;
			}
		}
		return exists;
	}
	/**
	======================================================================
	METHOD : findPrice
	DESCRIPTION : brief description of what the method does
	PRE-CONDITION : states conditions that must be true before method
	is invoked
	POST-CONDITION : tells what will be true after method executed
	======================================================================
	*/
	private double findPrice(JTable table, String name) {
		double price = 0;
		
		for(int i=0; i<table.getRowCount(); i++) {
			if(name.equals(table.getValueAt(i, 1))) {
				price = Double.parseDouble(table.getValueAt(i, 3).toString());
			}
		}
		return price;
	}
}
