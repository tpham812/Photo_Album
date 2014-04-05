package cs213.photoAlbum.simpleview;

import cs213.photoAlbum.control.AlbumController;
import cs213.photoAlbum.control.IAlbumController;
import cs213.photoAlbum.control.IPhotoController;
import cs213.photoAlbum.control.IUserController;
import cs213.photoAlbum.control.PhotoController;
import cs213.photoAlbum.control.UserController;

public class AbstractView {

	/**
	 * Controller to manage user admin. 
	 */
	protected IUserController userController;
	/** The photo controller. */
	protected IPhotoController photoController;
	/** The album controller. */
	protected IAlbumController albumController;

	public AbstractView() {
		this.userController = new UserController();
		this.photoController = new PhotoController();
		this.albumController = new AlbumController();	
	}

}