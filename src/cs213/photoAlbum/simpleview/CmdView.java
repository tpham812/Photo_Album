package cs213.photoAlbum.simpleview;

import java.util.List;
import java.util.Scanner;

import cs213.photoAlbum.control.AlbumController;
import cs213.photoAlbum.control.IAlbumController;
import cs213.photoAlbum.control.IPhotoController;
import cs213.photoAlbum.control.IUserController;
import cs213.photoAlbum.control.PhotoController;
import cs213.photoAlbum.control.UserController;
import cs213.photoAlbum.model.User;

public class CmdView {

	/**
	 * java cs213.photoAlbum.simpleview.CmdView listusers java
	 * cs213.photoAlbum.simpleview.CmdView adduser <user id> "<user name>" java
	 * cs213.photoAlbum.simpleview.CmdView deleteuser <user id> java
	 * cs213.photoAlbum.simpleview.CmdView login <user id> createAlbum "<name>"
	 * deleteAlbum "<name>" listAlbums listPhotos "<name>" addPhoto "<fileName>"
	 * "<caption>" "<albumName>" movePhoto "<fileName>" "<oldAlbumName>"
	 * "<newAlbumName>" removePhoto "<fileName>" "<albumName>" addTag
	 * "<fileName>" <tagType>:"<tagValue>" deleteTag "<fileName>"
	 * <tagType>:"<tagValue>" listPhotoInfo "<fileName>" getPhotosByDate <start
	 * date> <end date> getPhotosByTag [<tagType>:]"<tagValue>"
	 * [,[<tagType>:]"<tagValue>"]... logout
	 */

	private IUserController userController;

	private IPhotoController photoController;

	private IAlbumController albumController;

	public CmdView() {
		this.userController = new UserController();
		this.photoController = new PhotoController();
		this.albumController = new AlbumController();
	}

	public static void main(String[] args) {

		CmdView cmdView = new CmdView();
		cmdView.processArgs(args);
	}

	public void processArgs(String[] args) {

		if (args.length == 0) {
			System.err.println("Error: Please input a command");
			return;
		}

		String cmdName = args[0];

		if (cmdName.equals("listusers")) {

			listUsers();			
		} else if (cmdName.equals("adduser")) {
			
			addUser(args);			
		} else if (cmdName.equals("deleteuser")) {
			
			deleteUser(args);
		} else if (cmdName.equals("login")) {
			
			User user = login(args);			
			if(user != null) {
				launchInteractiveMode();
			}
			

		} else {
			System.err.println("Error: Unknown command " + cmdName);
		}
	}

	private void launchInteractiveMode() {
		
        Scanner scanner = new Scanner(System.in);  

        while(scanner.hasNext()){
        	String l = scanner.nextLine();
        	System.out.println(l);
        }
        
        scanner.close();
        
		
		
	}

	private User login(String[] args) {
		
		User user;
		
		if(args.length != 2){
			System.err.println("Error: Usage for login <user id>");
		}
		
		String userId = args[1];						
		user = userController.login(userId);				
		if(user == null) {				
			System.out.println("user " + userId + " does not exist");
		}
		
		return user;
	}

	private void deleteUser(String[] args) {
		if(args.length != 2){
			System.err.println("Error: Usage for deleteuser <user id>");
			return;
		}
		
		String userId = args[1];						
		boolean user = userController.deleteUser(userId);
					
		if(user) {
			System.out.println("deleted user " + userId);
		} else {
			System.out.println("user " + userId + " does not exist");
		}
	}

	private void addUser(String[] args) {
		if(args.length != 3){
			System.err.println("Error: Usage for adduser <user id> \"<user name>\" ");
			return;
		}
		
		String userId = args[1];
		String name = args[2];
		
		boolean user = userController.addUser(userId, name);
					
		if(user) {
			System.out.println("created user "+userId + " with name "+name);
		} else {
			System.out.println("user "+userId + " already exists with name "+name);
		}
	}

	private void listUsers() {
		List<String> users = userController.listUsers();
		
		if(users.size() > 0) {
			for(String s: users) {
				System.out.println(s);
			}
		} else {
			System.out.println("no users exist");
		}
	}

}
