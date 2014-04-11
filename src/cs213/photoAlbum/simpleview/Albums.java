package cs213.photoAlbum.simpleview;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import cs213.photoAlbum.model.IAlbum;
import cs213.photoAlbum.util.CalendarUtils;

public class Albums {

	GuiView guiView;
	PanelListener panelListener;
	JFrame[] frame = new JFrame[4];
	ActionListener buttonListener;
	JPanel[] panel = new JPanel[11];
	JTextField[] tf = new JTextField[2];
	JButton[] button = new JButton[10];
	JButton closeButton;
	JLabel[] label = new JLabel[3];
	JLabel errorLabel;
	JTable table;
	JScrollPane sp; 
	JTableHeader header;
	String[] columnNames = {"Album", "# Photos", "Date Range", "Oldest Date"};
	String[][] albumInfo;
	DefaultTableModel tableModel = null;
	Collection<IAlbum> albums;
	
	public Albums(GuiView gv) {
		
		guiView = gv;
		panelListener = new PanelListener(this);
		frame[0] = new JFrame("Albums");
		frame[0].addWindowListener(panelListener);
		buttonListener = new ButtonListener(this);
		panel[0] = new JPanel();
		panel[0].setLayout(new BoxLayout(panel[0], BoxLayout.Y_AXIS));
		panel[1] = new JPanel();
		panel[1].setLayout(new BoxLayout(panel[1], BoxLayout.X_AXIS));
		panel[2] = new JPanel();
		panel[2].setLayout(new BoxLayout(panel[2], BoxLayout.X_AXIS));
		panel[3] = new JPanel();
		panel[3].setLayout(new BoxLayout(panel[3], BoxLayout.X_AXIS));
		button[0] = new JButton("Logout");
		button[0].addActionListener(buttonListener);
		button[1] = new JButton("Search");
		button[1].addActionListener(buttonListener);
		button[2] = new JButton("Delete");
		button[2].addActionListener(buttonListener);
		button[3] = new JButton("Edit");
		button[3].addActionListener(buttonListener);
		button[4] = new JButton("Add");
		button[4].addActionListener(buttonListener);
		button[5] = new JButton("View");
		button[6] = new JButton("Save");
		button[6].addActionListener(buttonListener);
		button[7] = new JButton("Cancel");
		button[7].addActionListener(buttonListener);
		button[8] = new JButton("Save");
		button[8].addActionListener(buttonListener);
		button[9] = new JButton("Cancel");
		button[9].addActionListener(buttonListener);
		button[5].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				frame[0].dispose();
				
				int[] selection = table.getSelectedRows();
				int row;
				
				if(selection.length > 0) {				
					row = table.convertRowIndexToModel(selection[0]);
					IAlbum album = null;
					
					System.out.println("Row # " + row);
					
					Iterator<IAlbum> itr = albums.iterator();
					for(int j=0;j<=row && itr.hasNext();j++) {						
						album = itr.next();
					}
					
					try {
						guiView.viewContainer.setAlbum(album);
						PhotoView photoView = new PhotoView(guiView);
						photoView.setVisible(true);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		createAlbumPanel();
		createAddAlbumPanel();
		createEditAlbumPanel();
		createErrorPanel();
	}
	
	@SuppressWarnings("serial")
	public void createAlbumPanel() {

		table = new JTable(tableModel) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
				}
		};
		
		header = table.getTableHeader();
		sp = new JScrollPane(table);

		frame[0].setSize(500, 550);
		frame[0].setMaximumSize(new Dimension(500,550));
		frame[0].setResizable(false);
		frame[0].setLocationRelativeTo(null);
		
		table.setMaximumSize(new Dimension(450,400));
		table.setRowSelectionAllowed(true);
		
		panel[1].add(Box.createRigidArea(new Dimension(421,0)));
		panel[1].add(button[0]);
		
		panel[2].add(button[1]);
		panel[2].add(Box.createRigidArea(new Dimension(25,0)));
		panel[2].add(button[2]);
		panel[2].add(Box.createRigidArea(new Dimension(25,0)));
		panel[2].add(button[3]);
		panel[2].add(Box.createRigidArea(new Dimension(25,0)));
		panel[2].add(button[4]);
		
		panel[2].add(Box.createRigidArea(new Dimension(25,0)));
		panel[2].add(button[5]);
		
		panel[3].add(Box.createRigidArea(new Dimension(21,0)));
		panel[3].add(header);
		
		panel[0].add(panel[1]);
		panel[0].add(Box.createRigidArea(new Dimension(0,15)));
		panel[0].add(panel[2]);
		panel[0].add(Box.createRigidArea(new Dimension(0,20)));
		panel[0].add(panel[3]);
		panel[0].add(table);
		
		frame[0].add(panel[0]);
		frame[0].setVisible(false);
	}

