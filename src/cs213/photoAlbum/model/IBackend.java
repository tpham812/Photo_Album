package cs213.photoAlbum.model;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author dheeptha
 * The Interface IBackend.
 */
public interface IBackend {

	/**
	 * Read file.
	 *
	 * @param p the p
	 * @return the file
	 */
	File readFile(Photo p);

	/**
	 * Adds the file.
	 *
	 * @param p the p
	 * @param stream the stream
	 */
	void addFile(Photo p, InputStream stream);

	/**
	 * Delete file.
	 *
	 * @param p the p
	 */
	void deleteFile(Photo p);

	/**
	 * Read user.
	 *
	 * @param userId the user id
	 */
	void readUser(String userId);

	/**
	 * Write user.
	 *
	 * @param u the u
	 */
	void writeUser(User u);

	/**
	 * Delete user.
	 *
	 * @param userId the user id
	 */
	void deleteUser(String userId);

	/**
	 * Gets the user.
	 *
	 * @param userIds the user ids
	 * @return the user
	 */
	List<User> getUser(List<String> userIds);

}
