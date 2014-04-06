package cs213.photoAlbum.simpleview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class SearchPhotos {

	ViewContainer viewContainer;
	ActionListener buttonListener;
	JButton[] button = new JButton[3];
	JFrame frame;
	JPanel[] panel = new JPanel[9];
	JLabel[] label = new JLabel[11];
	JComboBox<String>[] cb = new JComboBox[8];
	String[] Test = {"Test", "Test1", "Test2", "Test3"};	
	
	
	public SearchPhotos (ViewContainer cv){
		
		this.viewContainer = cv;
		initializeComponents();
		
	}
	public void initializeComponents() {
		
		frame = new JFrame("Search Photos");
		
		button[0] = new JButton("Search");
		button[1] = new JButton("+");
		button[2] = new JButton("Back");
		
		for(int i = 0; i < cb.length; i++) {
			cb[i] = new JComboBox<String>(Test);
			cb[i].setBackground(Color.white);
		}
	
		panel[0] = new JPanel();
		panel[0].setLayout(new BoxLayout(panel[0], BoxLayout.Y_AXIS));
		
		for(int i = 1; i < panel.length; i++) {
			panel[i] = new JPanel();
			panel[i].setLayout(new BoxLayout(panel[i], BoxLayout.X_AXIS));
		}
		
		label[0] = new JLabel("Month");
		label[1] = new JLabel("Day");
		label[2] = new JLabel("Year");
		label[3] = new JLabel("Date Range: ");
		label[4] = new JLabel("Tags: ");
		label[5] = new JLabel("Tag Type");
		label[6] = new JLabel("Tag Value");
		label[7] = new JLabel("to");
		label[8] = new JLabel("Month");
		label[9] = new JLabel("Day");
		label[10] = new JLabel("Year");
		
	}
	
	public void displayPanel() {
		
		frame.setSize(600, 320);
		frame.setMaximumSize(new Dimension(600,320));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		label[3].setFont(new Font(null, Font.BOLD, 16));
		panel[1].add(label[3]);
		panel[1].setMaximumSize(new Dimension(500,40));
		
		panel[2].add(cb[0]);
		panel[2].add(Box.createRigidArea(new Dimension(10, 0)));
		panel[2].add(cb[1]);
		panel[2].add(Box.createRigidArea(new Dimension(10, 0)));
		panel[2].add(cb[2]);
		panel[2].add(Box.createRigidArea(new Dimension(10, 0)));
		panel[2].add(label[7]);
		panel[2].add(Box.createRigidArea(new Dimension(10, 0)));
		panel[2].add(cb[3]);
		panel[2].add(Box.createRigidArea(new Dimension(10, 0)));
		panel[2].add(cb[4]);
		panel[2].add(Box.createRigidArea(new Dimension(10, 0)));
		panel[2].add(cb[5]);
		panel[2].add(Box.createRigidArea(new Dimension(8, 0)));
		panel[2].setMaximumSize(new Dimension(500,25));
		
		panel[3].add(Box.createRigidArea(new Dimension(10, 0)));
		panel[3].add(label[0]);
		panel[3].add(Box.createRigidArea(new Dimension(52, 0)));
		panel[3].add(label[1]);
		panel[3].add(Box.createRigidArea(new Dimension(55, 0)));
		panel[3].add(label[2]);
		panel[3].add(Box.createRigidArea(new Dimension(72, 0)));
		panel[3].add(label[8]);
		panel[3].add(Box.createRigidArea(new Dimension(52, 0)));
		panel[3].add(label[9]);
		panel[3].add(Box.createRigidArea(new Dimension(56, 0)));
		panel[3].add(label[10]);
		panel[3].setMaximumSize(new Dimension(500,40));
		
		label[4].setFont(new Font(null, Font.BOLD, 16));
		panel[4].add(label[4]);
		panel[4].setMaximumSize(new Dimension(500,40));
		
		panel[5].add(cb[6]);
		panel[5].add(Box.createRigidArea(new Dimension(15, 0)));
		panel[5].add(cb[7]);
		panel[5].add(Box.createRigidArea(new Dimension(15, 0)));
		panel[5].add(button[1]);
		panel[5].setMaximumSize(new Dimension(500,25));
		
		panel[6].add(Box.createRigidArea(new Dimension(65, 0)));
		panel[6].add(label[5]);
		panel[6].add(Box.createRigidArea(new Dimension(185, 0)));
		panel[6].add(label[6]);
		panel[6].setMaximumSize(new Dimension(500,40));
		
		panel[7].add(Box.createRigidArea(new Dimension(225,0)));
		panel[7].add(button[0]);
		panel[7].setMaximumSize(new Dimension(500,40));
		
		panel[8].add(button[2]);
		panel[8].setMaximumSize(new Dimension(500,40));
		
		panel[0].add(Box.createRigidArea(new Dimension(0,20)));
		panel[0].add(panel[1]);
		panel[0].add(panel[2]);
		panel[0].add(panel[3]);
		panel[0].add(panel[4]);
		panel[0].add(panel[5]);
		panel[0].add(panel[6]);
		panel[0].add(Box.createRigidArea(new Dimension(0, 15)));
		panel[0].add(panel[7]);
		panel[0].add(panel[8]);
		panel[0].add(Box.createRigidArea(new Dimension(0, 15)));
		
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
