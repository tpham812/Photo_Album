package cs213.photoAlbum.control;

import java.util.Comparator;

import cs213.photoAlbum.model.Photo;

public class PhotoComparator implements Comparator<Photo> {
	
	@Override
	public int compare(Photo o1, Photo o2) {			
		int i = o1.getDateTime().compareTo(o2.getDateTime());
		
		if (i == 0) {
			i = o1.getName().compareTo(o2.getName());
		}
		
		return i;
	}
}