	public void createAddAlbumPanel() {
		
		frame[1] = new JFrame("Add Album");
		frame[1].addWindowListener(panelListener);
		frame[1].setSize(300, 175);
		frame[1].setMaximumSize(new Dimension(300,175));
		frame[1].setResizable(false);
		frame[1].setLocationRelativeTo(null);
		
		panel[4] = new JPanel();
		panel[4].setLayout(new BoxLayout(panel[4], BoxLayout.Y_AXIS));
		panel[5] = new JPanel();
		panel[5].setLayout(new BoxLayout(panel[5], BoxLayout.X_AXIS));
		panel[6] = new JPanel();
		panel[6].setLayout(new BoxLayout(panel[6], BoxLayout.X_AXIS));
		
		tf[0] = new JTextField();
		tf[0].setAlignmentX(JTextField.CENTER_ALIGNMENT);
		tf[0].setEditable(true);
		tf[0].setMaximumSize(new Dimension(125,20));
		label[0] = new JLabel("Album Name:  ");
		
		panel[5].add(label[0]);
		panel[5].add(tf[0]);
		
		panel[6].add(button[6]);
		panel[6].add(Box.createRigidArea(new Dimension(10,0)));
		panel[6].add(button[7]);
		
		panel[4].add(Box.createRigidArea(new Dimension(0,35)));
		panel[4].add(panel[5]);
		panel[4].add(Box.createRigidArea(new Dimension(0,25)));
		panel[4].add(panel[6]);
		panel[4].add(Box.createRigidArea(new Dimension(0,25)));
		
		frame[1].add(panel[4]);
		frame[1].setVisible(false);
	}
	
	public void createEditAlbumPanel() {
		
		frame[2] = new JFrame("Edit Album");
		frame[2].addWindowListener(panelListener);
		frame[2].setSize(300, 175);
		frame[2].setMaximumSize(new Dimension(300,175));
		frame[2].setResizable(false);
		frame[2].setLocationRelativeTo(null);
		
		panel[7] = new JPanel();
		panel[7].setLayout(new BoxLayout(panel[7], BoxLayout.Y_AXIS));
		panel[8] = new JPanel();
		panel[8].setLayout(new BoxLayout(panel[8], BoxLayout.X_AXIS));
		panel[9] = new JPanel();
		panel[9].setLayout(new BoxLayout(panel[9], BoxLayout.X_AXIS));
		
		tf[1] = new JTextField();
		tf[1].setAlignmentX(JTextField.CENTER_ALIGNMENT);
		tf[1].setEditable(true);
		tf[1].setMaximumSize(new Dimension(125,20));
		label[1] = new JLabel("New album Name:  ");
		
		panel[8].add(label[1]);
		panel[8].add(tf[1]);
		
		panel[9].add(button[8]);
		panel[9].add(Box.createRigidArea(new Dimension(10,0)));
		panel[9].add(button[9]);
		
		panel[7].add(Box.createRigidArea(new Dimension(0,35)));
		panel[7].add(panel[8]);
		panel[7].add(Box.createRigidArea(new Dimension(0,25)));
		panel[7].add(panel[9]);
		panel[7].add(Box.createRigidArea(new Dimension(0,25)));
		
		frame[2].add(panel[7]);
		frame[2].setVisible(false);
	}
	
	public void createErrorPanel() {
		
		frame[3] = new JFrame("Error");
		frame[3].addWindowListener(panelListener);
		panel[10] = new JPanel();
		panel[10].setLayout(new BoxLayout(panel[10], BoxLayout.Y_AXIS));
		closeButton = new JButton("Close");
		closeButton.addActionListener(new ButtonListener(this));
		errorLabel = new JLabel();
		errorLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		closeButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		panel[10].add(Box.createRigidArea(new Dimension (0, 30)));
		panel[10].add(errorLabel);
		panel[10].add(Box.createRigidArea(new Dimension(0, 35)));
		panel[10].add(closeButton);
		frame[3].add(panel[10]);
		frame[3].setLocationRelativeTo(null);
		frame[3].setResizable(false);
		frame[3].setVisible(false);
	}
	
	public void displayUserAlbum() {
		
		albums = guiView.viewContainer.listAlbums();
		
		if (albums.isEmpty()) {
			albumInfo = new String[][]{};
		} else {
			
			albumInfo = new String[albums.size()][4];
			int i = 0;
			for (IAlbum a : albums) {
				Calendar max = a.maxPhotoDate();
				Calendar min = a.minPhotoDate();
				albumInfo[i][0] = a.getAlbumName(); 
				albumInfo[i][1] = Integer.toString(a.getPhotos().size());
										
				if (max == null) {
					albumInfo[i][2] = "";
					albumInfo[i][3] = "";
				} else {
					albumInfo[i][2] = CalendarUtils.toFmtDate(min) + " - " + CalendarUtils.toFmtDate(max);
					
					albumInfo[i][3] = CalendarUtils.toFmtDate(min);
				}
				i++;
			}
		}
		tableModel = new DefaultTableModel(albumInfo, columnNames);
		table.setModel(tableModel);
		if(!albums.isEmpty()) {
			table.setRowSelectionInterval(0, 0);
		}
	}
	
