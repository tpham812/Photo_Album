package cs213.photoAlbum.control;

import java.util.Comparator;

import cs213.photoAlbum.model.Photo;

public class PhotoComparator implements Comparator<Photo> {
	
	@Override
	public int compare(Photo o1, Photo o2) {			
		return o1.getDateTime().compareTo(o2.getDateTime());
	}
}