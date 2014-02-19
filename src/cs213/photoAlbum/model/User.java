package cs213.photoAlbum.model;

import java.util.List;


/**
 * @author brett
 *  New class User which will include the unique ID for each user, their full name, and the album(s) of the user
 *  
*/
public class User {
	private String id = ""; 
	private String fname = "";
	List<Album> albumList; 
	
	/**
	 * Constructor for the User object of the User class
	 * @author brett
	 * @param id The users unique string ID
	 * @param fname The users full name
	 */
	
	public User(String id , String fname){
	 this.id = id; 
	 this.fname = fname;
	}
	
	/**
	 * Adds new album to the list of albums
	 * @author brett
	 * 
	 * 
	 */
	public void addAlbum(){
		
	}
	/**
	 * deletes album associated to the user
	 * @author brett
	 * 
	 * 
	 */
	public void deleteAlbum(){ 
		
	}
	
	/**
	 * Renames the specified album
	 * @author brett
	 * @param album The album title which is to be renamed
	 * @return album The newly created album title
	 */
	public String rename(String album){
		return album; 
	}
	
	/**
	 * @author brett
	 * @return id The users unique string ID 
	 */
	public String toString(){
		return id; 
	}
}