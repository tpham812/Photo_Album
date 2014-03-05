package cs213.photoAlbum.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;

/**
 * Photo owned by a {@link User} and part of multiple {@link Album}s.
 * @author dheeptha
 * 
 **/
public class Photo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Name of the photo. */
	private String name;

	/** Photo caption. */
	private String caption;

	/**  Date time when the photo was taken. */
	private Calendar dateTime;

	/**  Tags associated with the photo. */
	private Map<String,String> tags;
	
	/** The person tags. */
	private Set<String> personTags;

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the caption.
	 * 
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * Sets the caption.
	 * 
	 * @param caption
	 *            the new caption
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * Gets the date time.
	 * 
	 * @return the date time
	 */
	public Calendar getDateTime() {
		return dateTime;
	}

	/**
	 * Sets the date time.
	 * 
	 * @param dateTime
	 *            the new date time
	 */
	public void setDateTime(Calendar dateTime) {
		this.dateTime = dateTime;
		this.dateTime.set(Calendar.MILLISECOND, 0);
	}

	/**
	 * Gets the tags.
	 *
	 * @return the tags
	 */
	public Map<String, String> getTags() {
		return tags;
	}

	/**
	 * Sets the tags.
	 *
	 * @param tags the tags
	 */
	public void setTags(Map<String, String> tags) {
		this.tags = tags;
	}

	/**
	 * Gets the person tags.
	 *
	 * @return the person tags
	 */
	public Set<String> getPersonTags() {
		return personTags;
	}

	/**
	 * Sets the person tags.
	 *
	 * @param personTags the new person tags
	 */
	public void setPersonTags(Set<String> personTags) {
		this.personTags = personTags;
	}


}
