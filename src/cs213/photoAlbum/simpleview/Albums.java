package cs213.photoAlbum.simpleview;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class Albums {

	CmdView cv;
	JFrame frame;
	ActionListener buttonListener;
	JPanel[] panel = new JPanel[4];
	JButton[] button = new JButton[5];
	JTable table;
	JScrollPane sp; 
	JTableHeader header;
	String[] columnNames = {"Album", "Number of Photos", "Date Range", "Oldest Date"};
	String[][] test = {{"Test", "2", "9/12/14 - 10/12/16", "10/12/16"},
						{"Test2", "3", "8/12/91 - 9/12/95", "9/12/95"}};
	
	public Albums(CmdView cv) {
		
		this.cv = cv;
		frame = new JFrame("Albums");
		panel[0] = new JPanel();
		panel[0].setLayout(new BoxLayout(panel[0], BoxLayout.Y_AXIS));
		panel[1] = new JPanel();
		panel[1].setLayout(new BoxLayout(panel[1], BoxLayout.X_AXIS));
		panel[2] = new JPanel();
		panel[2].setLayout(new BoxLayout(panel[2], BoxLayout.X_AXIS));
		panel[3] = new JPanel();
		panel[3].setLayout(new BoxLayout(panel[3], BoxLayout.X_AXIS));
		button[0] = new JButton("Logout");
		button[1] = new JButton("Search");
		button[2] = new JButton("Delete");
		button[3] = new JButton("Edit");
		button[4] = new JButton("Add");
		table = new JTable(test,columnNames);
		header = table.getTableHeader();
		sp = new JScrollPane(table);
		createAdminPanel();
	}
	
	public void createAdminPanel() {
		
		frame.setSize(500, 550);
		frame.setMaximumSize(new Dimension(500,550));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		table.setMaximumSize(new Dimension(450,400));
		
		panel[1].add(Box.createRigidArea(new Dimension(420,0)));
		panel[1].add(button[0]);
		
		panel[2].add(button[1]);
		panel[2].add(Box.createRigidArea(new Dimension(25,0)));
		panel[2].add(button[2]);
		panel[2].add(Box.createRigidArea(new Dimension(25,0)));
		panel[2].add(button[3]);
		panel[2].add(Box.createRigidArea(new Dimension(25,0)));
		panel[2].add(button[4]);
		
		panel[3].add(Box.createRigidArea(new Dimension(21,0)));
		panel[3].add(header);
		
		panel[0].add(panel[1]);
		panel[0].add(Box.createRigidArea(new Dimension(0,15)));
		panel[0].add(panel[2]);
		panel[0].add(Box.createRigidArea(new Dimension(0,20)));
		panel[0].add(panel[3]);
		panel[0].add(table);
		
		frame.add(panel[0]);
		frame.setVisible(true);
	}
	
	class ButtonListener implements ActionListener {

		CmdView cv;
		
		public ButtonListener(CmdView cv) {
			
			this.cv = cv;
		}
		public void actionPerformed(ActionEvent e) {
			
			
		}
	}
}