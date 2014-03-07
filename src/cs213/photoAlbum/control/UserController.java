package cs213.photoAlbum.control;

import java.util.List;

import cs213.photoAlbum.model.Backend;
import cs213.photoAlbum.model.IUser;
import cs213.photoAlbum.model.User;

/**
 * Controller interface for {@link User} functions such as login, logout, add/delete/list users.
 *
 * @author dheeptha
 */
public class UserController implements IUserController {
	
	/** The backend. */
	private Backend backend;
	
	/**
	 * Instantiates a new user controller.
	 */
	public UserController(){
		this.backend = new Backend();		
	}

	@Override
	public List<String> listUsers() {
		return backend.listUsers();
	}

	@Override
	public boolean addUser(String userId, String userName) {

		IUser u = backend.readUser(userId);
		if(u != null){
			return false;
		}
		
		u = new User(userId, userName);		
		return backend.writeUser(u);		
	}

	@Override
	public boolean deleteUser(String userId) {
		return backend.deleteUser(userId);
	}

	@Override
	public IUser login(String userId) {
		return backend.readUser(userId);
	}

	@Override
	public void logout(IUser u) {
		backend.writeUser(u);		
	}

}
