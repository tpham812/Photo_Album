package cs213.photoAlbum.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Backend implements IBackend {

	@Override
	public File retrievePhotoFile(Photo photo, User user) {
		return new File(photo.getName());
	}

	@Override
	public User readUser(String userId) {

		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		User user = null;

		try {
			fileIn = new FileInputStream(getSerializedFile(userId));
			in = new ObjectInputStream(fileIn);
			user = (User) in.readObject();

		} catch (Exception e) {
			System.err.println("Error: Failed to read file for " + userId);
		} finally {
			close(fileIn, userId);
			close(in, userId);
		}
		return user;
	}

	@Override
	public void writeUser(User u) {

		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;

		try {
			fileOut = new FileOutputStream(getSerializedFile(u.getUserID()));
			out = new ObjectOutputStream(fileOut);
			out.writeObject(u);

		} catch (Exception e) {
			System.err.println("Error: Failed to write file for " + u.getUserID());
		} finally {
			close(out, u.getUserID());
			close(fileOut, u.getUserID());
		}
	}

	private String getSerializedFile(String userId) {
		return "data/" + userId + ".ser";
	}
	
	@Override
	public void deleteUser(String userId) {

		new File(getSerializedFile(userId)).delete();
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
			System.err.println("Error: Failed to close file for " + userId);
		}
	}

	private void close(FileInputStream fileIn, String userId) {
		try {
			if (fileIn != null) {
				fileIn.close();
			}
		} catch (IOException e) {
			System.err.println("Error: Failed to close file for " + userId);
		}
	}

	private void close(FileOutputStream fileOut, String userId) {
		try {
			if (fileOut != null) {
				fileOut.close();
			}
		} catch (IOException e) {
			System.err.println("Error: Failed to close file for " + userId);
		}
	}

	private void close(ObjectOutputStream out, String userId) {
		try {
			if (out != null) {

				out.close();
			}
		} catch (IOException e) {
			System.err.println("Error: Failed to close file for " + userId);
		}
	}

}
