package cs213.photoAlbum.simpleview;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import cs213.photoAlbum.control.AlbumController;
import cs213.photoAlbum.control.IAlbumController;
import cs213.photoAlbum.control.IPhotoController;
import cs213.photoAlbum.control.IUserController;
import cs213.photoAlbum.control.PhotoController;
import cs213.photoAlbum.control.UserController;
import cs213.photoAlbum.model.IAlbum;
import cs213.photoAlbum.model.IUser;
import cs213.photoAlbum.util.CalendarUtils;

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
	
	public List<String> listUser() {
		
		return userController.listUsers();
	}

	public boolean addUser(String userId, String userName) {
		
		return userController.addUser(userId, userName);
	}
	
	public boolean deleteUser(String userId) {
		
		return userController.deleteUser(userId);
	}
	
	public Collection<IAlbum> listAlbums() {
		Collection<IAlbum> albums = albumController.listAlbums(user);

		return albums;
	}

}
