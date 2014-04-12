package cs213.photoAlbum.simpleview;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collection;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import cs213.photoAlbum.model.IAlbum;
import cs213.photoAlbum.model.IPhoto;
import cs213.photoAlbum.simpleview.SearchPhotos.PanelListener;

public class AddPhoto extends JFrame {

	ViewContainer viewContainer;

	private PanelListener panelListener;

	private JLabel errorLabel;

	private JFrame errorFrame;

	private IAlbum album;

	private JPanel editPanel;

	private JList<String> albumsList;

	private JTextField captionField;

	private GuiView guiView;

	private JLabel fileLabel;

	private String fileName = "";
	
	public AddPhoto(IAlbum album, GuiView gv) {

		this.guiView = gv;
		this.viewContainer = gv.viewContainer;
		this.album = album;
		this.editPanel = new JPanel(new GridBagLayout());
		createErrorPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("");
		setMinimumSize(new Dimension(600, 400));
		setLocationRelativeTo(null);

		GridBagConstraints c;

		int j = 0;

		JButton button;

		button = new JButton(" Logout ");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				guiView.viewContainer.logout();
				dispose();
				guiView.login.show();
			}
		});

		c = new GridBagConstraints();
		c.insets = new Insets(0, 10, 0, 10);
		c.gridx = 3;
		c.gridy = j++;
		editPanel.add(button, c);

		c = new GridBagConstraints();
		c.insets = new Insets(5, 0, 5, 0);
		c.gridx = 0;
		c.gridy = j;
		editPanel.add(new JLabel("Pathname: "), c);

		c = new GridBagConstraints();
		c.insets = new Insets(5, 0, 5, 5);
		c.gridx = 1;
		c.gridy = j;

		fileLabel = new JLabel();

		editPanel.add(fileLabel, c);

		// Create a file chooser
		final JFileChooser fc = new JFileChooser();

		JButton openButton = new JButton("Open a File...");
		openButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int returnVal = fc.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					// This is where a real application would open the file.
					
					fileName = file.getAbsolutePath();
					
					String name = fileName;
					if(name.length() > 40) {
						name = "..." + name.substring(name.length() - 40);
					}
					fileLabel.setText(name);
				}
			}
		});

		c = new GridBagConstraints();
		c.insets = new Insets(5, 0, 5, 0);
		c.gridx = 2;
		c.gridy = j++;
		editPanel.add(openButton, c);

		c = new GridBagConstraints();
		c.insets = new Insets(5, 0, 5, 0);
		c.gridx = 0;
		c.gridy = j;
		editPanel.add(new JLabel("Caption:  "), c);

		c = new GridBagConstraints();
		c.insets = new Insets(5, 0, 5, 0);
		c.gridx = 1;
		c.gridy = j++;
		c.ipadx = 100;

		captionField = new JTextField();
		editPanel.add(captionField, c);

		c = new GridBagConstraints();
		c.insets = new Insets(5, 0, 5, 0);
		c.gridx = 0;
		c.gridy = j;
		JLabel jLabel = new JLabel("Albums : ");
		editPanel.add(jLabel, c);

		Collection<IAlbum> albums = viewContainer.getUser().getAlbums();
		String listData[] = new String[albums.size()];
		int i = 0;
		for (IAlbum ia : albums) {

			listData[i++] = ia.getAlbumName();
		}

		albumsList = new JList<String>(listData);
		JScrollPane scrollPane = new JScrollPane(albumsList);
		scrollPane.setPreferredSize(new Dimension(100, 100));

		c = new GridBagConstraints();
		c.insets = new Insets(5, 0, 5, 0);
		c.gridx = 1;
		c.gridy = j++;
		c.ipadx = 100;
		c.ipady = 100;
		editPanel.add(scrollPane, c);

		button = new JButton("Cancel");
		button.addActionListener(new BackAction(this));

		c = new GridBagConstraints();
		c.insets = new Insets(0, 10, 0, 10);
		c.gridx = 0;
		c.gridy = j;
		editPanel.add(button, c);

		button = new JButton("Save");
		button.addActionListener(new AddAction(this));
		c = new GridBagConstraints();
		c.insets = new Insets(0, 10, 0, 10);
		c.gridx = 1;
		c.gridy = j;
		editPanel.add(button, c);

		editPanel.setVisible(true);

		setContentPane(editPanel);
		setVisible(true);
	}

	private class AddAction implements ActionListener {

		private AddPhoto addPhoto;

		public AddAction(AddPhoto addPhoto) {
			this.addPhoto = addPhoto;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (fileName != null && !fileName.isEmpty()) {
				if (!captionField.getText().isEmpty()) {

					List<String> sels = albumsList.getSelectedValuesList();

					if (!sels.isEmpty()) {
						for (String s : sels) {
							viewContainer.albumController.addPhoto(
									fileName,
									captionField.getText(), s,
									viewContainer.getUser());
						}

						viewContainer.saveUser();

						addPhoto.dispose();
						viewContainer.setAlbum(viewContainer.getAlbum());
						new PhotoView(guiView).setVisible(true);
					} else {
						errorLabel.setText("Must select at least one album");
						errorFrame.setSize(260, 150);
						errorFrame.setVisible(true);

					}
				} else {
					errorLabel.setText("Photo must have caption");
					errorFrame.setSize(260, 150);
					errorFrame.setVisible(true);

				}
			}else {
				errorLabel.setText("File is not specified");
				errorFrame.setSize(260, 150);
				errorFrame.setVisible(true);

			}
		}
	}

	private class BackAction implements ActionListener {

		private AddPhoto addPhoto;

		public BackAction(AddPhoto addPhoto) {
			this.addPhoto = addPhoto;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {

			addPhoto.dispose();
			new PhotoView(guiView).setVisible(true);
		}
	}

	public void createErrorPanel() {

		errorFrame = new JFrame("Error");
		errorFrame.addWindowListener(panelListener);
		errorFrame.setAlwaysOnTop(true);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		errorLabel = new JLabel();
		errorLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		panel.add(Box.createRigidArea(new Dimension(0, 30)));
		panel.add(errorLabel);
		panel.add(Box.createRigidArea(new Dimension(0, 35)));
		errorFrame.add(panel);
		errorFrame.setLocationRelativeTo(null);
		errorFrame.setResizable(false);
		errorFrame.setVisible(false);
	}

}
