package cs213.photoAlbum.model;

import java.io.Serializable;
import java.util.List;

/**
 * Album of a {@link User} containing multiple {@link Photo}s
 * 
 * @author brett
 * 
 *
 */
public class Album implements Serializable {
	
	
	/** Name of the album */
	private String name; 
	
	/** Number of photos */
	private int photos;
	
	/** List of the photos */
	public List<Photo> photoList; 
	
	/**
	 * Constructor for the album object
	 * @param name name of the album
	 * @param photos number of photos
	 * @param photoList	the list of photos
	 */
	public Album(String name, int photos, List<Photo> photoList){
		this.name = name; 
		this.photos = photos;
	}
	
	/**
	 * Recaptions the photo
	 * @return name new name of photo
	 */
	public String recaptionPhoto(){
		return name; 
		
	}
	
	/**
	 * Returns the list of photos
	 * @return photoList the list of photos
	 */
	public List<Photo> getPhotos(){
		return photoList; 
		
	}
	
	
	/**
	 * Sets the name of the album
	 * @param name name of album
	 */
	public void setAlbumName(String name){
		this.name=name;
	}
	
	/**
	 * Sets the number of photos in the album
	 * @param photos
	 */
	public void setAlbumPhotos(int photos){
		this.photos = photos; 
	}
	
	/**
	 * Returns the name of the album
	 * @return name name of album
	 */
	public String getAlbumName(){
		return name; 
	}
	
	/**
	 * Returns the number of photos in the album
	 * @return photos number of photos in album
	 */
	public int getNumPhotos(){
		return photos; 
	}

	
}
