package cs213.photoAlbum.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents the User, containing a unique ID, their full name, and the
 * album(s) of the user.
 *
 * @author brett
 */
public class User implements Serializable, IUser {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** ID of the user. */
	private String id;

	/** full name of the user. */
	private String fname;

	/** List of albums. */
	public Map<String, IAlbum> albumList;

	/** User's photos indexed by the name of the file. */
	Map<String, IPhoto> photos;

	/**
	 * Constructor for the User object of the User class.
	 * 
	 * @param id
	 *            The users unique string ID
	 * @param fname
	 *            The users full name
	 */
	public User(String id, String fname) {
		/** Users ID */
		this.id = id;
		/** Users full name */
		this.fname = fname;
		this.albumList = new LinkedHashMap<String, IAlbum>();
		this.photos = new LinkedHashMap<String, IPhoto>();
	}

	@Override
	public void addAlbum(Album album) {		
		
		albumList.put(album.getAlbumName(), album);				
	}
	
	@Override
	public boolean containsAlbum(String albumName) {
		return albumList.containsKey(albumName);
	}
	
	@Override
	public IAlbum getAlbum(String albumName) {
		return albumList.get(albumName);
	}

	@Override
	public void addPhoto(Photo photo, IAlbum album) {
		
		photos.put(photo.getName(), photo);		
	}
	
	@Override
	public IAlbum deleteAlbum(String albumName) {				
		return albumList.remove(albumName);
	}

	@Override
	public String rename(IAlbum album) {
		return id;

	}

	@Override
	public String toString() {
		return id;
	}

	@Override
	public void setUserID(String id) {
		this.id = id;
	}

	@Override
	public void setUserFullName(String fname) {
		this.fname = fname;
	}
	
	@Override
	public String getUserID() {
		return id;
	}

	@Override
	public String getUserFullName() {
		return fname;
	}

	@Override
	public Collection<IAlbum> getAlbums() {		
		return albumList.values(); 		
	}

	@Override
	public Map<String, IPhoto> getPhotos() {
		return photos;
	}

	@Override
	public void setPhotos(Map<String, IPhoto> photos) {
		this.photos = photos;
	}

}