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
	JPanel panel, panel2, panel3, panel4;
	JButton button, button2, button3;
	JLabel label;
	JTextField tf;
	JList<User> list;
	JScrollPane sp;
	
	public Admin(CmdView cv) {
		
		this.cv = cv;
		frame = new JFrame("Admin");
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel4 = new JPanel();
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
		button = new JButton("Logout");
		button2 = new JButton("Add User");
		button3 = new JButton("Delete");
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
		tf.setEditable(true);
		tf.setMaximumSize(new Dimension(125,20));
		
		sp.setMaximumSize(new Dimension(350, 350));
		
		panel2.add(Box.createRigidArea(new Dimension(420,0)));
		panel2.add(button);
		
		panel3.add(label);
		panel3.add(tf);
		panel3.add(Box.createRigidArea(new Dimension(10,0)));
		panel3.add(button2);
		
		panel4.add(Box.createRigidArea(new Dimension(17,0)));
		panel4.add(sp);
		panel4.add(Box.createRigidArea(new Dimension(20,0)));
		panel4.add(button3);
		
		panel.add(panel2);
		panel.add(Box.createRigidArea(new Dimension(0,15)));
		panel.add(panel3);
		panel.add(Box.createRigidArea(new Dimension(0,15)));
		panel.add(panel4);
		
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