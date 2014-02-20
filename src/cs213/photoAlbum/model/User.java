package cs213.photoAlbum.model;

import java.util.List;
import java.util.Map;


/**
 * Represents the User, containing a unique ID, their full name, and the album(s) of the user
 * 
 * @author brett
 **/
public class User {
	
	/**  ID of the user. */
	private String id; 
	
	/**  full name of the user. */
	private String fname;
	
	/**  List of albums. */
	List<Album> albumList; 
	
	/**  User's photos indexed by the name of the file. */
	Map<String,Photo> photos;
	 
	/**
	 * Constructor for the User object of the User class.
	 *
	 * @author brett
	 * @param id The users unique string ID
	 * @param fname The users full name
	 */	
	public User(String id , String fname){
		/** Users ID */
	 this.id = id; 
	 	/** Users full name*/
	 this.fname = fname;
	}
	
	/**
	 * Adds new album to the list of albums.
	 *
	 * @param user The user
	 */
	public void addAlbum(User user){
		
	}
	
	/**
	 * deletes album .
	 */
	public void deleteAlbum(){ 
		
	}
	
	/**
	 * Renames the specified album.
	 *
	 * @param album The album title which is to be renamed
	 * @return album The newly created album title
	 */
	public String rename(Album album){
		return id; 
		
	}
	
	/**
	 * To string.
	 *
	 * @return id The users unique string ID
	 */
	public String toString(){
		return id; 
	}
	
	
	/**
	 * Sets the users ID.
	 *
	 * @param id Users ID
	 */
	 public void setUserID(String id){
		 this.id = id; 
	 }
	 
	 
	 /**
 	 * Sets the users full name.
 	 *
 	 * @param fname Users full name
 	 */
	 public void setUserFullName(String fname){
		 this.fname = fname; 
	 }
	 
	 /**
 	 * Returns the users id.
 	 *
 	 * @return id users id
 	 */
	 public String getUserID(){
		 return id; 
	 }
	 
	 /**
 	 * Returns the users full name.
 	 *
 	 * @return fname Users full name
 	 */
	 public String getUserFullName(){
		 return fname; 
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
	 * @param photos the photos
	 */
	public void setPhotos(Map<String, Photo> photos) {
		this.photos = photos;
	}
	 
	 
}