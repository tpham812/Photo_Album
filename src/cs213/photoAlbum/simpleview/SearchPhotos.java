package cs213.photoAlbum.simpleview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cs213.photoAlbum.model.IAlbum;
import cs213.photoAlbum.model.IPhoto;
import cs213.photoAlbum.util.DefaultComboBoxModelAction;


/**
 * Creates the GUI for searching photos
 * 
 * @author Truong Pham
 */
public class SearchPhotos {
	
	
	DefaultTableModel tableModel;
	GuiView guiView;
	ActionListener buttonListener;
	PanelListener panelListener;
	JButton[] button = new JButton[2];
	JButton closeButton;
	JLabel errorLabel;
	JFrame[] frame = new JFrame[2];
	JTable table;
	JScrollPane sp;
	JPanel[] panel = new JPanel[9];
	JLabel[] label = new JLabel[11];
	JComboBox<String>[] cb = new JComboBox[8];
	String[] columnNames = {"Tag Type", "Tag Value"};
	int[] month = {1,2,3,4,5,6,7,8,9,10,11,12};
	int[] day = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
	DefaultComboBoxModel<String> monthModel, dayModel, yearModel, monthModel2, dayModel2, yearModel2;
	DefaultComboBoxModelAction modelAction = new DefaultComboBoxModelAction();
	
