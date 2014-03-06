package cs213.photoAlbum.control;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cs213.photoAlbum.model.Backend;
import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.model.User;

public class PhotoController implements IPhotoController {

	private Backend backend;

	public PhotoController() {
		this.backend = new Backend();
	}

	public boolean containsPhoto(String fileName, User user) {
		return user.getPhotos().containsKey(fileName);
	}
	

	@Override
	public boolean addTag(String fileName, String tagType, String tagValue, User user) {

		Photo photo = user.getPhotos().get(fileName);
		if (photo == null) {
			return false;
		}

		Map<String, Set<String>> tagMap = photo.getTags();
		Set<String> tags = tagMap.get(tagType);
		if (tags == null) {
			tags = new LinkedHashSet<String>();
			tagMap.put(tagType, tags);
		}

		if (tagType.equals("location") && tags.size() == 1) {
			return false;
		}

		if (tags.contains(tagValue)) {
			return false;
		}

		tags.add(tagValue);
		return true;
	}

	@Override
	public boolean deleteTag(String fileName, String tagType, String tagValue, User user) {

		Photo photo = user.getPhotos().get(fileName);
		if (photo == null) {
			return false;
		}

		Map<String, Set<String>> tagMap = photo.getTags();
		Set<String> tags = tagMap.get(tagType);

		if (tags == null) {
			return false;
		}

		if (tags.remove(tagValue)) {

			if (tags.isEmpty()) {
				 tagMap.remove(tagType);
			}
			return true;
		}
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
	public List<Photo> getPhotosByTag(List<String> tagNames, List<String> tagValues, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean fileExists(String fileName) {
		return backend.fileExists(fileName);
	}

}
