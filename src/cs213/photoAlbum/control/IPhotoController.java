package cs213.photoAlbum.control;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.model.User;

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
	boolean addTag(String fileName, String tagType, String tagValue, User user);

	/**
	 * Delete a tag associated with the user's photo
	 *
	 * @param fileName the file name
	 * @param tagType the tag type
	 * @param tagValue the tag value
	 * @param user the user
	 * @return true, if successful
	 */
	boolean deleteTag(String fileName, String tagType, String tagValue, User user);

	/**
	 * Obtain all the info available about the photo.
	 *
	 * @param fileName the file name
	 * @param user the user
	 * @return the map
	 */
	Map<String, String> listPhotoInfo(String fileName, User user);

	/**
	 * Gets the photos by date range for the user
	 *
	 * @param start the start
	 * @param end the end
	 * @param user the user
	 * @return the photos by date
	 */
	List<Photo> getPhotosByDate(Calendar start, Calendar end, User user);

	/**
	 * Gets the photos by tag for the user.
	 *
	 * @param tags the tags
	 * @param user the user
	 * @return the photos by tag
	 */
	List<Photo> getPhotosByTag(List<String> tagNames, List<String> tagValues , User user);

}
