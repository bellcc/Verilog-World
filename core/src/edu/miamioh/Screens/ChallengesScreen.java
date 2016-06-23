/**
 * @author Zachary McQuigg
 * @date   06-16-2016
 * @info   
 */


package edu.miamioh.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.miamioh.verilogWorld.VerilogWorldMain;
 
public class ChallengesScreen implements Screen {
	private VerilogWorldMain verilogWorldMain;
	
	private SpriteBatch batch;
	private BitmapFont font;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    
    private TextButtonStyle buttonStyleL1;
    private TextButtonStyle buttonStyleL2;
    private TextButtonStyle buttonStyleL3;
    private TextButtonStyle buttonStyleL4;
    private TextButtonStyle buttonStyleL5;
    private TextButtonStyle buttonStyleL6;
    private TextButtonStyle buttonStyleL7;
    private TextButtonStyle buttonStyleL8;
    private TextButtonStyle buttonStyleL9;
    private TextButtonStyle buttonStyleL10;
    private TextButtonStyle buttonStyleL11;
    private TextButtonStyle buttonStyleL12;
    private TextButtonStyle buttonStyleB;
    private TextButtonStyle buttonStyleIm;
    private TextButtonStyle buttonStyleN;

    protected Skin skinL1;
    protected Skin skinL2;
    protected Skin skinL3;
    protected Skin skinL4;
    protected Skin skinL5;
    protected Skin skinL6;
    protected Skin skinL7;
    protected Skin skinL8;
    protected Skin skinL9;
    protected Skin skinL10;
    protected Skin skinL11;
    protected Skin skinL12;
    protected Skin skinB;
    protected Skin skinIm1;
    protected Skin skinIm2;
    protected Skin skinIm3;
    protected Skin textSkin;
    protected Skin skinN;
    
    private TextArea textArea;
    private boolean textActorCheck;

    private int buttonHeight;
    private int buttonWidth;
    private int level;
    
    private Table mainTable;
    
    private Game g;
    