	/**
	 * Cosntructor to initialize GUI components
	 * 
	 * @param gv GuiView object
	 */
	public SearchPhotos (GuiView gv){
		
		guiView = gv;
		buttonListener = new ButtonListener(this);
		panelListener = new PanelListener(this);		
		frame[0] = new JFrame("Search Photos");
		frame[0].addWindowListener(panelListener);
		tableModel = new DefaultTableModel(columnNames,20);
		table = new JTable(tableModel);
		sp = new JScrollPane(table);
		sp.setSize(500, 250);
		
		button[0] = new JButton("Search");
		button[0].addActionListener(buttonListener);
		button[1] = new JButton("Back");
		button[1].addActionListener(buttonListener);
		
		monthModel = new DefaultComboBoxModel<String>();
		dayModel = new DefaultComboBoxModel<String>();
		yearModel = new DefaultComboBoxModel<String>();
		monthModel2 = new DefaultComboBoxModel<String>();
		dayModel2 = new DefaultComboBoxModel<String>();
		yearModel2 = new DefaultComboBoxModel<String>();
		
		for(int i = 0; i < cb.length; i++) {
			cb[i] = new JComboBox<String>();
			cb[i].setBackground(Color.white);
		}
			
		panel[0] = new JPanel();
		panel[0].setLayout(new BoxLayout(panel[0], BoxLayout.Y_AXIS));
		
		for(int i = 1; i < 8; i++) {
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
		createErrorPanel();
	}
	
	/**
	 * Creates the search photo panel
	 */
	public void createSearchPhotoPanel() {
		
		modelAction.newComboBox(monthModel, month);
		modelAction.newComboBox(monthModel2, month);
		modelAction.newComboBox(dayModel, day);
		modelAction.newComboBox(dayModel2, month);
		
		cb[0].setModel(monthModel);
		cb[0].setSelectedIndex(0);
		cb[1].setModel(dayModel);
		cb[1].setSelectedIndex(0);
		cb[3].setModel(monthModel2);
		cb[3].setSelectedIndex(0);
		cb[4].setModel(dayModel2);
		cb[4].setSelectedIndex(0);
		
		frame[0].setSize(600, 500);
		frame[0].setResizable(false);
		frame[0].setLocationRelativeTo(null);
		
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
		
		panel[5].add(sp);
		panel[5].setMaximumSize(new Dimension(500,200));
		
		panel[6].add(Box.createRigidArea(new Dimension(225,0)));
		panel[6].add(button[0]);
		panel[6].setMaximumSize(new Dimension(500,40));
		
		panel[7].add(button[1]);
		panel[7].setMaximumSize(new Dimension(500,40));
		
		panel[0].add(Box.createRigidArea(new Dimension(0,20)));
		panel[0].add(panel[1]);
		panel[0].add(Box.createRigidArea(new Dimension(0,5)));
		panel[0].add(panel[2]);
		panel[0].add(Box.createRigidArea(new Dimension(0,5)));
		panel[0].add(panel[3]);
		panel[0].add(Box.createRigidArea(new Dimension(0,20)));
		panel[0].add(panel[4]);
		panel[0].add(Box.createRigidArea(new Dimension(0,5)));
		panel[0].add(panel[5]);
		panel[0].add(Box.createRigidArea(new Dimension(0, 15)));
		panel[0].add(panel[6]);
		panel[0].add(panel[7]);
		panel[0].add(Box.createRigidArea(new Dimension(0, 15)));
		
		frame[0].add(panel[0]);
		frame[0].setVisible(false);	
	}
	
	public void createErrorPanel() {
		
		frame[1] = new JFrame("Error");
		frame[1].addWindowListener(panelListener);
		panel[8] = new JPanel();
		panel[8].setLayout(new BoxLayout(panel[8], BoxLayout.Y_AXIS));
		closeButton = new JButton("Close");
		closeButton.addActionListener(buttonListener);
		errorLabel = new JLabel();
		errorLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		closeButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		panel[8].add(Box.createRigidArea(new Dimension (0, 30)));
		panel[8].add(errorLabel);
		panel[8].add(Box.createRigidArea(new Dimension(0, 35)));
		panel[8].add(closeButton);
		frame[1].add(panel[8]);
		frame[1].setLocationRelativeTo(null);
		frame[1].setResizable(false);
		frame[1].setVisible(false);
	}
	
	/**
	 * Gets the start date and end date in the given album and store all the dates
	 * starting from start date up to end date into the year combo boxes
	 * 
	 * @param albumName album name
	 */
	public void setYearComboBox(String albumName) {
		
		IAlbum al = guiView.viewContainer.getAlbum(albumName);
		Calendar maxDate = al.maxPhotoDate();
		@SuppressWarnings("deprecation")
		int max = maxDate.getTime().getYear() + 1900;
		Calendar minDate = al.minPhotoDate();
		@SuppressWarnings("deprecation")
		int min = minDate.getTime().getYear() + 1900;
		modelAction.setYearComboBox(yearModel, max, min);
		modelAction.setYearComboBox(yearModel2, max, min);
		cb[2].setModel(yearModel);
		cb[2].setSelectedIndex(0);
		cb[5].setModel(yearModel2);
		cb[5].setSelectedIndex(0);
	}
	
	/**
	 * Shows the search photo panel while updating the start date
	 * and end date for given album
	 * 
	 * @param albumName album name
	 */
	public void show(String albumName) {
		
		setYearComboBox(albumName);
		frame[0].setVisible(true);
		
	}
	
	class ButtonListener implements ActionListener {

		SearchPhotos sp;
		
		public ButtonListener(SearchPhotos sp) {
			
			this.sp = sp;
		}
		
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == sp.button[0]) {
				if(!sp.table.isEditing()) {
					Date startDate, endDate;
					Calendar start = Calendar.getInstance(), end = Calendar.getInstance();
					List<String> tagValues = new ArrayList<String>();
					List<String> tagNames = new ArrayList<String>();
					SortedSet<IPhoto> tagSet;
					SortedSet<IPhoto> dateSet;
					if(sp.cb[0].getSelectedIndex() == 0 && sp.cb[1].getSelectedIndex() == 0 && sp.cb[2].getSelectedIndex() == 0 && sp.cb[3].getSelectedIndex() == 0 && sp.cb[4].getSelectedIndex() == 0 && sp.cb[5].getSelectedIndex() == 0) {
						startDate = null;
						endDate = null;
						boolean check = getTags(tagValues, tagNames);
						if(!check) {
							sp.errorLabel.setText("Must enter in both Tag Type and Tag Value");
							sp.frame[0].disable();
							sp.frame[1].setSize(260, 150);
							sp.frame[1].setVisible(true);
						}
						else {
							tagSet = sp.guiView.viewContainer.getPhotosByTag(tagNames, tagValues);
							if(tagValues.isEmpty()) {
								sp.errorLabel.setText("No tags or dates were given for search");
								sp.frame[0].disable();
								sp.frame[1].setSize(260, 150);
								sp.frame[1].setVisible(true);
							}
							else {
								// here is where you search only by tags
							}
						}
					}
					else if(sp.cb[0].getSelectedIndex() != 0 && sp.cb[1].getSelectedIndex() != 0 && sp.cb[2].getSelectedIndex() != 0 && sp.cb[3].getSelectedIndex() != 0 && sp.cb[4].getSelectedIndex() != 0 && sp.cb[5].getSelectedIndex() != 0) {
						startDate = new Date(Integer.parseInt((String)cb[2].getSelectedItem())-1900, Integer.parseInt((String)cb[0].getSelectedItem()) - 1, Integer.parseInt((String)cb[1].getSelectedItem()));
						endDate = new Date(Integer.parseInt((String)cb[5].getSelectedItem())-1900, Integer.parseInt((String)cb[3].getSelectedItem()) - 1, Integer.parseInt((String)cb[4].getSelectedItem()));
						if(startDate.compareTo(endDate) > 0 ) {
							sp.errorLabel.setText("Starting date must be before end date");
							sp.frame[0].disable();
							sp.frame[1].setSize(260, 150);
							sp.frame[1].setVisible(true);
						}
						else {
							start.setTime(startDate);
							end.setTime(endDate);
							boolean check = getTags(tagValues, tagNames);
							if(!check) {
								sp.errorLabel.setText("Must enter in both Tag Type and Tag Value");
								sp.frame[0].disable();
								sp.frame[1].setSize(260, 150);
								sp.frame[1].setVisible(true);
							}
							else {
								tagSet = sp.guiView.viewContainer.getPhotosByTag(tagNames, tagValues);
								dateSet = sp.guiView.viewContainer.getPhotosByDate(start, end);
								if(tagValues.isEmpty()) {
								
									//here is where you search by dates only 
								}
								else {
									tagSet.retainAll(dateSet); // intersect the tagSet and dateSet
									//here is where you search by both tags and date
								}
							}
						}
					}
					else {
						sp.errorLabel.setText("At least one of the date selection box is empty");
						sp.frame[0].disable();
						sp.frame[1].setSize(295, 150);
						sp.frame[1].setVisible(true);
					}
				}
				else {
					sp.errorLabel.setText("You must finish inputting in table before searching");
					sp.frame[0].disable();
					sp.frame[1].setSize(310, 150);
					sp.frame[1].setVisible(true);
				}
			}
			else if(e.getSource() == sp.button[1]) {
				if(!sp.table.isEditing()) {
					clearTagPanel();
					sp.frame[0].setVisible(false);
					sp.guiView.albums.show();
				}
			}
			else if(e.getSource() == sp.closeButton) {
				sp.frame[0].enable();
				sp.frame[1].setVisible(false);
			}
		}
		
