package cs213.photoAlbum.control;

import java.util.List;

import cs213.photoAlbum.model.User;

/**
 * @author dheeptha
 * The Interface IUserController.
 */
public interface IUserController {

	/**
	 * List users.
	 *
	 * @return the list
	 */
	List<User> listUsers();

	/**
	 * Adds the user.
	 *
	 * @param userId the user id
	 * @param userName the user name
	 * @return true, if successful
	 */
	boolean addUser(String userId, String userName);

	/**
	 * Delete the user.
	 *
	 * @param userId the user id
	 * @return true, if successful
	 */
	boolean deleteUser(String userId);

	/**
	 * Login.
	 *
	 * @param userId the user id
	 * @return the user
	 */
	User login(String userId);

	/**
	 * Logout.
	 */
	void logout();

}
