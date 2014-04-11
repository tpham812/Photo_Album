package cs213.photoAlbum.simpleview;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;

import cs213.photoAlbum.control.AlbumController;
import cs213.photoAlbum.control.IAlbumController;
import cs213.photoAlbum.control.IPhotoController;
import cs213.photoAlbum.control.IUserController;
import cs213.photoAlbum.control.PhotoController;
import cs213.photoAlbum.control.UserController;
import cs213.photoAlbum.model.IAlbum;
import cs213.photoAlbum.model.IPhoto;
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
	
	private IAlbum album;

	protected List<IPhoto> photos;

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

	public boolean createAlbum(String albumName) {

		return albumController.createAlbum(albumName, user);
	}

	public IAlbum getAlbum(String albumName) {

		return albumController.getAlbum(albumName, user);
	}

	public boolean isAlbumExist(String albumName) {

		return user.containsAlbum(albumName);
	}

	public void editAlbum(String newAlbumName, String oldAlbumName) {

		albumController.editAlbum(newAlbumName, oldAlbumName, user);
	}

	public void logout() {

		userController.logout(user);
	}

	public boolean deleteAlbum(String albumName) {

		return albumController.deleteAlbum(albumName, user);
	}

	public Collection<IAlbum> listAlbums() {
		Collection<IAlbum> albums = albumController.listAlbums(user);

		return albums;
	}

	public boolean saveUser() {
		return userController.writeUser(user);
	}

	public IAlbum getAlbum() {
		return album;
	}

	public void setAlbum(IAlbum album) {
		this.album = album;
		
		if(album != null) {
			this.photos = new ArrayList<IPhoto>(album.getPhotos());
		} else {
			this.photos = null;
		}
	}

	public List<IPhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(Collection<IPhoto> photos) {
		this.photos = new ArrayList<IPhoto>(photos);
	}

	public SortedSet<IPhoto> getPhotosByDate(Calendar start, Calendar end) {
		
		return  photoController.getPhotosByDate(start, end, user);
	}
	
	public SortedSet<IPhoto> getPhotosByTag(List<String> tagNames, List<String> tagValues) {
		
		return photoController.getPhotosByTag(tagNames, tagValues, user);
	}
	public void setUser(IUser user) {
		this.user = user;
	}

}
