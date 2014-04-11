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
	
	public void setHourComboBox(DefaultComboBoxModel<String> model) {
		
		model.addElement("");
		for(int i = 1; i <= 24; i++) {
			model.addElement(Integer.toString(i));
		}
	}
	
	public void setMinute_SecondsComboBox(DefaultComboBoxModel<String> model, DefaultComboBoxModel<String> model2) {
		
		model.addElement("");
		model2.addElement("");
		for(int i = 0; i < 60; i++) {
			model.addElement(Integer.toString(i));
			model2.addElement(Integer.toString(i));
		}
	}
}
