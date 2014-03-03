package cs213.photoAlbum.model;

import java.io.File;
import java.util.List;

/**
 * IBackend is the interface to perform backend functions such as storage/retrieval of files and saving the user data.
 * @author dheeptha
 * 
 */
public interface IBackend {

	/**
	 * Retrieve the user's photo file.
	 *
	 * @param photo the photo
	 * @param user the user
	 * @return the file
	 */
	File retrievePhotoFile(Photo photo, User user);

	/**
	 * Read user.
	 *
	 * @param userId the user id
	 */
	User readUser(String userId);

	/**
	 * Write user.
	 *
	 * @param u the user
	 */
	boolean writeUser(User u);

	/**
	 * Delete user.
	 *
	 * @param userId the user id
	 */
	boolean deleteUser(String userId);

	/**
	 * Gets the users corresponding to the userids.
	 *
	 * @param userIds the user ids
	 * @return the user
	 */
	List<User> getUser(List<String> userIds);

}
