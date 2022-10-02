package application.naive.client;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class ApplicationGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private NaiveApplicationController newApplicationController;
	private String path , alias;
	private JTextField textField_1;
	private JTextField textField_2, textField_3, textField_4;
	private JList<String> list;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationGui frame = new ApplicationGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ApplicationGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(10, 0, 101, 22);
		contentPane.add(menuBar);
		
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		newApplicationController = new NaiveApplicationController();
		JMenuItem mntmNewMenuItem = new JMenuItem("Register File");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fs = new JFileChooser(new File(System.getProperty("user.home") + "/Desktop"));
				fs.setDialogTitle("Load File");
				int result = fs.showOpenDialog(null);
		
				if (result == JFileChooser.APPROVE_OPTION) {
					File newFile = fs.getSelectedFile();
					path = newFile.getPath();
				} else {
					System.out.println("No File was selected");
					return;
				}
				String[] ar = path.split("\\.");
				String sep;
				if(ar[1].equals("csv")) {
					sep = ",";
				}else if(ar[1].equals("tsv")){
					sep ="\\t+";
				}else {
					sep ="|";
				}
				
				newApplicationController.registerFile(textField.getText(), path, sep);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Return Fields");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] columns = newApplicationController.getFileManager().getFileColumnNames(textField.getText());
				
				if (columns != null) {
					DefaultListModel<String> DML = new DefaultListModel<String>();
					
					for (int i=0; i<columns.length; i++) {
						DML.addElement(columns[i]);
					}
	
					list.setModel(DML);
				} else {
					System.out.println("No data");
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Return Stream");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alias = textField.getText();
				String outputFileName = alias + "NEWFILE";
				Map<String, List<String>> filters = getFilters();
				
				List<String[]> result = newApplicationController.getFileManager().filterStructuredFile(alias, filters);
				newApplicationController.saveToResultTextFile(outputFileName, result);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mnNewMenu_1 = new JMenuItem("Filter");
		mnNewMenu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alias = textField.getText();
				
				Map<String, List<String>> filters = getFilters();
				
				newApplicationController.executeFilterAndShowJTable(alias, filters, alias + "NEW");
			}
		});
		mnNewMenu.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Results in chart");
		mnNewMenu.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("BarChart");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Thread queryThread = new Thread() {
			         public void run() {
			        	alias = textField.getText();
			        	Map<String, List<String>> filters = getFilters();
			        	String yAxis = textField_4.getText();
			        	String xAxis = textField_3.getText();
			        	
			        	List<String[]> result = newApplicationController.getFileManager().filterStructuredFile(alias, filters);	
			        	newApplicationController.showSingleSeriesBarChart(alias, result, xAxis, yAxis, alias + "BarChart");	
			         }
			      };
			      queryThread.start();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("LineChart");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Thread queryThread = new Thread() {
			         public void run() {
			        	alias = textField.getText();
			        	Map<String, List<String>> filters = getFilters();
			        	
			        	String yAxis = textField_4.getText();
			        	String xAxis = textField_3.getText();
			        	
			        	List<String[]> result = newApplicationController.getFileManager().filterStructuredFile(alias, filters);	
			        	newApplicationController.showSingleSeriesLineChart(alias, result, xAxis, yAxis, alias + "BarChart");	
			         }
			      };
			      queryThread.start();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_6);
		
		JLabel lblNewLabel = new JLabel("Álias");
		lblNewLabel.setBounds(42, 156, 63, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(112, 153, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Filter");
		lblNewLabel_1.setBounds(42, 186, 69, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("List");
		lblNewLabel_2.setBounds(42, 211, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("x Axis");
		lblNewLabel_3.setBounds(42, 126, 69, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Y Axis");
		lblNewLabel_4.setBounds(42, 96, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		textField_1 = new JTextField();
		textField_1.setBounds(112, 184, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(112, 211, 299, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(112, 123, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(112, 93, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		list = new JList<String>();
		list.setBounds(270, 11, 137, 178);
		contentPane.add(list);
		
	}
	
	public Map<String, List<String>> getFilters() {
		String a = textField_1.getText();
		String []b = a.split(" ");			
		String c = textField_2.getText();
		String[] d = c.split(" ");			
		String[] e;
		
		Map<String, List<String>> allvalues = new HashMap<>();
		for (int i=0; i<b.length; i++) {
			
			e = d[i].split(",");
			List<String> x = Arrays.asList(e);
			allvalues.put(b[i], x);
			
		} 
		
		
		return allvalues;
	}
}