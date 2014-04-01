package cs213.photoAlbum.simpleview;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;

import cs213.photoAlbum.model.User;

public class Admin {

	CmdView cv;
	JFrame frame;
	ActionListener buttonListener;
	JPanel[] panel = new JPanel[4];
	JButton[] button = new JButton[3];
	JLabel label;
	JTextField tf;
	JList<User> list;
	JScrollPane sp;
	
	public Admin(CmdView cv) {
		
		this.cv = cv;
		frame = new JFrame("Admin");
		panel[0] = new JPanel();
		panel[0].setLayout(new BoxLayout(panel[0], BoxLayout.Y_AXIS));
		panel[1] = new JPanel();
		panel[1].setLayout(new BoxLayout(panel[1], BoxLayout.X_AXIS));
		panel[2] = new JPanel();
		panel[2].setLayout(new BoxLayout(panel[2], BoxLayout.X_AXIS));
		panel[3] = new JPanel();
		panel[3].setLayout(new BoxLayout(panel[3], BoxLayout.X_AXIS));
		button[0] = new JButton("Logout");
		button[1] = new JButton("Add User");
		button[2] = new JButton("Delete");
		list = new JList<User>();
		sp = new JScrollPane(list);
		label = new JLabel("User ID:   ");
		createAdminPanel();
	}
	
	public void createAdminPanel() {
		
		frame.setSize(500, 500);
		frame.setMaximumSize(new Dimension(500,500));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tf = new JTextField();
		tf.setEditable(true);
		tf.setMaximumSize(new Dimension(125,20));
		
		sp.setMaximumSize(new Dimension(350, 350));
		
		panel[1].add(Box.createRigidArea(new Dimension(420,0)));
		panel[1].add(button[0]);
		
		panel[2].add(label);
		panel[2].add(tf);
		panel[2].add(Box.createRigidArea(new Dimension(10,0)));
		panel[2].add(button[1]);
		
		panel[3].add(Box.createRigidArea(new Dimension(17,0)));
		panel[3].add(sp);
		panel[3].add(Box.createRigidArea(new Dimension(20,0)));
		panel[3].add(button[2]);
		
		panel[0].add(panel[1]);
		panel[0].add(Box.createRigidArea(new Dimension(0,15)));
		panel[0].add(panel[2]);
		panel[0].add(Box.createRigidArea(new Dimension(0,15)));
		panel[0].add(panel[3]);
		
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