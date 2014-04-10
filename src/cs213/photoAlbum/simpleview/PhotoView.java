package cs213.photoAlbum.simpleview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedSet;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

import cs213.photoAlbum.model.IAlbum;
import cs213.photoAlbum.model.IPhoto;
import cs213.photoAlbum.util.CalendarUtils;

public class PhotoView extends JFrame {

	ViewContainer viewContainer;

	private JScrollPane iconsPane;

	private IAlbum album;

	private JLabel photoLabel = new JLabel();

	private JPanel editPanel;

	private IPhoto photo;

	private JTable tagTable;

	private JList<String> albumsList;
	
	private JTextField captionField;

	private GuiView guiView;

	public PhotoView(IAlbum album, GuiView guiView) throws Exception {

		this.guiView = guiView;
		this.viewContainer = guiView.viewContainer;
		this.album = album;
		this.editPanel = new JPanel(new GridBagLayout());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("");
		setMinimumSize(new Dimension(700, 700));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);

		photoLabel.setVerticalTextPosition(JLabel.TOP);
		photoLabel.setHorizontalTextPosition(JLabel.CENTER);
		photoLabel.setHorizontalAlignment(JLabel.CENTER);
		photoLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(photoLabel, BorderLayout.CENTER);

		add(editPanel, BorderLayout.EAST);

		iconsPane = new JScrollPane();
		JToolBar iconsBar = new JToolBar();
		iconsPane.getViewport().add(iconsBar);
		iconsPane.setPreferredSize(new Dimension(200, 150));
		add(iconsPane, BorderLayout.NORTH);

		DisplayPhotoAction photo1Action = null;

		for (IPhoto photo : album.getPhotos()) {

			ImageIcon fullIcon = getIcon(photo.getName(), photo.getCaption());

			if (fullIcon != null) {

				ImageIcon tIcon = new ImageIcon(makeThumbnail(fullIcon.getImage(), 100, 100));
				tIcon.setDescription(photo.getCaption());

				DisplayPhotoAction thumbAction = new DisplayPhotoAction(fullIcon, tIcon, photo, this);

				if (photo1Action == null) {
					photo1Action = thumbAction;
				}

				JButton thumbButton = new JButton(thumbAction);
				thumbButton.setText(photo.getCaption());
				thumbButton.setVerticalTextPosition(SwingConstants.BOTTOM);
				thumbButton.setHorizontalTextPosition(SwingConstants.CENTER);

				iconsBar.add(thumbButton, iconsBar.getComponentCount() - 1);
			}
		}

