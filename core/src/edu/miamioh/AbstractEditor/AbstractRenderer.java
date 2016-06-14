
/**
 * @author Chris Bell
 * @date   6-4-2016
 * @info   
 */

package edu.miamioh.AbstractEditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

public abstract class AbstractRenderer implements Disposable{

	public OrthographicCamera camera;
	public ShapeRenderer renderer;
	
	public Color gridLineColor = Color.LIGHT_GRAY;
	
	public int worldX;
	public int worldY;
	
	private int toolBarWidth;
	private int toolBarOptionsWidth;
	
	public AbstractRenderer() {
		
		initCamera();
		renderer = new ShapeRenderer();
		
		worldX = 0;
		worldY = 0;
		
		toolBarWidth = 50;
		toolBarOptionsWidth = 100;
	}
	
	public abstract void init();
	public abstract void render();
	public abstract void resize(int width, int height);
	public abstract void dispose();

	public void initCamera() {
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
	}

	public OrthographicCamera getCamera() {
		return camera;
	}
	
	public ShapeRenderer getRenderer() {
		return renderer;
	}
	
	public void setGridLineColor(Color gridLineColor) {
		this.gridLineColor = gridLineColor;
	}
	
	public Color getGridLineColor() {
		return gridLineColor;
	}
	
	public int getWorldX() {
		return worldX;
	}
	
	public void setWorldX(int worldX) {
		this.worldX = worldX;
	}
	
	public int getWorldY() {
		return worldY;
	}
	
	public void setWorldY(int worldY) {
		this.worldY = worldY;
	}

	public int getToolBarWidth() {
		return toolBarWidth;
	}

	public void setToolBarWidth(int toolBarWidth) {
		this.toolBarWidth = toolBarWidth;
	}
	
	public int getToolBarOptionsWidth() {
		return toolBarOptionsWidth;
	}

	public void setToolBarOptionsWidth(int toolBarOptionsWidth) {
		this.toolBarOptionsWidth = toolBarOptionsWidth;
	}
	
}
