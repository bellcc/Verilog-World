package edu.miamioh.ErrorWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExpandActionListener implements ActionListener {
	
	private ErrorInstance error;
	
	public ExpandActionListener(ErrorInstance error) {
		this.error = error;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		error.displayFullMessage();
	}
}