		if (photo1Action != null) {
			photo1Action.showPhoto();
		}

	}

	protected ImageIcon getIcon(String path, String description) throws Exception {

		return new ImageIcon((new File(path)).toURI().toURL(), description);
	}

	private Image makeThumbnail(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();
		return resizedImg;
	}

	private class DisplayPhotoAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		private Icon fullIcon;
		private PhotoView photoView;
		private IPhoto photo;

		public DisplayPhotoAction(Icon fullIcon, Icon thumb, IPhoto photo, PhotoView photoView) {
			this.fullIcon = fullIcon;
			putValue(LARGE_ICON_KEY, thumb);
			putValue(SHORT_DESCRIPTION, photo.getCaption());
			this.photoView = photoView;
			this.photo = photo;
			// this.photo = photo;
		}

		public void actionPerformed(ActionEvent e) {

			showPhoto();
		}

		public void showPhoto() {
			photoLabel.setIcon(fullIcon);
			photoLabel.setText(photo.getCaption());
			photoView.photo = this.photo;
			setTitle(photo.getCaption());

			editPanel.removeAll();

			GridBagConstraints c;

			JPanel ep2 = new JPanel(new GridBagLayout());

			c = new GridBagConstraints();
			c.insets = new Insets(5, 0, 5, 0);
			c.gridx = 0;
			c.gridy = 0;
			ep2.add(new JLabel("Pathname: "), c);

			c = new GridBagConstraints();
			c.insets = new Insets(5, 0, 5, 0);
			c.gridx = 1;
			c.gridy = 0;
			ep2.add(new JLabel(photo.getName()), c);

			c = new GridBagConstraints();
			c.insets = new Insets(5, 0, 5, 0);
			c.gridx = 0;
			c.gridy = 1;
			ep2.add(new JLabel("Date: "), c);

			c = new GridBagConstraints();
			c.insets = new Insets(5, 0, 5, 0);
			c.gridx = 1;
			c.gridy = 1;
			ep2.add(new JLabel(CalendarUtils.toFmtDate(photo.getDateTime())), c);

			c = new GridBagConstraints();
			c.insets = new Insets(5, 0, 5, 0);
			c.gridx = 0;
			c.gridy = 2;
			ep2.add(new JLabel("Caption:  "), c);

			c = new GridBagConstraints();
			c.insets = new Insets(5, 0, 5, 0);
			c.gridx = 1;
			c.gridy = 2;
			c.ipadx = 100;

			captionField = new JTextField(photo.getCaption());
			ep2.add(captionField, c);

			c = new GridBagConstraints();
			c.insets = new Insets(5, 0, 5, 0);
			c.gridx = 0;
			c.gridy = 0;
			editPanel.add(ep2, c);

			String[] columnNames = { "Tag type", "Value" };
			Object[][] data = new Object[20][2];

			int i = 0;
			for (Entry<String, SortedSet<String>> e1 : photo.getTags().entrySet()) {

				for (String e2 : e1.getValue()) {
					data[i][0] = e1.getKey();
					data[i][1] = e2;

					i++;
					if (i >= 20) {
						break;
					}
				}
				if (i >= 20) {
					break;
				}
			}

			while (i < 20) {
				data[i][0] = "";
				data[i][1] = "";
				i++;
			}

			tagTable = new JTable(data, columnNames);
			tagTable.setPreferredScrollableViewportSize(new Dimension(200, 100));
			tagTable.setFillsViewportHeight(true);
			tagTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

			JScrollPane scrollPane = new JScrollPane(tagTable);

			c = new GridBagConstraints();
			c.insets = new Insets(5, 0, 5, 0);
			c.gridx = 0;
			c.gridy = 1;
			c.ipadx = 200;
			c.ipady = 100;
			editPanel.add(scrollPane, c);

			ep2 = new JPanel(new GridBagLayout());

			c = new GridBagConstraints();
			c.insets = new Insets(5, 0, 5, 0);
			c.gridx = 0;
			c.gridy = 0;
			JLabel jLabel = new JLabel("Albums : ");
			ep2.add(jLabel, c);

			List<Integer> sels = new ArrayList<Integer>();

			Collection<IAlbum> albums = viewContainer.getUser().getAlbums();
			String listData[] = new String[albums.size()];
			i = 0;
			for (IAlbum ia : albums) {
				if (ia.getPhotoMap().containsKey(photo.getName())) {
					sels.add(i);
				}

				listData[i++] = ia.getAlbumName();
			}

			albumsList = new JList<String>(listData);
			scrollPane = new JScrollPane(albumsList);
			scrollPane.setPreferredSize(new Dimension(100, 100));
			albumsList.setSelectedIndices(toIntArray(sels));

			c = new GridBagConstraints();
			c.insets = new Insets(5, 0, 5, 0);
			c.gridx = 1;
			c.gridy = 0;
			ep2.add(scrollPane, c);

			c = new GridBagConstraints();
			c.insets = new Insets(0, 0, 0, 0);
			c.gridx = 0;
			c.gridy = 2;
			c.ipadx = 200;
			c.ipady = 100;

			editPanel.add(ep2, c);

			ep2 = new JPanel(new GridBagLayout());
			JButton button;
			button = new JButton("Save");
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					if(!captionField.getText().isEmpty()) {
						photo.setCaption(captionField.getText());
					}

					photo.getTags().clear();

					int nRow = tagTable.getRowCount();

					for (int i = 0; i < nRow; i++) {

						String key = tagTable.getValueAt(i, 0).toString();
						String val = tagTable.getValueAt(i, 1).toString();

						if (key != null && val != null && key.length() > 0 && val.length() > 0) {
							viewContainer.photoController.addTag(photo.getName(), key, val, viewContainer.getUser());
						}
					}

					List<String> sels = albumsList.getSelectedValuesList();

					for (String s : sels) {
						viewContainer.albumController.addPhoto(photo.getName(), photo.getCaption(), s,
								viewContainer.getUser());
					}

					ListModel<String> model = albumsList.getModel();

					for (int i = 0; i < model.getSize(); i++) {
						String aName = model.getElementAt(i);
						if (!sels.contains(aName)) {
							viewContainer.albumController.removePhoto(photo.getName(), aName, viewContainer.getUser());
						}
					}

					viewContainer.saveUser();
				}
			});
			c = new GridBagConstraints();
			c.insets = new Insets(0, 10, 0, 10);
			c.gridx = 1;
			c.gridy = 0;
			ep2.add(button, c);

			button = new JButton("Reload");
			button.addActionListener(this);
			c = new GridBagConstraints();
			c.insets = new Insets(0, 10, 0, 10);
			c.gridx = 2;
			c.gridy = 0;
			ep2.add(button, c);

			c = new GridBagConstraints();
			c.insets = new Insets(0, 10, 0, 10);
			c.gridx = 0;
			c.gridy = 3;
			editPanel.add(ep2, c);

			ep2 = new JPanel(new GridBagLayout());
			button = new JButton("Back to Albums");
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					photoView.dispose();
					guiView.albums.show();
				}
			});
			c = new GridBagConstraints();
			c.insets = new Insets(0, 10, 0, 10);
			c.gridx = 0;
			c.gridy = 0;
			ep2.add(button, c);

			button = new JButton("Add new photo");
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

				}
			});
			c = new GridBagConstraints();
			c.insets = new Insets(0, 10, 0, 10);
			c.gridx = 1;
			c.gridy = 0;
			ep2.add(button, c);

			c = new GridBagConstraints();
			c.insets = new Insets(0, 10, 0, 10);
			c.gridx = 0;
			c.gridy = 4;
			editPanel.add(ep2, c);

			editPanel.setVisible(true);
			validate();
			repaint();
		}

	}

	int[] toIntArray(List<Integer> list) {
		int[] ret = new int[list.size()];
		int i = 0;
		for (Integer e : list)
			ret[i++] = e.intValue();
		return ret;
	}
}
