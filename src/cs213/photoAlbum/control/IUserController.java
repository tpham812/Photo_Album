package cs213.photoAlbum.control;

import java.util.List;

import cs213.photoAlbum.model.IUser;
import cs213.photoAlbum.model.User;


/**
 * Controller interface for {@link User} functions such as login, logout, add/delete/list users.
 *
 * @author dheeptha
 */
public interface IUserController {

	/**
	 * List users.
	 *
	 * @return the list
	 */
	List<String> listUsers();

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
	IUser login(String userId);

	/**
	 * Logout.
	 *
	 * @param u the u
	 */
	void logout(IUser u);

}
