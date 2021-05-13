import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
/*
======================================================================
 CLASS NAME  : InventoryGUI
 DESCRIPTION : Displays the full list of items and corresponding their corresponding. This is where the user can add, delete, search, sort, or update the items 
 COPYRIGHT   : April 28, 2021
 REVISION HISTORY
 Date:               			By:          				Description:
 April 28, 2021			Karl Jensen F. Cayme			Adding of JTable for item lists and JButtons for add, update, delete, and clear functions
 April 29, 2021			Karl Jensen F. Cayme			Coding of ActionListeners for JTable and JButtons
 May 1, 2021			Karl Jensen F. Cayme			Addition of Search function for the JTable and File Handling					

======================================================================
*/
public class InventoryGUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7555970530637877033L;
	private JPanel contentPane;
	private JTable inventoryTable;
	private JTextField searchTF;
	private JScrollPane scrollPane;
	private JLabel lblSearch;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblRegistrationHeading;
	private JLabel lblName;
	private JLabel lblcategory;
	private JLabel lblStocks;
	private JTextField nameTF;
	private JTextField stocksTF;
	private JLabel lblPrice;
	private JButton deleteBtn;
	private JFrame messageFrame;
	private JTextField brandTF;
	private JButton updateBtn;
	private JButton clearBtn;
	private JTextField priceTF;
	private JLabel lblIcon;

	public InventoryGUI(JTable menuTable, JTable checkoutTable, JLabel lblTotalValue) {
		
		Image inventoryImg = new ImageIcon(this.getClass().getResource("/inventory.png")).getImage();
		Image inventoryScaled = inventoryImg.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
		setIconImage(inventoryScaled);
		setTitle("Inventory");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setFont(new Font("Helvetica", Font.PLAIN, 12));
		setJMenuBar(menuBar);
		
		JMenu optionMenu = new JMenu("Option");
		menuBar.add(optionMenu);
		
		JMenuItem logoutItem = new JMenuItem("Log Out");
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(47, 79, 79));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20, 0, 84, 144, 251, 0, 0, 21, 32, 0, 0, 0, 0, 0, 40, 18, 18, 0};
		gbl_contentPane.rowHeights = new int[]{39, 30, 25, 0, 0, 0, 0, 0, 0, 0, 96, 0, 99, 30, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblIcon = new JLabel("");
		Image iconScaled = inventoryImg.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		lblIcon.setIcon(new ImageIcon(iconScaled));
		GridBagConstraints gbc_lblIcon = new GridBagConstraints();
		gbc_lblIcon.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblIcon.gridheight = 3;
		gbc_lblIcon.insets = new Insets(0, 0, 5, 5);
		gbc_lblIcon.gridx = 2;
		gbc_lblIcon.gridy = 0;
		contentPane.add(lblIcon, gbc_lblIcon);
		
		lblSearch = new JLabel("Search:");
		lblSearch.setForeground(new Color(255, 255, 255));
		lblSearch.setFont(new Font("Helvetica", Font.PLAIN, 12));
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.anchor = GridBagConstraints.EAST;
		gbc_lblSearch.fill = GridBagConstraints.VERTICAL;
		gbc_lblSearch.insets = new Insets(0, 0, 5, 5);
		gbc_lblSearch.gridx = 3;
		gbc_lblSearch.gridy = 1;
		contentPane.add(lblSearch, gbc_lblSearch);
		
		panel = new JPanel();
		panel.setBackground(new Color(47, 79, 79));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 4;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 4;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		
		inventoryTable = new JTable();
		inventoryTable.setFont(new Font("Helvetica", Font.PLAIN, 14));
		inventoryTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"SKU", "Brand", "Product", "Category", "Stocks", "Price"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		inventoryTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		inventoryTable.getColumnModel().getColumn(1).setPreferredWidth(120);
		inventoryTable.getColumnModel().getColumn(2).setPreferredWidth(300);
		inventoryTable.getColumnModel().getColumn(3).setPreferredWidth(124);
		inventoryTable.getColumnModel().getColumn(4).setPreferredWidth(80);
		inventoryTable.getColumnModel().getColumn(5).setPreferredWidth(80);
		inventoryTable.setRowHeight(30);
		inventoryTable.setAutoCreateRowSorter(true);
		inventoryTable.setRowSelectionAllowed(true);
		
		//create FileManager object for file handling
		InventoryManager manager = new InventoryManager("inventoryData.csv",inventoryTable);
		manager.createFile();
				
		MenuManager menu = new MenuManager("inventoryData.csv",menuTable);
				
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel)inventoryTable.getModel());
		inventoryTable.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);				
				
		JTableHeader header = inventoryTable.getTableHeader();
		header.setBackground(new Color(255, 255, 255));
		header.setFont(new Font("Helvetica", Font.PLAIN, 16));
		
		JComboBox<String> categoryCB = new JComboBox<String>();
		categoryCB.setFont(new Font("Helvetica", Font.PLAIN, 12));
		categoryCB.addItem("-Select Category-"	);
		String[] categoryList = {"Men's Apparel","Women's Apparel","Appliances & Electronics" ,"Kids & Toys","School Supplies","Accessories"};
		for(int i=0; i<categoryList.length; i++) {
			categoryCB.addItem(categoryList[i]);
		}
		
		//MouseListener to check if the user is clicking a row in the JTable
		inventoryTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//use DefaultTableModel object to manage contents of the JTable, this is necessary
				DefaultTableModel model = (DefaultTableModel)inventoryTable.getModel();
				int clickedIndex = inventoryTable.getSelectedRow();
				int i = inventoryTable.convertRowIndexToModel(clickedIndex);
				brandTF.setText(model.getValueAt(i, 1).toString());
				nameTF.setText(model.getValueAt(i, 2).toString());
				String originalCategory = model.getValueAt(i, 3).toString();
				for(int j=1; j<categoryCB.getItemCount(); j++) {
					if(originalCategory.compareTo(categoryCB.getItemAt(j).toUpperCase()) == 0) {
						categoryCB.setSelectedIndex(j);			
					}
				}
				stocksTF.setText(model.getValueAt(i, 4).toString());
				priceTF.setText(model.getValueAt(i, 5).toString());
			}
		});
		
		logoutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setTable(inventoryTable);
		    	manager.updateFile();
	        	dispose();
			}
		});
		optionMenu.add(logoutItem);
		
		searchTF = new JTextField();
		searchTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DefaultTableModel model = (DefaultTableModel)inventoryTable.getModel();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
				inventoryTable.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(searchTF.getText().toUpperCase().trim()));
			}
		});
		searchTF.setColumns(10);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addComponent(searchTF, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(searchTF, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);

		
		scrollPane = new JScrollPane(null, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 10;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 7;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);
		scrollPane.setViewportView(inventoryTable);
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 7;
		gbc_panel_1.gridheight = 9;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 9;
		gbc_panel_1.gridy = 3;
		contentPane.add(panel_1, gbc_panel_1);
		
		lblRegistrationHeading = new JLabel("Product Details");
		lblRegistrationHeading.setFont(new Font("Helvetica", Font.BOLD, 18));
		
		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Helvetica", Font.BOLD, 12));
		
		lblcategory = new JLabel("Category:");
		lblcategory.setFont(new Font("Helvetica", Font.BOLD, 12));
		
		lblStocks = new JLabel("Stocks:");
		lblStocks.setFont(new Font("Helvetica", Font.BOLD, 12));
		
		nameTF = new JTextField();
		nameTF.setFont(new Font("Helvetica", Font.PLAIN, 12));
		nameTF.setColumns(10);
		
		stocksTF = new JTextField();
		stocksTF.setFont(new Font("Helvetica", Font.PLAIN, 12));
		stocksTF.setColumns(10);
		
		lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Helvetica", Font.BOLD, 12));
		
		JButton addBtn = new JButton("Add");
		addBtn.setFont(new Font("Helvetica", Font.PLAIN, 12));
		addBtn.setBackground(new Color(71, 230, 129));
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)inventoryTable.getModel();
				//checks if the textfields are empty and the chosen option in the combobox is not the first option
				setAlwaysOnTop(false);
				String productName = nameTF.getText().trim().toUpperCase();
				String brandName = brandTF.getText().trim().toUpperCase();
				if(!manager.exists(productName, brandName)) {
					if(!brandTF.getText().trim().isBlank() && !nameTF.getText().trim().isEmpty() && !stocksTF.getText().trim().isEmpty() && 
							!priceTF.getText().trim().isEmpty() && categoryCB.getSelectedIndex() !=0) {
						//try to parse text to integer and double or catch an error
						try {
							double price = Double.parseDouble(priceTF.getText());
							int stocks = Integer.parseInt(stocksTF.getText());
							//check if price and stock values are negatives
							
							if(price < 0 || stocks < 0) {
								JOptionPane.showMessageDialog(messageFrame, "Invalid Input!", "Error", JOptionPane.ERROR_MESSAGE);
							}
							else {
								Item item = new Item(brandTF.getText().trim(),nameTF.getText().trim(), categoryCB.getSelectedItem().toString(),
										price,stocks,inventoryTable);
								//create array of objects containing the data to be added in the table
								Object[] data = {item.getSKU(),item.getBrand(),item.getName(),item.getCategory(),item.getStocks(),
										item.getPrice()};
								//add the object array to a row in JTable via DefaultTableMOdel object
								model.addRow(data);
								JOptionPane.showMessageDialog(messageFrame, "Generated SKU: " + item.getSKU(), "Product Registration Successful", 
										JOptionPane.INFORMATION_MESSAGE);
							}
						}
						catch(Exception e1) {							
							JOptionPane.showMessageDialog(messageFrame, "Invalid Input!", "Error", JOptionPane.ERROR_MESSAGE);
						}
						finally {
							brandTF.setText("");
							nameTF.setText("");
							stocksTF.setText("");
							priceTF.setText("");
							categoryCB.setSelectedIndex(0);
							
						}
					}
					else {
						JOptionPane.showMessageDialog(messageFrame, "All details must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(messageFrame, "Item already exists.", "Add Error", JOptionPane.ERROR_MESSAGE);
				}
				model.fireTableDataChanged();
				setAlwaysOnTop(true);
			}
		});
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setFont(new Font("Helvetica", Font.PLAIN, 12));
		deleteBtn.setBackground(new Color(250, 97, 97));
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)inventoryTable.getModel();
				setAlwaysOnTop(false);
				int clickedIndex = inventoryTable.getSelectedRow();
				int i = inventoryTable.convertRowIndexToModel(clickedIndex);
				if(i == -1) {
					JOptionPane.showMessageDialog(messageFrame, "Select a row to delete", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(JOptionPane.showConfirmDialog(messageFrame, "Are you sure you want to delete?", "Confirmation", JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE)  == 0) {
						model.removeRow(i);
					}
				}
				setAlwaysOnTop(true);
			}
		});
		
		JLabel lblBrand = new JLabel("Brand:");
		lblBrand.setFont(new Font("Helvetica", Font.BOLD, 12));
		
		brandTF = new JTextField();
		brandTF.setFont(new Font("Helvetica", Font.PLAIN, 12));
		brandTF.setColumns(10);
		
		updateBtn = new JButton("Update");
		updateBtn.setFont(new Font("Helvetica", Font.PLAIN, 12));
		updateBtn.setBackground(new Color(250, 219, 97));
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)inventoryTable.getModel();
				setAlwaysOnTop(false);
				//try to parse the Strings for stocks and price and catch error
				try {
					int clickedIndex = inventoryTable.getSelectedRow();
					int i = inventoryTable.convertRowIndexToModel(clickedIndex);
					//Row datas are assigned to new variables for shorter condition syntax in the next if-statement
					String brandOld = model.getValueAt(i, 1).toString();
					String nameOld = model.getValueAt(i, 2).toString();
					String categoryOld = model.getValueAt(i, 3).toString();	
					//int and double data in table must be parsed because it is stored as String
					int stocksOld = Integer.parseInt(model.getValueAt(i, 4).toString());
					double priceOld = Double.parseDouble(model.getValueAt(i, 5).toString());
					
					//checks if there are any changes in details
					if(!brandOld.equals(brandTF.getText()) || !nameOld.equals(nameTF.getText()) || 
							!categoryOld.equals(categoryCB.getSelectedItem().toString().toUpperCase()) || 
							stocksOld != Integer.parseInt(stocksTF.getText()) || priceOld != Double.parseDouble(priceTF.getText())) {
						
						model.setValueAt(brandTF.getText().trim(), i, 1);
						model.setValueAt(nameTF.getText().trim(), i, 2);
						model.setValueAt(categoryCB.getSelectedItem().toString().toUpperCase().trim(), i, 3);
						model.setValueAt(Integer.parseInt(stocksTF.getText()), i, 4);
						model.setValueAt(Double.parseDouble(priceTF.getText()), i, 5);
						JOptionPane.showMessageDialog(messageFrame, "Product Details changed.", "Update Successful", 
								JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(messageFrame, "No changes ommited.", "Update Status", 
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(messageFrame, "Product detail update failed.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				model.fireTableDataChanged();
				setAlwaysOnTop(true);
			}
		});

		clearBtn = new JButton("Clear");
		clearBtn.setFont(new Font("Helvetica", Font.PLAIN, 12));
		clearBtn.setBackground(new Color(199, 209, 206));
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				brandTF.setText("");
				nameTF.setText("");
				stocksTF.setText("");
				priceTF.setText("");
				categoryCB.setSelectedIndex(0);
			}
		});
		
		priceTF = new JTextField();
		priceTF.setFont(new Font("Helvetica", Font.PLAIN, 12));
		priceTF.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addGap(42)
							.addComponent(lblRegistrationHeading, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addComponent(lblBrand, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(172, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(brandTF, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblName)
									.addPreferredGap(ComponentPlacement.RELATED, 144, GroupLayout.PREFERRED_SIZE))
								.addComponent(nameTF, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
							.addGap(28))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblcategory)
								.addComponent(categoryCB, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(28, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblStocks, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(40))
								.addComponent(stocksTF, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
							.addGap(129))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 40, GroupLayout.PREFERRED_SIZE))
								.addComponent(priceTF, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
							.addGap(129))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(addBtn, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
								.addComponent(updateBtn, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(clearBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(deleteBtn, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRegistrationHeading)
					.addGap(9)
					.addComponent(lblBrand, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(brandTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(nameTF, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblcategory)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(categoryCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblStocks)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(stocksTF, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(priceTF, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(addBtn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(deleteBtn, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(updateBtn, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(clearBtn, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(9))
		);
		panel_1.setLayout(gl_panel_1);
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	setAlwaysOnTop(false);
		    	DefaultTableModel inventoryModel = (DefaultTableModel)inventoryTable.getModel();
		    	TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(inventoryModel);
		    	List<RowSorter.SortKey> sortKeys = new ArrayList<>();
				sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
				sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
				sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
				sorter.setSortKeys(sortKeys);
		    	inventoryTable.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(""));
				
		    	manager.setTable(inventoryTable);
		    	manager.updateFile();
		    	DefaultTableModel menuModel = (DefaultTableModel) menuTable.getModel();
		    	menuModel.setRowCount(0);
		    	menu.readFile();
		    	
		    	DecimalFormat formatter = new DecimalFormat("#,###.00");
		    	menu.updateCheckout(checkoutTable);
		    	double updatedTotal = menu.getTotal(checkoutTable);
		    	lblTotalValue.setText("Php 0" + formatter.format(updatedTotal));
	        	dispose();
		    }
		});
	}
}
