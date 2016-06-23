package edu.miamioh.schematicRenderer;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @author bdshaffer73
 */
public class SchematicRendererScreen implements Screen {

    private SchematicRenderer schematic;
    private ShapeRenderer renderer;

    /**
     * Constructor for SchematicRendererMain.
     */
    public SchematicRendererScreen(){schematic = new SchematicRenderer();}

    public void setRoot_tree(ParseTree root_tree){
        schematic.setRoot_tree(root_tree);
        if(schematic.getRoot_tree() == null) {
            System.out.println("The root_tree has been set to null.");
        } else {
            System.out.println("The root_tree has successfully been set.");
        }
    }

    private void setRenderer(){schematic.setRenderer(this.renderer);}

    public ParseTree getRoot_tree(){
        return schematic.getRoot_tree();
    }

    public void compile(){
        if(schematic.getRoot_tree() != null){
            schematic.getData();
        } else {
            System.out.println("The root_tree has not been set or has not been created.");
        }
    }

    @Override
    public void show() {
        renderer = new ShapeRenderer();
//        if (root_tree == null) throw new AssertionError();
//    	schematic = new SchematicRenderer(this, root_tree, renderer);
//        schematic.getData();
    }

    /**
     * This method draws a new window with the schematic design determined by
     * the Verilog logic.
     */
    @Override
    public void render(float arg0) {
        Gdx.gl.glClearColor(255, 255, 255, 255);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(255, 0, 0, 0);
        renderer.line(0, 0, 100, 100);
        renderer.end();

//        schematic.render();
    }

    /**
     * Called when the {@link Application} is resized. This can happen at any point during a non-paused state but will never happen
     * before a call to create().
     *
     * @param width  the new width in pixels
     * @param height the new height in pixels
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * Called when the {@link Application} is paused, usually when it's not active or visible on screen. An Application is also
     * paused before it is destroyed.
     */
    @Override
    public void pause() {
    }

    /**
     * Called when the {@link Application} is resumed from a paused state, usually when it regains focus.
     */
    @Override
    public void resume() {
    }

    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose() {
//        schematic.dispose();
    }

	@Override
	public void hide() {}

}
