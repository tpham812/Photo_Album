package cs213.photoAlbum.simpleview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cs213.photoAlbum.model.IAlbum;
import cs213.photoAlbum.util.DefaultComboBoxModelAction;


public class SearchPhotos {
	
	
	GuiView guiView;
	ActionListener buttonListener;
	JButton[] button = new JButton[3];
	JFrame frame;
	JTextField tf;
	JPanel[] panel = new JPanel[8];
	JLabel[] label = new JLabel[11];
	JComboBox<Integer>[] cb = new JComboBox[8];
	int[] month = {1,2,3,4,5,6,7,8,9,10,11,12};
	int[] day = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
	DefaultComboBoxModel<Integer> monthModel, dayModel, yearModel;
	DefaultComboBoxModelAction modelAction = new DefaultComboBoxModelAction();
	
	public SearchPhotos (GuiView gv){
		
		guiView = gv;
		tf = new JTextField();
		tf.setSize(500, 100);
		tf.setMaximumSize(new Dimension(500,100));
		frame = new JFrame("Search Photos");
		
		button[0] = new JButton("Search");
		button[1] = new JButton("Back");
		
		monthModel = new DefaultComboBoxModel<Integer>();
		dayModel = new DefaultComboBoxModel<Integer>();
		yearModel = new DefaultComboBoxModel<Integer>();
		
		for(int i = 0; i < cb.length; i++) {
			cb[i] = new JComboBox<Integer>();
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
		createSearchPhotoPanel();
	}
	
	public void createSearchPhotoPanel() {
		
		modelAction.newComboBox(monthModel, month);
		modelAction.newComboBox(dayModel, day);	
		
		cb[0].setModel(monthModel);
		cb[1].setModel(dayModel);
		cb[3].setModel(monthModel);
		cb[4].setModel(dayModel);
		
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
		
		panel[5].add(tf);
		panel[5].setMaximumSize(new Dimension(500,100));
		
		panel[6].add(Box.createRigidArea(new Dimension(225,0)));
		panel[6].add(button[0]);
		panel[6].setMaximumSize(new Dimension(500,40));
		
		panel[7].add(button[1]);
		panel[7].setMaximumSize(new Dimension(500,40));
		
		panel[0].add(Box.createRigidArea(new Dimension(0,20)));
		panel[0].add(panel[1]);
		panel[0].add(panel[2]);
		panel[0].add(panel[3]);
		panel[0].add(Box.createRigidArea(new Dimension(0,10)));
		panel[0].add(panel[4]);
		panel[0].add(panel[5]);
		panel[0].add(Box.createRigidArea(new Dimension(0, 15)));
		panel[0].add(panel[6]);
		panel[0].add(panel[7]);
		panel[0].add(Box.createRigidArea(new Dimension(0, 15)));
		
		frame.add(panel[0]);
		frame.setVisible(false);	
	}
	
	public void setYearComboBox(String albumName) {
		
		IAlbum al = guiView.viewContainer.getAlbum(albumName);
		Calendar maxDate = al.maxPhotoDate();
		@SuppressWarnings("deprecation")
		int max = maxDate.getTime().getYear() + 1900;
		Calendar minDate = al.minPhotoDate();
		@SuppressWarnings("deprecation")
		int min = minDate.getTime().getYear() + 1900;
		modelAction.setYearComboBox(yearModel, max, min);
		cb[2].setModel(yearModel);
		cb[5].setModel(yearModel);
	}
	
	public void show(String albumName) {
		
		setYearComboBox(albumName);
		frame.setVisible(true);
	}
	
	class ButtonListener implements ActionListener {

		SearchPhotos sp;
		
		public ButtonListener(SearchPhotos sp) {
			
			this.sp = sp;
		}
		public void actionPerformed(ActionEvent e) {
			
			
		}
	}
}