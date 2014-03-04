package cs213.photoAlbum.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents the User, containing a unique ID, their full name, and the
 * album(s) of the user
 * 
 * @author brett
 **/
public class User implements Serializable {

	/** ID of the user. */
	private String id;

	/** full name of the user. */
	private String fname;

	/** List of albums. */
	public List<Album> albumList;

	/** User's photos indexed by the name of the file. */
	Map<String, Photo> photos;

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
	}

	/**
	 * Adds new album to the list of albums.
	 * 
	 * @param user
	 *            The user
	 */
	public void addAlbum(Album album) {
		/* check that album adds */
		if(albumList==null){
			List<Album> alb =	new ArrayList<Album>();	
			alb.add(album);
			this.albumList=alb;
			System.out.println("album created for user "+ this + ":");
			System.out.println(album.getAlbumName()); 
		}
		else {
			this.albumList.add(album);
				System.out.println("album created for user "+ this + ":");
				System.out.println(album.getAlbumName()); 
		}
	
	}

	/**
	 * deletes album .
	 */
	public void deleteAlbum(String albumName) {
		if (albumList == null){
			System.out.println("There are currently no albums");
		} else {
			for (int i = 0; i < this.albumList.size(); i++){
				if (albumName.equals(this.albumList.get(i).getAlbumName())){
					this.albumList.remove(this.albumList.get(i)); 
					System.out.println("deleted album from user " + this + ":");
					System.out.println(albumName); 
					return; 
				}
			}
			System.out.println("album does not exist for user " + this); 
		}
		//scroll through album list, find album and delete it 

	}

	/**
	 * Renames the specified album.
	 * 
	 * @param album
	 *            The album title which is to be renamed
	 * @return album The newly created album title
	 */
	public String rename(Album album) {
		return id;

	}

	/**
	 * To string.
	 * 
	 * @return id The users unique string ID
	 */
	public String toString() {
		return id;
	}

	/**
	 * Sets the users ID.
	 * 
	 * @param id
	 *            Users ID
	 */
	public void setUserID(String id) {
		this.id = id;
	}

	/**
	 * Sets the users full name.
	 * 
	 * @param fname
	 *            Users full name
	 */
	public void setUserFullName(String fname) {
		this.fname = fname;
	}

	/**
	 * Returns the users id.
	 * 
	 * @return id users id
	 */
	public String getUserID() {
		return id;
	}

	/**
	 * Returns the users full name.
	 * 
	 * @return fname Users full name
	 */
	public String getUserFullName() {
		return fname;
	}

	public List<Album> getAlbums() {
		if (this.albumList.size() == 0){
			System.out.println("No albums exist for user " + this);
		} else {
			System.out.println("Albums for user " + this + ":");
			for (int i = 0; i < this.albumList.size(); i++){
				System.out.println(this.albumList.get(i).getAlbumName()+ " " + "number of photos:" + this.albumList.get(i).getNumPhotos()+ ",");
			}	
		}
		return albumList; 
		
	}

	/**
	 * Gets the photos.
	 * 
	 * @return the photos
	 */
	public Map<String, Photo> getPhotos() {
		return photos;
	}

	/**
	 * Sets the photos.
	 * 
	 * @param photos
	 *            the photos
	 */
	public void setPhotos(Map<String, Photo> photos) {
		this.photos = photos;
	}

}