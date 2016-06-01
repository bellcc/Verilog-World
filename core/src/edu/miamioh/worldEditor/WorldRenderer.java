
/**
 * @author Chris Bell
 * @date   05-19-2016
 * @info   
 */

package edu.miamioh.worldEditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;

import edu.miamioh.util.Constants;
import edu.miamioh.worldEditor.actors.BlocksActor;
import edu.miamioh.worldEditor.actors.HomeActor;
import edu.miamioh.worldEditor.actors.TilesActor;
import edu.miamioh.worldEditor.blockActors.BlankBlockActor;
import edu.miamioh.worldEditor.tileActors.BlankTileActor;

public class WorldRenderer implements Disposable{
	
	private WorldController worldController;
	private static WorldRenderer worldRenderer;
	
	private OrthographicCamera camera;
	private ShapeRenderer renderer;
	
	private Color gridLineColor = Color.LIGHT_GRAY;
	
	private int worldX;
	private int worldY;
	
	private Stage stage;
	private Stage blockStage;
	private Stage tileStage;
	
	private static boolean homeActor;
	private static boolean blocksActor;
	private static boolean tilesActor;
	
	public WorldRenderer(WorldController worldController) {
		
		init();
	
	}

	public void init() {

		worldRenderer = this;
		worldController = new WorldController();
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);

		renderer = new ShapeRenderer();
		
		stage = new Stage();
		blockStage = new Stage();		
		tileStage = new Stage();
		
		worldController.initInputMultiplexer();
		
