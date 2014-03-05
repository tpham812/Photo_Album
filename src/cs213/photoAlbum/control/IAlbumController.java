package cs213.photoAlbum.control;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import cs213.photoAlbum.model.Album;
import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.model.User;

/**
 * Controller interface to create/delete {@link Album}, and to manage {@link Photo} in the {@link Album}
 * @author dheeptha
 * .
 */
public interface IAlbumController {

	/**
	 * List the user's albums
	 *
	 * @param user the user
	 * @return the list
	 */
	Collection<Album> listAlbums(User user);

	/**
	 * Creates an album for the user
	 *
	 * @param albumName the album name
	 * @param user the user
	 * @return true, if successful
	 */
	boolean createAlbum(String albumName, User user);

	
	Album getAlbum(String albumName, User user);
	
	
	/**
	 * Deletes the user's album 
	 *
	 * @param albumName the album name
	 * @param user the user
	 * @return true, if successful
	 */
	boolean deleteAlbum(String albumName, User user);

	/**
	 * List all the photos in the album
	 *
	 * @param albumName the album name
	 * @param user the user
	 * @return the list
	 */
	Collection<Photo> listPhotos(String albumName, User user);

	/**
	 * Adds the photo to the user
	 *
	 * @param fileName the file name
	 * @param caption the caption
	 * @param albumName the album name
	 * @param user the user
	 * @return true, if successful
	 */
	boolean addPhoto(String fileName, String caption, String albumName, User user);

	/**
	 * Move the user's photo to a different album
	 *
	 * @param fileName the file name
	 * @param oldAlbumName the old album name
	 * @param newAlbumName the new album name
	 * @param user the user
	 * @return true, if successful
	 */
	boolean movePhoto(String fileName, String oldAlbumName, String newAlbumName, User user);

	/**
	 * Removes the user's photo from the album.
	 *
	 * @param fileName the file name
	 * @param albumName the album name
	 * @param user the user
	 * @return true, if successful
	 */
	boolean removePhoto(String fileName, String albumName, User user);
	
}
