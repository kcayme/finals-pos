import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class InventoryGUI extends JFrame {

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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryGUI frame = new InventoryGUI();
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
	public InventoryGUI() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu optionMenu = new JMenu("Option");
		menuBar.add(optionMenu);
		
		JMenuItem logoutItem = new JMenuItem("Log Out");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20, 84, 55, 251, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 96, 132, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblSearch = new JLabel("Search Product");
		lblSearch.setFont(new Font("Helvetica", Font.PLAIN, 12));
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.insets = new Insets(0, 0, 5, 5);
		gbc_lblSearch.gridx = 1;
		gbc_lblSearch.gridy = 1;
		contentPane.add(lblSearch, gbc_lblSearch);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.WEST;
		gbc_panel.gridwidth = 8;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 2;
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
			private static final long serialVersionUID = 172762057821372960L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, Integer.class, Double.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		inventoryTable.setAutoCreateRowSorter(true);
		
		searchTF = new JTextField();
		searchTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
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
					.addComponent(searchTF, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(40, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(searchTF)
		);
		panel.setLayout(gl_panel);

		
		scrollPane = new JScrollPane(null, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 9;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		JComboBox<String> categoryCB = new JComboBox<String>();
		categoryCB.setFont(new Font("Helvetica", Font.PLAIN, 12));
		categoryCB.addItem("-Select Category-"	);
		String[] categoryList = {"Men's Apparel","Women's Apparel","Appliances & Electronics" ,"Kids and Toys","School Supplies","Accessories"};
		for(int i=0; i<categoryList.length; i++) {
			categoryCB.addItem(categoryList[i]);
		}
		
		inventoryTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		inventoryTable.getColumnModel().getColumn(1).setPreferredWidth(140);
		inventoryTable.getColumnModel().getColumn(2).setPreferredWidth(287);
		inventoryTable.getColumnModel().getColumn(3).setPreferredWidth(124);
		inventoryTable.getColumnModel().getColumn(4).setPreferredWidth(80);
		inventoryTable.getColumnModel().getColumn(5).setPreferredWidth(80);
		//use DefaultTableModel object to manage contents of the JTable, this is necessary
		DefaultTableModel model = (DefaultTableModel)inventoryTable.getModel();
		scrollPane.setViewportView(inventoryTable);
		
		//create FileManager object for filehandling
		FileManager manager = new FileManager("inventoryData.csv",inventoryTable);
		manager.createFile();
		
		logoutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.updateFile(inventoryTable);
	        	dispose();
			}
		});
		optionMenu.add(logoutItem);
		
		//MouseListener to check if the user is clicking a row in the JTable
		inventoryTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = inventoryTable.getSelectedRow();
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
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 7;
		gbc_panel_1.gridheight = 9;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 8;
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
		
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//checks if the textfields are empty and the chosen option in the combobox is not the first option
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
							Object[] data = {item.getSKU(),item.getBrand(),item.getName(),item.getCategory(),item.getstocks(),
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
		});
		
		deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = inventoryTable.getSelectedRow();
				if(i == -1) {
					JOptionPane.showMessageDialog(messageFrame, "Select a row to delete", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					if(JOptionPane.showConfirmDialog(messageFrame, "Are you sure you want to delete?", "Confirmation", JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE)  == 0) {
						model.removeRow(i);
					}
				}
			}
		});
		
		JLabel lblBrand = new JLabel("Brand:");
		lblBrand.setFont(new Font("Helvetica", Font.BOLD, 12));
		
		brandTF = new JTextField();
		brandTF.setFont(new Font("Helvetica", Font.PLAIN, 12));
		brandTF.setColumns(10);
		
		updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//try to parse the Strings for stocks and price and catch error
				try {
					int i = inventoryTable.getSelectedRow();
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
			}
		});

		clearBtn = new JButton("Clear");
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
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(addBtn, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
								.addComponent(updateBtn, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(clearBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(deleteBtn, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)))
						.addComponent(lblBrand, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(45)
					.addComponent(lblRegistrationHeading, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
					.addGap(41))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(brandTF)
					.addGap(102))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblName)
					.addContainerGap(172, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(nameTF, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
					.addGap(28))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblcategory)
					.addContainerGap(155, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(categoryCB, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblStocks, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(169))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(169, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(priceTF, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
						.addComponent(stocksTF, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
					.addGap(129))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblRegistrationHeading)
					.addGap(7)
					.addComponent(lblBrand, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(brandTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(nameTF, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
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
					.addGap(36)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(addBtn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(deleteBtn, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(updateBtn, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(clearBtn, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(46))
		);
		panel_1.setLayout(gl_panel_1);
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	manager.updateFile(inventoryTable);
	        	dispose();
		    }
		});
		
	}
}
