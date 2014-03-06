package cs213.photoAlbum.control;

import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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

		Map<String, SortedSet<String>> tagMap = photo.getTags();
		SortedSet<String> tags = tagMap.get(tagType);
		if (tags == null) {
			tags = new TreeSet<String>();
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

		Map<String, SortedSet<String>> tagMap = photo.getTags();
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
	public SortedSet<Photo> getPhotosByDate(Calendar start, Calendar end, User user) {

		SortedSet<Photo> result = new TreeSet<Photo>(new PhotoComparator());

		for (Photo p : user.getPhotos().values()) {

			if (p.getDateTime().equals(start) || p.getDateTime().equals(end)
					|| (p.getDateTime().after(start) && p.getDateTime().before(end))) {

				result.add(p);
			}
		}

		return result;
	}

	@Override
	public SortedSet<Photo> getPhotosByTag(List<String> tagNames, List<String> tagValues, User user) {

		SortedSet<Photo> result = new TreeSet<Photo>(new PhotoComparator());

		boolean m = false;

		for (Photo p : user.getPhotos().values()) {
			m = true;

			for (int i = 0; i < tagNames.size(); i++) {
				String tagName = tagNames.get(0);
				String tagValue = tagValues.get(0);

				if (!matches(p, tagName, tagValue)) {
					m = false;
					break;
				}
			}
			if (m) {
				result.add(p);
			}
		}

		return result;
	}

	private boolean matches(Photo p, String tagName, String tagValue) {

		if (tagName.isEmpty()) {
			for (SortedSet<String> vals : p.getTags().values()) {
				if (vals.contains(tagValue)) {
					return true;
				}
			}
		} else {
			SortedSet<String> s = p.getTags().get(tagName);
			if (s != null) {
				return s.contains(tagValue);
			}
		}
		return false;
	}

	public boolean fileExists(String fileName) {
		return backend.fileExists(fileName);
	}

}
