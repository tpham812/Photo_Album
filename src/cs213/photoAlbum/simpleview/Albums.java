package cs213.photoAlbum.simpleview;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Collection;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import cs213.photoAlbum.model.IAlbum;
import cs213.photoAlbum.util.CalendarUtils;

public class Albums {

	ViewContainer viewContainer;
	JFrame frame;
	ActionListener buttonListener;
	JPanel[] panel = new JPanel[4];
	JButton[] button = new JButton[5];
	JTable table;
	JScrollPane sp; 
	JTableHeader header;
	String[] columnNames = {"Album", "# Photos", "Date Range", "Oldest Date"};
	String[][] test;
	
	public Albums(ViewContainer cv) {
		
		this.viewContainer = cv;
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
		
	}
	
	public void displayPanel() {
		
		Collection<IAlbum> albums = viewContainer.listAlbums();
		
		if (albums.isEmpty()) {
			test = new String[][]{};
		} else {
			
			test = new String[albums.size()][4];
			int i = 0;
			for (IAlbum a : albums) {
				Calendar max = a.maxPhotoDate();
				Calendar min = a.minPhotoDate();

				//"Album", "Number of Photos", "Date Range", "Oldest Date"
				test[i][0] = a.getAlbumName(); 
				test[i][1] = Integer.toString(a.getPhotos().size());
										
				if (max == null) {
					test[i][2] = "";
					test[i][3] = "";
				} else {
					test[i][2] = CalendarUtils.toFmtDate(min) + " - " + CalendarUtils.toFmtDate(max);
					
					test[i][3] = CalendarUtils.toFmtDate(min);
				}
				
				i++;
			}
		}

		
		table = new JTable(test,columnNames);
		
		
		header = table.getTableHeader();
		sp = new JScrollPane(table);

		
		frame.setSize(500, 550);
		frame.setMaximumSize(new Dimension(500,550));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		table.setMaximumSize(new Dimension(450,400));
		
		panel[1].add(Box.createRigidArea(new Dimension(420,0)));
		panel[1].add(button[0]);
		
		panel[2].add(button[1]);
		panel[2].add(Box.createRigidArea(new Dimension(10,0)));
		panel[2].add(button[2]);
		panel[2].add(Box.createRigidArea(new Dimension(10,0)));
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

		ViewContainer cv;
		
		public ButtonListener(ViewContainer cv) {
			
			this.cv = cv;
		}
		public void actionPerformed(ActionEvent e) {
			
			
		}
	}
}