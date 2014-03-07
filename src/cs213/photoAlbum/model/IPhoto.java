package cs213.photoAlbum.model;

import java.util.Calendar;
import java.util.Map;
import java.util.SortedSet;

public interface IPhoto {

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	abstract String getName();

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	abstract void setName(String name);

	/**
	 * Gets the caption.
	 * 
	 * @return the caption
	 */
	abstract String getCaption();

	/**
	 * Sets the caption.
	 * 
	 * @param caption
	 *            the new caption
	 */
	abstract void setCaption(String caption);

	/**
	 * Gets the date time.
	 * 
	 * @return the date time
	 */
	abstract Calendar getDateTime();

	/**
	 * Sets the date time.
	 * 
	 * @param dateTime
	 *            the new date time
	 */
	abstract void setDateTime(Calendar dateTime);

	/**
	 * Gets the tags.
	 *
	 * @return the tags
	 */
	abstract Map<String, SortedSet<String>> getTags();

	/**
	 * Sets the tags.
	 *
	 * @param tags the tags
	 */
	abstract void setTags(Map<String, SortedSet<String>> tags);

}