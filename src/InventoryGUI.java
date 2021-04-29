import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InventoryGUI extends JFrame {

	private JPanel contentPane;
	private JTable inventoryTable;
	private JTextField searchTF;
	private JScrollPane scrollPane;
	private JScrollBar scrollBar;
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
	private JTextField priceTF;
	private JLabel lblSKU;
	private JLabel lblSKUString;
	private JButton deleteBtn;
	private JButton updateBtn;
	private JButton clearBtn;
	private JLabel messageFrame;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20, 84, 55, 251, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 96, 462, 0, 0};
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
		
		searchTF = new JTextField();
		//searchTF.setSize(500,10);
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

		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 9;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		inventoryTable = new JTable();
		inventoryTable.setFont(new Font("Helvetica", Font.PLAIN, 12));
		inventoryTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"SKU", "Product", "Category", "Stocks", "Price"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Integer.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, true, false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		inventoryTable.getColumnModel().getColumn(0).setPreferredWidth(154);
		inventoryTable.getColumnModel().getColumn(1).setPreferredWidth(287);
		inventoryTable.getColumnModel().getColumn(2).setPreferredWidth(124);
		inventoryTable.getColumnModel().getColumn(3).setPreferredWidth(61);
		inventoryTable.getColumnModel().getColumn(4).setPreferredWidth(56);
		scrollPane.setViewportView(inventoryTable);
		
		scrollBar = new JScrollBar();
		GridBagConstraints gbc_scrollBar = new GridBagConstraints();
		gbc_scrollBar.gridheight = 9;
		gbc_scrollBar.fill = GridBagConstraints.VERTICAL;
		gbc_scrollBar.insets = new Insets(0, 0, 5, 5);
		gbc_scrollBar.anchor = GridBagConstraints.EAST;
		gbc_scrollBar.gridx = 6;
		gbc_scrollBar.gridy = 3;
		contentPane.add(scrollBar, gbc_scrollBar);
		
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
		nameTF.setColumns(10);
		
		JComboBox categoryCB = new JComboBox();
		categoryCB.addItem("-Select Category-"	);
		String[] categoryList = {"Men's Apparel","Women's Apparel","Appliances" ,"Kids and Toys","School Supplies","Accessories"};
		for(int i=0; i<categoryList.length; i++) {
			categoryCB.addItem(categoryList[i]);
		}
		
		stocksTF = new JTextField();
		stocksTF.setColumns(10);
		
		lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Helvetica", Font.BOLD, 12));
		
		priceTF = new JTextField();
		priceTF.setColumns(10);
		
		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//checks if the textfields are empty and the chosen option in the combobox
				if(!nameTF.getText().trim().isEmpty() && !stocksTF.getText().trim().isEmpty() && !priceTF.getText().trim().isEmpty()
						&& categoryCB.getSelectedIndex() !=0) {
					//try to parse text to integer and double or catch an error
					try {
						DefaultTableModel model = (DefaultTableModel)inventoryTable.getModel();
						Object[] data = {"SKUsample",nameTF.getText(),categoryCB.getSelectedIndex(),Integer.parseInt(stocksTF.getText()),
								Double.parseDouble(priceTF.getText())};
						model.addRow(data);
						nameTF.setText("");
						stocksTF.setText("");
						priceTF.setText("");
						categoryCB.setSelectedIndex(0);
					}
					catch(Exception e1) {
						JOptionPane.showMessageDialog(messageFrame, "Invalid Input!", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(messageFrame, "All details must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		lblSKU = new JLabel("Generated SKU:");
		lblSKU.setFont(new Font("Helvetica", Font.BOLD, 12));
		
		lblSKUString = new JLabel("N/A");
		lblSKUString.setFont(new Font("Helvetica", Font.PLAIN, 12));
		
		deleteBtn = new JButton("Delete");
		
		updateBtn = new JButton("Update");
		
		clearBtn = new JButton("Clear");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblName, Alignment.LEADING)
								.addComponent(nameTF, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblcategory))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(categoryCB, 0, 160, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblStocks)))
					.addContainerGap())
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(131, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(priceTF, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
						.addComponent(stocksTF, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
					.addGap(95))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSKU, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(68, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSKUString, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(addBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(updateBtn, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(clearBtn, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
						.addComponent(deleteBtn, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(38, Short.MAX_VALUE)
					.addComponent(lblRegistrationHeading, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblRegistrationHeading)
					.addGap(18)
					.addComponent(lblName)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(nameTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblcategory)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(categoryCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblStocks)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(stocksTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(priceTF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSKU, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSKUString)
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(addBtn, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(deleteBtn, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(updateBtn, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(clearBtn, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(45, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		//the main program will not be closed upon closing this frame
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
