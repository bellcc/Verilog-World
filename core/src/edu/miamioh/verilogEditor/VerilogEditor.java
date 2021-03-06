package edu.miamioh.verilogEditor;

/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014 Peter Jamieson, Naoki Mizuno, and Boyu Zhang
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import edu.miamioh.simulator.Parse;
import edu.miamioh.simulator.RootModuleSimulator;
import edu.miamioh.verilogWorld.VerilogWorldMain;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.undo.CannotUndoException;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.Locale;

@SuppressWarnings("serial")
public class VerilogEditor extends JFrame implements ActionListener {
    
    private static final int	       MINWIDTH	      = 1000;
    private static final int	       MINHEIGHT      = 1000;
    
    private MyTextPane		       codeText	      = null;
    private MyTextPane		       errorText      = null;
    private MyUndo1		       myUndoManager1 = null;
    private IntegerRangeDocumentFilter filterOne;
    
    public File			       verilogFiles;
    
    private String		       fileName;
    
    private Parse		       Compiler;
    private RootModuleSimulator	       sim;
    
    private long		       startTime;
    private long		       totalFocusTime;
    private boolean		       isFirstSimCycle;
    
    // /**
    // * @param args
    // * {Name Root_Path Level_Number} The exact name of the file to be
    // * opened, the exact top-level path of the game files, and the
    // * Level Number (eg. Lv0).
    // */
    // public static void main(String[] args) throws IOException {
    // String fileName = "SevenSeg.v";
    // String rootPath = System.getProperty("user.dir") + "//..//";
    // String filePath = rootPath + "core/assets/modules/" + fileName;
    // Parse compiler = new Parse(new JTextPane(), rootPath);
    // RootModuleSimulator sim = compiler.getRootModuleSimulator();
    //
    // compiler.compileFileForGame(fileName);
    //
    // new VerilogEditor(sim, compiler, rootPath, filePath);
    // }
    
    // public VerilogEditor(RootModuleSimulator sim, Parse compiler, String
    // rootPath, String filePath, String fileName) {
    //
    // this.fileName = fileName;
    // try {
    // compiler.compileFileForGame(fileName);
    // } catch (IOException e) {
    // System.err.println("Problem compiling source file.");
    // e.printStackTrace();
    // System.exit(1);
    // }
    //
    // new VerilogEditor(sim, compiler, rootPath, filePath, fileName);
    // }
    
