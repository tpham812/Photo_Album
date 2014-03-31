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
	JPanel panel, panel2, panel3, panel4;
	JButton button, button2, button3, button4, button5;
	JTable table;
	JScrollPane sp; 
	JTableHeader header;
	String[] columnNames = {"Album", "Number of Photos", "Date Range", "Oldest Date"};
	String[][] test = {{"Test", "2", "9/12/14 - 10/12/16", "10/12/16"},
						{"Test2", "3", "8/12/91 - 9/12/95", "9/12/95"}};
	
	public Albums(CmdView cv) {
		
		this.cv = cv;
		frame = new JFrame("Albums");
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel4 = new JPanel();
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
		button = new JButton("Logout");
		button2 = new JButton("Search");
		button3 = new JButton("Delete");
		button4 = new JButton("Edit");
		button5 = new JButton("Add");
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
		
		
		panel2.add(Box.createRigidArea(new Dimension(420,0)));
		panel2.add(button);
		
		panel3.add(button2);
		panel3.add(Box.createRigidArea(new Dimension(25,0)));
		panel3.add(button3);
		panel3.add(Box.createRigidArea(new Dimension(25,0)));
		panel3.add(button4);
		panel3.add(Box.createRigidArea(new Dimension(25,0)));
		panel3.add(button5);
		
		panel4.add(Box.createRigidArea(new Dimension(21,0)));
		panel4.add(header);
		
		panel.add(panel2);
		panel.add(Box.createRigidArea(new Dimension(0,15)));
		panel.add(panel3);
		panel.add(Box.createRigidArea(new Dimension(0,20)));
		panel.add(panel4);
		panel.add(table);
		
		frame.add(panel);
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