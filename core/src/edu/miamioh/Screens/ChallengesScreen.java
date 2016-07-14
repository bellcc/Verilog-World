/**
 * @author Zachary McQuigg
 * @date   06-16-2016
 * @info   
 */


package edu.miamioh.Screens;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.apache.commons.io.FileUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.miamioh.Configuration.ConfigurationParser;
import edu.miamioh.Level.Level;
import edu.miamioh.verilogWorld.VerilogWorldController;
import edu.miamioh.verilogWorld.VerilogWorldMain;
import edu.miamioh.worldEditor.WorldEditorController;
import edu.miamioh.worldEditor.WorldEditorScreen;
import edu.miamioh.worldSimulator.WorldSimulator;
 
public class ChallengesScreen implements Screen {
		
	private SpriteBatch batch;
	private SpriteBatch batch2;
	private Sprite sprite;
	private BitmapFont font;
    private static Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    
    private TextButton backButton;
    private TextButton im1Button;

    private static TextButton nextButton;
    
    private TextButtonStyle buttonStyleB;
    private TextButtonStyle buttonStyleIm;
    private TextButtonStyle buttonStyleN;

    private Skin skinB;
    private Skin skinIm1;

    private static Skin textSkin;
    private Skin skinN;
    
    private static TextArea textArea;
    private static boolean textActorCheck;
    private static int textAreaY;
    private static int textAreaX;
   
    private ArrayList<TextButton> levelArray;
    private static ArrayList<String> levelDescription;
    private ArrayList<TextButton> tutorialArray;
    private static ArrayList<String> tutorialDescription;

    private static int buttonHeight;
    private static int buttonWidth;
    private static boolean challenges;
    private static boolean tutorials;
    private static int index;
    
    private Table mainTable;
        
    public ChallengesScreen() {
    	
    }
    
    public ChallengesScreen(VerilogWorldMain vwm) {
   	   
    }  


