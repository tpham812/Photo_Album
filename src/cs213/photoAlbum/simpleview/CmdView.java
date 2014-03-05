package cs213.photoAlbum.simpleview;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import cs213.photoAlbum.control.AlbumController;
import cs213.photoAlbum.control.IAlbumController;
import cs213.photoAlbum.control.IPhotoController;
import cs213.photoAlbum.control.IUserController;
import cs213.photoAlbum.control.PhotoController;
import cs213.photoAlbum.control.UserController;
import cs213.photoAlbum.model.Album;
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
			if (user != null) {
				launchInteractiveMode(user);
			}

		} else {
			System.err.println("Error: Unknown command " + cmdName);
		}
	}

	private void launchInteractiveMode(User u) {

		Scanner scanner = new Scanner(System.in);
		List<String> params = null;

		while (scanner.hasNext()) {
			String l = scanner.nextLine();

			if (l.startsWith("createAlbum")) {

				params = getQuotedParams(l, 1);
				if (params.size() == 1) {
					if(albumController.createAlbum(params.get(0), u)){
						System.out.println("created album for user " + u.getUserID() + ":");						
					} else {
						System.out.println("album exists for user " + u.getUserID() + ":");
					}
					System.out.println(params.get(0));
				}

			} else if (l.startsWith("deleteAlbum")) {

				params = getQuotedParams(l, 1);
				if (params.size() == 1) {
					if(albumController.deleteAlbum(params.get(0), u)){
						System.out.println("deleted album from user " + u.getUserID() + ":");						
					} else {
						System.out.println("album does not exist for user " + u.getUserID() + ":");
					}	
					System.out.println(params.get(0));
				}

			} else if (l.startsWith("listAlbums")) {

				Collection<Album> albums = albumController.listAlbums(u);
				
				if(albums.isEmpty()){
					System.out.println("No albums exist for user " + u.getUserID());
				} else {
					System.out.println("Albums for user " + u.getUserID() + ":");
				}
				

			} else if (l.startsWith("listPhotos")) {

				params = getQuotedParams(l, 1);
				if (params.size() == 1) {
					albumController.listPhotos(params.get(0), u);
				}

			} else if (l.startsWith("addPhoto")) {

				params = getQuotedParams(l, 3);
				if (params.size() == 3) {
					albumController.addPhoto(params.get(0), params.get(1), params.get(2), u);
				}

			} else if (l.startsWith("movePhoto")) {
				params = getQuotedParams(l, 3);
				if (params.size() == 3) {
					albumController.movePhoto(params.get(0), params.get(1), params.get(2), u);
				}
			} else if (l.startsWith("removePhoto")) {

				params = getQuotedParams(l, 3);
				if (params.size() == 3) {
					albumController.removePhoto(params.get(0), params.get(1), u);
				}

			} else if (l.startsWith("addTag") || l.startsWith("deleteTag")) {

				params = getQuotedParams(l, 1);
				if (params.size() == 1) {
					String photo = params.get(0);
					l = l.substring(l.indexOf(photo) + photo.length() + 1);
					StringBuffer tagName = new StringBuffer();
					StringBuffer tagValue = new StringBuffer();
					parseTag(l, tagName, tagValue);

					if (tagName.length() > 0 && tagValue.length() > 0) {
						
						System.out.println(photo + " " + tagName + " " + tagValue);

						if (l.startsWith("addTag")) {
							photoController.addTag(photo, tagName.toString(), tagValue.toString(), u);
						} else {
							photoController.deleteTag(photo, tagName.toString(), tagValue.toString(), u);
						}
					}
				}

			} else if (l.startsWith("listPhotoInfo")) {

				params = getQuotedParams(l, 1);
				if (params.size() == 1) {
					photoController.listPhotoInfo(params.get(0), u);
				}

			} else if (l.startsWith("getPhotosByDate")) {

				String vals[] = l.split(" ");
				if (vals.length == 3) {
					Calendar start = parseDate(vals[1]);
					Calendar end = parseDate(vals[2]);

					if (start != null && end != null) {												
						photoController.getPhotosByDate(start, end, u);
					}
				}

			} else if (l.startsWith("getPhotosByTag")) {
				
				List<String> tagNames = new ArrayList<String>();
				List<String> tagValues = new ArrayList<String>();
				
				l = l.replaceAll("getPhotosByTag", "");				
				parseTag(l, tagNames, tagValues);
				
				photoController.getPhotosByTag(tagNames, tagValues, u);
				
				
			} else if (l.startsWith("logout")) {
				break;
			} else {
				System.err.println("Error: invalid command " + l);
			}

		}

		scanner.close();
	}
	
	private void parseTag(String l, List<String> tagNames, List<String> tagValues) {

		while(l.length() > 0) {
			StringBuffer tagName = new StringBuffer();
			StringBuffer tagValue = new StringBuffer();
			
			int i1 = l.indexOf('"');
			int i2 = l.indexOf('"', i1 + 1);
			String l1 = l.substring(0, i2 + 1);
			
			parseTag(l1, tagName, tagValue);

			if(tagValue.length() != 0) {
				
				tagNames.add(tagName.toString());
				tagValues.add(tagValue.toString());				
				l = l.substring(i2 + 1);
				
			} else {
				break;
			}
		
			l = l.substring(l.indexOf(',') + 1);
		}		
	}

	private void parseTag(String l, StringBuffer tagName, StringBuffer tagValue) {

		String sp[] = l.split(":");
		String val = null;
		if (sp.length == 2) {
			tagName.append(sp[0].trim());
			val = sp[1];

		} else if (sp.length == 1) {
			val = sp[0];
		}

		if (val != null) {

			List<String> params = getQuotedParams(val, 1);
			if (!params.isEmpty()) {
				tagValue.append(params.get(0));
			}
		}
	}

	private Calendar parseDate(String string) {

		Calendar cal = Calendar.getInstance();

		Date dateStr = null;
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");

		try {
			dateStr = formatter.parse(string);
			cal.setTime(dateStr);
			cal.set(Calendar.MILLISECOND, 0);
		} catch (ParseException e) {
			System.err.println("Failed to parse date " + string);
		}

		return cal;
	}

	private List<String> getQuotedParams(String l, int numParams) {

		int i = 0, j = 0;
		String s;
		List<String> strings = new ArrayList<String>();

		for (int k = 0; k < numParams; k++) {
			i = l.indexOf('"', i);
			j = l.indexOf('"', i + 1);

			if (i == -1 || j == -1) {
				strings.clear();
				break;
			}
			s = l.substring(i + 1, j);
			strings.add(s);

			i = j + 1;

		}

		return strings;
	}

	private User login(String[] args) {

		User user;

		if (args.length != 2) {
			System.err.println("Error: Usage for login <user id>");
		}

		String userId = args[1];
		user = userController.login(userId);
		if (user == null) {
			System.out.println("user " + userId + " does not exist");
		}

		return user;
	}

	private void deleteUser(String[] args) {
		if (args.length != 2) {
			System.err.println("Error: Usage for deleteuser <user id>");
			return;
		}

		String userId = args[1];
		boolean user = userController.deleteUser(userId);

		if (user) {
			System.out.println("deleted user " + userId);
		} else {
			System.out.println("user " + userId + " does not exist");
		}
	}

	private void addUser(String[] args) {
		if (args.length != 3) {
			System.err.println("Error: Usage for adduser <user id> \"<user name>\" ");
			return;
		}

		String userId = args[1];
		String name = args[2];

		boolean user = userController.addUser(userId, name);

		if (user) {
			System.out.println("created user " + userId + " with name " + name);
		} else {
			System.out.println("user " + userId + " already exists with name " + name);
		}
	}

	private void listUsers() {
		List<String> users = userController.listUsers();

		if (users.size() > 0) {
			for (String s : users) {
				System.out.println(s);
			}
		} else {
			System.out.println("no users exist");
		}
	}

}
