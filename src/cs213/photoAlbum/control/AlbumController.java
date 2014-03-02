package cs213.photoAlbum.control;

import java.util.List;

import cs213.photoAlbum.model.Album;
import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.model.User;

public class AlbumController implements IAlbumController {

	@Override
	public List<Album> listAlbums(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createAlbum(String albumName, User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAlbum(String albumName, User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Photo> listPhotos(String albumName, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addPhoto(String fileName, String caption, String albumName, User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean movePhoto(String fileName, String oldAlbumName, String newAlbumName, User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removePhoto(String fileName, String albumName, User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
