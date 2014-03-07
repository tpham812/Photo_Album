package cs213.photoAlbum.control;

import java.util.Collection;

import cs213.photoAlbum.model.Album;
import cs213.photoAlbum.model.IAlbum;
import cs213.photoAlbum.model.IPhoto;
import cs213.photoAlbum.model.IUser;
import cs213.photoAlbum.model.Photo;

// TODO: Auto-generated Javadoc
/**
 * Controller interface to create/delete {@link Album}, and to manage {@link Photo} in the {@link Album}.
 *
 * @author dheeptha
 * .
 */
public interface IAlbumController {

	/**
	 * List the user's albums.
	 *
	 * @param user the user
	 * @return the list
	 */
	Collection<IAlbum> listAlbums(IUser user);

	/**
	 * Creates an album for the user.
	 *
	 * @param albumName the album name
	 * @param user the user
	 * @return true, if successful
	 */
	boolean createAlbum(String albumName, IUser user);

	
	/**
	 * Gets the album.
	 *
	 * @param albumName the album name
	 * @param user the user
	 * @return the album
	 */
	IAlbum getAlbum(String albumName, IUser user);
	
	
	/**
	 * Deletes the user's album .
	 *
	 * @param albumName the album name
	 * @param user the user
	 * @return true, if successful
	 */
	boolean deleteAlbum(String albumName, IUser user);

	/**
	 * List all the photos in the album.
	 *
	 * @param albumName the album name
	 * @param user the user
	 * @return the list
	 */
	Collection<IPhoto> listPhotos(String albumName, IUser user);

	/**
	 * Adds the photo to the user.
	 *
	 * @param fileName the file name
	 * @param caption the caption
	 * @param albumName the album name
	 * @param user the user
	 * @return true, if successful
	 */
	IPhoto addPhoto(String fileName, String caption, String albumName, IUser user);

	/**
	 * Move the user's photo to a different album.
	 *
	 * @param fileName the file name
	 * @param oldAlbumName the old album name
	 * @param newAlbumName the new album name
	 * @param user the user
	 * @return true, if successful
	 */
	boolean movePhoto(String fileName, String oldAlbumName, String newAlbumName, IUser user);

	/**
	 * Removes the user's photo from the album.
	 *
	 * @param fileName the file name
	 * @param albumName the album name
	 * @param user the user
	 * @return true, if successful
	 */
	boolean removePhoto(String fileName, String albumName, IUser user);
	
	/**
	 * Contains photo.
	 *
	 * @param fileName the file name
	 * @param albumName the album name
	 * @param user the user
	 * @return true, if successful
	 */
	boolean containsPhoto(String fileName, String albumName, IUser user);

	
}
