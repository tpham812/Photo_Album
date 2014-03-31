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

public class Login {

	CmdView cv;
	JFrame frame;
	ActionListener buttonListener;
	JPanel panel, panel2, panel3;
	JButton button;
	JLabel label;
	JTextField tf;
	
	public Login(CmdView cv) {
		
		this.cv = cv;
		frame = new JFrame("Login");
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		button = new JButton("Login");
		createLoginPanel();
	}
	
	public void createLoginPanel() {
		
		
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
		
		panel2.add(Box.createRigidArea(new Dimension(0,25)));
		
		panel3.add(label);
		panel3.add(tf);
		panel3.add(Box.createRigidArea((new Dimension(15,0))));
		panel3.add(button);
		
		panel.add(panel2);
		panel.add(Box.createRigidArea(new Dimension(0,25)));
		panel.add(panel3);
		
		frame.add(panel);
		frame.setVisible(true);
	}

	class ButtonListener implements ActionListener {
		
		Login login;
		
		public ButtonListener(Login login) {
			
			this.login = login;
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}