		createToolBar();
		createBlockStage();
		createTileStage();
		
	}
	
	public void createTileStage() {
		
		Actor tileBlock = new BlankTileActor().getButton();
		tileBlock.setPosition(50, 500);
		tileBlock.setHeight(100);
		tileBlock.setWidth(100);
		
		tileStage.addActor(tileBlock);
		
	}

	public void createBlockStage() {
		
		Actor blankBlock = new BlankBlockActor().getButton();
		blankBlock.setPosition(50, 500);
		blankBlock.setHeight(100);
		blankBlock.setWidth(100);
		
		blockStage.addActor(blankBlock);
		
	}
	
	public void createToolBar() {
		
		setHomeActor(true);
		setBlocksActor(false);
		setTilesActor(false);
		
		//TODO Make these global and changeable.
		int toolCount = 3;
		int toolBarHeight = Constants.WINDOW_HEIGHT / toolCount;
		int toolBarWidth = 50;
		
		Actor homeActor = new HomeActor().getHomeActor();
		homeActor.setPosition(0, Constants.WINDOW_HEIGHT - toolBarHeight);
		homeActor.setHeight(toolBarHeight);
		homeActor.setWidth(toolBarWidth);
				
		Actor blocksActor = new BlocksActor().getBlocksActor();
		blocksActor.setPosition(0, Constants.WINDOW_HEIGHT - (2 * toolBarHeight));
		blocksActor.setHeight(toolBarHeight);
		blocksActor.setWidth(toolBarWidth);
		
		Actor tilesActor = new TilesActor().getTilesActor();
		tilesActor.setPosition(0, 0);
		tilesActor.setHeight(Constants.WINDOW_HEIGHT - (2 * toolBarHeight));
		tilesActor.setWidth(toolBarWidth);
		
		stage.addActor(homeActor);
		stage.addActor(blocksActor);
		stage.addActor(tilesActor);
		
	}
	
	public void render() {
		
		camera.update();
		renderer.setProjectionMatrix(camera.combined);
		
		//Set the background color to white.
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderBackground();
		renderSelector();
		
		renderToolBar();
		
	}
	
	public void renderToolBar() {
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		if(homeActor) {
			
			
			
		}else if(blocksActor) {
			
			blockStage.act(Gdx.graphics.getDeltaTime());
			blockStage.draw();
			
		}else if(tilesActor) {
			
			tileStage.act(Gdx.graphics.getDeltaTime());
			tileStage.draw();
			
		}
		
	}

	public void renderBackground() {

		int worldWidth = Constants.WORLD_WIDTH * Constants.GRID_WIDTH;
		int worldHeight = Constants.WORLD_HEIGHT * Constants.GRID_HEIGHT;

		renderer.begin(ShapeType.Line);
		renderer.setColor(gridLineColor);
				
		//Vertical Lines
		for(int i = 0;i <= worldWidth;i += Constants.GRID_WIDTH) {
			
			renderer.line(i, 0, i, worldHeight);
			
		}
		
		//Horizontal Lines
		for(int i = 0;i <= worldHeight;i += Constants.GRID_HEIGHT) {
			
			renderer.line(0, i, worldWidth, i);
			
		}
		
		renderer.end();
		
		int translateX = worldX - Constants.BUFFER_WIDTH;
		int translateY = worldY - Constants.BUFFER_HEIGHT;
		
		if(worldX <	 Constants.BUFFER_WIDTH) {
			
			translateX = (-1) * (Constants.BUFFER_WIDTH - worldX);
			
		}
		
		if(worldY < Constants.BUFFER_HEIGHT) {
			
			translateY = (-1) * (Constants.BUFFER_HEIGHT - worldY);
			
		}
		
		if(worldWidth < Constants.WINDOW_WIDTH) {
			
			translateX = (-1) * ((Constants.WINDOW_WIDTH / 2) - (worldWidth / 2));
			
		}
		
		if(worldHeight < Constants.WINDOW_HEIGHT) {
			
			translateY = (-1) * ((Constants.WINDOW_HEIGHT / 2) - (worldHeight / 2));
			
		}
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera.setToOrtho(false, w, h);
		//camera.translate(translateX, translateY);
		camera.translate(translateX - 50, translateY);	
		
	}
	
	/**
	 * This method is strongly coupled with the renderBackground 
	 * method because the translations of the camera affect the 
	 * row/column detection algorithm and the drawSelector algorithm.
	 */
	public void renderSelector() {

		int row = detectRow();
		int column = detectColumn();

		column = detectColumn();
		row = detectRow();
				
		int x = column * Constants.GRID_WIDTH;
		int y = row * Constants.GRID_HEIGHT;

		if((column >= 0 && column <= (Constants.WORLD_WIDTH - 1)) && (row >= 0 && row <= (Constants.WORLD_HEIGHT - 1))) {
			
			renderer.begin(ShapeType.Filled);
			renderer.setColor(Color.DARK_GRAY);
			renderer.rect(x, y, Constants.GRID_WIDTH, Constants.GRID_HEIGHT);

			renderer.end();
			
		}
		
	}
	
	public int detectColumn() {
		
		int column = -1;
		int currentX = worldController.getMousePoint().getX() - 50;
		
		if(hasIrregularWidth()) {
			
			int worldWidth = Constants.WORLD_WIDTH * Constants.GRID_WIDTH;
			
			int displayLeftX = (Constants.WINDOW_WIDTH / 2) - (worldWidth / 2);
			int displayRightX = displayLeftX + worldWidth;
			
			if(displayLeftX <= (Constants.WINDOW_WIDTH - currentX) && displayRightX >= (Constants.WINDOW_WIDTH - currentX)) {
				
				column = (int) (Constants.WINDOW_WIDTH - currentX - displayLeftX) / Constants.GRID_WIDTH;
				column = (Constants.WORLD_WIDTH - 1) - column;
			}
			
		}else {

			column = ((worldX + currentX) - Constants.BUFFER_WIDTH) / Constants.GRID_WIDTH;

			if(worldX < Constants.BUFFER_WIDTH) {
				
				int displayLowerX = Constants.BUFFER_WIDTH - worldX;

				if(currentX < displayLowerX)  {
	
					column = -1;
					
				}
				
			}
			
		}
				
		return column;
	}
	
	public int detectRow() {
		
		int row = -1;
		int currentY = worldController.getMousePoint().getY();
		
		if(hasIrregularHeight()) {
			
			int worldHeight = Constants.WORLD_HEIGHT * Constants.GRID_HEIGHT;
			
			int displayLowerY = (Constants.WINDOW_HEIGHT / 2) - (worldHeight / 2);
			int displayUpperY = displayLowerY + worldHeight;

			if(displayLowerY <= (Constants.WINDOW_HEIGHT - currentY) && displayUpperY >= (Constants.WINDOW_HEIGHT - currentY)) {
				
				row = (int) (Constants.WINDOW_HEIGHT - currentY - displayLowerY) / Constants.GRID_HEIGHT;
				
			}

		}else {
			
			row = ((worldY + (Constants.WINDOW_HEIGHT - currentY)) - Constants.BUFFER_HEIGHT) / Constants.GRID_HEIGHT;
			
			if(worldY < Constants.BUFFER_HEIGHT) {

				int displayLowerY = Constants.BUFFER_HEIGHT - worldY;

				if((Constants.WINDOW_HEIGHT - currentY < displayLowerY))  {
	
					row = -1;
					
				}
				
			}
			
		}
				
		return row;
	}
	
	private boolean hasIrregularHeight() {
		
		if((Constants.WORLD_HEIGHT * Constants.GRID_HEIGHT) < Constants.WINDOW_HEIGHT) {
			return true;
		}
		
		return false;
	}
	
	private boolean hasIrregularWidth() {
		
		if((Constants.WORLD_WIDTH * Constants.GRID_WIDTH) < Constants.WINDOW_WIDTH) {
			return true;
		}
		
		return false;
	}
	
	public void resize(int width, int height) {
		
	}
	
	@Override
	public void dispose() {
		
		stage.dispose();
		
	}
	
	public static WorldRenderer getWorldRenderer() {
		return worldRenderer;
	}
	
	public void setWorldX(int x) {
		worldX = x;
	}
	
	public int getWorldX() {
		return worldX;
	}
	
	public void setWorldY(int y) {
		worldY = y;
	}
	
	public int getWorldY() {
		return worldY;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public Stage getBlockStage() {
		
		return blockStage;
	}

	public boolean getHomeActor() {
		return homeActor;
	}

	public void setHomeActor(boolean homeActor) {
		this.homeActor = homeActor;
	}

	public boolean getBlocksActor() {
		return blocksActor;
	}

	public void setBlocksActor(boolean blocksActor) {
		this.blocksActor = blocksActor;
	}

	public boolean getTilesActor() {
		return tilesActor;
	}

	public void setTilesActor(boolean tilesActor) {
		this.tilesActor = tilesActor;
	}

}
