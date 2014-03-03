package cs213.photoAlbum.control;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.model.User;

public class PhotoController implements IPhotoController {

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
	public List<Photo> getPhotosByTag(Map<String, String> tags, User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
