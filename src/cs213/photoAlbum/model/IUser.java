package cs213.photoAlbum.model;

import java.util.Collection;
import java.util.Map;


/**
 * The Interface IUser.
 */
public interface IUser {

	/**
	 * Adds new album to the list of albums.
	 *
	 * @param album the album
	 */
	abstract void addAlbum(Album album);

	/**
	 * Contains album.
	 *
	 * @param albumName the album name
	 * @return true, if successful
	 */
	abstract boolean containsAlbum(String albumName);

	/**
	 * Gets the album.
	 *
	 * @param albumName the album name
	 * @return the album
	 */
	abstract IAlbum getAlbum(String albumName);

	/**
	 * Adds the photo.
	 *
	 * @param photo the photo
	 * @param album the album
	 */
	abstract void addPhoto(Photo photo, IAlbum album);

	/**
	 * deletes album .
	 *
	 * @param albumName the album name
	 */
	abstract void deleteAlbum(String albumName);

	/**
	 * Renames the specified album.
	 * 
	 * @param album
	 *            The album title which is to be renamed
	 * @return album The newly created album title
	 */
	abstract String rename(IAlbum album);

	/**
	 * To string.
	 * 
	 * @return id The users unique string ID
	 */
	abstract String toString();

	/**
	 * Sets the users ID.
	 * 
	 * @param id
	 *            Users ID
	 */
	abstract void setUserID(String id);

	/**
	 * Sets the users full name.
	 * 
	 * @param fname
	 *            Users full name
	 */
	abstract void setUserFullName(String fname);

	/**
	 * Returns the users id.
	 * 
	 * @return id users id
	 */
	abstract String getUserID();

	/**
	 * Returns the users full name.
	 * 
	 * @return fname Users full name
	 */
	abstract String getUserFullName();

	/**
	 * Gets the albums.
	 *
	 * @return the albums
	 */
	abstract Collection<IAlbum> getAlbums();

	/**
	 * Gets the photos.
	 * 
	 * @return the photos
	 */
	abstract Map<String, IPhoto> getPhotos();

	/**
	 * Sets the photos.
	 * 
	 * @param photos
	 *            the photos
	 */
	abstract void setPhotos(Map<String, IPhoto> photos);

}