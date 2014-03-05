package cs213.photoAlbum.control;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import cs213.photoAlbum.model.Backend;
import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.model.User;

public class PhotoController implements IPhotoController {
	
	private Backend backend;

	public PhotoController() {
		this.backend = new Backend();
	}

	@Override
	public boolean addTag(String fileName, String tagType, String tagValue, User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTag(String fileName, String tagType, String tagValue, User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, String> listPhotoInfo(String fileName, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Photo> getPhotosByDate(Calendar start, Calendar end, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Photo> getPhotosByTag(List<String> tagNames, List<String> tagValues , User user) {
		// TODO Auto-generated method stub
		return null;
	}
	

	public boolean fileExists(String fileName) {
		return backend.fileExists(fileName);
	}

}
