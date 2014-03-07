package cs213.photoAlbum.control;

import java.util.Calendar;
import java.util.List;
import java.util.SortedSet;

import cs213.photoAlbum.model.IPhoto;
import cs213.photoAlbum.model.IUser;
import cs213.photoAlbum.model.Photo;

// TODO: Auto-generated Javadoc
/**
 * Controller interface for {@link Photo} management, such as adding/deleting tags, get photos by date range/tag.
 * @author dheeptha
 * 
 */
public interface IPhotoController {

	/**
	 * Add a tag to the user's photo.
	 *
	 * @param fileName the file name
	 * @param tagType the tag type
	 * @param tagValue the tag value
	 * @param user the user
	 * @return true, if successful
	 */
	boolean addTag(String fileName, String tagType, String tagValue, IUser user);

	/**
	 * Delete a tag associated with the user's photo.
	 *
	 * @param fileName the file name
	 * @param tagType the tag type
	 * @param tagValue the tag value
	 * @param user the user
	 * @return true, if successful
	 */
	boolean deleteTag(String fileName, String tagType, String tagValue, IUser user);

	/**
	 * Gets the photos by date range for the user.
	 *
	 * @param start the start
	 * @param end the end
	 * @param user the user
	 * @return the photos by date
	 */
	SortedSet<IPhoto> getPhotosByDate(Calendar start, Calendar end, IUser user);

	/**
	 * Gets the photos by tag for the user.
	 *
	 * @param tagNames the tag names
	 * @param tagValues the tag values
	 * @param user the user
	 * @return the photos by tag
	 */
	SortedSet<IPhoto> getPhotosByTag(List<String> tagNames, List<String> tagValues , IUser user);

	/**
	 * Contains photo.
	 *
	 * @param fileName the file name
	 * @param user the user
	 * @return true, if successful
	 */
	boolean containsPhoto(String fileName, IUser user);	

	/**
	 * File exists.
	 *
	 * @param fileName the file name
	 * @return true, if successful
	 */
	public boolean fileExists(String fileName);
}
