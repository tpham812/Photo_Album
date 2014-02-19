package cs213.photoAlbum.model;

import java.util.List;

/**
 * @author brett
 * 
 *
 */
public class Album {
	private String name; 
	private int photos;
	private List<Photo> photoList; 
	
	public Album(String name, int photos){
		
	}
	

	
	public void addPhoto(){
		
	}
	
	public void deletePhoto(){
		
	}
	
	public String renamePhoto(){
		return null;
	}
	
	public List<Photo> getPhotos(){
		return photoList; 
	}
	
	public String toString(){
		return name; 
	}
}
