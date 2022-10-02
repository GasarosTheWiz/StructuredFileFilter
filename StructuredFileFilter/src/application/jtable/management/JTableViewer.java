package application.jtable.management;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;



public class JTableViewer extends JPanel {

	private static final long serialVersionUID = 1L;
	private final JTable table;

	public JTableViewer(List<String[]> pData, String[] pColumnNames) {
		super(new BorderLayout(3, 3));
		TableModel tableModel = new TableModel(pData, pColumnNames);
		System.out.println("Rows: " + tableModel.getRowCount());
		System.out.println("Cols: " + tableModel.getColumnCount());
		this.table = new JTable(tableModel);
	}

	public void createAndShowJTable() {

		this.table.setPreferredScrollableViewportSize(new Dimension(800, 600));
		this.table.setFillsViewportHeight(true);
		JPanel ButtonOpen = new JPanel(new FlowLayout(FlowLayout.CENTER));
		add(ButtonOpen, BorderLayout.SOUTH);

		
		JScrollPane scrollPane = new JScrollPane(table);

		
		add(scrollPane, BorderLayout.CENTER);

		
		setBorder(new EmptyBorder(5, 5, 5, 5));

		 
		JFrame frame = new JFrame("JTable for the data");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		
		frame.setContentPane(this);

		
		frame.pack();
		frame.setVisible(true);
	}
}
