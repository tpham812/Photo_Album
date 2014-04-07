package cs213.photoAlbum.simpleview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import cs213.photoAlbum.model.IAlbum;
import cs213.photoAlbum.model.IPhoto;

public class PhotoView extends JFrame {

	private JScrollPane iconsPane;
	
	private IAlbum album;
	
	private JLabel photoLabel = new JLabel();

	
	public PhotoView(IAlbum album) throws Exception {
		
		this.album = album;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("");
		setSize(600, 500);
		setLocationRelativeTo(null);
		
		photoLabel.setVerticalTextPosition(JLabel.BOTTOM);
		photoLabel.setHorizontalTextPosition(JLabel.CENTER);
		photoLabel.setHorizontalAlignment(JLabel.CENTER);
		photoLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(photoLabel, BorderLayout.CENTER);

		iconsPane = new JScrollPane();
		JToolBar iconsBar = new JToolBar();
		iconsPane.getViewport().add(iconsBar);
		iconsPane.setPreferredSize(new Dimension(200, 150));
		add(iconsPane, BorderLayout.NORTH);


		for(IPhoto photo:album.getPhotos()) {
			
			ImageIcon icon = getIcon(photo.getName(), photo.getCaption());

			if (icon != null) {

				ImageIcon tIcon = new ImageIcon(makeThumbnail(icon.getImage(), 100, 100));
				tIcon.setDescription(photo.getCaption());
				
				DisplayPhotoAction thumbAction = new DisplayPhotoAction(icon, tIcon, photo.getCaption());

				JButton thumbButton = new JButton(thumbAction);
				thumbButton.setText(photo.getCaption());
				thumbButton.setVerticalTextPosition(SwingConstants.BOTTOM);
				thumbButton.setHorizontalTextPosition(SwingConstants.CENTER);
				
				iconsBar.add(thumbButton, iconsBar.getComponentCount() - 1);
			}
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
		
		private Icon photo;
		private String caption;

		public DisplayPhotoAction(Icon photo, Icon thumb, String caption) {
			this.photo = photo;
			this.caption = caption;
			putValue(LARGE_ICON_KEY, thumb);			
			putValue(SHORT_DESCRIPTION, caption);
		}

		public void actionPerformed(ActionEvent e) {
			photoLabel.setIcon(photo);
			photoLabel.setText(caption);
			setTitle(caption);
		}
	}
}