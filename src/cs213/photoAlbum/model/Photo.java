package cs213.photoAlbum.model;

import java.util.Calendar;
import java.util.Set;

/**
 * @author dheeptha
 * The Class Photo.
 */
public class Photo {

	/** The name. */
	private String name;

	/** The caption. */
	private String caption;

	/** The date time. */
	private Calendar dateTime;

	/** The tags. */
	private Set<String> tags;

	/** The user. */
	private User user;

	/**
	 * The Enum TAG.
	 */
	public static enum TAG {

		/** The location. */
		location,
		/** The person. */
		person
	}

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
	public Set<String> getTags() {
		return tags;
	}

	/**
	 * Sets the tags.
	 * 
	 * @param tags
	 *            the new tags
	 */
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	/**
	 * Gets the user.
	 * 
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 * 
	 * @param user
	 *            the new user
	 */
	public void setUser(User user) {
		this.user = user;
	};

}
