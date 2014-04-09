package cs213.photoAlbum.util;

import javax.swing.DefaultListModel;

public class DefaultListModelAction {

	
	public void newList(DefaultListModel<String> model, String[] users) {
		
		model.clear();
		for(int count = 0; count < users.length; count++) {
			model.addElement(users[count]);
		}
	}
}