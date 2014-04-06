package cs213.photoAlbum.simpleview;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cs213.photoAlbum.model.IUser;

public class Login {

	ViewContainer viewContainer;
	JFrame frame;
	ActionListener buttonListener;
	JPanel[] panel = new JPanel[3];
	JButton button;
	JLabel label;
	JTextField tf;
	
	protected Admin admin;
	
	protected Albums album;
	
	public Login(ViewContainer cv) {
		
		this.viewContainer = cv;
		frame = new JFrame("Login");
		panel[0] = new JPanel();
		panel[0].setLayout(new BoxLayout(panel[0], BoxLayout.Y_AXIS));
		panel[1] = new JPanel();
		panel[1].setLayout(new BoxLayout(panel[1], BoxLayout.Y_AXIS));
		panel[2] = new JPanel();
		panel[2].setLayout(new BoxLayout(panel[2], BoxLayout.X_AXIS));
		button = new JButton("Login");
		button.addActionListener(new ButtonListener(this));
		

		this.admin = new Admin (viewContainer);
		this.album = new Albums(viewContainer);
	}
		
	public void displayPanel() {	

		frame.setSize(500, 150);
		frame.setMaximumSize(new Dimension(500,200));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tf = new JTextField();
		tf.setEditable(true);
		tf.setEditable(true);
		tf.setMaximumSize(new Dimension(125,20));
		
		label = new JLabel("User ID:     ");
		
		panel[1].add(Box.createRigidArea(new Dimension(0,25)));
		
		panel[2].add(label);
		panel[2].add(tf);
		panel[2].add(Box.createRigidArea((new Dimension(15,0))));
		panel[2].add(button);
		
		panel[0].add(panel[1]);
		panel[0].add(Box.createRigidArea(new Dimension(0,25)));
		panel[0].add(panel[2]);
		
		frame.add(panel[0]);
		frame.setVisible(true);
	}

	class ButtonListener implements ActionListener {
		
		Login login;
		
		public ButtonListener(Login l) {
			
			this.login = l;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			
			System.out.println(e);
			
			String userId = login.tf.getText();
			
			if(!userId.isEmpty()) {
				IUser u = login.viewContainer.login(login.tf.getText());
				
				if(u != null) {
					
					login.frame.dispose();
					
					if(u.getUserID().equals("admin")) {
						admin.displayPanel();						
					} else {
						album.displayPanel();
					}
					
				}
			}
		}
	}
}