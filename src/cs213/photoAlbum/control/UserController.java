package cs213.photoAlbum.control;

import java.util.List;

import cs213.photoAlbum.model.Backend;
import cs213.photoAlbum.model.User;

public class UserController implements IUserController {
	
	private Backend backend;
	
	public UserController(){
		this.backend = new Backend();		
	}

	@Override
	public List<String> listUsers() {
		return backend.listUsers();
	}

	@Override
	public boolean addUser(String userId, String userName) {

		User u = backend.readUser(userId);
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
	public User login(String userId) {
		return backend.readUser(userId);
	}

	@Override
	public void logout(User u) {
		backend.writeUser(u);		
	}

}
