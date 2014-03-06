package cs213.photoAlbum.simpleview;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;

import cs213.photoAlbum.control.AlbumController;
import cs213.photoAlbum.control.IAlbumController;
import cs213.photoAlbum.control.IPhotoController;
import cs213.photoAlbum.control.IUserController;
import cs213.photoAlbum.control.PhotoController;
import cs213.photoAlbum.control.UserController;
import cs213.photoAlbum.model.Album;
import cs213.photoAlbum.model.IAlbum;
import cs213.photoAlbum.model.IPhoto;
import cs213.photoAlbum.model.IUser;
import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.util.CalendarUtils;

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
			System.out.println("Error: Please input a command");
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

			IUser user = login(args);
			if (user != null) {
				launchInteractiveMode(user);
			}

		} else {
			System.out.println("Error: Unknown command " + cmdName);
		}
	}

	private void launchInteractiveMode(IUser u) {

		Scanner scanner = new Scanner(System.in);
		List<String> params = null;

		while (scanner.hasNext()) {
			String l = scanner.nextLine();

			if (l.startsWith("createAlbum")) {

				params = getQuotedParams(l, 1);
				if (params.size() == 1) {
					if (albumController.createAlbum(params.get(0), u)) {
						System.out.println("created album for user " + u.getUserID() + ":");
					} else {
						System.out.println("album exists for user " + u.getUserID() + ":");
					}
					System.out.println(params.get(0));
				} else {
					System.out.println("Error: Invalid arguments");
				}

			} else if (l.startsWith("deleteAlbum")) {

				params = getQuotedParams(l, 1);
				if (params.size() == 1) {
					if (albumController.deleteAlbum(params.get(0), u)) {
						System.out.println("deleted album from user " + u.getUserID() + ":");
					} else {
						System.out.println("album does not exist for user " + u.getUserID() + ":");
					}
					System.out.println(params.get(0));
				}else {
					System.out.println("Error: Invalid arguments");
				}

			} else if (l.startsWith("listAlbums")) {

				Collection<IAlbum> albums = albumController.listAlbums(u);

				if (albums.isEmpty()) {
					System.out.println("No albums exist for user " + u.getUserID());
				} else {
					System.out.println("Albums for user " + u.getUserID() + ":");
					for (IAlbum a : albums) {
						Calendar max = a.maxPhotoDate();
						Calendar min = a.minPhotoDate();

						if (max == null) {
							System.out.println(a.getAlbumName() + " number of photos: " + a.getPhotos().size());
						} else {
							System.out.println(a.getAlbumName() + " number of photos: " + a.getPhotos().size() + ", "
									+ CalendarUtils.toFmtDate(min) + " - " + CalendarUtils.toFmtDate(max));
						}
					}
				}

			} else if (l.startsWith("listPhotos")) {

				params = getQuotedParams(l, 1);
				if (params.size() == 1) {

					IAlbum a = albumController.getAlbum(params.get(0), u);

					if (a == null) {
						System.out.println("album does not exist for user " + u.getUserID() + ":");
						System.out.println(params.get(1));
					} else {
						Collection<IPhoto> photos = albumController.listPhotos(params.get(0), u);

						if (photos.isEmpty()) {
							System.out.println("No photos exist for album " + a.getAlbumName());

						} else {
							System.out.println("Photos for album " + a.getAlbumName() + ":");

							for (IPhoto p : photos) {
								System.out.println(p.getName() + " - " + CalendarUtils.toFmtDate(p.getDateTime()));
							}
						}
					}
				} else {
					System.out.println("Error: Invalid arguments");
				}

			} else if (l.startsWith("addPhoto")) {

				params = getQuotedParams(l, 3);
				if (params.size() == 3) {

					if (!photoController.fileExists(params.get(0))) {
						System.out.println("File " + params.get(0) + " does not exist");
					} else {
						IPhoto p = albumController.addPhoto(params.get(0), params.get(1), params.get(2), u);
						if (p != null) {
							System.out.println("Added photo " + params.get(0) + ":");
							System.out.println(p.getCaption() + " - " + "Album: " + params.get(2));
						} else {
							System.out.println("Photo " + params.get(0) + " already exists in album " + params.get(2));
						}
					}
				}else {
					System.out.println("Error: Invalid arguments");
				}

			} else if (l.startsWith("movePhoto")) {
				params = getQuotedParams(l, 3);
				if (params.size() == 3) {

					if (!albumController.containsPhoto(params.get(0), params.get(2), u)) {

						if (albumController.movePhoto(params.get(0), params.get(1), params.get(2), u)) {

							System.out.println("Moved photo " + params.get(0) + ":");
							System.out.println(params.get(0) + " - From album " + params.get(1) + " to album "
									+ params.get(2));
						} else {
							System.out.println("Photo " + params.get(0) + " does not exist in " + params.get(1));
						}
					}
				}else {
					System.out.println("Error: Invalid arguments");
				}
			} else if (l.startsWith("removePhoto")) {

				params = getQuotedParams(l, 2);
				if (params.size() == 2) {
					if (albumController.removePhoto(params.get(0), params.get(1), u)) {
						System.out.println("Removed photo:");
						System.out.println(params.get(0) + " - From album " + params.get(1));
					} else {
						System.out.println("Photo " + params.get(0) + " is not in album " + params.get(1));
					}
				}else {
					System.out.println("Error: Invalid arguments");
				}

			} else if (l.startsWith("addTag") || l.startsWith("deleteTag")) {
				boolean addPhoto = l.startsWith("addTag");
				params = getQuotedParams(l, 1);
				if (params.size() == 1) {
					String photo = params.get(0);
					l = l.substring(l.indexOf(photo) + photo.length() + 1);
					StringBuffer tagName = new StringBuffer();
					StringBuffer tagValue = new StringBuffer();
					parseTag(l, tagName, tagValue);

					if (!photoController.containsPhoto(photo, u)) {
						System.out.println("Error: Photo " + photo + " does not exist");

					} else if (tagName.length() > 0 && tagValue.length() > 0) {
						if (addPhoto) {
							if (photoController.addTag(photo, tagName.toString(), tagValue.toString(), u)) {

								System.out.println("Added tag:");
								System.out.println(photo + " " + tagName + ":" + tagValue);
							} else {
								System.out.println("Tag already exists for " + photo + " " + tagName + ":" + tagValue);
							}
						} else {

							if (photoController.deleteTag(photo, tagName.toString(), tagValue.toString(), u)) {

								System.out.println("Deleted tag:");
								System.out.println(photo + " " + tagName + ":" + tagValue);
							} else {
								System.out.println("Tag does not exist for " + photo + " " + tagName + ":" + tagValue);
							}

						}
					}

				}else {
					System.out.println("Error: Invalid arguments");
				}

			} else if (l.startsWith("listPhotoInfo")) {

				params = getQuotedParams(l, 1);
				if (params.size() == 1) {

					IPhoto p = u.getPhotos().get(params.get(0));

					if (p == null) {
						System.out.println("Photo " + params.get(0) + " does not exist");
					} else {

						System.out.println("Photo file name: " + p.getName());
						System.out.println("Album: " + formatAlbum(p, u.getAlbums()));
						System.out.println("Date: " + CalendarUtils.toFmtDate(p.getDateTime()));
						System.out.println("Caption: " + p.getCaption());
						System.out.println("Tags:");
						Map<String, SortedSet<String>> tags = 
								new TreeMap<String, SortedSet<String>>();
						
						tags.putAll(p.getTags());
						
						SortedSet<String> set = tags.remove("location");
						if(set != null) {
							for (String val : set) {
								System.out.println("location:" + val);
							}
						}
						
						set = tags.remove("person");
						if(set != null) {
							for (String val : set) {
								System.out.println("person:" + val);
							}
						}
						
						for (Entry<String, SortedSet<String>> e : tags.entrySet()) {
							set = e.getValue();
							for (String val : set) {
								System.out.println(e.getKey() + ":" + val);
							}
						}
					}
				}else {
					System.out.println("Error: Invalid arguments");
				}

			} else if (l.startsWith("getPhotosByDate")) {

				String vals[] = l.split(" ");
				if (vals.length == 3) {
					Calendar start = parseDate(vals[1]);
					Calendar end = parseDate(vals[2]);

					if (start != null && end != null) {
						SortedSet<IPhoto> photosByDate = photoController.getPhotosByDate(start, end, u);
						
						System.out.println("Photos for user " + u.getUserID() + " in range " + vals[1] + " to " + vals[2] +":") ;
						for (IPhoto p:photosByDate) {
							System.out.println(p.getCaption()+ " - Album: " + formatAlbum(p, u.getAlbums()) + " - Date: " + CalendarUtils.toFmtDate(p.getDateTime()));	
						}
					}
				}else {
					System.out.println("Error: Invalid arguments");
				}

			} else if (l.startsWith("getPhotosByTag")) {

				List<String> tagNames = new ArrayList<String>();
				List<String> tagValues = new ArrayList<String>();

				l = l.replaceAll("getPhotosByTag", "");
				parseTag(l, tagNames, tagValues);

				if(!tagNames.isEmpty() && !tagValues.isEmpty()) {
					
					SortedSet<IPhoto> photosByDate = photoController.getPhotosByTag(tagNames, tagValues, u);;
					
					System.out.println("Photos for user " + u.getUserID() + " with tags " + l.trim() + ":") ;
					for (IPhoto p:photosByDate) {
						System.out.println(p.getCaption()+ " - Album: " + formatAlbum(p, u.getAlbums()) + " - Date: " + CalendarUtils.toFmtDate(p.getDateTime()));	
					}	
				}else {
					System.out.println("Error: Invalid arguments");
				}
				

			} else if (l.startsWith("logout")) {
				userController.logout(u);
				break;
			} else {
				System.out.println("Error: invalid command " + l);
			}

		}

		scanner.close();
	}

	private String formatAlbum(IPhoto p, Collection<IAlbum> albums) {

		StringBuffer buf = new StringBuffer();

		for (IAlbum a : albums) {
			if (a.getPhotos().contains(p)) {
				buf.append(a.getAlbumName()).append(",");
			}
		}

		if (buf.length() > 0) {
			buf.setLength(buf.length() - 1);
		}

		return buf.toString();
	}

	private void parseTag(String l, List<String> tagNames, List<String> tagValues) {

		while (l.length() > 0) {
			StringBuffer tagName = new StringBuffer();
			StringBuffer tagValue = new StringBuffer();

			int i1 = l.indexOf('"');
			int i2 = l.indexOf('"', i1 + 1);
			String l1 = l.substring(0, i2 + 1);

			parseTag(l1, tagName, tagValue);

			if (tagValue.length() != 0) {

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

		Calendar cal = null;

		try {
			cal = CalendarUtils.parseDate(string);

		} catch (ParseException e) {
			System.out.println("Error: Failed to parse date " + string);
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

	private IUser login(String[] args) {

		IUser user;

		if (args.length != 2) {
			System.out.println("Error: Usage for login <user id>");
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
			System.out.println("Error: Usage for deleteuser <user id>");
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
			System.out.println("Error: Usage for adduser <user id> \"<user name>\" ");
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