    public VerilogEditor(RootModuleSimulator sim, Parse compiler,
	    String rootPath, String filePath, String fileName) {
	// Create the window
	super("Verilog Text Editor: " + filePath);
	this.setMinimumSize(new Dimension(MINWIDTH, MINHEIGHT));
	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	Locale.setDefault(Locale.ENGLISH);
	
	this.fileName = fileName;
	try {
	    compiler.compileFileForGame(fileName);
	} catch (IOException e) {
	    System.err.println("Problem compiling source file.");
	    e.printStackTrace();
	    System.exit(1);
	}
	
	// if (System.getProperty("os.name").startsWith("Mac"))
	// newLine = "\n";
	// else
	// newLine = "\n";
	
	checkDir(filePath);
	
	// set the location the window will appear on the screen
	this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	JPanel contentPane = new JPanel();
	contentPane.setLayout(new GridBagLayout());
	this.setContentPane(contentPane);
	
	// tool bar
	JToolBar toolBar = new JToolBar("Still draggable");
	toolBar.setFloatable(false);
	toolBar.setRollover(true);
	GridBagConstraints cToolBar = new GridBagConstraints();
	cToolBar.gridx = 0;
	cToolBar.gridy = 0;
	cToolBar.fill = GridBagConstraints.BOTH;
	cToolBar.weightx = 0;
	cToolBar.weighty = 0;
	contentPane.add(toolBar, cToolBar);
	addButtons(toolBar);
	toolBar.setBorder(BorderFactory.createEtchedBorder());
	
	// split panel code
	final JSplitPane splitPane = new JSplitPane();
	splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
	splitPane.setDividerSize(2);
	splitPane.setPreferredSize(new Dimension(600, 550));
	splitPane.setContinuousLayout(true);
	splitPane.setOneTouchExpandable(true);
	// make the divider keep its position(in percentage to the whole window)
	// during dragging
	splitPane.addComponentListener(new ComponentAdapter() {
	    
	    @Override
	    public void componentResized(ComponentEvent e) {
		splitPane.setDividerLocation(0.7);
	    }
	});
	
	// grid bag constraints for splitpane
	GridBagConstraints cSplitPane = new GridBagConstraints();
	cSplitPane.gridx = 0;
	cSplitPane.gridy = 1;
	cSplitPane.fill = GridBagConstraints.BOTH;
	cSplitPane.weightx = 1;
	cSplitPane.weighty = 1;
	contentPane.add(splitPane, cSplitPane);
	codeText = new MyTextPane();
	setTabs(codeText, 8);
	Font font1 = new Font("Consolas", Font.PLAIN, 16);
	codeText.setFont(font1);
	
	// line number
	codeText.setBorder(new LineNumberBorder());
	
	// non-editable text panel
	filterOne = new IntegerRangeDocumentFilter(codeText);
	((AbstractDocument) codeText.getDocument())
		.setDocumentFilter(filterOne);
	
	// highlight keywords
	codeText.getDocument()
		.addDocumentListener(new SyntaxHighlighter(codeText));
	
	// something strange with the JTextPane's new line character.
	// For more information see here:
	// http://docs.oracle.com/javase/7/docs/api/javax/swing/text/DefaultEditorKit.html
	codeText.getDocument()
		.putProperty(DefaultEditorKit.EndOfLineStringProperty, "\n");
	
	// undo and redo
	myUndoManager1 = new MyUndo1();
	codeText.getDocument().addUndoableEditListener(myUndoManager1);
	
	// read in the already existed file or create a new file
	// verilogFiles = new File(pathOfEditorJar + "VerilogFiles/" + name +
	// ".v");
	verilogFiles = new File(filePath);
//	System.out.println(filePath);
	if (!verilogFiles.exists()) {
	    try {
		verilogFiles.createNewFile();
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }
	} else {
	    try {
		InputStreamReader reader = new InputStreamReader(
			new FileInputStream(verilogFiles));
		BufferedReader br = new BufferedReader(reader);
		String line = "";
		String temp = null;
		if ((temp = br.readLine()) != null) line = temp;
		while ((temp = br.readLine()) != null)
		    line = line + "\n" + temp;
		Document docCode = codeText.getDocument();
		docCode.insertString(0, line, null);
		myUndoManager1.discardAllEdits();
		// non-editable text panel
		if (codeText.getText().indexOf("count") == -1)
		    filterOne.setStart(940);
		else
		    filterOne.setStart(962);
		
		br.close();
		reader.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    } catch (BadLocationException e) {
		e.printStackTrace();
	    }
	}
	autoAddBrackets();
	errorText = new MyTextPane();
	errorText.setEditable(false);
	Font font2 = new Font("Consolas", Font.PLAIN, 12);
	errorText.setFont(font2);
	
	JScrollPane upperArea = new JScrollPane(codeText,
		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JPanel lowerArea = new JPanel();
	lowerArea.setLayout(new GridBagLayout());
	
	JScrollPane errorArea = new JScrollPane(errorText,
		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JLabel errorLog = new JLabel("Error log");
	
	// animationPanel = new AnimationPanel(simulateInput);
	
	this.setVisible(true);
	
	// grid bag constraints for errorlog and errorarea
	GridBagConstraints cErrorLog = new GridBagConstraints();
	cErrorLog.gridx = 0;
	cErrorLog.gridy = 0;
	cErrorLog.fill = GridBagConstraints.BOTH;
	cErrorLog.weightx = 0;
	cErrorLog.weighty = 0;
	lowerArea.add(errorLog, cErrorLog);
	
	GridBagConstraints cErrorArea = new GridBagConstraints();
	cErrorArea.gridx = 0;
	cErrorArea.gridy = 1;
	cErrorArea.fill = GridBagConstraints.BOTH;
	cErrorArea.weightx = 1;
	cErrorArea.weighty = 1;
	lowerArea.add(errorArea, cErrorArea);
	splitPane.add(upperArea, JSplitPane.LEFT, 1);
	splitPane.add(lowerArea, JSplitPane.RIGHT, 2);
	
	// below is the menu bar code
	// including listener and short cut key
	JMenuBar menubar = new JMenuBar();
	this.setJMenuBar(menubar);
	JMenu fileMenu = new JMenu("File");
	JMenu editMenu = new JMenu("Edit");
	JMenu simulationMenu = new JMenu("Simulation");
	
	JMenuItem saveMenuItem = new JMenuItem("Save");
	saveMenuItem.setAccelerator(
		KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK));
	saveMenuItem.addActionListener(this);
	
	JMenuItem verifyMenuItem = new JMenuItem("Verify");
	verifyMenuItem.setAccelerator(
		KeyStroke.getKeyStroke('R', InputEvent.CTRL_MASK));
	verifyMenuItem.addActionListener(this);
	
	JMenuItem exitMenuItem = new JMenuItem("Exit");
	exitMenuItem.setAccelerator(
		KeyStroke.getKeyStroke('E', InputEvent.CTRL_MASK));
	exitMenuItem.addActionListener(this);
	
	JMenuItem undoMenuItem = new JMenuItem("Undo");
	undoMenuItem.setAccelerator(
		KeyStroke.getKeyStroke('Z', InputEvent.CTRL_MASK));
	undoMenuItem.addActionListener(this);
	
	JMenuItem redoMenuItem = new JMenuItem("Redo");
	redoMenuItem.setAccelerator(
		KeyStroke.getKeyStroke('Y', InputEvent.CTRL_MASK));
	redoMenuItem.addActionListener(this);
	
	JMenuItem sarMenuItem = new JMenuItem("Search and Replace");
	sarMenuItem.setAccelerator(
		KeyStroke.getKeyStroke('F', InputEvent.CTRL_MASK));
	sarMenuItem.addActionListener(this);
	
	JMenuItem simulateMenuItem = new JMenuItem("Simulate");
	simulateMenuItem.setAccelerator(
		KeyStroke.getKeyStroke('M', InputEvent.CTRL_MASK));
	simulateMenuItem.addActionListener(this);
	
	JMenuItem resetMenuItem = new JMenuItem("Reset Simulation");
	resetMenuItem.setAccelerator(
		KeyStroke.getKeyStroke('R', InputEvent.CTRL_MASK));
	resetMenuItem.addActionListener(this);
	
	menubar.add(fileMenu);
	menubar.add(editMenu);
	menubar.add(simulationMenu);
	fileMenu.add(verifyMenuItem);
	fileMenu.addSeparator();
	fileMenu.add(saveMenuItem);
	fileMenu.addSeparator();
	fileMenu.add(exitMenuItem);
	editMenu.add(undoMenuItem);
	editMenu.add(redoMenuItem);
	editMenu.add(sarMenuItem);
	simulationMenu.add(simulateMenuItem);
	simulationMenu.add(resetMenuItem);
	
	this.Compiler = compiler;
	this.sim = sim;
	
	this.addWindowListener(new WindowAdapter() {
	    
	    public void windowClosing(WindowEvent e) {
		// get the current text in the code area.
		String fileContent = "";
		try {
		    InputStreamReader reader = new InputStreamReader(
			    new FileInputStream(verilogFiles));
		    BufferedReader br = new BufferedReader(reader);
		    String temp = null;
		    if ((temp = br.readLine()) != null) fileContent = temp;
		    while ((temp = br.readLine()) != null) {
			fileContent = fileContent + "\n" + temp;
		    }
		    br.close();
		    reader.close();
		} catch (IOException e1) {
		    e1.printStackTrace();
		}
		
		if (codeText.getText().equals(fileContent)) {
		    totalFocusTime += (System.currentTimeMillis() - startTime)
			    / 1000;
		    // sendEditorTime(totalFocusTime);
		    dispose();
		} else
		    closingPopFunction();
	    }
	});
	
	this.addWindowFocusListener(new WindowFocusListener() {
	    
	    public void windowGainedFocus(WindowEvent e) {
		startTime = System.currentTimeMillis();
	    }
	    
	    public void windowLostFocus(WindowEvent e) {
		totalFocusTime += (System.currentTimeMillis() - startTime)
			/ 1000;
		
	    }
	    
	});
    }
    
    /**
     * Checks to see if there's a directory at the specified path. If not,
     * creates it.
     * 
     * @param path
     */
    private static void checkDir(String path) {
	
	// File verilogDir = new File(pathOfEditorJar + "VerilogFiles");
	File verilogDir = new File(path.substring(0, path.lastIndexOf("/")));
	if (!verilogDir.exists() && !verilogDir.isDirectory())
	
	{
	    System.out.println("Directory does not exist.");
	    
	    if (verilogDir.mkdir())
		System.out.println("Directory has been created.");
	    else
		System.out.println("Failed to create the directory.");
	    
	} else {
	    System.out.println("Directory already exists.");
	}
	
    }// end checkDir
    
    // This block of code set how many space you get when you press the "tab"
    public static void setTabs(JTextPane textPane, int charactersPerTab)
    
    {
	FontMetrics fm = textPane.getFontMetrics(textPane.getFont());
	int charWidth = fm.charWidth(' ');
	int tabWidth = charWidth * charactersPerTab;
	
	TabStop[] tabs = new TabStop[50];
	
	for (int j = 0; j < tabs.length; j++)
	
	{
	    int tab = j + 1;
	    tabs[j] = new TabStop(tab * tabWidth);
	}
	
	TabSet tabSet = new TabSet(tabs);
	SimpleAttributeSet attributes = new SimpleAttributeSet();
	StyleConstants.setTabSet(attributes, tabSet);
	int length = textPane.getDocument().getLength();
	textPane.getStyledDocument().setParagraphAttributes(0, length,
		attributes, false);
    }
    
    // This block of code create a button with image
    protected static JButton makeToolBarButton(String imageName,
	    String toolTipText, String altText) {
	// Look for the image.
	String imgLocation = "/images/" + imageName + ".png";
	URL imageURL = VerilogEditor.class.getResource(imgLocation);
	
	// Create and initialize the button.
	JButton button = new JButton();
	button.setToolTipText(toolTipText);
	
	if (imageURL != null) { // image found
	    button.setIcon(new ImageIcon(imageURL, altText));
	} else { // no image found
	    button.setText(altText);
	    System.err.println("Resource not found: " + imgLocation);
	}
	
	return button;
    }
    
    // add tool bar buttons and their listeners
    protected void addButtons(JToolBar toolBar) {
	JButton saveButton = makeToolBarButton("save", "Save", "Save");
	saveButton.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		saveButtonFunction();
	    }
	});
	toolBar.add(saveButton);
	JButton verifyButton = makeToolBarButton("verify", "Verify", "Verify");
	verifyButton.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		verifyButtonFunction();
	    }
	});
	toolBar.add(verifyButton);
	
	JButton undoButton = makeToolBarButton("undo", "Undo", "Undo");
	undoButton.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		undoButtonFunction();
	    }
	});
	toolBar.add(undoButton);
	JButton redoButton = makeToolBarButton("redo", "Redo", "Redo");
	redoButton.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		redoButtonFunction();
	    }
	});
	toolBar.add(redoButton);
	JButton searchButton = makeToolBarButton("search", "Search and replace",
		"Search and replace");
	searchButton.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		salButtonFunction();
	    }
	});
	toolBar.add(searchButton);
	
	JButton simulateButton = makeToolBarButton("simulate", "Simulate",
		"Simulate");
	simulateButton.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		simulateButtonFunction();
	    }
	});
	toolBar.add(simulateButton);
	
	JButton resetButton = makeToolBarButton("reset", "Reset simulation",
		"Reset simulation");
	resetButton.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		resetButtonFunction();
	    }
	});
	toolBar.add(resetButton);
	
	JButton schematicRenderButton = makeToolBarButton("schemEdit",
		"Schematic Compiler", "Schematic Compiler");
	schematicRenderButton.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		schematicButtonFunction();
	    }
	});
	toolBar.add(schematicRenderButton);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
	action(e);
    }
    
    // distinguish which key is pressed
    public void action(ActionEvent e) {
	String str = e.getActionCommand();
	if (str.equals("Save")) {
	    saveButtonFunction();
	} else if (str.equals("Verify")) {
	    verifyButtonFunction();
	} else if (str.equals("Upload")) {
	    uploadButtonFunction();
	} else if (str.equals("Exit")) {
	    exitButtonFunction();
	} else if (str.equals("Undo")) {
	    undoButtonFunction();
	} else if (str.equals("Redo")) {
	    redoButtonFunction();
	} else if (str.equals("Search and Replace")) {
	    salButtonFunction();
	} else if (str.equals("Simulate")) {
	    simulateButtonFunction();
	} else if (str.equals("Reset Simulation")) {
	    resetButtonFunction();
	    // } else if (str.equals("Combinational")) {
	    // comboHeaderButtonFunction();
	    // } else if (str.equals("Sequential")) {
	    // seqHeaderButtonFunction();
	}
    }
    
    // save
    public void saveButtonFunction() {
	
	try {
	    FileWriter out = new FileWriter(verilogFiles);
	    out.write(codeText.getText() + "\n");
	    out.close();
	    errorText.setText(errorText.getText() + "\nSaving complete.");
	} catch (Exception e1) {
	    System.out.println(e1);
	}
    }
    
    // verify
    public void verifyButtonFunction() {
	
	this.isFirstSimCycle = true;
	try {
	    FileWriter out = new FileWriter(verilogFiles);
	    out.write(codeText.getText());
	    out.close();
	    
	    /* print out what we're compiling */
	    errorText.setText(errorText.getText() + "\nCompiling " + fileName);
	    
	    // parse the base file
	    Compiler.setErrorText(errorText);
	    Compiler.compileFileForEditor(fileName);
	    
	    // Reset sim and let user know we are done
	    if (Compiler.isCompiled()) {
		errorText.setText(errorText.getText() + "\nCompiling done!");
	    } else {
		errorText.setText(errorText.getText() + "\nCompiling failed.");
	    }
	} catch (Exception e1) {
	    System.out.println(e1);
	}
	
    }
    
    // upload
    public void uploadButtonFunction() {
	StyledDocument doc = codeText.getStyledDocument();
	try {
	    doc.insertString(doc.getLength(), "upload button pressed\n", null);
	} catch (Exception e1) {
	    System.out.println(e1);
	}
    }
    
    // exit
    public void exitButtonFunction() {
	String fileContent = "";
	try {
	    InputStreamReader reader = new InputStreamReader(
		    new FileInputStream(verilogFiles));
	    BufferedReader br = new BufferedReader(reader);
	    String temp = null;
	    if ((temp = br.readLine()) != null) fileContent = temp;
	    while ((temp = br.readLine()) != null) {
		fileContent = fileContent + "\n" + temp;
	    }
	    br.close();
	    reader.close();
	} catch (IOException e1) {
	    e1.printStackTrace();
	}
	
	if (codeText.getText().equals(fileContent)) {
	    totalFocusTime += (System.currentTimeMillis() - startTime) / 1000;
	    errorText.setText("" + totalFocusTime);
	    // sendEditorTime(totalFocusTime);
	    dispose();
	} else
	    closingPopFunction();
    }
    
    // undo
    public void undoButtonFunction() {
	try {
	    if (myUndoManager1.canUndo()) myUndoManager1.undo();
	} catch (CannotUndoException e) {
	    Toolkit.getDefaultToolkit().beep();
	}
    }
    
    // redo
    public void redoButtonFunction() {
	try {
	    if (myUndoManager1.canRedo()) myUndoManager1.redo();
	} catch (CannotUndoException e) {
	    Toolkit.getDefaultToolkit().beep();
	}
    }
    
    // search and replace
    public void salButtonFunction() {
	new SearchAndReplaceDialog(this, codeText);
    }
    
    public void autoAddBrackets() {
	AutoBracket filter = new AutoBracket();
	filter.addAutoCompleteListener(new AutoCompleteListener() {
	    
	    private int updatePolicy = DefaultCaret.UPDATE_WHEN_ON_EDT;
	    
	    @Override
	    public void willAutoComplete(AutoBracket filter) {
		updatePolicy = ((DefaultCaret) codeText.getCaret())
			.getUpdatePolicy();
		((DefaultCaret) codeText.getCaret())
			.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
	    }
	    
	    @Override
	    public void didAutoComplete(AutoBracket filter) {
		((DefaultCaret) codeText.getCaret())
			.setUpdatePolicy(updatePolicy);
	    }
	});
	((AbstractDocument) codeText.getDocument()).setDocumentFilter(filter);
    }
    
    public void simulateButtonFunction() {
	
	// For first sim cycle, display starting state
	if (this.isFirstSimCycle) {
	    sim.resetSimulation();
	    this.isFirstSimCycle = false;
	    return;
	}
	
	// For all other cycles after that, actually simulate the circuit
	if (Compiler.isCompiled()) {
	    sim.sim_cycle();
	} else {
	    errorText.setText(errorText.getText()
		    + "\nThe Verilog code has not been successfully compiled yet.\nPlease click the check mark above and/or fix Verilog errors.");
	}
    }
    
    public void resetButtonFunction() {
	
	// print out to text pane
	errorText.setText(errorText.getText() + "\nReseting simulation...");
	
	if (Compiler.isCompiled()) {
	    sim.resetSimulation();
	} else {
	    errorText.setText(errorText.getText()
		    + "File not compiled. Compile first to simulate");
	}
    }
    
    /**
     * Sends the ParseTree to the SchematicRenderer to compile a schematic.
     */
    public void schematicButtonFunction() {
	
	if (Compiler.isCompiled()) {
	    VerilogWorldMain.getVerilogWorldMain().getSchematicRendererScreen()
		    .setRoot_tree(this.sim.getRootModuleTree());
	    if (VerilogWorldMain.getVerilogWorldMain().getSchematicRendererScreen()
			    .compile())
	    	errorText.setText(errorText.getText() + "\nThe Schematic has been successfully compiled."
	    			+ "\nPlease exit the editor to view the schematic.");
	    else
	    	errorText.setText(errorText.getText() + "\nThe schematic failed to compile.");
	    	
	    	
	} else {
	    errorText.setText(errorText.getText()
		    + "\nThe Verilog code has not been successfully compiled yet.\nPlease click the check mark above and/or fix Verilog errors.");
	}
    }
    
    /*
     * public void comboHeaderButtonFunction() {
     * codeText.setText(readHeaderFile("header/stop_light_combo.v"));
     * filterOne.setStart(940); }
     * 
     * public void seqHeaderButtonFunction() {
     * codeText.setText(readHeaderFile("header/stop_light_seq.v"));
     * filterOne.setStart(962); }
     */
    public void closingPopFunction() {
	String[] str = { "Content changed.", "Do you want to save this file?" };
	int selection = JOptionPane.showConfirmDialog(this, str,
		"Save this file?", JOptionPane.YES_NO_CANCEL_OPTION);
	switch (selection) {
	    case JOptionPane.YES_OPTION:
		saveButtonFunction();
	    case JOptionPane.NO_OPTION:
		// sendEditorTime(totalFocusTime);
		dispose();
		break;
	    case JOptionPane.CANCEL_OPTION:
		return;
	}
    }
    
    public String readHeaderFile(String fileName) {
	String headerContent = "";
	try {
	    InputStream reader = this.getClass()
		    .getResourceAsStream("/" + fileName);
	    BufferedReader br = new BufferedReader(
		    new InputStreamReader(reader));
	    String temp = null;
	    headerContent = br.readLine();
	    while ((temp = br.readLine()) != null) {
		if (System.getProperty("os.name").startsWith("Mac"))
		    headerContent = headerContent + "\r" + temp;
		else
		    headerContent = headerContent + "\n" + temp;
	    }
	    br.close();
	    reader.close();
	} catch (IOException e1) {
	    e1.printStackTrace();
	}
	return headerContent;
    }
    
    // public void sendEditorTime(long editorTime) {
    // try {
    // Socket socket = new Socket(InetAddress.getByName(LOCAL_IP_ADDRESS),
    // LOCAL_PORT);
    // DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
    //
    // dos.writeInt(TYPE_USAGE_EDITOR);
    // dos.writeLong(editorTime);
    // dos.flush();
    //
    // dos.close();
    // socket.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // String mes = "Error communicating with local server";
    // JOptionPane.showMessageDialog(null, mes, "Error",
    // JOptionPane.ERROR_MESSAGE);
    // }
    // }
    
}