		public boolean getTags(List<String> tagValues, List<String> tagNames) {
			
			String tagV = "";
			String tagN = "";
			Object obj1, obj2;
			
			for(int i = 0; i < sp.table.getRowCount(); i++) {
					if((obj1 = sp.table.getValueAt(i, 0)) == null && (obj2 = sp.table.getValueAt(i, 1)) == null) {
						continue;
					}
					else if(!(tagN =  sp.table.getValueAt(i, 0).toString()).trim().equals("") && !(tagV = sp.table.getValueAt(i, 1).toString()).trim().equals("")) {
						tagValues.add(tagV);
						tagNames.add(tagN);
					}
					else if(tagN.equals("") && tagV.equals("")) {
						continue;
					}
					else 
						return false;
			}
			return true;
		}
		
		public void clearTagPanel() {
			
			for(int i = 0; i < sp.table.getRowCount(); i++) {
				sp.tableModel.setValueAt(null, i, 0);
				sp.tableModel.setValueAt(null, i, 1);
			}
		}
	}
	
	class PanelListener implements WindowListener {

		SearchPhotos sp;
		
		public PanelListener(SearchPhotos sp) {
			
			this.sp = sp;
		}
		
		@SuppressWarnings("deprecation")
		public void windowClosing(WindowEvent arg0) {
			
			
			if(arg0.getSource() == sp.frame[0]) {
				sp.guiView.viewContainer.logout();
				System.exit(0);
			}
			else if(arg0.getSource() == sp.frame[1]) {
				sp.frame[0].enable();
				sp.frame[1].setVisible(false);
			}
		}

		public void windowActivated(WindowEvent arg0) {}

		public void windowClosed(WindowEvent arg0) {}
		
		public void windowDeactivated(WindowEvent arg0) {}

		public void windowDeiconified(WindowEvent arg0) {}

		public void windowIconified(WindowEvent arg0) {}

		public void windowOpened(WindowEvent arg0) {}
	}
}