package cs213.photoAlbum.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	public Map<String, Photo> photos; 
	
	/**
	 * Constructor for the album object
	 * @param name name of the album
	 */
	public Album(String name){
		this.name = name; 
		this.photos = new LinkedHashMap<String, Photo>();
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
	public Collection<Photo> getPhotos(){
		return photos.values(); 
	}
	
	public Photo getPhoto(String name) {
		return photos.get(name);
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
	
	public Calendar maxPhotoDate(){
		
		Calendar cal = null;
		
		for(Photo p: photos.values()){
			
			if(cal == null) {
				cal = p.getDateTime();
			} else if (p.getDateTime().after(cal)){
				cal = p.getDateTime();
			}			
		}
		
		return cal;		
	}

	
	public Calendar minPhotoDate(){
		
		Calendar cal = null;
		
		for(Photo p: photos.values()){
			
			if(cal == null) {
				cal = p.getDateTime();
			} else if (p.getDateTime().before(cal)){
				cal = p.getDateTime();
			}			
		}		
		return cal;		
	}
	
	
}
