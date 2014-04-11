package cs213.photoAlbum.util;

import javax.swing.DefaultComboBoxModel;

public class DefaultComboBoxModelAction {

	public void newComboBox(DefaultComboBoxModel<Integer> model, int[] date) {
		
		model.removeAllElements();
		for(int i = 0; i < date.length; i++) {
			model.addElement(date[i]);
		}
	}
	
	public void setYearComboBox(DefaultComboBoxModel<Integer> model, int max, int min) {
		
		for(int i = min; i  <= max; i++) {
			model.addElement(i);
		}
	}
}
