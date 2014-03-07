package cs213.photoAlbum.control;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import cs213.photoAlbum.model.Album;
import cs213.photoAlbum.model.Backend;
import cs213.photoAlbum.model.IAlbum;
import cs213.photoAlbum.model.IBackend;
import cs213.photoAlbum.model.IPhoto;
import cs213.photoAlbum.model.IUser;
import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.util.CalendarUtils;

public class AlbumController implements IAlbumController {

	private IBackend backend;
	
	public AlbumController() {
		this.backend = new Backend();
	}
	
	@Override
	public SortedSet<IAlbum> listAlbums(IUser user) {
		
		SortedSet<IAlbum> albums = new TreeSet<IAlbum>(new AlbumComparator());		
		albums.addAll(user.getAlbums());
		return albums;
	}

	@Override
	public boolean createAlbum(String albumName, IUser user) {

		if (user.containsAlbum(albumName)) {
			return false;
		}

		user.addAlbum(new Album(albumName));
		return true;
	}

	@Override
	public boolean deleteAlbum(String albumName, IUser user) {

		if (!user.containsAlbum(albumName)) {
			return false;
		}

		user.deleteAlbum(albumName);
		return true;
	}

	@Override
	public Collection<IPhoto> listPhotos(String albumName, IUser user) {

		return user.getAlbum(albumName).getPhotos();
	}
	
	public boolean containsPhoto(String fileName, String albumName, IUser user) {
		IAlbum album = user.getAlbum(albumName);		
		if(album == null || !album.getPhotoMap().containsKey(fileName)) {
			return false;
		}
		return true;
	}

	@Override
	public IPhoto addPhoto(String fileName, String caption, String albumName, IUser user) {
		
		IPhoto photo = null;
		
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

		IAlbum album = user.getAlbum(albumName);		
		if(album == null || album.getPhotoMap().containsKey(fileName)) {
			return null;
		} else {
			album.getPhotoMap().put(fileName, photo);
		}		
		return photo;
	}

	@Override
	public boolean movePhoto(String fileName, String oldAlbumName, String newAlbumName, IUser user) {
		
		IPhoto p = getPhoto(fileName,oldAlbumName,user);
		
		if (p == null) {
			return false;
		}
		removePhoto(fileName, oldAlbumName, user);
		p = addPhoto(fileName, p.getCaption(), newAlbumName, user);
		
		return p != null;
	}
	
	public IPhoto getPhoto(String fileName, String albumName, IUser user){
		
		IAlbum album = user.getAlbum(albumName);		

		if (album == null) {
			return null;
		} 
		
		return album.getPhoto(fileName);
	}

	@Override
	public boolean removePhoto(String fileName, String albumName, IUser user) {
		
		Map<String, IPhoto> photos = user.getAlbum(albumName).getPhotoMap();
		if(!photos.containsKey(fileName)) {
			return false;
		} else {
			photos.remove(fileName);
		}
		
		boolean photoExists = false;
		for(IAlbum a: user.getAlbums()){
			
			if(a.getPhotoMap().containsKey(fileName)){
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
	public IAlbum getAlbum(String albumName, IUser user) {
		return user.getAlbum(albumName);
	}
}