	public void show() {
		
		displayUserAlbum();
		frame[0].setVisible(true);
	}
	
	class ButtonListener implements ActionListener {

		Albums album;
		
		public ButtonListener(Albums al) {
			
			album = al;
		}
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == album.button[0]) {
				album.guiView.viewContainer.logout();
				album.frame[0].setVisible(false);
				album.frame[1].setVisible(false);
				album.guiView.login.show();
				album.frame[2].setVisible(false);
			}
			else if(e.getSource() == album.button[1]) {
				String num;
				if((num = (String)album.table.getValueAt(album.table.getSelectedRow(), 1)).equals("0")) {
					album.errorLabel.setText("No photos in album to search");
					album.frame[0].disable();
					album.frame[3].setSize(210, 150);
					album.frame[3].setVisible(true);
				}
				else {
					album.frame[0].setVisible(false);
					album.guiView.sp.show((String)album.table.getValueAt(album.table.getSelectedRow(), 0));
				}
			}
			else if(e.getSource() == album.button[2]) {
				album.guiView.viewContainer.deleteAlbum((String)table.getValueAt(table.getSelectedRow(), 0));
				displayUserAlbum();
			}
			else if(e.getSource() == album.button[3]) {
				album.tf[1].setText((String)album.table.getValueAt(album.table.getSelectedRow(), 0));
				album.frame[0].disable();
				album.frame[2].setVisible(true);
			}
			else if(e.getSource() == album.button[4]) {
				album.frame[0].disable();
				album.frame[1].setVisible(true);
			}
			else if(e.getSource() == album.button[6]) {
				String albumName = album.tf[0].getText();
				if(!albumName.equals("")) {
					boolean isExist = album.guiView.viewContainer.createAlbum(albumName);
					if(isExist) {
						album.displayUserAlbum();
						album.tf[0].setText(null);
						album.frame[0].enable();
						album.frame[1].setVisible(false);
					}
					else {
						album.errorLabel.setText("Album already exists");
						album.frame[1].disable();
						album.frame[3].setSize(200, 150);
						album.frame[3].setVisible(true);
					}
				}
				else {
						
					album.errorLabel.setText("Must enter in an Album Name before creation");
					album.frame[1].disable();
					album.frame[3].setSize(300, 150);
					album.frame[3].setVisible(true);
				}
			}
			else if(e.getSource() == album.button[7]) {
					album.frame[0].enable();
					album.frame[1].setVisible(false);
			}
			else if(e.getSource() == album.button[8]) {
				String albumName = album.tf[1].getText();
				if(!albumName.equals("")) {
					boolean isExist = album.guiView.viewContainer.isAlbumExist(albumName);
					if(!isExist) {
						album.guiView.viewContainer.editAlbum(albumName, (String)album.table.getValueAt(album.table.getSelectedRow(), 0));
						displayUserAlbum();
						album.tf[1].setText(null);
						album.frame[0].enable();
						album.frame[2].setVisible(false);
					}
					else {
						album.errorLabel.setText("Album name already exists");
						album.frame[2].disable();
						album.frame[3].setSize(210, 150);
						album.frame[3].setVisible(true);
					}
				}
				else {
					album.errorLabel.setText("Must enter in an album name before saving");
					album.frame[2].disable();
					album.frame[3].setSize(300, 150);
					album.frame[3].setVisible(true);
				}
			}
			else if(e.getSource() == album.button[9]) {
				album.frame[0].enable();
				album.frame[2].setVisible(false);
			}
			else if(e.getSource() == album.closeButton) {
				if(!album.frame[1].isVisible() && !album.frame[2].isVisible()) {
					album.frame[0].enable();
					album.frame[3].setVisible(false);
				}
				else if(!album.frame[1].isEnabled()) {
					album.frame[1].enable();
					album.frame[3].setVisible(false);
				}
				else {
					album.frame[2].enable();
					album.frame[3].setVisible(false);
				}
			}
		}
	}
	
	class PanelListener implements WindowListener {

		Albums album;
		
		public PanelListener(Albums al) {
			
			album = al;
		}
		
		@SuppressWarnings("deprecation")
		public void windowClosing(WindowEvent arg0) {
			
			if(arg0.getSource() == album.frame[0]) {
				album.guiView.viewContainer.logout();
				System.exit(0);
			}
			else if(!album.frame[1].isVisible() && !album.frame[2].isVisible()) {
				album.frame[0].enable();
				album.frame[3].setVisible(false);
			}
			else if(!album.frame[1].isEnabled() && !album.frame[0].isEnabled()) {
				album.tf[0].setText(null);
				album.frame[1].enable();
			}
			else if(!album.frame[2].isEnabled() && !album.frame[0].isEnabled()) {
				album.frame[2].enable();
			}	
			else {
				album.frame[0].enable();
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