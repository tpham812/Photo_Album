package cs213.photoAlbum.control;

import java.util.List;

import cs213.photoAlbum.model.Album;
import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.model.User;

/**
 * @author dheeptha
 * The Interface IAlbumController.
 */
public interface IAlbumController {

	/**
	 * List albums.
	 *
	 * @param user the user
	 * @return the list
	 */
	List<Album> listAlbums(User user);

	/**
	 * Creates the album.
	 *
	 * @param albumName the album name
	 * @param user the user
	 * @return true, if successful
	 */
	boolean createAlbum(String albumName, User user);

	/**
	 * Delete album.
	 *
	 * @param albumName the album name
	 * @param user the user
	 * @return true, if successful
	 */
	boolean deleteAlbum(String albumName, User user);

	/**
	 * List photos.
	 *
	 * @param albumName the album name
	 * @param user the user
	 * @return the list
	 */
	List<Photo> listPhotos(String albumName, User user);

	/**
	 * Adds the photo.
	 *
	 * @param fileName the file name
	 * @param caption the caption
	 * @param albumName the album name
	 * @param user the user
	 * @return true, if successful
	 */
	boolean addPhoto(String fileName, String caption, String albumName, User user);

	/**
	 * Move photo.
	 *
	 * @param fileName the file name
	 * @param oldAlbumName the old album name
	 * @param newAlbumName the new album name
	 * @param user the user
	 * @return true, if successful
	 */
	boolean movePhoto(String fileName, String oldAlbumName, String newAlbumName, User user);

	/**
	 * Removes the photo.
	 *
	 * @param fileName the file name
	 * @param albumName the album name
	 * @param user the user
	 * @return true, if successful
	 */
	boolean removePhoto(String fileName, String albumName, User user);

}