    public ChallengesScreen(Game g) {
    	
    	this.g = g;
    	
    	verilogWorldMain = new VerilogWorldMain();
    	    	
    	font = new BitmapFont();
		
    	skinL1 = new Skin();
    	skinL2 = new Skin();
    	skinL3 = new Skin();
    	skinL4 = new Skin();
    	skinL5 = new Skin();
    	skinL6 = new Skin();
    	skinL7 = new Skin();
    	skinL8 = new Skin();
    	skinL9 = new Skin();
    	skinL10 = new Skin();
    	skinL11 = new Skin();
    	skinL12 = new Skin();
    	skinB = new Skin();
    	skinIm1 = new Skin();
    	skinIm2 = new Skin();
    	skinIm3 = new Skin();
    	skinN = new Skin();
    	
    	textSkin = new Skin(Gdx.files.internal ("uiskin.json"));

    	buttonSetUp();
    	
	    batch = new SpriteBatch();
	    camera = new OrthographicCamera();
	    viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), camera);
	    viewport.apply();

	    camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        
        stage = new Stage(viewport, batch);

        Gdx.input.setInputProcessor(stage);
        
       
        /* This is required to remove the old textArea actor before a new one is added.
         * If this was not used, then when clicking on one of the levels for the first
         * time, there will be an error because the program will try to remove the textArea
         * actor when there is no textArea actor currently present to remove.
         */
        textActorCheck = false;
	   
    }


	    @Override
	    public void show() {
	       
	    	//This sets up a table to add the buttons to
	    	mainTable = new Table();

	        //Creates buttons
	        TextButton l1Button = new TextButton("", skinL1);
	        TextButton l2Button = new TextButton("", skinL2);
	        TextButton l3Button = new TextButton("", skinL3);
	        TextButton l4Button = new TextButton("", skinL4);
	        TextButton l5Button = new TextButton("", skinL5);
	        TextButton l6Button = new TextButton("", skinL6);
	        TextButton l7Button = new TextButton("", skinL7);
	        TextButton l8Button = new TextButton("", skinL8);
	        TextButton l9Button = new TextButton("", skinL9);
	        TextButton l10Button = new TextButton("", skinL10);
	        TextButton l11Button = new TextButton("", skinL11);
	        TextButton l12Button = new TextButton("", skinL12);
	        TextButton backButton = new TextButton("", skinB);
	        TextButton im1Button = new TextButton("", skinIm1);
	        TextButton im2Button = new TextButton("", skinIm2);
	        TextButton im3Button = new TextButton("", skinIm3);
	        TextButton nextButton = new TextButton("", skinN);

	        buttonHeight = (Gdx.graphics.getHeight() - (Gdx.graphics.getHeight()/4))/10;
	        buttonWidth = Gdx.graphics.getWidth()/5;
	        

	        //Add buttons to table
	        mainTable.add(l1Button).height(buttonHeight).width(buttonWidth);
	        mainTable.row();
	        mainTable.add(l2Button).height(buttonHeight).width(buttonWidth);
	        mainTable.row();
	        mainTable.add(l3Button).height(buttonHeight).width(buttonWidth);
	        mainTable.row();
	        mainTable.add(l4Button).height(buttonHeight).width(buttonWidth);
	        mainTable.row();
	        mainTable.add(l5Button).height(buttonHeight).width(buttonWidth);
	        mainTable.row();
	        mainTable.add(l6Button).height(buttonHeight).width(buttonWidth);
	        mainTable.row();
	        mainTable.add(l7Button).height(buttonHeight).width(buttonWidth);
	        mainTable.row();
	        mainTable.add(l8Button).height(buttonHeight).width(buttonWidth);
	        mainTable.row();
	        mainTable.add(l9Button).height(buttonHeight).width(buttonWidth);
	        mainTable.row();
	        mainTable.add(l10Button).height(buttonHeight).width(buttonWidth);
	        mainTable.row();
	        mainTable.add(l11Button).height(buttonHeight).width(buttonWidth);
	        mainTable.row();
	        mainTable.add(l12Button).height(buttonHeight).width(buttonWidth);
	        mainTable.row();

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

	       
	        //table.setWidth(buttonWidth + 20);
	        //table.setHeight(buttonHeight*10);
	        //table.setPosition(0, (Gdx.graphics.getHeight()- (buttonHeight*10))/2);
	        
	        table.setFillParent(true);
	        table.add(scroller).width(buttonWidth + 20).height(buttonHeight * 10);
	        table.left();
	        
	        System.out.println(scroller.getMinHeight());
	        System.out.println(table.getMinHeight());
	        System.out.println(mainTable.getMinHeight());
	        System.out.println(buttonHeight);
	        
	        stage.addActor(backButton);
	        stage.addActor(im1Button);
	        stage.addActor(im2Button);
	        stage.addActor(im3Button);
	        stage.addActor(table);
	        	        
	        //Click listeners for each of the buttons
	        l1Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {	            	
	            	if(textActorCheck) {
	        			textArea.remove();
	        			nextButton.remove();
	            	}
	            	
	        		String text = "LEVEL 01\n\n"
	        				+ "This is a rather large and important string that "
	        				+ "describes this level and everything about it. I will "
	        				+ "continue to write write write. Done";
	            	textArea = new TextArea(text, textSkin);
	            	textArea.setDisabled(true);
	            	textArea.setWidth(buttonWidth * 3);
	            	textArea.setHeight(buttonHeight*10);
	    	        textArea.setPosition((buttonWidth / 2) + buttonWidth, (Gdx.graphics.getHeight()- (buttonHeight*10))/2);
	        		stage.addActor(textArea);
	        		textActorCheck = true;
	        		
	        		stage.addActor(nextButton);
	        		level = 1;
	            }
	        });
	        
	        l2Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {	 
	            	if(textActorCheck) {
	        			textArea.remove();
	        			nextButton.remove();
	            	}
	            	
	            	String text = "LEVEL 02\n\n"
	        				+ "This is a rather large and important string that "
	        				+ "describes this level and everything about it. I will "
	        				+ "continue to write write write. Done";
	            	textArea = new TextArea(text, textSkin);
	            	textArea.setDisabled(true);
	            	textArea.setWidth(buttonWidth * 3);
	            	textArea.setHeight(buttonHeight*10);
	    	        textArea.setPosition((buttonWidth / 2) + buttonWidth, (Gdx.graphics.getHeight()- (buttonHeight*10))/2);
	        		stage.addActor(textArea);
	        		textActorCheck = true;
	        		
	        		stage.addActor(nextButton);
	        		level = 2;
	            }
	        });
	        
	        l3Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	if(textActorCheck) {
	        			textArea.remove();
	        			nextButton.remove();
	            	}
	            	
	            	String text = "LEVEL 03\n\n"
	        				+ "This is a rather large and important string that "
	        				+ "describes this level and everything about it. I will "
	        				+ "continue to write write write. Done";
	            	textArea = new TextArea(text, textSkin);
	            	textArea.setDisabled(true);
	            	textArea.setWidth(buttonWidth * 3);
	            	textArea.setHeight(buttonHeight*10);
	    	        textArea.setPosition((buttonWidth / 2) + buttonWidth, (Gdx.graphics.getHeight()- (buttonHeight*10))/2);
	        		stage.addActor(textArea);
	        		textActorCheck = true;
	        		
	        		stage.addActor(nextButton);
	        		level = 3;
	            }
	        });
	        
	        l4Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	if(textActorCheck) {
	        			textArea.remove();
	        			nextButton.remove();
	            	}
	        		
	            	String text = "LEVEL 04\n\n"
	        				+ "This is a rather large and important string that "
	        				+ "describes this level and everything about it. I will "
	        				+ "continue to write write write. Done";
	            	textArea = new TextArea(text, textSkin);
	            	textArea.setDisabled(true);
	            	textArea.setWidth(buttonWidth * 3);
	            	textArea.setHeight(buttonHeight*10);
	    	        textArea.setPosition((buttonWidth / 2) + buttonWidth, (Gdx.graphics.getHeight()- (buttonHeight*10))/2);
	        		stage.addActor(textArea);
	        		textActorCheck = true;
	        		
	        		stage.addActor(nextButton);
	        		level = 4;
	            }
	        });
	        
	        l5Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	if(textActorCheck) {
	        			textArea.remove();
	        			nextButton.remove();
	            	}
	        	
	            	String text = "LEVEL 05\n\n"
	        				+ "This is a rather large and important string that "
	        				+ "describes this level and everything about it. I will "
	        				+ "continue to write write write. Done";
	            	textArea = new TextArea(text, textSkin);
	            	textArea.setDisabled(true);
	            	textArea.setWidth(buttonWidth * 3);
	            	textArea.setHeight(buttonHeight*10);
	    	        textArea.setPosition((buttonWidth / 2) + buttonWidth, (Gdx.graphics.getHeight()- (buttonHeight*10))/2);
	        		stage.addActor(textArea);
	        		textActorCheck = true;
	        		
	        		stage.addActor(nextButton);
	        		level = 5;
	            }
	        });
	        
	        l6Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	if(textActorCheck) {
	        			textArea.remove();
	        			nextButton.remove();
	            	}
	        		
	            	String text = "LEVEL 06\n\n"
	        				+ "This is a rather large and important string that "
	        				+ "describes this level and everything about it. I will "
	        				+ "continue to write write write. Done";
	            	textArea = new TextArea(text, textSkin);
	            	textArea.setDisabled(true);
	            	textArea.setWidth(buttonWidth * 3);
	            	textArea.setHeight(buttonHeight*10);
	    	        textArea.setPosition((buttonWidth / 2) + buttonWidth, (Gdx.graphics.getHeight()- (buttonHeight*10))/2);
	        		stage.addActor(textArea);
	        		textActorCheck = true;
	        		
	        		stage.addActor(nextButton);
	        		level = 6;
	            }
	        });
	        
	        l7Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	if(textActorCheck) {
	        			textArea.remove();
	        			nextButton.remove();
	            	}
	        		
	            	String text = "LEVEL 07\n\n"
	        				+ "This is a rather large and important string that "
	        				+ "describes this level and everything about it. I will "
	        				+ "continue to write write write. Done";
	            	textArea = new TextArea(text, textSkin);
	            	textArea.setDisabled(true);
	            	textArea.setWidth(buttonWidth * 3);
	            	textArea.setHeight(buttonHeight*10);
	    	        textArea.setPosition((buttonWidth / 2) + buttonWidth, (Gdx.graphics.getHeight()- (buttonHeight*10))/2);
	        		stage.addActor(textArea);
	        		textActorCheck = true;
	        		
	        		stage.addActor(nextButton);
	        		level = 7;
	            }
	        });
	        
	        l8Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	if(textActorCheck) {
	        			textArea.remove();
	        			nextButton.remove();
	            	}
	        		
	            	String text = "LEVEL 08\n\n"
	        				+ "This is a rather large and important string that "
	        				+ "describes this level and everything about it. I will "
	        				+ "continue to write write write. Done";
	            	textArea = new TextArea(text, textSkin);
	            	textArea.setDisabled(true);
	            	textArea.setWidth(buttonWidth * 3);
	            	textArea.setHeight(buttonHeight*10);
	    	        textArea.setPosition((buttonWidth / 2) + buttonWidth, (Gdx.graphics.getHeight()- (buttonHeight*10))/2);
	        		stage.addActor(textArea);
	        		textActorCheck = true;
	        		
	        		stage.addActor(nextButton);
	        		level = 8;
	            }
	        });
	        
	        l9Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	if(textActorCheck) {
	        			textArea.remove();
	        			nextButton.remove();
	            	}
	        		
	            	String text = "LEVEL 09\n\n"
	        				+ "This is a rather large and important string that "
	        				+ "describes this level and everything about it. I will "
	        				+ "continue to write write write. Done";
	            	textArea = new TextArea(text, textSkin);
	            	textArea.setDisabled(true);
	            	textArea.setWidth(buttonWidth * 3);
	            	textArea.setHeight(buttonHeight*10);
	    	        textArea.setPosition((buttonWidth / 2) + buttonWidth, (Gdx.graphics.getHeight()- (buttonHeight*10))/2);
	        		stage.addActor(textArea);
	        		textActorCheck = true;
	        		
	        		stage.addActor(nextButton);
	        		level = 9;
	            }
	        });
	        
	        l10Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	if(textActorCheck) {
	        			textArea.remove();
	        			nextButton.remove();
	            	}
	        		
	            	String text = "LEVEL 10\n\n"
	        				+ "This is a rather large and important string that "
	        				+ "describes this level and everything about it. I will "
	        				+ "continue to write write write. Done";
	            	textArea = new TextArea(text, textSkin);
	            	textArea.setDisabled(true);
	            	textArea.setWidth(buttonWidth * 3);
	            	textArea.setHeight(buttonHeight*10);
	    	        textArea.setPosition((buttonWidth / 2) + buttonWidth, (Gdx.graphics.getHeight()- (buttonHeight*10))/2);
	        		stage.addActor(textArea);
	        		textActorCheck = true;
	        		
	        		stage.addActor(nextButton);
	        		level = 10;
	            }
	        });
	        l11Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	if(textActorCheck) {
	        			textArea.remove();
	        			nextButton.remove();
	            	}
	        		
	            	String text = "LEVEL 11\n\n"
	        				+ "This is a rather large and important string that "
	        				+ "describes this level and everything about it. I will "
	        				+ "continue to write write write. Done";
	            	textArea = new TextArea(text, textSkin);
	            	textArea.setDisabled(true);
	            	textArea.setWidth(buttonWidth * 3);
	            	textArea.setHeight(buttonHeight*10);
	    	        textArea.setPosition((buttonWidth / 2) + buttonWidth, (Gdx.graphics.getHeight()- (buttonHeight*10))/2);
	        		stage.addActor(textArea);
	        		textActorCheck = true;
	        		
	        		stage.addActor(nextButton);
	        		level = 11;
	            }
	        });
	        l12Button.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	if(textActorCheck) {
	        			textArea.remove();
	        			nextButton.remove();
	            	}
	        		
	            	String text = "LEVEL 12\n\n"
	        				+ "This is a rather large and important string that "
	        				+ "describes this level and everything about it. I will "
	        				+ "continue to write write write. Done";
	            	textArea = new TextArea(text, textSkin);
	            	textArea.setDisabled(true);
	            	textArea.setWidth(buttonWidth * 3);
	            	textArea.setHeight(buttonHeight*10);
	    	        textArea.setPosition((buttonWidth / 2) + buttonWidth, (Gdx.graphics.getHeight()- (buttonHeight*10))/2);
	        		stage.addActor(textArea);
	        		textActorCheck = true;
	        		
	        		stage.addActor(nextButton);
	        		level = 12;
	            }
	        });
	        
	        backButton.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	g.setScreen(new PlayScreen(g));
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
	            	switch(level){
	            	
		            	case 1:
			            	System.out.println("Play Level 1");
			            	break;
		            	case 2:
			            	System.out.println("Play Level 2");
			            	break;
		            	case 3:
			            	System.out.println("Play Level 3");
			            	break;
		            	case 4:
			            	System.out.println("Play Level 4");
			            	break;
		            	case 5:
			            	System.out.println("Play Level 5");
			            	break;
		            	case 6:
			            	System.out.println("Play Level 6");
			            	break;
		            	case 7:
			            	System.out.println("Play Level 7");
			            	break;
		            	case 8:
			            	System.out.println("Play Level 8");
			            	break;
		            	case 9:
			            	System.out.println("Play Level 9");
			            	break;
		            	case 10:
			            	System.out.println("Play Level 10");
			            	break;
		            	case 11:
			            	System.out.println("Play Level 11");
			            	break;
		            	case 12:
			            	System.out.println("Play Level 12");
			            	break;
	            	
	            	}
	            }
	        });
	    }

	    @Override
	    public void render(float delta) {
	        Gdx.gl.glClearColor((float)0/255, (float)0/255, (float)0/255, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	        stage.act();	        
	        stage.draw();
	    }

	    @Override
	    public void resize(int width, int height) {
	    	//stage.setViewport(new StretchViewport(width, height));
	        //mainTable.left();
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
	        
	    	skinL1.dispose();
	        skinL2.dispose();
	        skinL3.dispose();
	        skinL4.dispose();
	        skinL5.dispose();
	        skinL6.dispose();
	        skinL7.dispose();
	        skinL8.dispose();
	        skinL9.dispose();
	        skinL10.dispose();
	        skinL11.dispose();
	        skinL12.dispose();
	        skinB.dispose();
	        skinIm1.dispose();
	        skinIm2.dispose();
	        skinIm3.dispose();
	        skinN.dispose();
	        stage.dispose();
	    }
	    
	    public void buttonSetUp(){
	    	
	    	buttonStyle(skinL1, buttonStyleL1, "level 1.png");
	    	buttonStyle(skinL2, buttonStyleL2, "level 2.png");
	    	buttonStyle(skinL3, buttonStyleL3, "level 3.png");
	    	buttonStyle(skinL4, buttonStyleL4, "level 4.png");
	    	buttonStyle(skinL5, buttonStyleL5, "level 5.png");
	    	buttonStyle(skinL6, buttonStyleL6, "level 6.png");
	    	buttonStyle(skinL7, buttonStyleL7, "level 7.png");
	    	buttonStyle(skinL8, buttonStyleL8, "level 8.png");
	    	buttonStyle(skinL9, buttonStyleL9, "level 9.png");
	    	buttonStyle(skinL10, buttonStyleL10, "level 10.png");
	    	buttonStyle(skinL11, buttonStyleL11, "level 11.png");
	    	buttonStyle(skinL12, buttonStyleL12, "level 12.png");
	    	buttonStyle(skinB, buttonStyleB, "back.png");
	    	buttonStyle(skinIm1, buttonStyleIm, "import.png");
	    	buttonStyle(skinIm2, buttonStyleIm, "import.png");
	    	buttonStyle(skinIm3, buttonStyleIm, "import.png");
	    	buttonStyle(skinN, buttonStyleN, "next.png");

			
	    }
	    
	    public void buttonStyle(Skin skin, TextButtonStyle buttonStyle, String imgName){
	    	
	    	
	    	skin.add("default", font);
		
	    	//adds an image texture to the skin of each button
			skin.add("textColor", new Texture(Gdx.files.internal(imgName)));
			
	    	//This sets up a style for each button
			buttonStyle = new TextButtonStyle();
			buttonStyle.up = skin.newDrawable("textColor", Color.WHITE);
			buttonStyle.down = skin.newDrawable("textColor", Color.DARK_GRAY);
			buttonStyle.checked = skin.newDrawable("textColor", Color.WHITE);
			buttonStyle.over = skin.newDrawable("textColor", Color.LIGHT_GRAY);
			buttonStyle.font = skin.getFont("default");
			skin.add("default", buttonStyle);
			
	    }
}

