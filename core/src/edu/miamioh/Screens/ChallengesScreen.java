/**
 * @author Zachary McQuigg
 * @date   06-16-2016
 * @info   
 */


package edu.miamioh.Screens;

import java.util.ArrayList;

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

import edu.miamioh.verilogWorld.VerilogWorldMain;
 
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
    private TextButton im2Button;
    private TextButton im3Button;
    private static TextButton nextButton;
    
    private TextButtonStyle buttonStyleB;
    private TextButtonStyle buttonStyleIm;
    private TextButtonStyle buttonStyleN;

    private Skin skinB;
    private Skin skinIm1;
    private Skin skinIm2;
    private Skin skinIm3;
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
	   	skinIm2 = new Skin();
	   	skinIm3 = new Skin();
	   	skinN = new Skin();
	    	
	   	textSkin = new Skin(Gdx.files.internal ("uiskin.json"));

    	buttonStyle(skinB, buttonStyleB);
    	buttonStyle(skinIm1, buttonStyleIm);
    	buttonStyle(skinIm2, buttonStyleIm);
    	buttonStyle(skinIm3, buttonStyleIm);
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
	    im2Button = new TextButton("IMPORT", skinIm2);
	    im3Button = new TextButton("IMPORT", skinIm3);
	    nextButton = new TextButton("NEXT", skinN);
	        

	    if(getChallenges()) {
	    	addLevelTest();
		        
		   	for(int i = 0; i < levelArray.size(); i++){
		   		mainTable.add(levelArray.get(i)).height(buttonHeight).width(buttonWidth);
		   		mainTable.row();
		   	}
	    }
	    else if(getTutorials()) {
	    	addTutorialTest();
		        
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
        im2Button.setHeight(buttonHeight);
        im2Button.setWidth(buttonWidth);
        im3Button.setHeight(buttonHeight);
        im3Button.setWidth(buttonWidth);
        nextButton.setHeight(buttonHeight);
        nextButton.setWidth(buttonWidth);
        
        
        backButton.setPosition(0, 0);
        im1Button.setPosition(0, ((7 * Gdx.graphics.getHeight())/8) + 20);
        im2Button.setPosition(buttonWidth * 2, ((7 * Gdx.graphics.getHeight())/8) + 20);
        im3Button.setPosition(buttonWidth * 4, ((7 * Gdx.graphics.getHeight())/8) + 20);
        nextButton.setPosition(Gdx.graphics.getWidth() - buttonWidth, 0);
        
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
        stage.addActor(im2Button);
        stage.addActor(im3Button);
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
        skinIm2.dispose();
        skinIm3.dispose();
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
            	System.out.println("import Click Listener");
            	if(textActorCheck) {
        			textArea.remove();
        			nextButton.remove();
            	}
            }
        });
        
        im2Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	System.out.println("import Click Listener");
            	if(textActorCheck) {
        			textArea.remove();
        			nextButton.remove();
            	}
            }
        });
        
        im3Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	System.out.println("import Click Listener");
            	if(textActorCheck) {
        			textArea.remove();
        			nextButton.remove();
            	}
            }
        });
        
        nextButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	
            	if(getChallenges()){
    	        	for(int i = 0; i < levelArray.size(); i++) {
    	        		if(levelArray.indexOf(levelArray.get(index)) == levelArray.indexOf(levelArray.get(i))){
    	        			System.out.println("Play Level " + (index+1));
    	        		}
    	        	}
            	}
            	else if(getTutorials()){
    	        	for(int i = 0; i < tutorialArray.size(); i++) {
    	        		if(tutorialArray.indexOf(tutorialArray.get(index)) == tutorialArray.indexOf(tutorialArray.get(i))){
    	        			System.out.println("Play Tutorial " + (index+1));
    	        		}
    	        	}
            	}
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
	            	String describe = "level " + (index+1) + "\n\n" + levelDescription.get(j);
        			textArea = new TextArea(describe, textSkin);    	
       			}
       		}
    	}
    	else if(getTutorials()) {
        	for(int j = 0; j < tutorialDescription.size(); j++) {
        		if(tutorialDescription.indexOf(tutorialDescription.get(index)) == tutorialDescription.indexOf(tutorialDescription.get(j))){
	            	String describe = "tutorial " + (index+1) + "\n\n" + tutorialDescription.get(j);
        			textArea = new TextArea(describe, textSkin);    	
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
    
    public void addLevelTest() {
    	
        Skin skinTest1 = new Skin();
        Skin skinTest2 = new Skin();
        Skin skinTest3 = new Skin();
        Skin skinTest4 = new Skin();
        Skin skinTest5 = new Skin();
        Skin skinTest6 = new Skin();
        Skin skinTest7 = new Skin();
        Skin skinTest8 = new Skin();
        Skin skinTest9 = new Skin();
        Skin skinTest10 = new Skin();
        Skin skinTest11 = new Skin();
        Skin skinTest12 = new Skin();
        Skin skinTest13 = new Skin();
        Skin skinTest14 = new Skin();
        Skin skinTest15 = new Skin();

        
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        
        addLevel("LEVEL 1", "testing 1st level description", skinTest1, textButtonStyle);
        addLevel("LEVEL 2", "testing 2nd level description", skinTest2, textButtonStyle);
        addLevel("LEVEL 3", "testing 3rd level description", skinTest3, textButtonStyle);
        addLevel("LEVEL 4", "testing 4th level description", skinTest4, textButtonStyle);
        addLevel("LEVEL 5", "testing 5th level description", skinTest5, textButtonStyle);
        addLevel("LEVEL 6", "testing 6th level description", skinTest6, textButtonStyle);
        addLevel("LEVEL 7", "testing 7th level description", skinTest7, textButtonStyle);
        addLevel("LEVEL 8", "testing 8th level description", skinTest8, textButtonStyle);
        addLevel("LEVEL 9", "testing 9th level description", skinTest9, textButtonStyle);
        addLevel("LEVEL 10", "testing 10th level description", skinTest10, textButtonStyle);
        addLevel("LEVEL 11", "testing 11th level description", skinTest11, textButtonStyle);
        addLevel("LEVEL 12", "testing 12th level description", skinTest12, textButtonStyle);
        addLevel("LEVEL 13", "testing 13th level description", skinTest13, textButtonStyle);
        addLevel("LEVEL 14", "testing 14th level description", skinTest14, textButtonStyle);
        addLevel("LEVEL 15", "testing 15th level description", skinTest15, textButtonStyle);

        
    }
    
    public void addTutorialTest() {
        
        Skin skinTest1 = new Skin();
        Skin skinTest2 = new Skin();
        Skin skinTest3 = new Skin();
        Skin skinTest4 = new Skin();
        Skin skinTest5 = new Skin();
        Skin skinTest6 = new Skin();
        Skin skinTest7 = new Skin();
        Skin skinTest8 = new Skin();
        Skin skinTest9 = new Skin();
        Skin skinTest10 = new Skin();
        
        TextButtonStyle textButtonStyle = new TextButtonStyle();
    	
        addTutorial("TUTORIAL 1", "testing 1st tutorial description", skinTest1, textButtonStyle);
        addTutorial("TUTORIAL 2", "testing 2nd tutorial description", skinTest2, textButtonStyle);
        addTutorial("TUTORIAL 3", "testing 3rd tutorial description", skinTest3, textButtonStyle);
        addTutorial("TUTORIAL 4", "testing 4th tutorial description", skinTest4, textButtonStyle);
        addTutorial("TUTORIAL 5", "testing 5th tutorial description", skinTest5, textButtonStyle);
        addTutorial("TUTORIAL 6", "testing 6th tutorial description", skinTest6, textButtonStyle);
        addTutorial("TUTORIAL 7", "testing 7th tutorial description", skinTest7, textButtonStyle);
        addTutorial("TUTORIAL 8", "testing 8th tutorial description", skinTest8, textButtonStyle);
        addTutorial("TUTORIAL 9", "testing 9th tutorial description", skinTest9, textButtonStyle);
        addTutorial("TUTORIAL 10", "testing 10th tutorial description", skinTest10, textButtonStyle);
    }
}

