package cs213.photoAlbum.model;

import java.util.List;

/**
 * The class Album
 * @author brett
 * 
 *
 */
public class Album {
	
	/** Name of the album */
	private String name; 
	
	/** Number of photos */
	private int photos;
	
	/** List of the photos */
	private List<Photo> photoList; 
	
	/**
	 * Constructor for the album object
	 * @param name name of the album
	 * @param photos number of photos
	 * @param photoList	the list of photos
	 */
	public Album(String name, int photos, List<Photo> photoList){
		
	}
	
	/**
	 * Recaptions the photo
	 * @return name new name of photo
	 */
	public String recaptionPhoto(){
		return null;
	}
	
	/**
	 * Returns the list of photos
	 * @return photoList the list of photos
	 */
	public List<Photo> getPhotos(){
		return null;

	}
	
	
	/**
	 * Sets the name of the album
	 * @param name name of album
	 */
	public void setAlbumName(String name){
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