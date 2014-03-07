package cs213.photoAlbum.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * IBackend is the interface to perform backend functions such as storage/retrieval of files and saving the user data.
 * @author dheeptha
 * 
 */
public class Backend implements IBackend {

	@Override
	public File retrievePhotoFile(IPhoto photo, IUser user) {
		return getFile(photo.getName());
	}
	
	public boolean fileExists(String name){
		return getFile(name).exists();
	}
	
	public File getFile(String name){
		return new File(name);
	}

	@Override
	public User readUser(String userId) {

		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		User user = null;

		try {
			File f = new File(getSerializedFile(userId));			
			if(!f.exists()) {
				return null;
			}
			
			fileIn = new FileInputStream(f);
			in = new ObjectInputStream(fileIn);
			user = (User) in.readObject();

		} catch (Exception e) {
			System.out.println("Error: Failed to read file for " + userId);
		} finally {
			close(in, userId);
		}
		return user;
	}
	
	public List<String> listUsers(){
		
		List<String> users = new ArrayList<String>();		
		File f = new File("data");
		
	    for (File e : f.listFiles()) {
	        
	    	String name = e.getName();
	    	if(name.endsWith(".ser")) {
		    	name = name.substring(0,name.length()-4);
		        users.add(name);
	    	}
	    }
		return users;
	}

	@Override
	public boolean  writeUser(IUser u) {

		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;

		try {
			fileOut = new FileOutputStream(getSerializedFile(u.getUserID()));
			out = new ObjectOutputStream(fileOut);
			out.writeObject(u);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: Failed to write file for " + u.getUserID());
			return false;
		} finally {
			close(out, u.getUserID());
		}		
	}

	private String getSerializedFile(String userId) {
		return "data/" + userId + ".ser";
	}
	
	@Override
	public boolean deleteUser(String userId) {

		return new File(getSerializedFile(userId)).delete();
	}

	@Override
	public List<User> getUser(List<String> userIds) {

		List<User> users = new ArrayList<User>();
		for (String s : userIds) {
			users.add(readUser(s));
		}
		return users;
	}

	private void close(ObjectInputStream in, String userId) {
		try {
			if (in != null) {

				in.close();
			}
		} catch (IOException e) {
			System.out.println("Error: Failed to close file for " + userId);
		}
	}


	private void close(ObjectOutputStream out, String userId) {
		try {
			if (out != null) {

				out.close();
			}
		} catch (IOException e) {
			System.out.println("Error: Failed to close file for " + userId);
		}
	}

}
