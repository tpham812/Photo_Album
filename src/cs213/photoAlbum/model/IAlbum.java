package cs213.photoAlbum.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Interface IAlbum.
 */
public interface IAlbum {

	/**
	 * Recaptions the photo.
	 *
	 * @return name new name of photo
	 */
	abstract String recaptionPhoto();

	/**
	 * Returns the list of photos.
	 *
	 * @return photoList the list of photos
	 */
	abstract Collection<IPhoto> getPhotos();
	
	/**
	 * Gets the photo map.
	 *
	 * @return the photo map
	 */
	Map<String, IPhoto> getPhotoMap();

	/**
	 * Gets the photo.
	 *
	 * @param name the name
	 * @return the photo
	 */
	abstract IPhoto getPhoto(String name);

	/**
	 * Sets the name of the album.
	 *
	 * @param name name of album
	 */
	abstract void setAlbumName(String name);

	/**
	 * Returns the name of the album.
	 *
	 * @return name name of album
	 */
	abstract String getAlbumName();

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	abstract int hashCode();

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	abstract boolean equals(Object obj);

	/**
	 * Max photo date.
	 *
	 * @return the calendar
	 */
	abstract Calendar maxPhotoDate();

	/**
	 * Min photo date.
	 *
	 * @return the calendar
	 */
	abstract Calendar minPhotoDate();

}