package cs213.photoAlbum.model;

import java.util.List;

/**
 * The class Album
 * @author brett
 * 
 *
 */
public class Album {
	
	/** The name of the album */
	private String name; 
	
	/** The number of photos*/
	private int photos;
	
	/** The list of photos */
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
	public setName(String name){
		this.name = name; 
	}
	
}
