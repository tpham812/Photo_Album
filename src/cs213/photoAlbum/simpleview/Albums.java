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
import cs213.photoAlbum.simpleview.Login.ButtonListener;
import cs213.photoAlbum.simpleview.Login.panelListener;
import cs213.photoAlbum.util.CalendarUtils;

public class Albums {

	GuiView guiView;
	PanelListener panelListener;
	JFrame frame, frame2, frame3, frame4;
	ActionListener buttonListener;
	JPanel[] panel = new JPanel[11];
	JTextField[] tf = new JTextField[2];
	JButton[] button = new JButton[9];
	JButton closeButton;
	JLabel[] label = new JLabel[3];
	JLabel errorLabel;
	JTable table;
	JScrollPane sp; 
	JTableHeader header;
	String[] columnNames = {"Album", "# Photos", "Date Range", "Oldest Date"};
	String[][] test;
	DefaultTableModel tableModel = null;
	
	Collection<IAlbum> albums;
	
	public Albums(GuiView gv) {
		
		guiView = gv;
		panelListener = new PanelListener(this);
		frame = new JFrame("Albums");
		frame.addWindowListener(panelListener);
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
		button[2] = new JButton("Delete");
		button[3] = new JButton("Edit");
		button[3].addActionListener(buttonListener);
		button[4] = new JButton("Add");
		button[4].addActionListener(buttonListener);
		button[5] = new JButton("View");
		button[6] = new JButton("Save");
		button[6].addActionListener(buttonListener);
		button[7] = new JButton("Cancel");
		button[7].addActionListener(buttonListener);
		button[8] = new JButton("Logout");
		button[8].addActionListener(buttonListener);
		button[5].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				frame.dispose();
				
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
						PhotoView photoView = new PhotoView(album);
						photoView.setVisible(true);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		displayPanel();
		createAddAlbumPanel();
		createErrorPanel();
		createEditAlbumPanel();
	}
	
	public void createErrorPanel() {
		
		frame4 = new JFrame("Error");
		frame4.addWindowListener(panelListener);
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
		frame4.add(panel[10]);
		frame4.setLocationRelativeTo(null);
		frame4.setResizable(false);
		frame4.setVisible(false);
	}
	public void createAddAlbumPanel() {
		
		frame2 = new JFrame("Add Album");
		frame2.addWindowListener(panelListener);
		frame2.setSize(300, 175);
		frame2.setMaximumSize(new Dimension(300,175));
		frame2.setResizable(false);
		frame2.setLocationRelativeTo(null);
		
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
		
		frame2.add(panel[4]);
		frame2.setVisible(false);
	}
	
	public void createEditAlbumPanel() {
		
		frame3 = new JFrame("Edit Album");
		frame3.addWindowListener(panelListener);
		frame3.setSize(300, 175);
		frame3.setMaximumSize(new Dimension(300,175));
		frame3.setResizable(false);
		frame3.setLocationRelativeTo(null);
		
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
		
		panel[9].add(button[6]);
		panel[9].add(Box.createRigidArea(new Dimension(10,0)));
		panel[9].add(button[7]);
		
		panel[7].add(Box.createRigidArea(new Dimension(0,35)));
		panel[7].add(panel[8]);
		panel[7].add(Box.createRigidArea(new Dimension(0,25)));
		panel[7].add(panel[9]);
		panel[7].add(Box.createRigidArea(new Dimension(0,25)));
		
		frame3.add(panel[7]);
		frame3.setVisible(false);
		
	}
	@SuppressWarnings("serial")
	public void displayPanel() {

		table = new JTable(tableModel) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
				}
		};
		
		header = table.getTableHeader();
		sp = new JScrollPane(table);

		frame.setSize(500, 550);
		frame.setMaximumSize(new Dimension(500,550));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
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
		
		frame.add(panel[0]);
		frame.setVisible(false);
	}
	
	public void setUserAlbum() {
		
		albums = guiView.viewContainer.listAlbums();
		
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
		tableModel = new DefaultTableModel(test, columnNames);
		table.setModel(tableModel);
	}
	
	public void show() {
		
		setUserAlbum();
		frame.setVisible(true);
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
				album.frame.setVisible(false);
				album.frame2.setVisible(false);
				album.guiView.login.show();
				album.frame3.setVisible(false);
			}
			
			else if(e.getSource() == album.button[4]) {
				album.frame.disable();
				album.frame2.setVisible(true);
			}

			else if(e.getSource() == button[7]) {
				if(album.frame2.isVisible()) {
					album.frame.enable();
					album.frame2.setVisible(false);
				}
				else {
					
					IAlbum al = album.guiView.viewContainer.getAlbum((String)table.getValueAt(table.getSelectedRow(), 0));
					album.tf[1].setText(al.getAlbumName());
					album.frame.enable();
					album.frame3.setVisible(false);
				}
			}
			else if(e.getSource() == album.button[3]) {
				album.frame.disable();
				album.frame3.setVisible(true);
			}
			else if(e.getSource() == button[6]) {
				if(album.frame2.isVisible()) {
					String albumName = album.tf[0].getText();
					if(!albumName.equals("")) {
						boolean isExist = album.guiView.viewContainer.createAlbum(albumName);
						if(isExist) {
							album.setUserAlbum();
							album.tf[0].setText(null);
							album.frame.enable();
							album.frame2.setVisible(false);
						}
						else {
							album.errorLabel.setText("Album already exists");
							album.frame2.disable();
							album.frame4.setSize(200, 150);
							album.frame4.setVisible(true);
						}
					}
					else {
						
						album.errorLabel.setText("Must enter in an Album Name before creation");
						album.frame2.disable();
						album.frame4.setSize(300, 150);
						album.frame4.setVisible(true);
					}
				}
				else {
					String albumName = album.tf[1].getText();
					if(!albumName.equals("")) {
						IAlbum al = album.guiView.viewContainer.getAlbum((String)table.getValueAt(table.getSelectedRow(), 0));
						IAlbum al2 = album.guiView.viewContainer.getAlbum(albumName);
						if(al2 == null) {
							al.setAlbumName(albumName);
							setUserAlbum();
							album.tf[1].setText(null);
							album.frame.enable();
							album.frame3.setVisible(false);
						}
						else {
							album.errorLabel.setText("Album name already exists");
							album.frame3.disable();
							album.frame4.setSize(210, 150);
							album.frame4.setVisible(true);
						}
					}
					else {
						album.errorLabel.setText("Must enter in an album name before saving");
						album.frame3.disable();
						album.frame4.setSize(300, 150);
						album.frame4.setVisible(true);
					}
				}
			}
			else if(e.getSource() == album.closeButton) {
				
				if(!album.frame2.isEnabled()) {
					album.frame2.enable();
					album.frame4.setVisible(false);
				}
				else {
					album.frame3.enable();
					album.frame4.setVisible(false);
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
			
			if(arg0.getSource() == album.frame) {
				album.guiView.viewContainer.logout();
				album.frame.setVisible(false);
				album.frame2.setVisible(false);
				album.guiView.login.show();
			}
			else if(!album.frame2.isEnabled() && !album.frame.isEnabled()) {
				album.tf[0].setText(null);
				album.frame2.enable();
			}
		//	else if(!album.frame3.isEnabled() && !album.frame.isEnabled()) {
		//		album.frame3.enable();
		//	}
			else {
				album.frame.enable();
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