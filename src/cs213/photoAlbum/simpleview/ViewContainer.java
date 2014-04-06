package cs213.photoAlbum.simpleview;

import cs213.photoAlbum.control.AlbumController;
import cs213.photoAlbum.control.IAlbumController;
import cs213.photoAlbum.control.IPhotoController;
import cs213.photoAlbum.control.IUserController;
import cs213.photoAlbum.control.PhotoController;
import cs213.photoAlbum.control.UserController;
import cs213.photoAlbum.model.IUser;

public class ViewContainer {
	
	/**
	 * Controller to manage user admin. 
	 */
	protected IUserController userController;

	/** The photo controller. */
	protected IPhotoController photoController;

	/** The album controller. */
	protected IAlbumController albumController;
	
	private IUser user;


	public void setUser(IUser user) {
		this.user = user;
	}

	public ViewContainer() {

		this.userController = new UserController();
		this.photoController = new PhotoController();
		this.albumController = new AlbumController();	
	}
	
	public IUser login(String userId) {

		user = userController.login(userId);
		return user;
	}
	
	
	public IUser getUser() {
		return user;
	}
	

}
