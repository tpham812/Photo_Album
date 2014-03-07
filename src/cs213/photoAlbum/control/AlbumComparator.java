package cs213.photoAlbum.control;

import java.util.Comparator;

import cs213.photoAlbum.model.IAlbum;

public class AlbumComparator implements Comparator<IAlbum> {

	@Override
	public int compare(IAlbum o1, IAlbum o2) {		
		return o1.getAlbumName().compareTo(o2.getAlbumName());
	}

}
