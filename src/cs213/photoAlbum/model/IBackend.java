package cs213.photoAlbum.model;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author dheeptha
 * IBackend is the interface to perform backend functions
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
	 * Store the contents of the InputStream as a file represented by {@link Photo#getName()} for the user
	 *
	 * @param photo the photo
	 * @param user the user
	 * @param stream the stream
	 */
	void storeFile(Photo photo, InputStream stream, User user);

	/**
	 * Delete the user's photo
	 *
	 * @param photo the photo
	 * @param user the user
	 */
	void deleteFile(Photo photo, User user);

	/**
	 * Read user.
	 *
	 * @param userId the user id
	 */
	void readUser(String userId);

	/**
	 * Write user.
	 *
	 * @param u the user
	 */
	void writeUser(User u);

	/**
	 * Delete user.
	 *
	 * @param userId the user id
	 */
	void deleteUser(String userId);

	/**
	 * Gets the users corresponding to the userids.
	 *
	 * @param userIds the user ids
	 * @return the user
	 */
	List<User> getUser(List<String> userIds);

}
