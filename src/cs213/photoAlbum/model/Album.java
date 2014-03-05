package cs213.photoAlbum.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Album of a {@link User} containing multiple {@link Photo}s
 * 
 * @author brett
 * 
 *
 */
public class Album implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Name of the album */
	private String name; 
		
	/** List of the photos */
	public List<Photo> photoList; 
	
	/**
	 * Constructor for the album object
	 * @param name name of the album
	 */
	public Album(String name){
		this.name = name; 
		this.photoList = new ArrayList<Photo>();
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
	 * Returns the name of the album
	 * @return name name of album
	 */
	public String getAlbumName(){
		return name; 
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	
}
