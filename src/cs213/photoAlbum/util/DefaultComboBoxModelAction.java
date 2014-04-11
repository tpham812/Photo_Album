package cs213.photoAlbum.util;

import javax.swing.DefaultComboBoxModel;

public class DefaultComboBoxModelAction {

	public void newComboBox(DefaultComboBoxModel<String> model, int[] date) {
		
		model.addElement("");
		for(int i = 0; i < date.length; i++) {
			model.addElement(Integer.toString(date[i]));
		}
	}
	
	public void setYearComboBox(DefaultComboBoxModel<String> model, int max, int min) {
		
		model.removeAllElements();
		model.addElement("");
		for(int i = min; i  <= max; i++) {
			model.addElement(Integer.toString(i));
		}
	}
}
