package cs213.photoAlbum.control;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import cs213.photoAlbum.model.Album;
import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.model.User;

public class AlbumController implements IAlbumController {

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
	public List<Photo> listPhotos(String albumName, User user) {
		for (int i = 0; i < user.albumList.size(); i++) {
			if (albumName.equals(user.albumList.get(i).getAlbumName())) {
				return user.albumList.get(i).getPhotos();
			}
		}

		return null;

	}

	@Override
	public boolean addPhoto(String fileName, String caption, String albumName, User user) {
		Photo photo = new Photo();
		photo.setName(fileName);
		photo.setCaption(caption);

		for (int i = 0; i < user.albumList.size(); i++) {
			if (albumName.equals(user.albumList.get(i).getAlbumName())) {
				user.albumList.get(i).photoList.add(photo);

				return true;
			}
		}
		return false;
	}

	@Override
	public boolean movePhoto(String fileName, String oldAlbumName, String newAlbumName, User user) {

		return false;
	}

	@Override
	public boolean removePhoto(String fileName, String albumName, User user) {
		for (int i = 0; i < user.albumList.size(); i++) {
			if (albumName.equals(user.albumList.get(i).getAlbumName())) {
				user.albumList.get(i).photoList.remove(fileName);
				return true;

			}

		}
		return false;
	}
}
