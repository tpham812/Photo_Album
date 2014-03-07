package cs213.photoAlbum.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Album of a {@link User} containing multiple {@link Photo}s.
 *
 * @author brett
 */
public class Album implements Serializable, IAlbum {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  Name of the album. */
	private String name; 
		
	/**  List of the photos. */
	public Map<String, IPhoto> photos; 
	
	/**
	 * Constructor for the album object. Creates new HashMap for the photos
	 *
	 * @param name name of the album
	 */
	public Album(String name){
		this.name = name; 
		this.photos = new LinkedHashMap<String, IPhoto>();
	}
	
	
	@Override
	public String recaptionPhoto(){
		return name; 
		
	}
	
	@Override
	public Collection<IPhoto> getPhotos(){
		return photos.values(); 
	}
	
	@Override
	public IPhoto getPhoto(String name) {
		return photos.get(name);
	}
	
	@Override
	public void setAlbumName(String name){
		this.name=name;
	}
	
	@Override
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
	
	@Override
	public Calendar maxPhotoDate(){
		
		Calendar cal = null;
		
		for(IPhoto p: photos.values()){
			
			if(cal == null) {
				cal = p.getDateTime();
			} else if (p.getDateTime().after(cal)){
				cal = p.getDateTime();
			}			
		}
		
		return cal;		
	}

	
	@Override
	public Calendar minPhotoDate(){
		
		Calendar cal = null;
		
		for(IPhoto p: photos.values()){
			
			if(cal == null) {
				cal = p.getDateTime();
			} else if (p.getDateTime().before(cal)){
				cal = p.getDateTime();
			}			
		}		
		return cal;		
	}

	@Override
	public Map<String, IPhoto> getPhotoMap() {
		return photos;
	}
	
	
}
