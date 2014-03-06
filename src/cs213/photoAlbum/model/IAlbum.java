package cs213.photoAlbum.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.Map;

public interface IAlbum {

	/**
	 * Recaptions the photo
	 * @return name new name of photo
	 */
	abstract String recaptionPhoto();

	/**
	 * Returns the list of photos
	 * @return photoList the list of photos
	 */
	abstract Collection<IPhoto> getPhotos();
	
	Map<String, IPhoto> getPhotoMap();

	abstract IPhoto getPhoto(String name);

	/**
	 * Sets the name of the album
	 * @param name name of album
	 */
	abstract void setAlbumName(String name);

	/**
	 * Returns the name of the album
	 * @return name name of album
	 */
	abstract String getAlbumName();

	abstract int hashCode();

	abstract boolean equals(Object obj);

	abstract Calendar maxPhotoDate();

	abstract Calendar minPhotoDate();

}