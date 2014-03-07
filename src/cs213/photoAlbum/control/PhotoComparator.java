package cs213.photoAlbum.control;

import java.util.Comparator;

import cs213.photoAlbum.model.IPhoto;

public class PhotoComparator implements Comparator<IPhoto> {
	
	@Override
	public int compare(IPhoto o1, IPhoto o2) {			
		int i = o1.getDateTime().compareTo(o2.getDateTime());
		
		if (i == 0) {
			i = o1.getName().compareTo(o2.getName());
		}
		
		return i;
	}
}