	@Override
	public void show() {

		font = new BitmapFont();    	
			
		if(getChallenges()) {
			levelArray = new ArrayList<TextButton>();
			levelDescription = new ArrayList<String>();
		}
		else if(getTutorials()) {
			tutorialArray = new ArrayList<TextButton>();
			tutorialDescription = new ArrayList<String>();
		}
	    	
    	skinB = new Skin();
	   	skinIm1 = new Skin();
	   	skinN = new Skin();
	    	
	   	textSkin = new Skin(Gdx.files.internal ("uiskin.json"));

    	buttonStyle(skinB, buttonStyleB);
    	buttonStyle(skinIm1, buttonStyleIm);
    	buttonStyle(skinN, buttonStyleN);
	    	
	    batch = new SpriteBatch();
	    camera = new OrthographicCamera();
	    viewport = new FitViewport(Gdx.graphics.getHeight(),Gdx.graphics.getHeight(), camera);
	    viewport.apply();

	    camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
	        
        stage = new Stage(viewport, batch);
	        
        //sets up a background image for the menu
	    batch2 = new SpriteBatch();
	    Texture backTex = new Texture(Gdx.files.internal("images/circuit_3.png"));
	    sprite = new Sprite(backTex);
	    sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

	    Gdx.input.setInputProcessor(stage);
	       
	    /* This is required to remove the old textArea actor before a new one is added.
	     * If this was not used, then when clicking on one of the levels for the first
	     * time, there will be an error because the program will try to remove the textArea
	     * actor when there is no textArea actor currently present to remove.
	     */
	    textActorCheck = false;	    	
	    	
	    //This sets up a table to add the buttons to
	   	mainTable = new Table();
	   	
	   	buttonHeight = (3 * viewport.getScreenHeight())/40;
	    buttonWidth = viewport.getScreenWidth()/5;
	        
	    backButton = new TextButton("BACK", skinB);
	    im1Button = new TextButton("IMPORT", skinIm1);
	    nextButton = new TextButton("NEXT", skinN);
	        

	    if(getChallenges()) {
	    	try {
				createLevelList();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		        
		   	for(int i = 0; i < levelArray.size(); i++){
		   		mainTable.add(levelArray.get(i)).height(buttonHeight).width(buttonWidth);
		   		mainTable.row();
		   	}
	    }
	    else if(getTutorials()) {
	    	try {
				createTutorialList();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		        
		    for(int i = 0; i < tutorialArray.size(); i++){
		    	mainTable.add(tutorialArray.get(i)).height(buttonHeight).width(buttonWidth);
		    	mainTable.row();
		   	}
	    }
	        
	    mainTable.left();
  
	    backButton.setHeight(buttonHeight);
	    backButton.setWidth(buttonWidth);
	    im1Button.setHeight(buttonHeight);
        im1Button.setWidth(buttonWidth);
        nextButton.setHeight(buttonHeight);
        nextButton.setWidth(buttonWidth);
        
        
        backButton.setPosition(0, 0);
        im1Button.setPosition(0, ((7 * Gdx.graphics.getHeight())/8) + 20);
        nextButton.setPosition(viewport.getScreenWidth() - buttonWidth, 0);
        
        Skin scrollSkin = new Skin(Gdx.files.internal("uiskin.json"));
        
        final Table table = new Table();
        
        final ScrollPane scroller = new ScrollPane(mainTable, scrollSkin);	        
        scroller.setScrollingDisabled(true, false);
        scroller.setFadeScrollBars(false);
        scroller.setOverscroll(false, false);
        
        table.setFillParent(true);
        table.add(scroller).width(buttonWidth + 20).height(buttonHeight * 10);
        table.left();
        
        stage.addActor(backButton);
        stage.addActor(im1Button);
        stage.addActor(table);
        	       	        
        textAreaY = viewport.getScreenHeight()/8;
        textAreaX = (3 * viewport.getScreenWidth())/10;
        
        clickedListeners();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)0/255, (float)0/255, (float)0/255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        
        batch2.begin();
        sprite.draw(batch2);
        batch2.end();
        
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    	viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        
        skinB.dispose();
        skinIm1.dispose();
        skinN.dispose();
        stage.dispose();
    }	    
    
    /*This method sets up the click and change listeners for every button displayed on the screen*/
    public void clickedListeners(){
        
    	if(getChallenges()){
	    	for(int i = 0; i < levelArray.size(); i++) {
	    		levelArray.get(i).addListener(new LevelChangeListener(i));
	    	}
    	}
    	else if(getTutorials()){
	    	for(int i = 0; i < tutorialArray.size(); i++) {
	    		tutorialArray.get(i).addListener(new LevelChangeListener(i));
	    	}
    	}
        
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	VerilogWorldMain.getVerilogWorldMain().setPlayScreen();	            	
            }
        });
        
        im1Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	
            	if(textActorCheck) {
        			textArea.remove();
        			nextButton.remove();
            	}
            	        		        		        						        			        		
        		JFileChooser chooser = new JFileChooser();
        		chooser.setCurrentDirectory(new java.io.File("."));
        		chooser.setDialogTitle("Select Directory Location");
        		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        		
        		chooser.setAcceptAllFileFilterUsed(false);
        		
        		if(chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
        			
        			File srcDir = new File(chooser.getSelectedFile().getAbsolutePath());
        			File destDir = null;
        			if(getChallenges()){
        				destDir = new File(VerilogWorldMain.getRootPath() + "/core/assets/levels");
        			}
        			else if(getTutorials()){
        				destDir = new File(VerilogWorldMain.getRootPath() + "/core/assets/tutorials");
        			}
					
        			try {
						FileUtils.copyDirectoryToDirectory(srcDir, destDir);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        		
        		VerilogWorldMain.getVerilogWorldMain().setChallengesScreen();
            	
            }
        });
        
        nextButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	
            	VerilogWorldMain.getVerilogWorldMain().setWorldEditorScreen(new Level());
            	String path = "";
            	String levelPath = "";
            	String[] fileNameArray;

            	if(getChallenges()){
		        	for(int i = 0; i < levelArray.size(); i++) {
		        		if(levelArray.indexOf(levelArray.get(index)) == levelArray.indexOf(levelArray.get(i))){
		                	path = VerilogWorldMain.getRootPath() + "/core/assets/levels";
		                	fileNameArray = new File(path).list();
		                	levelPath = path + "/" + fileNameArray[i];
		        		}
		        	}
            	}
            	else if(getTutorials()){
    	        	for(int i = 0; i < tutorialArray.size(); i++) {
    	        		if(tutorialArray.indexOf(tutorialArray.get(index)) == tutorialArray.indexOf(tutorialArray.get(i))){
		                	path = VerilogWorldMain.getRootPath() + "/core/assets/tutorials";
		                	fileNameArray = new File(path).list();
		                	levelPath = path + "/" + fileNameArray[i];    	        		
		                }
    	        	}
            	}
            	
    			Level level = new Level();
    			level.setProject(new File(levelPath + "/"));
    			WorldEditorController.getCurrentController().setCurrentLevel(level);
    			
    			ConfigurationParser parser = new ConfigurationParser();
    			level = parser.getConfiguration(new File(levelPath + "/world.xml"));
    			level.setProject(new File(levelPath + "/"));
    			
    			String rootPath = level.getProject().getAbsolutePath();
    			VerilogWorldController.getController().setRootPath(rootPath);
    			
    			WorldEditorController.getCurrentController().setCurrentLevel(level);
    			WorldEditorController.getCurrentController().updateWorld(level);
    			WorldEditorScreen.getScreen().updateWorldParameters();
    						
    			WorldSimulator sim = VerilogWorldController.getController().getSim();
    			sim.setBlocks(level.getBlockList());
    			sim.updateModules();
            }
        });
    }
    
    /*creates a button style the buttons*/
    public void buttonStyle(Skin skin, TextButtonStyle buttonStyle){
    	
    	
    	skin.add("default", font);
	
		Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
		pixmap.setColor(Color.WHITE);
		pixmap.drawRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
		pixmap.setColor(Color.DARK_GRAY);
		pixmap.fillRectangle(1, 1, pixmap.getWidth() - 2, pixmap.getHeight() - 2);
    	
    	//adds an image texture to the skin of each button
		//skin.add("textColor", new Texture(Gdx.files.internal("images/" + imgName)));
		
		skin.add("textColor", new Texture(pixmap));

		
    	//This sets up a style for each button
		buttonStyle = new TextButtonStyle();
		buttonStyle.up = skin.newDrawable("textColor", Color.WHITE);
		buttonStyle.down = skin.newDrawable("textColor", Color.GRAY);
		buttonStyle.over = skin.newDrawable("textColor", Color.CYAN);
		buttonStyle.font = skin.getFont("default");
		skin.add("default", buttonStyle);
		
    }
    
    /*adds a level button to the level menu*/
    public void addLevel(String buttonText, String description, Skin skin, TextButtonStyle style) {
    	buttonStyle(skin, style);
    	TextButton button = new TextButton(buttonText, skin);
    	levelDescription.add(description);
    	levelArray.add(button);
    }
    
    /*adds a tutorial button to the tutorial menu*/
    public void addTutorial(String buttonText, String description, Skin skin, TextButtonStyle style) {
    	buttonStyle(skin, style);
    	TextButton button = new TextButton(buttonText, skin);
    	tutorialDescription.add(description);
    	tutorialArray.add(button);	    		
    }
    	  
    
    /*This method is called from the LevelChangeListener class.
     *The index is the value of "i" that is used to loop through
     *the levels and add them to the menu*/
    public static void listen(int index) {
    	
    	ChallengesScreen.index = index;

    	if(textActorCheck) {
			textArea.remove();
			nextButton.remove();
    	}  	
    	
    	if(getChallenges()) {
        	for(int j = 0; j < levelDescription.size(); j++) {
        		if(levelDescription.indexOf(levelDescription.get(index)) == levelDescription.indexOf(levelDescription.get(j))){
        			textArea = new TextArea(levelDescription.get(j), textSkin);    	
       			}
       		}
    	}
    	else if(getTutorials()) {
        	for(int j = 0; j < tutorialDescription.size(); j++) {
        		if(tutorialDescription.indexOf(tutorialDescription.get(index)) == tutorialDescription.indexOf(tutorialDescription.get(j))){
        			textArea = new TextArea(tutorialDescription.get(j), textSkin);    	
       			}
       		}
    	}
    		            		    	
    	textArea.setDisabled(true);
    	textArea.setWidth(buttonWidth * 3);
    	textArea.setHeight(buttonHeight*10);
        textArea.setPosition(textAreaX, textAreaY);
		stage.addActor(textArea);
		textActorCheck = true;
		
		stage.addActor(nextButton);
		
    }
    
    
    /*The setters and getters for tutorials and challenges are 
     *Used to determine whether the tutorial or challenges button was click
     *on the play screen */
    public void setTutorials(boolean tutorials) {
    	ChallengesScreen.tutorials = tutorials;
    }
    
    public void setChallenges(boolean challenges) {
    	ChallengesScreen.challenges = challenges;
    }
    
    public static boolean getTutorials() {
    	return tutorials;
    }
    
    public static boolean getChallenges() {
    	return challenges;
    }    
    
    public void createLevelList() throws IOException {
    	String path = VerilogWorldMain.getRootPath() + "/core/assets/levels";
    	String description;
    	String[] levelList = new File(path).list();
    	
    	int x = new File(path).list().length;
    	
    	for(int i = 0; i < x; i++){
    		Skin skin = new Skin();
            TextButtonStyle textButtonStyle = new TextButtonStyle();
            
            BufferedReader br = new BufferedReader(new FileReader(path + "/" + levelList[i] + "/description.txt"));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append("\n");
                    line = br.readLine();
                }
                description = sb.toString();
            } finally {
                br.close();
            }
           
            addLevel(levelList[i], description, skin, textButtonStyle);

    	}
        
    }
    
    public void createTutorialList() throws IOException {
        
    	String path = VerilogWorldMain.getRootPath() + "/core/assets/tutorials";
    	String description;
    	String[] tutorialList = new File(path).list();
    	
    	int x = new File(path).list().length;
    	
    	for(int i = 0; i < x; i++){
    		Skin skin = new Skin();
            TextButtonStyle textButtonStyle = new TextButtonStyle();
            
            BufferedReader br = new BufferedReader(new FileReader(path + "/" + tutorialList[i] + "/description.txt"));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append("\n");
                    line = br.readLine();
                }
                description = sb.toString();
            } finally {
                br.close();
            }
           
            addTutorial(tutorialList[i], description, skin, textButtonStyle);

    	}
    }
}

