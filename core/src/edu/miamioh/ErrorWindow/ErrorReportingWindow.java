package edu.miamioh.ErrorWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class ErrorReportingWindow extends JFrame {
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 800;
	
	private int ERROR_WIDTH;
	
	private JScrollPane scrollPane;
	private JPanel panel;
	private GridBagConstraints errorCons;
	
	private int errorCount = 0;
	
	public ErrorReportingWindow() {
		
		/*
		 * Window configuration
		 */
		this.setTitle("Error Window");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints cons = new GridBagConstraints();
		this.setLayout(layout);
		
		/*
		 * Label
		 */
		JLabel label = new JLabel("Error Window");
		label.setBorder(new EmptyBorder(5, 1, 1, 1));
		label.setFont(new Font(label.getFont().getFontName(), Font.PLAIN, 30));
		cons.gridx = 0;
		cons.gridy = 0;
		this.add(label, cons);
		
		/*
		 * Scroll pane
		 */
		scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(new Color(0.8f, 0.8f, 0.8f, 1.0f));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new BevelBorder(0));
		cons.gridx = 0;
		cons.gridy = 1;
		cons.insets = new Insets(10, 10, 10, 10);
		cons.ipadx = WIDTH;
		cons.ipady = HEIGHT;
		
		/*
		 * Create the panel which the scroll pane views and on which the errors are laid out
		 */
		panel = new JPanel();
		panel.setBackground(new Color(0.8f, 0.8f, 0.8f, 1.0f));
		panel.setLayout(new GridBagLayout());
		errorCons = new GridBagConstraints();
		
		/*
		 * Add the components to each other
		 */
		scrollPane.getViewport().add(panel);
		this.add(scrollPane, cons);
		this.pack();
		
		/*
		 * Calculate the size of the separate error panels
		 */
		ERROR_WIDTH = scrollPane.getWidth() - 50;
	}
	
	public void display() {
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void addError(ErrorInstance error) {
		
		error.setPreferredSize(new Dimension(ERROR_WIDTH, 130));
		error.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		
		errorCons.gridx = 0;
		errorCons.gridy = errorCount;
		errorCons.insets = new Insets(5, 10, 5, 10);
		
		panel.add(error, errorCons);
		
		++errorCount;
	}
}
