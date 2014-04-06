package cs213.photoAlbum.simpleview;

import javax.swing.UIManager;

import cs213.photoAlbum.control.IAlbumController;
import cs213.photoAlbum.control.IPhotoController;
import cs213.photoAlbum.control.IUserController;

public class GuiView {
	
	protected Login login;
	
	protected SearchPhotos sp;

	/**
	 * Controller to manage user admin. 
	 */
	protected IUserController userController;

	/** The photo controller. */
	protected IPhotoController photoController;

	/** The album controller. */
	protected IAlbumController albumController;
	
	private ViewContainer viewContainer;
	
	public GuiView() {
		super();
		viewContainer = new ViewContainer();
		this.login = new Login(viewContainer);
		this.sp = new SearchPhotos(viewContainer);
		
	}

	protected void showGUI() {
		login.displayPanel();
		
	}
	
	public static void main(String[] args) {
		final GuiView guiView = new GuiView();
		
		/* Use an appropriate Look and Feel */
		try {

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				guiView.showGUI();
			}
		});
	}

}
