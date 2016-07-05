package edu.miamioh.ErrorWindow;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import edu.miamioh.verilogWorld.VerilogWorldController;

public class ErrorInstance extends JPanel {
	
	private String moduleName;
	private JTextArea contents;
	
	public ErrorInstance(String moduleName, int blockX, int blockY) {
		this.moduleName = moduleName + "_(" + blockX + ", " + blockY + ")";
		this.contents = new JTextArea();
		
		/*
		 * Configure this error panel's layout options
		 */
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		/*
		 * Set up text area on which to print errors
		 */
		contents.setBorder(new CompoundBorder(new EmptyBorder(new Insets(2, 5, 2, 5)), new LineBorder(Color.BLACK)));
		contents.setEditable(false);
		contents.setMargin(new Insets(10, 10, 10, 10));
		
		// Set up the label
		JLabel label = new JLabel(this.moduleName);
		label.setBorder(new EmptyBorder(new Insets(2, 3, 5, 3)));
		label.setFont(new Font(label.getFont().getFontName(), Font.PLAIN, 16));
		
		/*
		 * Set up the expand button
		 */
		ImageIcon icon = new ImageIcon(VerilogWorldController.getController().getRootPath() + "/core/assets/images/expand.png");
		JButton button = new JButton(icon);
		button.setBackground(new Color(1, 1, 1, 1));
		
		this.add(label);
		this.add(contents);
		this.add(button);
	}
	
	public void addError(String error) {
		contents.append(error);
	}
}
