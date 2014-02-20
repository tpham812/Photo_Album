package cs213.photoAlbum.control;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.model.User;

/**
 * @author dheeptha
 * The Interface IPhotoController.
 */
public interface IPhotoController {

	/**
	 * Adds the tag.
	 *
	 * @param fileName the file name
	 * @param tagType the tag type
	 * @param tagValue the tag value
	 * @param user the user
	 * @return true, if successful
	 */
	boolean addTag(String fileName, String tagType, String tagValue, User user);

	/**
	 * Delete tag.
	 *
	 * @param fileName the file name
	 * @param tagType the tag type
	 * @param tagValue the tag value
	 * @param user the user
	 * @return true, if successful
	 */
	boolean deleteTag(String fileName, String tagType, String tagValue, User user);

	/**
	 * List photo info.
	 *
	 * @param fileName the file name
	 * @param user the user
	 * @return the map
	 */
	Map<String, String> listPhotoInfo(String fileName, User user);

	/**
	 * Gets the photos by date.
	 *
	 * @param start the start
	 * @param end the end
	 * @param user the user
	 * @return the photos by date
	 */
	List<Photo> getPhotosByDate(Date start, Date end, User user);

	/**
	 * Gets the photos by tag.
	 *
	 * @param tags the tags
	 * @param user the user
	 * @return the photos by tag
	 */
	List<Photo> getPhotosByTag(Map<String, String> tags, User user);

}
