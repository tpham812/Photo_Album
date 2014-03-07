package cs213.photoAlbum.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedSet;

/**
 * Photo owned by a {@link User} and part of multiple {@link Album}s.
 * @author dheeptha
 * 
 **/
public class Photo implements Serializable, IPhoto {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Name of the photo. */
	private String name;

	/** Photo caption. */
	private String caption;

	/**  Date time when the photo was taken. */
	private Calendar dateTime;

	/**  Tags associated with the photo. */
	private Map<String,SortedSet<String>> tags;
	
	/**
	 * Instantiates a new photo.
	 */
	public Photo(){
		this.tags = new LinkedHashMap<String, SortedSet<String>>();
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getCaption() {
		return caption;
	}

	@Override
	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Override
	public Calendar getDateTime() {
		return dateTime;
	}

	@Override
	public void setDateTime(Calendar dateTime) {
		this.dateTime = dateTime;
		this.dateTime.set(Calendar.MILLISECOND, 0);
	}

	@Override
	public Map<String, SortedSet<String>> getTags() {
		return tags;
	}

	@Override
	public void setTags(Map<String, SortedSet<String>> tags) {
		this.tags = tags;
	}

}
