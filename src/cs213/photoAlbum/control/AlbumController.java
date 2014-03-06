package cs213.photoAlbum.control;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cs213.photoAlbum.model.Album;
import cs213.photoAlbum.model.Backend;
import cs213.photoAlbum.model.IBackend;
import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.model.User;
import cs213.photoAlbum.util.CalendarUtils;

public class AlbumController implements IAlbumController {

	private IBackend backend;
	
	public AlbumController() {
		this.backend = new Backend();
	}
	
	@Override
	public Collection<Album> listAlbums(User user) {
		return user.getAlbums();
	}

	@Override
	public boolean createAlbum(String albumName, User user) {

		if (user.containsAlbum(albumName)) {
			return false;
		}

		user.addAlbum(new Album(albumName));
		return true;
	}

	@Override
	public boolean deleteAlbum(String albumName, User user) {

		if (!user.containsAlbum(albumName)) {
			return false;
		}

		user.deleteAlbum(albumName);
		return true;
	}

	@Override
	public Collection<Photo> listPhotos(String albumName, User user) {

		return user.getAlbum(albumName).getPhotos();
	}
	
	public boolean containsPhoto(String fileName, String albumName, User user) {
		Album album = user.getAlbum(albumName);		
		if(album == null || !album.photos.containsKey(fileName)) {
			return false;
		}
		return true;
	}

	@Override
	public Photo addPhoto(String fileName, String caption, String albumName, User user) {
		
		Photo photo = null;
		
		if(user.getPhotos().containsKey(fileName)) {
			photo = user.getPhotos().get(fileName);
			
		} else {
			photo = new Photo();
			photo.setName(fileName);
			photo.setCaption(caption);
			
			File file = backend.getFile(fileName);			
			photo.setDateTime(CalendarUtils.toCalendar(file.lastModified()));
			
			user.getPhotos().put(fileName, photo);
		}

		Album album = user.getAlbum(albumName);		
		if(album == null || album.photos.containsKey(fileName)) {
			return null;
		} else {
			album.photos.put(fileName, photo);
		}		
		return photo;
	}

	@Override
	public boolean movePhoto(String fileName, String oldAlbumName, String newAlbumName, User user) {
		
		Photo p = getPhoto(fileName,oldAlbumName,user);
		
		if (p == null) {
			return false;
		}
		removePhoto(fileName, oldAlbumName, user);
		p = addPhoto(fileName, p.getCaption(), newAlbumName, user);
		
		return p != null;
	}
	
	public Photo getPhoto(String fileName, String albumName, User user){
		
		Album album = user.getAlbum(albumName);		

		if (album == null) {
			return null;
		} 
		
		return album.getPhoto(fileName);
	}

	@Override
	public boolean removePhoto(String fileName, String albumName, User user) {
		
		Map<String, Photo> photos = user.getAlbum(albumName).photos;
		if(!photos.containsKey(fileName)) {
			return false;
		} else {
			photos.remove(fileName);
		}
		
		boolean photoExists = false;
		for(Album a: user.getAlbums()){
			if(a.photos.containsKey(fileName)){
				photoExists = true;
				break;
			}
		}
		
		if(!photoExists) {
			user.getPhotos().remove(fileName);
		}

		return true;
	}

	@Override
	public Album getAlbum(String albumName, User user) {
		return user.getAlbum(albumName);
	}
}
