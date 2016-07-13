package edu.miamioh.ErrorWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import edu.miamioh.verilogWorld.VerilogWorldController;

public class ErrorInstance extends JPanel {
	
	public String header;
	public JTextArea contents;
	private JButton button;
	
	private int LABEL_HEIGHT = 50;
	private int TEXT_HEIGHT = 85;
	private int BUTTON_WIDTH = (int)((ErrorReportingWindow.WIDTH - 46) * 0.8);
	private int BUTTON_HEIGHT = 22;
	
//	public ErrorInstance(String moduleName, int blockX, int blockY) {
//		this.moduleName = moduleName + "_(" + blockX + ", " + blockY + ")";
//		this.contents = new JTextArea();
//		
//		/*
//		 * Configure this error panel's layout options
//		 */
//		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
//		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
//		
//		/*
//		 * Set up text area on which to print errors
//		 */
//		contents.setBorder(new CompoundBorder(new EmptyBorder(new Insets(2, 5, 2, 5)), new LineBorder(Color.BLACK)));
//		//contents.setEditable(false);
//		contents.setMargin(new Insets(10, 10, 10, 10));
//		
//		// Set up the label
//		JLabel label = new JLabel(this.moduleName);
//		label.setBorder(new EmptyBorder(new Insets(2, 3, 5, 3)));
//		label.setFont(new Font(label.getFont().getFontName(), Font.PLAIN, 16));
//		
//		/*
//		 * Set up the expand button
//		 */
//		ImageIcon icon = new ImageIcon(VerilogWorldController.getController().getRootPath() + "/core/assets/images/expand.png");
//		JButton button = new JButton(icon);
//		button.setBackground(new Color(1, 1, 1, 1));
//		
//		this.add(label);
//		this.add(contents);
//		this.add(button);
//	}
	
	public ErrorInstance(String header) {
		this.header = header;
		
		this.setLayout(null);
		this.setBackground(new Color(0.7f, 0.7f, 1.0f, 1.0f));
		
		// Set up the label
		JLabel label = new JLabel(this.header);
		label.setLocation(8, -10);
		label.setSize(200, LABEL_HEIGHT);
		label.setFont(new Font(label.getFont().getFontName(), Font.PLAIN, 16));
		
		/*
		 * Set up text area on which to print errors
		 */
		this.contents = new JTextArea();
		contents.setBorder(new EmptyBorder(new Insets(2, 7, 7, 7)));
		contents.setLocation(8, LABEL_HEIGHT - 20);
		contents.setSize(ErrorReportingWindow.WIDTH - 46, TEXT_HEIGHT);
		contents.setEditable(false);
		contents.setLineWrap(true);
		contents.setWrapStyleWord(true);
		contents.setBackground(new Color(0.9f, 0.9f, 0.9f, 1.f));
		
		/*
		 * Set up the expand button
		 */
		ImageIcon icon = new ImageIcon(VerilogWorldController.getController().getRootPath() + "/core/assets/images/expand.png");
		button = new JButton(icon);
		button.setBackground(new Color(0.8f, 0.8f, 0.8f, 1.f));
		button.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		button.setLocation((ErrorReportingWindow.WIDTH - 46) / 2 - BUTTON_WIDTH / 2 + 7, LABEL_HEIGHT + TEXT_HEIGHT - 15);
		button.addActionListener(new ExpandActionListener(this));
		
		this.add(label);
		this.add(contents);
		this.add(button);
	}
	
	public ErrorInstance(String blockName, int blockX, int blockY) {
		this(blockName + " (" + blockX + ", " + blockY + ")");
	}
	
	public void displayFullMessage() {
		//contents.append("Hello");
		this.resize();
	}
	
	public void resize() {
		
		//contents.getD
		
		int oldTextHeight = TEXT_HEIGHT;
		TEXT_HEIGHT = 150;
		
		this.setMinimumSize(new Dimension(this.getWidth(), 150 + TEXT_HEIGHT - oldTextHeight));
		contents.setSize(ErrorReportingWindow.WIDTH - 46, TEXT_HEIGHT);
		button.setLocation((ErrorReportingWindow.WIDTH - 46) / 2 - BUTTON_WIDTH / 2 + 7, LABEL_HEIGHT + TEXT_HEIGHT - 15);
	}
	
	public void addError(String error) {
		contents.append(error + "\n");
	}
}
